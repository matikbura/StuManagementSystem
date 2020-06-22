package dao.Impl;

import exception.DaoException;
import utils.JDBCUtil;
import utils.Page;
import dao.AdminIDao;
import domain.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class AdminDaoImpl implements AdminIDao {
	public void addAdmin(Admin admin){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=JDBCUtil.getConnction();
			String sql="insert into admins values(?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,admin.getLoginName());
			pstmt.setString(2,admin.getName());
			pstmt.setString(3,admin.getPassword());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
		}
	}

	public void delAdmin(String LoginName){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=JDBCUtil.getConnction();
			String sql="delete from admins where LoginName=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, LoginName);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
		}
	}

	public void updateAdmin(Admin admin){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=JDBCUtil.getConnction();
			String sql="update admins set name=?,password=? where LoginName=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,admin.getName());
			pstmt.setString(2,admin.getPassword());
			pstmt.setString(3,admin.getLoginName());
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
		}
	}

	public Admin findAdminByLoginName(String loginName){
		Admin admin=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=JDBCUtil.getConnction();
			String sql="select * from admins where loginName=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, loginName);
			rs=pstmt.executeQuery();
			while(rs.next()){
				admin=new Admin();
				admin.setLoginName(rs.getString(1));
				admin.setName(rs.getString(2));
				admin.setPassword(rs.getString(3));
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
		}
		return admin;
	}

	public Map findAllAdmin(int curPage){
		Admin admin=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		Statement pstmt=null;
		ResultSet rs=null;
		ResultSet r=null;
		Map map=null;
		Page pa=null;
		try{
			conn=JDBCUtil.getConnction();
			String sql="select * from admins order by LoginName";
			pstmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=pstmt.executeQuery(sql);
			pa=new Page();
			pa.setPageSize(10);
			pa.setPageCount(rs);
			pa.setCurPage(curPage);
			r=pa.setRs(rs);
			r.previous();
			for(int i=0;i<pa.getPageSize();i++){
				if(r.next()){
					admin=new Admin();
					admin.setLoginName(rs.getString(1));
					admin.setName(rs.getString(2));
					admin.setPassword(rs.getString(3));
					list.add(admin);
				}else{
					break;
				}
			}
			map=new HashMap();
			map.put("list",list);
			map.put("pa",pa);
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
			JDBCUtil.close(r,null,null);
		}
		return map;
	}

	public List findAllAdmin(){
		Admin admin=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		Statement pstmt=null;
		ResultSet rs=null;
		try{
			conn=JDBCUtil.getConnction();
			String sql="select * from admins order by loginName";
			pstmt=conn.createStatement();
			rs=pstmt.executeQuery(sql);
			while(rs.next()){
				admin=new Admin();
				admin.setLoginName(rs.getString(1));
				admin.setName(rs.getString(2));
				admin.setPassword(rs.getString(3));
				list.add(admin);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
		}
		return list;
	}

	public List findAllAdminByMostCon(String loginName,String name){
		Admin admin=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;


		String sql="select * from admins where 1=1 ";



		if(loginName!=null&&!loginName.equals("")){
			sql+=" and loginName like '%"+loginName+"%'";
		}
		if(name!=null&&!name.equals("")){
			sql+=" and name like '%"+name+"%'";
		}


		sql+=" order by loginName";
		try{
			conn=JDBCUtil.getConnction();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				admin=new Admin();
				admin.setLoginName(rs.getString(1));
				admin.setName(rs.getString(2));
				admin.setPassword(rs.getString(3));
				list.add(admin);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
		}
		return list;
	}

	public Map findAllAdminByMostCon(String loginName,String name,int curPage){
		Admin admin=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		Statement pstmt=null;
		ResultSet rs=null;
		ResultSet r=null;
		Map map=null;
		Page pa=null;

		String sql="select * from admins where 1=1 ";

		if(loginName!=null&&!loginName.equals("")){
			sql+=" and loginName like '%"+loginName+"%'";
		}
		if(name!=null&&!name.equals("")){
			sql+=" and name like '%"+name+"%'";
		}

		sql+=" order by loginName";
		try{
			conn=JDBCUtil.getConnction();
			pstmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=pstmt.executeQuery(sql);
			pa=new Page();
			pa.setPageSize(10);
			pa.setPageCount(rs);
			pa.setCurPage(curPage);
			r=pa.setRs(rs);
			r.previous();
			for(int i=0;i<pa.getPageSize();i++){
				if(rs.next()){
					admin=new Admin();
					admin.setLoginName(rs.getString(1));
					admin.setName(rs.getString(2));
					admin.setPassword(rs.getString(3));
					list.add(admin);
				}else{
					break;
				}
			}
			map=new HashMap();
			map.put("list",list);
			map.put("pa",pa);
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
			JDBCUtil.close(r,null,null);
		}
		return map;
	}

	public Admin login(String loginName,String password){
		Admin admin=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=JDBCUtil.getConnction();
			String sql="select * from admins where loginName=? and password=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, loginName);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			while(rs.next()){
				admin=new Admin();
				admin.setLoginName(rs.getString("loginName"));
				admin.setName(rs.getString(2));
				admin.setPassword(rs.getString(1));
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
		}
		return admin;
	}
}
