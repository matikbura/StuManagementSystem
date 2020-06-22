package dao.Impl;

import dao.ClassIDao;
import dao.CourseArrangeIDao;
import dao.CourseIDao;
import dao.TeacherIDao;
import domain.Classtbl;
import domain.Course;
import domain.CourseArrange;
import domain.Teacher;
import exception.DaoException;
import utils.JDBCUtil;
import utils.Page;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseArrangeDaoImpl implements CourseArrangeIDao {
    @Override
    public void addCourseArrange(CourseArrange courseArrange) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn= JDBCUtil.getConnction();
            String sql="insert into coursearrange values(?,?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,courseArrange.getArrangeNo());
            pstmt.setString(2,courseArrange.getCourse().getCourseNo());
            pstmt.setString(3,courseArrange.getClasstbl().getClassNo());
            pstmt.setString(4,courseArrange.getTeacher().getTeacherNo());
            pstmt.setString(5,courseArrange.getStudyRoom());
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试!");
        }finally{
            JDBCUtil.close(rs,conn,pstmt);
        }
    }

    @Override
    public void delCourseArrange(String arrangeNo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnction();
            String sql="DELETE FROM coursearrange WHERE arrangeNo=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,arrangeNo);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试");
        }
    }

    @Override
    public void updateCourseArrange(CourseArrange courseArrange) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtil.getConnction();
            String sql = "UPDATE coursearrange SET courseNo=?,classNo=?,teacherNo=?,studyRoom=? WHERE arrangeNo=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,courseArrange.getCourse().getCourseNo());
            pstmt.setString(2,courseArrange.getClasstbl().getClassNo());
            pstmt.setString(3,courseArrange.getTeacher().getTeacherNo());
            pstmt.setString(4,courseArrange.getStudyRoom());
            pstmt.setString(5,courseArrange.getArrangeNo());
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试");
        }
        finally {

        }
    }

    @Override
    public CourseArrange findCourseArrangeByArrangeNo(String arrangeNo) {
        CourseArrange courseArrange=null;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        Classtbl classtbl = null;
        Teacher teacher = null;
        Course course = null;
        TeacherIDao teacherDao = new TeacherDaoImpl();
        CourseIDao courseDao = new CourseDaoImpl();
        ClassIDao classDao = new ClassDaoImpl();
        try{
            conn=JDBCUtil.getConnction();
            String sql="select * from coursearrange where arrangeNo=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, arrangeNo);
            rs=pstmt.executeQuery();
            while(rs.next()){
                classtbl = new Classtbl();
                teacher = new Teacher();
                course = new Course();
                courseArrange = new CourseArrange();
                courseArrange.setArrangeNo(rs.getString(1));
                course.setCourseNo(rs.getString(2));
                courseArrange.setCourse(course);
                classtbl.setClassNo(rs.getString(3));
                courseArrange.setClasstbl(classtbl);
                teacher.setTeacherNo(rs.getString(4));
                courseArrange.setTeacher(teacher);
                courseArrange.setStudyRoom(rs.getString(5));
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试!");
        }finally{
            JDBCUtil.close(rs,conn,pstmt);
        }
        if(courseArrange!=null){
            classtbl=courseArrange.getClasstbl();
            course=courseArrange.getCourse();
            teacher=courseArrange.getTeacher();

            classtbl=classDao.findClassByClasstblNo(classtbl.getClassNo());
            course=courseDao.findCourseByCourseNo(course.getCourseNo());
            teacher=teacherDao.findTeacherByTeacherNo(teacher.getTeacherNo());

            courseArrange.setClasstbl(classtbl);
            courseArrange.setCourse(course);
            courseArrange.setTeacher(teacher);
        }

        return courseArrange;
    }

    @Override
    public Map findAllCourseArrange(int curPage) {
        CourseArrange courseArrange=null;
        ArrayList list=new ArrayList();
        Connection conn=null;
        Statement pstmt=null;
        ResultSet rs=null;
        ResultSet r=null;


        TeacherIDao teacherDao = new TeacherDaoImpl();
        CourseIDao courseDao = new CourseDaoImpl();
        ClassIDao classDao = new ClassDaoImpl();
        Map map=null;
        Page pa=null;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select * from coursearrange order by arrangeNo";
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
                    Classtbl classtbl = new Classtbl();
                    Teacher teacher = new Teacher();
                    Course course = new Course();
                    courseArrange=new CourseArrange();
                    courseArrange.setArrangeNo(rs.getString(1));
                    course.setCourseNo(rs.getString(2));
                    courseArrange.setCourse(course);
                    classtbl.setClassNo(rs.getString(3));
                    courseArrange.setClasstbl(classtbl);
                    teacher.setTeacherNo(rs.getString(4));
                    courseArrange.setTeacher(teacher);
                    courseArrange.setStudyRoom(rs.getString(5));
                    list.add(courseArrange);
                }else{
                    break;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试!");
        }finally{
            JDBCUtil.close(rs,conn,pstmt);
            JDBCUtil.close(r,null,null);
        }
        for(int i=0;i<list.size();i++){
            Classtbl classtbl = new Classtbl();
            Teacher teacher = new Teacher();
            Course course = new Course();
            courseArrange = (CourseArrange)list.get(i);
            classtbl=courseArrange.getClasstbl();
            course=courseArrange.getCourse();
            teacher=courseArrange.getTeacher();
            classtbl=classDao.findClassByClasstblNo(classtbl.getClassNo());
            course=courseDao.findCourseByCourseNo(course.getCourseNo());
            teacher=teacherDao.findTeacherByTeacherNo(teacher.getTeacherNo());

            courseArrange.setClasstbl(classtbl);
            courseArrange.setCourse(course);
            courseArrange.setTeacher(teacher);
            list.set(i,courseArrange);
        }
        map=new HashMap();
        map.put("list",list);
        map.put("pa",pa);
        return map;
    }

    @Override
    public List findAllCourseArrangeByMostCon(String arrangeNo, String courseNo, String classNo, String teacherNo) {
        CourseArrange courseArrange=null;
        ArrayList list=new ArrayList();
        Connection conn=null;
        Statement pstmt=null;
        ResultSet rs=null;

        TeacherIDao teacherDao = new TeacherDaoImpl();
        CourseIDao courseDao = new CourseDaoImpl();
        ClassIDao classDao = new ClassDaoImpl();
        try{
            conn=JDBCUtil.getConnction();
            String sql="select * from coursearrange where 1=1 ";


            if(courseNo!=null&&!courseNo.equals("")){
                sql+=" and courseNo="+courseNo;
            }
            if(classNo!=null&&!classNo.equals("")){
                sql+=" and classNo="+classNo;
            }
            if(teacherNo!=null&&!teacherNo.equals("")){
                sql+=" and teacherNo="+teacherNo;
            }
            sql+=" order by arrangeNo";
            pstmt=conn.createStatement();
            rs=pstmt.executeQuery(sql);
            while (rs.next()){
                Classtbl classtbl = new Classtbl();
                Teacher teacher = new Teacher();
                Course course = new Course();
                courseArrange=new CourseArrange();
                courseArrange.setArrangeNo(rs.getString(1));
                course.setCourseNo(rs.getString(2));
                courseArrange.setCourse(course);
                classtbl.setClassNo(rs.getString(3));
                courseArrange.setClasstbl(classtbl);
                teacher.setTeacherNo(rs.getString(4));
                courseArrange.setTeacher(teacher);
                courseArrange.setStudyRoom(rs.getString(5));
                list.add(courseArrange);
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new DaoException("数据库操作异常");
        } finally {
            JDBCUtil.close(rs,conn,pstmt);
        }
        for(int i=0;i<list.size();i++){
            Classtbl classtbl = new Classtbl();
            Teacher teacher = new Teacher();
            Course course = new Course();
            courseArrange = (CourseArrange)list.get(i);
            classtbl=courseArrange.getClasstbl();
            course=courseArrange.getCourse();
            teacher=courseArrange.getTeacher();

            classtbl=classDao.findClassByClasstblNo(classtbl.getClassNo());
            course=courseDao.findCourseByCourseNo(course.getCourseNo());
            teacher=teacherDao.findTeacherByTeacherNo(teacher.getTeacherNo());

            courseArrange.setClasstbl(classtbl);
            courseArrange.setCourse(course);
            courseArrange.setTeacher(teacher);
            list.set(i,courseArrange);
        }
        return list;
    }

    @Override
    public List findAllCourseArrangeByNo(String courseNo, String classNo, String teacherNo) {
        CourseArrange courseArrange=null;
        ArrayList list=new ArrayList();
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;


        TeacherIDao teacherDao = new TeacherDaoImpl();
        CourseIDao courseDao = new CourseDaoImpl();
        ClassIDao classDao = new ClassDaoImpl();


        String sql="select * from coursearrange where 1=1 ";


        if(courseNo!=null&&!courseNo.equals("")){
            sql+=" and courseNo="+courseNo;
        }
        if(classNo!=null&&!classNo.equals("")){
            sql+=" and classNo="+classNo;
        }
        if(teacherNo!=null&&!teacherNo.equals("")){
            sql+=" and teacherNo="+teacherNo;
        }
        sql+=" order by arrangeNo";
        try{
            conn=JDBCUtil.getConnction();
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while (rs.next()){
                Classtbl classtbl = new Classtbl();
                Teacher teacher = new Teacher();
                Course course = new Course();
                courseArrange=new CourseArrange();
                courseArrange.setArrangeNo(rs.getString(1));
                course.setCourseNo(rs.getString(2));
                courseArrange.setCourse(course);
                classtbl.setClassNo(rs.getString(3));
                courseArrange.setClasstbl(classtbl);
                teacher.setTeacherNo(rs.getString(4));
                courseArrange.setTeacher(teacher);
                courseArrange.setStudyRoom(rs.getString(5));
                list.add(courseArrange);
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new DaoException("数据库操作异常");
        } finally {
            JDBCUtil.close(rs,conn,pstmt);
        }
        for(int i=0;i<list.size();i++){
            courseArrange = (CourseArrange)list.get(i);
            Classtbl classtbl = new Classtbl();
            Teacher teacher = new Teacher();
            Course course = new Course();
            classtbl=courseArrange.getClasstbl();
            course=courseArrange.getCourse();
            teacher=courseArrange.getTeacher();

            classtbl=classDao.findClassByClasstblNo(classtbl.getClassNo());
            course=courseDao.findCourseByCourseNo(course.getCourseNo());
            teacher=teacherDao.findTeacherByTeacherNo(teacher.getTeacherNo());

            courseArrange.setClasstbl(classtbl);
            courseArrange.setCourse(course);
            courseArrange.setTeacher(teacher);
            list.set(i,courseArrange);
        }
        return list;
    }

    @Override
    public Map findAllCourseArrangeByMostCon(String arrangeNo, String courseNo, String classNo, String teacherNo, int curPage) {
        CourseArrange courseArrange=null;
        ArrayList list=new ArrayList();
        Connection conn=null;
        Statement pstmt=null;
        ResultSet rs=null;
        ResultSet r=null;


        TeacherIDao teacherDao = new TeacherDaoImpl();
        CourseIDao courseDao = new CourseDaoImpl();
        ClassIDao classDao = new ClassDaoImpl();

        Map map=null;
        Page pa=null;

        String sql="select * from coursearrange where 1=1 ";
        if(arrangeNo!=null&&!arrangeNo.equals("")){
            sql+=" and arrangeNo="+arrangeNo;
        }
        if(courseNo!=null&&!courseNo.equals("")){
            sql+=" and courseNo="+courseNo;
        }
        if(classNo!=null&&!classNo.equals("")){
            sql+=" and classNo="+classNo;
        }
        if(teacherNo!=null&&!teacherNo.equals("")){
            sql+=" and teacherNo="+teacherNo;
        }
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
                    Classtbl classtbl = new Classtbl();
                    Teacher teacher = new Teacher();
                    Course course = new Course();
                    courseArrange=new CourseArrange();
                    courseArrange.setArrangeNo(rs.getString(1));
                    course.setCourseNo(rs.getString(2));
                    courseArrange.setCourse(course);
                    classtbl.setClassNo(rs.getString(3));
                    courseArrange.setClasstbl(classtbl);
                    teacher.setTeacherNo(rs.getString(4));
                    courseArrange.setTeacher(teacher);
                    courseArrange.setStudyRoom(rs.getString(5));
                    list.add(courseArrange);
                }else{
                    break;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试!");
        }finally{
            JDBCUtil.close(rs,conn,pstmt);
            JDBCUtil.close(r,null,null);
        }
        for(int i=0;i<list.size();i++){
            courseArrange = (CourseArrange)list.get(i);
            Classtbl classtbl = new Classtbl();
            Teacher teacher = new Teacher();
            Course course = new Course();
            classtbl=courseArrange.getClasstbl();
            course=courseArrange.getCourse();
            teacher=courseArrange.getTeacher();

            classtbl=classDao.findClassByClasstblNo(classtbl.getClassNo());
            course=courseDao.findCourseByCourseNo(course.getCourseNo());
            teacher=teacherDao.findTeacherByTeacherNo(teacher.getTeacherNo());

            courseArrange.setClasstbl(classtbl);
            courseArrange.setCourse(course);
            courseArrange.setTeacher(teacher);
            list.set(i,courseArrange);
        }
        map=new HashMap();
        map.put("list",list);
        map.put("pa",pa);
        return map;
    }

    @Override
    public List findAllCourseArrangeByTeacherNo(String teacherNo) {
        CourseArrange courseArrange=null;
        ArrayList list=new ArrayList();
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;


        TeacherIDao teacherDao = new TeacherDaoImpl();
        CourseIDao courseDao = new CourseDaoImpl();
        ClassIDao classDao = new ClassDaoImpl();

        String sql="select * from coursearrange where teacherNo=? ";
        try{
            conn=JDBCUtil.getConnction();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,teacherNo);
            rs=pstmt.executeQuery();
            while (rs.next()){
                Classtbl classtbl = new Classtbl();
                Teacher teacher = new Teacher();
                Course course = new Course();
                courseArrange=new CourseArrange();
                courseArrange.setArrangeNo(rs.getString(1));
                course.setCourseNo(rs.getString(2));
                courseArrange.setCourse(course);
                classtbl.setClassNo(rs.getString(3));
                courseArrange.setClasstbl(classtbl);
                teacher.setTeacherNo(rs.getString(4));
                courseArrange.setTeacher(teacher);
                courseArrange.setStudyRoom(rs.getString(5));
                list.add(courseArrange);
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new DaoException("数据库操作异常");
        } finally {
            JDBCUtil.close(rs,conn,pstmt);
        }
        for(int i=0;i<list.size();i++){
            courseArrange = (CourseArrange)list.get(i);
            Classtbl classtbl = new Classtbl();
            Teacher teacher = new Teacher();
            Course course = new Course();
            classtbl=courseArrange.getClasstbl();
            course=courseArrange.getCourse();
            teacher=courseArrange.getTeacher();

            classtbl=classDao.findClassByClasstblNo(classtbl.getClassNo());
            course=courseDao.findCourseByCourseNo(course.getCourseNo());
            teacher=teacherDao.findTeacherByTeacherNo(teacher.getTeacherNo());

            courseArrange.setClasstbl(classtbl);
            courseArrange.setCourse(course);
            courseArrange.setTeacher(teacher);
            list.set(i,courseArrange);
        }
        return list;
    }

    @Override
    public List findAllCourseArrange() {
        ArrayList list = new ArrayList();



        TeacherIDao teacherDao = new TeacherDaoImpl();
        CourseIDao courseDao = new CourseDaoImpl();
        ClassIDao classDao = new ClassDaoImpl();
        Connection conn= null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CourseArrange courseArrange = null;
        try {
            conn = JDBCUtil.getConnction();
            String sql = "SELECT * FROM coursearrange ORDER BY arrangeNo";
            pstmt = conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while (rs.next()){
                Classtbl classtbl = new Classtbl();
                Teacher teacher = new Teacher();
                Course course = new Course();
                courseArrange=new CourseArrange();
                courseArrange.setArrangeNo(rs.getString(1));
                course.setCourseNo(rs.getString(2));
                courseArrange.setCourse(course);
                classtbl.setClassNo(rs.getString(3));
                courseArrange.setClasstbl(classtbl);
                teacher.setTeacherNo(rs.getString(4));
                courseArrange.setTeacher(teacher);
                courseArrange.setStudyRoom(rs.getString(5));
                list.add(courseArrange);
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new DaoException("数据库操作异常");
        } finally {
            JDBCUtil.close(rs,conn,pstmt);
        }
        for(int i=0;i<list.size();i++){
             courseArrange = (CourseArrange)list.get(i);
            Classtbl classtbl = new Classtbl();
            Teacher teacher = new Teacher();
            Course course = new Course();
            classtbl=courseArrange.getClasstbl();
            course=courseArrange.getCourse();
            teacher=courseArrange.getTeacher();

            classtbl=classDao.findClassByClasstblNo(classtbl.getClassNo());
            course=courseDao.findCourseByCourseNo(course.getCourseNo());
            teacher=teacherDao.findTeacherByTeacherNo(teacher.getTeacherNo());

            courseArrange.setClasstbl(classtbl);
            courseArrange.setCourse(course);
            courseArrange.setTeacher(teacher);
            list.set(i,courseArrange);
        }
        return list;
    }

    @Override
    public List findAllCoursesByTeacherNo(String teacherNo) {
        Course course=null;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        ArrayList list;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select distinct course.courseNo,courseName,StudyTime,Grade,CourseType,Term from courseArrange join course on coursearrange.courseNo=course.courseNo where teacherNo=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, teacherNo);
            rs=pstmt.executeQuery();
            list = new ArrayList();
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
    public List findAllClassTblsByTeacherNo(String teacherNo) {
        Classtbl classtbl = null;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        ArrayList list;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select distinct classtbl.classNo,className,college from courseArrange join classtbl on courseArrange.classNo=classtbl.classNo where teacherNo=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, teacherNo);
            rs=pstmt.executeQuery();
            list = new ArrayList();
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
}
