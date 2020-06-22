package dao.Impl;

import dao.CourseIDao;
import domain.Course;
import exception.DaoException;
import utils.JDBCUtil;
import utils.Page;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDaoImpl implements CourseIDao {
    @Override
    public void addCourse(Course course) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn= JDBCUtil.getConnction();
            String sql="insert into course values(?,?,?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,course.getCourseNo());
            pstmt.setString(2,course.getCourseName());
            pstmt.setString(3,course.getStudyTime());
            pstmt.setString(4,course.getGrade());
            pstmt.setInt(5,course.getCourseType());
            pstmt.setInt(6,course.getTerm());
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试!");
        }finally{
            JDBCUtil.close(rs,conn,pstmt);
        }
    }

    @Override
    public void delCourse(String courseNo) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=JDBCUtil.getConnction();
            String sql="delete from course where courseNo=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, courseNo);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试!");
        }finally{
            JDBCUtil.close(rs,conn,pstmt);
        }
    }

    @Override
    public void updateCourse(Course course) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=JDBCUtil.getConnction();
            String sql="update course set courseName=?,studyTime=?,grade=?,courseType=?,term=? where courseNo=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,course.getCourseName());
            pstmt.setString(2,course.getStudyTime());
            pstmt.setString(3,course.getGrade());
            pstmt.setInt(4,course.getCourseType());
            pstmt.setInt(5,course.getTerm());
            pstmt.setString(6,course.getCourseNo());
            pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试!");
        }finally{
            JDBCUtil.close(rs,conn,pstmt);
        }
    }

    @Override
    public Course findCourseByCourseNo(String courseNo) {
        Course course=null;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select * from course where courseNo=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, courseNo);
            rs=pstmt.executeQuery();
            while(rs.next()){
                course=new Course();
                course.setCourseNo(rs.getString(1));
                course.setCourseName(rs.getString(2));
                course.setStudyTime(rs.getString(3));
                course.setGrade(rs.getString(4));
                course.setCourseType(rs.getInt(5));
                course.setTerm(rs.getInt(6));
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试!");
        }finally{
            JDBCUtil.close(rs,conn,pstmt);
        }
        return course;
    }

    @Override
    public Map findAllCourse(int curPage) {
        Course course=null;
        ArrayList list=new ArrayList();
        Connection conn=null;
        Statement pstmt=null;
        ResultSet rs=null;
        ResultSet r=null;
        Map map=null;
        Page pa=null;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select * from course order by courseNo";
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
                    course=new Course();
                    course.setCourseNo(rs.getString(1));
                    course.setCourseName(rs.getString(2));
                    course.setStudyTime(rs.getString(3));
                    course.setGrade(rs.getString(4));
                    course.setCourseType(rs.getInt(5));
                    course.setTerm(rs.getInt(6));
                    list.add(course);
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
    public List findAllCourse() {
        Course course=null;
        ArrayList list=new ArrayList();
        Connection conn=null;
        Statement pstmt=null;
        ResultSet rs=null;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select * from course order by courseNo";
            pstmt=conn.createStatement();
            rs=pstmt.executeQuery(sql);
            while(rs.next()){
                course=new Course();
                course.setCourseNo(rs.getString(1));
                course.setCourseName(rs.getString(2));
                course.setStudyTime(rs.getString(3));
                course.setGrade(rs.getString(4));
                course.setCourseType(rs.getInt(5));
                course.setTerm(rs.getInt(6));
                list.add(course);
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
    public List findAllCourseByMostCon(String courseNo, String courseName, String studyTime, String grade,
                                       int courseType, int term) {
        Course course=null;
        ArrayList list=new ArrayList();
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;


        String sql="select * from course where 1=1 ";



        if(courseNo!=null&&!courseNo.equals("")){
            sql+=" and courseNo like '%"+courseNo+"%'";
        }
        if(courseName!=null&&!courseName.equals("")){
            sql+=" and courseName like '%"+courseName+"%'";
        }
        if(studyTime!=null&&!studyTime.equals("")){
            sql+=" and studyTime like '%"+studyTime+"%'";
        }
        if(grade!=null&&!grade.equals("")){
            sql+=" and grade like '%"+grade+"%'";
        }
        if(!(courseType==0)){
            sql+=" and courseType like '%"+courseType+"%'";
        }
        if(!(term==0)){
            sql+=" and term like '%"+term+"%'";
        }


        sql+=" order by courseNo";
        try{
            conn=JDBCUtil.getConnction();
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                course=new Course();
                course.setCourseNo(rs.getString(1));
                course.setCourseName(rs.getString(2));
                course.setStudyTime(rs.getString(3));
                course.setGrade(rs.getString(4));
                course.setCourseType(rs.getInt(5));
                course.setTerm(rs.getInt(6));
                list.add(course);
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
    public Map findAllCourseByMostCon(String courseNo, String courseName, String studyTime, String grade,
                                      int courseType, int term, int curPage) {
        Course course=null;
        ArrayList list=new ArrayList();
        Connection conn=null;
        Statement pstmt=null;
        ResultSet rs=null;
        ResultSet r=null;
        Map map=null;
        Page pa=null;

        String sql="select * from course where 1=1 ";


        if(courseNo!=null&&!courseNo.equals("")){
            sql+=" and courseNo like '%"+courseNo+"%'";
        }
        if(courseName!=null&&!courseName.equals("")){
            sql+=" and courseName like '%"+courseName+"%'";
        }
        if(studyTime!=null&&!studyTime.equals("")){
            sql+=" and studyTime like '%"+studyTime+"%'";
        }
        if(grade!=null&&!grade.equals("")){
            sql+=" and grade like '%"+grade+"%'";
        }
        if(!(courseType==0)){
            sql+=" and courseType like '%"+courseType+"%'";
        }
        if(!(term==0)){
            sql+=" and term like '%"+term+"%'";
        }
        sql+=" order by courseNo";
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
                    course=new Course();
                    course.setCourseNo(rs.getString(1));
                    course.setCourseName(rs.getString(2));
                    course.setStudyTime(rs.getString(3));
                    course.setGrade(rs.getString(4));
                    course.setCourseType(rs.getInt(5));
                    course.setTerm(rs.getInt(6));
                    list.add(course);
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
