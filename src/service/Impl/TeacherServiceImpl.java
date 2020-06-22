package service.Impl;

import exception.ServiceException;
import exception.DaoException;
import service.TeacherIService;
import dao.Impl.TeacherDaoImpl;
import dao.TeacherIDao;
import domain.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TeacherServiceImpl implements TeacherIService {
	private TeacherIDao teacherDao = new TeacherDaoImpl();

	public void addTeacher(Teacher teacher) throws ServiceException {
		try{
			teacherDao.addTeacher(teacher);
		}catch(DaoException e){
			e.printStackTrace();
			throw new DaoException(e.getMessage(),e);
		}
	}

	public void delTeacher(String teacherNo) throws ServiceException {
		try{
			teacherDao.delTeacher(teacherNo);
		}catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException(e.getMessage(),e);
		}
	}

	public void updateTeacher(Teacher teacher) throws ServiceException{
		try{
			teacherDao.updateTeacher(teacher);
		}catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException(e.getMessage(),e);
		}
	}

	public Teacher findTeacherByTeacherNo(String teacherNo) throws ServiceException{
		Teacher teacher=null;
		try{
			teacher = teacherDao.findTeacherByTeacherNo(teacherNo);
		}catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException(e.getMessage(),e);
		}
		return teacher;
	}

	public Map findAllTeacher(int curPage) throws ServiceException{
		Map map=null;
		try{
			map = teacherDao.findAllTeacher(curPage);
		}catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException(e.getMessage(),e);
		}
		return map;
	}

	public List findAllTeacher() throws ServiceException{
		List list=new ArrayList();
		try{
			list = teacherDao.findAllTeacher();
		}catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException(e.getMessage(),e);
		}
		return list;
	}

	public List findAllTeacherByMostCon(String teacherNo,String teacherName,
			Integer professional,String phone,String subject,String email) throws ServiceException{
		List list = null;
		try{
			list = teacherDao.findAllTeacherByMostCon(teacherNo, teacherName, professional, phone, subject,email);
		}catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException(e.getMessage(),e);
		}
		return list;
	}

	public Map findAllTeacherByMostCon(String teacherNo,String teacherName,
			Integer professional,String phone,String subject,String email,int curPage) throws ServiceException{
		Map map=null;
		try{
			map = teacherDao.findAllTeacherByMostCon(teacherNo, teacherName, professional, phone, subject,email, curPage);
		}catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException(e.getMessage(),e);
		}
		return map;
	}

	public Teacher login(String teacherNo,String password) throws ServiceException{
		Teacher teacher = null;
		try{
			teacher = teacherDao.login(teacherNo, password);
		}catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException(e.getMessage(),e);
		}
		return teacher;
	}
}
