package service.Impl;

import dao.CourseIDao;
import dao.Impl.CourseDaoImpl;
import domain.Course;
import exception.DaoException;
import exception.ServiceException;
import service.CourseIService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseServiceImpl implements CourseIService {
    private CourseIDao courseIDao = new CourseDaoImpl();
    @Override
    public void addCourse(Course course) throws ServiceException {
        try{
            courseIDao.addCourse(course);
        }catch(DaoException e){
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
    }

    @Override
    public void delCourse(String courseNo) throws ServiceException {
        try{
            courseIDao.delCourse(courseNo);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
    }

    @Override
    public void updateCourse(Course course) throws ServiceException {
        try{
            courseIDao.updateCourse(course);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
    }

    @Override
    public Course findCourseByCourseNo(String courseNo) throws ServiceException {
        Course course=null;
        try{
            course = courseIDao.findCourseByCourseNo(courseNo);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return course;
    }

    @Override
    public Map findAllCourse(int curPage) throws ServiceException {
        Map map=null;
        try{
            map = courseIDao.findAllCourse(curPage);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return map;
    }

    @Override
    public List findAllCourse() throws ServiceException {
        List list=new ArrayList();
        try{
            list = courseIDao.findAllCourse();
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return list;
    }

    @Override
    public List findAllCourseByMostCon(String courseNo, String courseName, String studyTime,
                                       String grade, int courseType, int term) throws ServiceException {
        List list = null;
        try{
            list = courseIDao.findAllCourseByMostCon(courseNo,courseName,studyTime,grade,courseType,term);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return list;
    }

    @Override
    public Map findAllCourseByMostCon(String courseNo, String courseName, String studyTime, String grade, int courseType, int term, int curPage) throws ServiceException {
        Map map  = null;
        try{
            map = courseIDao.findAllCourseByMostCon(courseNo,courseName,studyTime,grade,courseType,term,curPage);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return map;
    }
}
