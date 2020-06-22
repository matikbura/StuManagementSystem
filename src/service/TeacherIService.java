package service;
import exception.ServiceException;
import domain.Teacher;

import java.util.List;
import java.util.Map;
public interface TeacherIService {

	void addTeacher(Teacher teacher) throws ServiceException, ServiceException;

	void delTeacher(String teacherNo) throws ServiceException;

	void updateTeacher(Teacher teacher) throws ServiceException;

	Teacher findTeacherByTeacherNo(String teacherNo) throws ServiceException;

	Map findAllTeacher(int curPage) throws ServiceException;

	List findAllTeacher() throws ServiceException;

	List findAllTeacherByMostCon(String teacherNo, String teacherName,
								 Integer professional, String phone,String email, String subject) throws ServiceException;

	Map findAllTeacherByMostCon(String teacherNo, String teacherName,
								Integer professional, String phone, String subject,String email, int curPage) throws ServiceException;

	Teacher login(String teacherNo, String password) throws ServiceException;
}
