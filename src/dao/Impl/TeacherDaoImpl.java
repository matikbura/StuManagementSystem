package dao.Impl;

import exception.DaoException;
import utils.JDBCUtil;
import utils.Page;
import dao.TeacherIDao;
import domain.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class TeacherDaoImpl implements TeacherIDao {

	public void addTeacher(Teacher teacher){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=JDBCUtil.getConnction();
			String sql="insert into teacher values(?,?,?,?,?,?,?,?,?)"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, teacher.getTeacherNo());
			pstmt.setString(2, teacher.getTeacherName());
			pstmt.setString(3, teacher.getPassword());
			pstmt.setInt(4, teacher.getProfessional());
			pstmt.setString(5, teacher.getEducation());
			pstmt.setString(6, teacher.getAddress());
			pstmt.setString(7, teacher.getPhone());
			pstmt.setString(8, teacher.getEmail());
			pstmt.setString(9, teacher.getSubject());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
		}
	}

	public void delTeacher(String teacherNo){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=JDBCUtil.getConnction();
			String sql="delete from teacher where teacherNo=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, teacherNo);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
		}
	}

	public void updateTeacher(Teacher teacher){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=JDBCUtil.getConnction();
			String sql="update teacher set teacherNo=?,teacherName=?,password=?,professional=?,"+
				"education=?,address=?,phone=?,email=?,subject=? where teacherNo=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, teacher.getTeacherNo());
			pstmt.setString(2, teacher.getTeacherName());
			pstmt.setString(3, teacher.getPassword());
			pstmt.setInt(4, teacher.getProfessional());
			pstmt.setString(5, teacher.getEducation());
			pstmt.setString(6, teacher.getAddress());
			pstmt.setString(7, teacher.getPhone());
			pstmt.setString(8, teacher.getEmail());
			pstmt.setString(9, teacher.getSubject());
			pstmt.setString(10, teacher.getTeacherNo());
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
		}
	}

	public Teacher findTeacherByTeacherNo(String teacherNo){
		Teacher teacher=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=JDBCUtil.getConnction();
			String sql="select * from teacher where teacherNo=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, teacherNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				teacher=new Teacher();
				teacher.setTeacherNo(rs.getString(1));
				teacher.setTeacherName(rs.getString(2));
				teacher.setPassword(rs.getString(3));
				teacher.setProfessional(rs.getInt(4));
				teacher.setEducation(rs.getString(5));
				teacher.setAddress(rs.getString(6));
				teacher.setPhone(rs.getString(7));
				teacher.setEmail(rs.getString(8));
				teacher.setSubject(rs.getString(9));
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
		}
		return teacher;
	}

	public Map findAllTeacher(int curPage){
		Teacher teacher=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		Statement pstmt=null;
		ResultSet rs=null;
		ResultSet r=null;
		Map map=null;
		Page pa=null;
		try{
			conn=JDBCUtil.getConnction();
			String sql="select * from teacher order by teacherNo"; 
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
					teacher=new Teacher();
					teacher.setTeacherNo(r.getString(1));
					teacher.setTeacherName(r.getString(2));
					teacher.setPassword(rs.getString(3));
					teacher.setProfessional(r.getInt(4));
					teacher.setEducation(r.getString(5));
					teacher.setAddress(r.getString(6));
					teacher.setPhone(r.getString(7));
					teacher.setEmail(r.getString(8));
					teacher.setSubject(r.getString(9));
					list.add(teacher);
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

	public List findAllTeacher(){
		Teacher teacher=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		Statement pstmt=null;
		ResultSet rs=null;
		try{
			conn=JDBCUtil.getConnction();
			String sql="select * from teacher order by teacherNo"; 
			pstmt=conn.createStatement();
			rs=pstmt.executeQuery(sql);
			while(rs.next()){
				teacher=new Teacher();
				teacher.setTeacherNo(rs.getString(1));
				teacher.setTeacherName(rs.getString(2));
				teacher.setPassword(rs.getString(3));
				teacher.setProfessional(rs.getInt(4));
				teacher.setEducation(rs.getString(5));
				teacher.setAddress(rs.getString(6));
				teacher.setPhone(rs.getString(7));
				teacher.setEmail(rs.getString(8));
				teacher.setSubject(rs.getString(9));
				list.add(teacher);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
		}
		return list;
	}

	public List findAllTeacherByMostCon(String teacherNo,String teacherName,
			Integer professional,String phone,String subject,String email){
		Teacher teacher=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		

		String sql="select * from teacher where 1=1 ";

		/*
		if(teacherNo!=null&&!teacherNo.equals("")){
			sql+=" and teacherNo='"+teacherNo+"'";
		}
		if(teacherName!=null&&!teacherName.equals("")){
			sql+=" and teacherName='"+teacherName+"'";
		}
		if(phone!=null&&!phone.equals("")){
			sql+=" and phone='"+phone+"'";
		}
		if(subject!=null&&!subject.equals("")){
			sql+=" and subject='"+subject+"'";
		}
		if(professional!=null&&!professional.equals("")){
			sql+=" and professional="+professional;
		}
		sql+=" order by teacherNo";*/
		

		if(teacherNo!=null&&!teacherNo.equals("")){
			sql+=" and teacherNo like '%"+teacherNo+"%'";
		}
		if(teacherName!=null&&!teacherName.equals("")){
			sql+=" and teacherName like '%"+teacherName+"%'";
		}
		if(phone!=null&&!phone.equals("")){
			sql+=" and phone like '%"+phone+"%'";
		}
		if(subject!=null&&!subject.equals("")){
			sql+=" and subject like '%"+subject+"%'";
		}
		if(professional!=null&&!professional.equals("")){
			sql+=" and professional like'%"+professional+"%'";
		}
		if(email!=null&&!email.equals("")){
			sql+=" and email like'%"+email+"%'";
		}
		sql+=" order by teacherNo";
		try{
			conn=JDBCUtil.getConnction();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				teacher=new Teacher();
				teacher.setTeacherNo(rs.getString(1));
				teacher.setTeacherName(rs.getString(2));
				teacher.setPassword(rs.getString(3));
				teacher.setProfessional(rs.getInt(4));
				teacher.setEducation(rs.getString(5));
				teacher.setAddress(rs.getString(6));
				teacher.setPhone(rs.getString(7));
				teacher.setEmail(rs.getString(8));
				teacher.setSubject(rs.getString(9));
				list.add(teacher);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
		}
		return list;
	}

	public Map findAllTeacherByMostCon(String teacherNo,String teacherName,
			Integer professional,String phone,String subject,String email,int curPage){
		Teacher teacher=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		Statement pstmt=null;
		ResultSet rs=null;
		ResultSet r=null;
		Map map=null;
		Page pa=null;

		String sql="select * from teacher where 1=1 ";

		if(teacherNo!=null&&!teacherNo.equals("")){
			sql+=" and teacherNo like '%"+teacherNo+"%'";
		}
		if(teacherName!=null&&!teacherName.equals("")){
			sql+=" and teacherName like '%"+teacherName+"%'";
		}
		if(phone!=null&&!phone.equals("")){
			sql+=" and phone like '%"+phone+"%'";
		}
		if(subject!=null&&!subject.equals("")){
			sql+=" and subject like '%"+subject+"%'";
		}
		if(professional!=null&&!professional.equals("")){
			sql+=" and professional like'%"+professional+"%'";
		}
		if(email!=null&&!email.equals("")){
			sql+=" and email like'%"+email+"%'";
		}
		sql+=" order by teacherNo";
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
					teacher=new Teacher();
					teacher.setTeacherNo(rs.getString(1));
					teacher.setTeacherName(rs.getString(2));
					teacher.setPassword(rs.getString(3));
					teacher.setProfessional(rs.getInt(4));
					teacher.setEducation(rs.getString(5));
					teacher.setAddress(rs.getString(6));
					teacher.setPhone(rs.getString(7));
					teacher.setEmail(rs.getString(8));
					teacher.setSubject(rs.getString(9));
					list.add(teacher);
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

	public Teacher login(String teacherNo,String password){
		Teacher teacher=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=JDBCUtil.getConnction();
			String sql="select * from teacher where teacherNo=? and password=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, teacherNo);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			while(rs.next()){
				teacher=new Teacher();
				teacher.setTeacherNo(rs.getString(1));
				teacher.setTeacherName(rs.getString(2));
				teacher.setPassword(rs.getString(3));
				teacher.setProfessional(rs.getInt(4));
				teacher.setEducation(rs.getString(5));
				teacher.setAddress(rs.getString(6));
				teacher.setPhone(rs.getString(7));
				teacher.setEmail(rs.getString(8));
				teacher.setSubject(rs.getString(9));
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("数据库操作异常，请稍后重试!");
		}finally{
			JDBCUtil.close(rs,conn,pstmt);
		}
		return teacher;
	}
}
