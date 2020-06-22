package service.Impl;

import dao.CourseArrangeIDao;
import dao.Impl.CourseArrangeDaoImpl;
import domain.CourseArrange;
import exception.DaoException;
import exception.ServiceException;
import service.CourseArrangeIService;

import java.util.List;
import java.util.Map;


public class CourseArrangeServiceImpl implements CourseArrangeIService {
	private CourseArrangeIDao courseArrangeDao = new CourseArrangeDaoImpl();

	public void addCourseArrange(CourseArrange courseArrange) throws ServiceException {
		try{
			courseArrangeDao.addCourseArrange(courseArrange);
		}catch(DaoException e){
			e.printStackTrace();
			throw new DaoException(e.getMessage(),e);
		}
	}

	public void delCourseArrange(String arrangeNo) throws ServiceException{
		try{
			courseArrangeDao.delCourseArrange(arrangeNo);
		}catch(DaoException e){
			e.printStackTrace();
			throw new DaoException(e.getMessage(),e);
		}
	}

	public void updateCourseArrange(CourseArrange courseArrange) throws ServiceException{
		try{
			courseArrangeDao.updateCourseArrange(courseArrange);
		}catch(DaoException e){
			e.printStackTrace();
			throw new DaoException(e.getMessage(),e);
		}
	}

	public CourseArrange findCourseArrangeByArrangeNo(String arrangeNo) throws ServiceException{
		CourseArrange courseArrange=null;
		try{
			courseArrange = courseArrangeDao.findCourseArrangeByArrangeNo(arrangeNo);
		}catch(DaoException e){
			e.printStackTrace();
			throw new DaoException(e.getMessage(),e);
		}
		return courseArrange;
	}

	public Map findAllCourseArrange(int curPage) throws ServiceException{
		Map map=null;
		try{
			map = courseArrangeDao.findAllCourseArrange(curPage);
		}catch(DaoException e){
			e.printStackTrace();
			throw new DaoException(e.getMessage(),e);
		}
		return map;
	}

	public List findAllCourseArrangeByMostCon(String arrangeNo,String courseNo,String classNo,String teacherNo) throws ServiceException{
		List list = null;
		try{
			list = courseArrangeDao.findAllCourseArrangeByMostCon(arrangeNo, courseNo, classNo, teacherNo);
		}catch(DaoException e){
			e.printStackTrace();
			throw new DaoException(e.getMessage(),e);
		}
		return list;
	}

	public List findAllCourseArrangeByNo(String courseNo,String classNo,String teacherNo) throws ServiceException{
		List list = null;
		try{
			list = courseArrangeDao.findAllCourseArrangeByNo(courseNo, classNo, teacherNo);
		}catch(DaoException e){
			e.printStackTrace();
			throw new DaoException(e.getMessage(),e);
		}
		return list;
	}

	public Map findAllCourseArrangeByMostCon(String arrangeNo,String courseNo,String classNo,String teacherNo,int curPage) throws ServiceException{
		Map map=null;
		try{
			map = courseArrangeDao.findAllCourseArrangeByMostCon(arrangeNo, courseNo, classNo, teacherNo, curPage);
		}catch(DaoException e){
			e.printStackTrace();
			throw new DaoException(e.getMessage(),e);
		}
		return map;
	}

	public List findAllClassTblsByTeacherNo(String teacherNo) throws ServiceException{
		List list = null;
		try{
			list = courseArrangeDao.findAllClassTblsByTeacherNo(teacherNo);
		}catch(DaoException e){
			e.printStackTrace();
			throw new DaoException(e.getMessage(),e);
		}
		return list;
	}

	public List findAllCoursesByTeacherNo(String teacherNo) throws ServiceException{
		List list = null;
		try{
			list = courseArrangeDao.findAllCoursesByTeacherNo(teacherNo);
		}catch(DaoException e){
			e.printStackTrace();
			throw new DaoException(e.getMessage(),e);
		}
		return list;
	}

	@Override
	public List findAllCourseArrangeByTeacherNo(String teacherNo) {
		List list = null;
		try{
			list = courseArrangeDao.findAllCourseArrangeByTeacherNo(teacherNo);
		}catch(DaoException e){
			e.printStackTrace();
			throw new DaoException(e.getMessage(),e);
		}
		return list;
	}

	@Override
	public List findAllCourseArrange() throws ServiceException {
		List list = null;
		try {
			list = courseArrangeDao.findAllCourseArrange();
		}catch (Exception e){
			e.printStackTrace();
			throw new ServiceException("服务层操作异常");
		}
		return list;
	}
}
