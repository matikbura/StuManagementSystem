package dao.Impl;

import dao.StudentIDao;
import domain.Student;
import exception.DaoException;
import utils.JDBCUtil;
import utils.Page;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StudentDaoImpl implements StudentIDao {

    public void addStudent(Student student){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=JDBCUtil.getConnction();
            String sql="insert into student values(?,?,?,?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,student.getStudentNo());
            pstmt.setString(2,student.getName());
            pstmt.setString(3,student.getPassword());
            pstmt.setString(4,student.getAddress());
            pstmt.setString(5,student.getPhone());
            pstmt.setString(6,student.getEmail());
            pstmt.setString(7,student.getClassNo());
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试!");
        }finally{
            JDBCUtil.close(rs,conn,pstmt);
        }
    }

    public void delStudent(String LoginName){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=JDBCUtil.getConnction();
            String sql="delete from student where studentNo=?";
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

    public void updateStudent(Student student){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=JDBCUtil.getConnction();
            String sql="update student set name=?,password=?,address=?,phone=?,email=?,classNo=? where studentNo=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,student.getName());
            pstmt.setString(2,student.getPassword());
            pstmt.setString(3,student.getAddress());
            pstmt.setString(4,student.getPhone());
            pstmt.setString(5,student.getEmail());
            pstmt.setString(6,student.getClassNo());
            pstmt.setString(7,student.getStudentNo());
            pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试!");
        }finally{
            JDBCUtil.close(rs,conn,pstmt);
        }
    }

    public Student findStudentByStudentNo(String studentNo){
        Student student=null;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select * from student where studentNo=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, studentNo);
            rs=pstmt.executeQuery();
            while(rs.next()){
                student=new Student();
                student.setStudentNo(rs.getString(1));
                student.setName(rs.getString(2));
                student.setPassword(rs.getString(3));
                student.setAddress(rs.getString(4));
                student.setPhone(rs.getString(5));
                student.setEmail(rs.getString(6));
                student.setClassNo(rs.getString(7));
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试!");
        }finally{
            JDBCUtil.close(rs,conn,pstmt);
        }
        return student;
    }

    public Map findAllStudent(int curPage){
        Student student=null;
        ArrayList list=new ArrayList();
        Connection conn=null;
        Statement pstmt=null;
        ResultSet rs=null;
        ResultSet r=null;
        Map map=null;
        Page pa=null;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select * from student order by studentNo";
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
                    student=new Student();
                    student.setStudentNo(rs.getString(1));
                    student.setName(rs.getString(2));
                    student.setPassword(rs.getString(3));
                    student.setAddress(rs.getString(4));
                    student.setPhone(rs.getString(5));
                    student.setEmail(rs.getString(6));
                    student.setClassNo(rs.getString(7));
                    list.add(student);
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

    public List findAllStudent(){
        Student student=null;
        ArrayList list=new ArrayList();
        Connection conn=null;
        Statement pstmt=null;
        ResultSet rs=null;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select * from student order by studentNo";
            pstmt=conn.createStatement();
            rs=pstmt.executeQuery(sql);
            while(rs.next()){
                student=new Student();
                student.setStudentNo(rs.getString(1));
                student.setName(rs.getString(2));
                student.setPassword(rs.getString(3));
                student.setAddress(rs.getString(4));
                student.setPhone(rs.getString(5));
                student.setEmail(rs.getString(6));
                student.setClassNo(rs.getString(7));
                list.add(student);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试!");
        }finally{
            JDBCUtil.close(rs,conn,pstmt);
        }
        return list;
    }

    public List findAllStudentByMostCon(String studentNo,String name,String address,
                                        String phone,String email,String classNo){
        Student student=null;
        ArrayList list=new ArrayList();
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;


        String sql="select * from student where 1=1 ";



        if(studentNo!=null&&!studentNo.equals("")){
            sql+=" and studentNo like '%"+studentNo+"%'";
        }
        if(name!=null&&!name.equals("")){
            sql+=" and name like '%"+name+"%'";
        }
        if(address!=null&&!address.equals("")){
            sql+=" and address like '%"+address+"%'";
        }
        if(phone!=null&&!phone.equals("")){
            sql+=" and phone like '%"+phone+"%'";
        }
        if(email!=null&&!email.equals("")){
            sql+=" and email like '%"+email+"%'";
        }
        if(classNo!=null&&!classNo.equals("")){
            sql+=" and classNo like '%"+classNo+"%'";
        }
        sql+=" order by studentNo";
        try{
            conn=JDBCUtil.getConnction();
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                student=new Student();
                student.setStudentNo(rs.getString(1));
                student.setName(rs.getString(2));
                student.setPassword(rs.getString(3));
                student.setAddress(rs.getString(4));
                student.setPhone(rs.getString(5));
                student.setEmail(rs.getString(6));
                student.setClassNo(rs.getString(7));
                list.add(student);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试!");
        }finally{
            JDBCUtil.close(rs,conn,pstmt);
        }
        return list;
    }

    public Map findAllStudentByMostCon(String studentNo,String name,String address,String phone,
                                       String email,String classNo,int curPage){
        Student student=null;
        ArrayList list=new ArrayList();
        Connection conn=null;
        Statement pstmt=null;
        ResultSet rs=null;
        ResultSet r=null;
        Map map=null;
        Page pa=null;

        String sql="select * from student where 1=1 ";

        if(studentNo!=null&&!studentNo.equals("")){
            sql+=" and studentNo like '%"+studentNo+"%'";
        }
        if(name!=null&&!name.equals("")){
            sql+=" and name like '%"+name+"%'";
        }
        if(address!=null&&!address.equals("")){
            sql+=" and address like '%"+address+"%'";
        }
        if(phone!=null&&!phone.equals("")){
            sql+=" and phone like '%"+phone+"%'";
        }
        if(email!=null&&!email.equals("")){
            sql+=" and email like '%"+email+"%'";
        }
        if(classNo!=null&&!classNo.equals("")){
            sql+=" and classNo like '%"+classNo+"%'";
        }
        sql+=" order by studentNo";

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
                    student=new Student();
                    student.setStudentNo(rs.getString(1));
                    student.setName(rs.getString(2));
                    student.setPassword(rs.getString(3));
                    student.setAddress(rs.getString(4));
                    student.setPhone(rs.getString(5));
                    student.setEmail(rs.getString(6));
                    student.setClassNo(rs.getString(7));
                    list.add(student);
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

    public Student login(String studentNo,String password){
        Student student=null;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select * from student where studentNo=? and password=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, studentNo);
            pstmt.setString(2, password);
            rs=pstmt.executeQuery();
            while(rs.next()){
                student = new Student();
                student.setStudentNo(rs.getString(1));
                student.setName(rs.getString(2));
                student.setPassword(rs.getString(3));
                student.setAddress(rs.getString(4));
                student.setPhone(rs.getString(5));
                student.setEmail(rs.getString(6));
                student.setClassNo(rs.getString(7));
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试!");
        }finally{
            JDBCUtil.close(rs,conn,pstmt);
        }
        return student;
    }
}
