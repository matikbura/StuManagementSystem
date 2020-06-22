package dao.Impl;

import dao.*;
import domain.*;
import exception.DaoException;
import utils.JDBCUtil;
import utils.Page;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreDaoImpl implements ScoreIDao {
    @Override
    public void addScore(Score score) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn= JDBCUtil.getConnction();
            if(score.getScore()!=null){
                String sql="insert into score values(null,?,?,?)";
                pstmt=conn.prepareStatement(sql);
                pstmt.setString(1,score.getStudent().getStudentNo());
                pstmt.setString(2,score.getCourseArrange().getArrangeNo());
                pstmt.setInt(3,score.getScore());
            }else {
                String sql="insert into score values(null,?,?,null)";
                pstmt=conn.prepareStatement(sql);
                pstmt.setString(1,score.getStudent().getStudentNo());
                pstmt.setString(2,score.getCourseArrange().getArrangeNo());
            }
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试!");
        }finally{
            JDBCUtil.close(rs,conn,pstmt);
        }
    }

    @Override
    public void delScore(String id) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=JDBCUtil.getConnction();
            String sql="delete from score where id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试!");
        }finally{
            JDBCUtil.close(rs,conn,pstmt);
        }
    }

    @Override
    public void updateScore(Score score) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=JDBCUtil.getConnction();
            if(score.getScore()!=null){
                String sql="update score set studentNo=?,arrangeNo=?,score=? where id=?";
                pstmt=conn.prepareStatement(sql);
                pstmt.setString(1,score.getStudent().getStudentNo());
                pstmt.setString(2,score.getCourseArrange().getArrangeNo());
                pstmt.setInt(3,score.getScore());
                pstmt.setInt(4,score.getId());
            }
            else {
                String sql="update score set studentNo=?,arrangeNo=?,score=? where id=?";
                pstmt=conn.prepareStatement(sql);
                pstmt.setString(1,score.getStudent().getStudentNo());
                pstmt.setString(2,score.getCourseArrange().getArrangeNo());
                pstmt.setObject(3,null);
                pstmt.setInt(4,score.getId());
            }
            pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试!");
        }finally{
            JDBCUtil.close(rs,conn,pstmt);
        }
    }

    @Override
    public Score findScoreByValues(String studentNo,String arrangeNo) {
        Score score=null;
        StudentIDao studentDao = new StudentDaoImpl();
        CourseArrangeIDao courseArrangeDao = new CourseArrangeDaoImpl();
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        Student student;
        CourseArrange courseArrange;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select * from score where studentNo=? and arrangeNo=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, studentNo);
            pstmt.setString(2,arrangeNo);
            rs=pstmt.executeQuery();
            while(rs.next()){
                score = new Score();
                student = new Student();
                courseArrange = new CourseArrange();
                score.setId(rs.getInt(1));
                student.setStudentNo(rs.getString(2));
                score.setStudent(student);
                courseArrange.setArrangeNo(rs.getString(3));
                score.setCourseArrange(courseArrange);
                Object object = rs.getObject(4);
                if (object == null) {
                    score.setScore(null);
                }else{
                    score.setScore(rs.getInt(4));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试!");
        }finally{
            JDBCUtil.close(rs,conn,pstmt);
        }
        if(score!=null){
            student = studentDao.findStudentByStudentNo(score.getStudent().getStudentNo());
            courseArrange = courseArrangeDao.findCourseArrangeByArrangeNo(score.getCourseArrange().getArrangeNo());
            score.setStudent(student);
            score.setCourseArrange(courseArrange);
        }
        return score;
    }

    @Override
    public Map findAllScore(int curPage) {
        Score score=null;
        StudentIDao studentDao = new StudentDaoImpl();
        CourseArrangeIDao courseArrangeDao = new CourseArrangeDaoImpl();
        Student student;
        CourseArrange courseArrange;
        ArrayList list=new ArrayList();
        Connection conn=null;
        Statement pstmt=null;
        ResultSet rs=null;
        ResultSet r=null;
        Map map=null;
        Page pa=null;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select * from score";
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
                    score = new Score();
                    student = new Student();
                    courseArrange = new CourseArrange();
                    score.setId(rs.getInt(1));
                    student.setStudentNo(rs.getString(2));
                    score.setStudent(student);
                    courseArrange.setArrangeNo(rs.getString(3));
                    score.setCourseArrange(courseArrange);
                    Object object = rs.getObject(4);
                    if (object == null) {
                        score.setScore(null);
                    }else{
                        score.setScore(rs.getInt(4));
                    }
                    list.add(score);
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
        for(int i=0;i<list.size();i++){
            score = (Score) list.get(i);
            student = studentDao.findStudentByStudentNo(score.getStudent().getStudentNo());
            courseArrange = courseArrangeDao.findCourseArrangeByArrangeNo(score.getCourseArrange().getArrangeNo());
            score.setStudent(student);
            score.setCourseArrange(courseArrange);
            list.set(i,score);
        }
        return map;
    }

    @Override
    public List findAllScore() {
        Score score=null;
        StudentIDao studentDao = new StudentDaoImpl();
        CourseArrangeIDao courseArrangeDao = new CourseArrangeDaoImpl();
        Student student;
        CourseArrange courseArrange;
        ArrayList list=new ArrayList();
        Connection conn=null;
        Statement pstmt=null;
        ResultSet rs=null;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select * from score order by id";
            pstmt=conn.createStatement();
            rs=pstmt.executeQuery(sql);
            while(rs.next()){
                student = new Student();
                courseArrange = new CourseArrange();
                score.setId(rs.getInt(1));
                student.setStudentNo(rs.getString(2));
                score.setStudent(student);
                courseArrange.setArrangeNo(rs.getString(3));
                score.setCourseArrange(courseArrange);
                Object object = rs.getObject(4);
                if (object == null) {
                    score.setScore(null);
                }else{
                    score.setScore(rs.getInt(4));
                }
                list.add(score);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试!");
        }finally{
            JDBCUtil.close(rs,conn,pstmt);
        }
        for(int i=0;i<list.size();i++){
            score = (Score) list.get(i);
            student = studentDao.findStudentByStudentNo(score.getStudent().getStudentNo());
            courseArrange = courseArrangeDao.findCourseArrangeByArrangeNo(score.getCourseArrange().getArrangeNo());
            score.setStudent(student);
            score.setCourseArrange(courseArrange);
            list.set(i,score);
        }
        return list;
    }

    @Override
    public List findAllCourseArrange() {
        CourseArrange courseArrange = null;
        CourseArrangeIDao courseArrangeDao = new CourseArrangeDaoImpl();
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        ArrayList list;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select distinct arrangeNo from score";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            list = new ArrayList();
            while(rs.next()){
                courseArrange=courseArrangeDao.findCourseArrangeByArrangeNo(rs.getString(1));
                list.add(courseArrange);
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
    public List findAllCourseArrangeByStudentNo(String studentNo) {
        CourseArrange courseArrange = null;
        CourseArrangeIDao courseArrangeDao = new CourseArrangeDaoImpl();
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        ArrayList list;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select distinct arrangeNo from score where sutdentNo=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,studentNo);
            rs=pstmt.executeQuery();
            list = new ArrayList();
            while(rs.next()){
                courseArrange = new CourseArrange();
                courseArrangeDao.findCourseArrangeByArrangeNo(rs.getString(1));
                list.add(courseArrange);
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
    public List findAllCourseArrangeByTeacherNo(String teacherNo) {
        CourseArrange courseArrange = null;
        CourseArrangeIDao courseArrangeDao = new CourseArrangeDaoImpl();
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        ArrayList list;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select distinct arrangeNo from score join courseArrange on score.arrangeNo=courseArrange.arrangeNo where teacherNo=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,teacherNo);
            rs=pstmt.executeQuery();
            list = new ArrayList();
            while(rs.next()){
                courseArrange = new CourseArrange();
                courseArrangeDao.findCourseArrangeByArrangeNo(rs.getString(1));
                list.add(courseArrange);
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
    public List findAllStudent() {
        Student student = null;
        StudentIDao studentDao = new StudentDaoImpl();
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        ArrayList list;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select distinct studentNo from score order by studentNo";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            list = new ArrayList();
            while(rs.next()){
                student= studentDao.findStudentByStudentNo(rs.getString(1));
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

    @Override
    public List findAllCourse() {
        Course course = null;
        CourseIDao courseDao = new CourseDaoImpl();
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        ArrayList list;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select distinct courseNo from score join courseArrange on score.arrangeNo=courseArrange.arrangeNo";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            list = new ArrayList();
            while(rs.next()){
                course= courseDao.findCourseByCourseNo(rs.getString(1));
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
    public List findAllCourseByStudentNo(String studentNo) {
        Course course = null;
        CourseIDao courseDao = new CourseDaoImpl();
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        ArrayList list;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select distinct courseNo from score join courseArrange on score.arrangeNo=courseArrange.arrangeNo where studentNo=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,studentNo);
            rs=pstmt.executeQuery();
            list = new ArrayList();
            while(rs.next()){
                course= courseDao.findCourseByCourseNo(rs.getString(1));
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
    public List findAllCourseTeacherNo(String teacherNo) {
        Course course = null;
        CourseIDao courseDao = new CourseDaoImpl();
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        ArrayList list;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select distinct courseNo from score join courseArrange on score.arrangeNo=courseArrange.arrangeNo where teacherNo=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,teacherNo);
            rs=pstmt.executeQuery();
            list = new ArrayList();
            while(rs.next()){
                course = courseDao.findCourseByCourseNo(rs.getString(1));
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
    public List findAllClasstbl() {
        Classtbl classtbl = null;
        ClassIDao classDao = new ClassDaoImpl();
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        ArrayList list;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select distinct classNo from score join courseArrange on score.arrangeNo=courseArrange.arrangeNo";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            list = new ArrayList();
            while(rs.next()){
                classtbl =   classDao.findClassByClasstblNo(rs.getString(1));
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
    public List findAllClasstblByStudentNo(String studentNo) {
        Classtbl classtbl = null;
        ClassIDao classDao = new ClassDaoImpl();
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        ArrayList list;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select distinct classNo from score join courseArrange on score.arrangeNo=courseArrange.arrangeNo where studentNo=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,studentNo);
            rs=pstmt.executeQuery();
            list = new ArrayList();
            while(rs.next()){
                classtbl =  classDao.findClassByClasstblNo(rs.getString(1));
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
    public List findAllClasstblByTeacherNo(String teacherNo) {
        Classtbl classtbl = null;
        ClassIDao classDao = new ClassDaoImpl();
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        ArrayList list;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select distinct classNo from score join courseArrange on score.arrangeNo=courseArrange.arrangeNo where teacherNo=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,teacherNo);
            rs=pstmt.executeQuery();
            list = new ArrayList();
            while(rs.next()){
                classtbl = classDao.findClassByClasstblNo(rs.getString(1));
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
    public Map findAllScoreByMostCon(String courseNo, String classNo,String teacherNo,String teacherName, String studentNo, String name, Integer scoreMin, Integer scoreMax, int curPage) {
        Score score=null;
        StudentIDao studentDao = new StudentDaoImpl();
        CourseArrangeIDao courseArrangeDao = new CourseArrangeDaoImpl();
        Student student;
        CourseArrange courseArrange;
        ArrayList list=new ArrayList();
        Connection conn=null;
        Statement pstmt=null;
        ResultSet rs=null;
        ResultSet r=null;
        Map map=null;
        Page pa=null;
        String sql="select score.id,score.studentNo,score.arrangeNo,score.score from score join coursearrange on score.arrangeNo=coursearrange.arrangeNo join student on score.studentNo=student.studentNo join teacher on coursearrange.teacherNo=teacher.teacherNo where 1=1 ";
        if(courseNo!=null&&!courseNo.equals("")){
            sql+=" and courseNo="+courseNo;
        }
        if(teacherNo!=null&&!teacherNo.equals("")){
            sql+=" and courseArrange.teacherNo like'%"+teacherNo+"%'";
        }
        if(studentNo!=null&&!studentNo.equals("")){
            sql+=" and score.studentNo like '%"+studentNo+"%'";
        }
        if(classNo!=null&&!classNo.equals("")){
            sql+=" and courseArrange.classNo="+classNo;
        }
        if(teacherName!=null&&!teacherName.equals("")){
            sql+=" and teacher.teacherName like '%"+teacherName+"%'";
        }
        if(name!=null&&!name.equals("")){
            sql+=" and student.name like '%"+name+"%'";
        }
        if(scoreMin!=null){
            sql+=" and score>="+scoreMin;
        }
        if(scoreMax!=null){
            sql+=" and score<"+scoreMax;
        }
        sql+=" order by id";
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
                    score = new Score();
                    student = new Student();
                    courseArrange = new CourseArrange();
                    score.setId(rs.getInt(1));
                    student.setStudentNo(rs.getString(2));
                    score.setStudent(student);
                    courseArrange.setArrangeNo(rs.getString(3));
                    score.setCourseArrange(courseArrange);
                    Object object = rs.getObject(4);
                    if (object == null) {
                        score.setScore(null);
                    }else{
                        score.setScore(rs.getInt(4));
                    }
                    list.add(score);
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
        for(int i=0;i<list.size();i++){
            score = (Score) list.get(i);
            student = studentDao.findStudentByStudentNo(score.getStudent().getStudentNo());
            courseArrange = courseArrangeDao.findCourseArrangeByArrangeNo(score.getCourseArrange().getArrangeNo());
            score.setStudent(student);
            score.setCourseArrange(courseArrange);
            list.set(i,score);
        }
        return map;
    }

    @Override
    public Score findScoreById(String id) {
        Score score=null;
        StudentIDao studentDao = new StudentDaoImpl();
        CourseArrangeIDao courseArrangeDao = new CourseArrangeDaoImpl();
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        Student student;
        CourseArrange courseArrange;
        try{
            conn=JDBCUtil.getConnction();
            String sql="select * from score where id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs=pstmt.executeQuery();
            while(rs.next()){
                score = new Score();
                student = new Student();
                courseArrange = new CourseArrange();
                score.setId(rs.getInt(1));
                student.setStudentNo(rs.getString(2));
                score.setStudent(student);
                courseArrange.setArrangeNo(rs.getString(3));
                score.setCourseArrange(courseArrange);
                Object object = rs.getObject(4);
                if(object!=null){
                    score.setScore(rs.getInt(4));
                }
                else {
                    score.setScore(null);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("数据库操作异常，请稍后重试!");
        }finally{
            JDBCUtil.close(rs,conn,pstmt);
        }
        if(score!=null){
            student = studentDao.findStudentByStudentNo(score.getStudent().getStudentNo());
            courseArrange = courseArrangeDao.findCourseArrangeByArrangeNo(score.getCourseArrange().getArrangeNo());
            score.setStudent(student);
            score.setCourseArrange(courseArrange);
        }
        return score;
    }


}
