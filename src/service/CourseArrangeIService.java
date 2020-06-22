package service;

import domain.CourseArrange;
import exception.ServiceException;

import java.util.List;
import java.util.Map;


public interface CourseArrangeIService {

	public void addCourseArrange(CourseArrange courseArrange) throws ServiceException;

	public void delCourseArrange(String arrangeNo) throws ServiceException;

	public void updateCourseArrange(CourseArrange courseArrange) throws ServiceException;

	public CourseArrange findCourseArrangeByArrangeNo(String arrangeNo) throws ServiceException;

	public Map findAllCourseArrange(int curPage) throws ServiceException;

	public List findAllCourseArrangeByMostCon(String arrangeNo,String courseNo,String classNo,String teacherNo) throws ServiceException;

	public List findAllCourseArrangeByNo(String courseNo,String classNo,String teacherNo) throws ServiceException;

	public Map findAllCourseArrangeByMostCon(String arrangeNo,String courseNo,String classNo,String teacherNo,int curPage) throws ServiceException;

	public List findAllClassTblsByTeacherNo(String teacherNo) throws ServiceException;

	public List findAllCoursesByTeacherNo(String teacherNo) throws ServiceException;

	List findAllCourseArrangeByTeacherNo(String teacherNo);

	List findAllCourseArrange() throws  ServiceException;
}
