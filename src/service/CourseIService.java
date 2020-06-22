package service;

import domain.Course;
import exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface CourseIService {

    void addCourse(Course course) throws ServiceException;

    void delCourse(String courseNo) throws ServiceException;

    void updateCourse(Course course) throws ServiceException;

    Course findCourseByCourseNo(String courseNo) throws ServiceException;

    Map findAllCourse(int curPage) throws ServiceException;

    List findAllCourse() throws ServiceException;

    List findAllCourseByMostCon(String courseNo, String courseName,
                                String studyTime, String grade, int courseType, int term) throws ServiceException;

    Map findAllCourseByMostCon(String courseNo, String courseName,
                               String studyTime, String grade, int courseType, int term, int curPage) throws ServiceException;
}
