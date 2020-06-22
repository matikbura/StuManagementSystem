package dao.Impl;

import dao.ClassIDao;
import domain.Classtbl;
import exception.DaoException;
import utils.JDBCUtil;
import utils.Page;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassDaoImpl implements ClassIDao {

	@Override
	public void addClass(Classtbl classtbl) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn= JDBCUtil.getConnction();
			String sql="insert into classtbl values(?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,classtbl.getClassNo());
			pstmt.setString(2,classtbl.getClassName());
			pstmt.setString(3,classtbl.getCollege());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
		}
	}

	@Override
	public void delClass(String classNo) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=JDBCUtil.getConnction();
			String sql="delete from classtbl where classNo=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, classNo);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
		}
	}

	@Override
	public void updateClass(Classtbl classtbl) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=JDBCUtil.getConnction();
			String sql="update classtbl set college=?,className=? where classNo=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,classtbl.getCollege());
			pstmt.setString(2,classtbl.getClassName());
			pstmt.setString(3,classtbl.getClassNo());
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
		}
	}

	@Override
	public Classtbl findClassByClasstblNo(String classNo) {
		Classtbl classtbl = null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=JDBCUtil.getConnction();
			String sql="select * from classtbl where classNo=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, classNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				classtbl=new Classtbl();
				classtbl.setClassNo(rs.getString(1));
				classtbl.setClassName(rs.getString(2));
				classtbl.setCollege(rs.getString(3));
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
		}
		return classtbl;
	}

	@Override
	public Map findAllClass(int curPage) {
		Classtbl classtbl=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		Statement pstmt=null;
		ResultSet rs=null;
		ResultSet r=null;
		Map map=null;
		Page pa=null;
		try{
			conn=JDBCUtil.getConnction();
			String sql="select * from classtbl order by classNo";
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
					classtbl=new Classtbl();
					classtbl.setClassNo(rs.getString(1));
					classtbl.setClassName(rs.getString(2));
					classtbl.setCollege(rs.getString(3));
					list.add(classtbl);
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

	@Override
	public List findAllClass() {
		Classtbl classtbl=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		Statement pstmt=null;
		ResultSet rs=null;
		try{
			conn=JDBCUtil.getConnction();
			String sql="select * from classtbl order by classNo";
			pstmt=conn.createStatement();
			rs=pstmt.executeQuery(sql);
			while(rs.next()){
				classtbl=new Classtbl();
				classtbl.setClassNo(rs.getString(1));
				classtbl.setClassName(rs.getString(2));
				classtbl.setCollege(rs.getString(3));
				list.add(classtbl);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
		}
		return list;
	}

	@Override
	public List findAllClassByMostCon(String classNo, String className, String college) {
		Classtbl classtbl=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;


		String sql="select * from admins where 1=1 ";



		if(classNo!=null&&!classNo.equals("")){
			sql+=" and classNo like '%"+classNo+"%'";
		}
		if(className!=null&&!className.equals("")){
			sql+=" and className like '%"+className+"%'";
		}
		if(college!=null&&!college.equals("")){
			sql+=" and college like '%"+college+"%'";
		}

		sql+=" order by classNo";
		try{
			conn=JDBCUtil.getConnction();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				classtbl=new Classtbl();
				classtbl.setClassNo(rs.getString(1));
				classtbl.setClassName(rs.getString(2));
				classtbl.setCollege(rs.getString(3));
				list.add(classtbl);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
		}
		return list;
	}

	@Override
	public Map findAllClassByMostCon(String classNo, String className, String college, int curPage) {
		Classtbl classtbl=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		Statement pstmt=null;
		ResultSet rs=null;
		ResultSet r=null;
		Map map=null;
		Page pa=null;

		String sql="select * from classtbl where 1=1 ";


		if(classNo!=null&&!classNo.equals("")){
			sql+=" and classNo like '%"+classNo+"%'";
		}
		if(className!=null&&!className.equals("")){
			sql+=" and className like '%"+className+"%'";
		}
		if(college!=null&&!college.equals("")){
			sql+=" and college like '%"+college+"%'";
		}

		sql+=" order by classNo";
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
					classtbl=new Classtbl();
					classtbl.setClassNo(rs.getString(1));
					classtbl.setClassName(rs.getString(2));
					classtbl.setCollege(rs.getString(3));
					list.add(classtbl);
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
}
