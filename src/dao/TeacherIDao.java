package dao;
import domain.Teacher;

import java.util.List;
import java.util.Map;
public interface TeacherIDao {

	void addTeacher(Teacher teacher);

	void delTeacher(String teacherNo);

	void updateTeacher(Teacher teacher);

	Teacher findTeacherByTeacherNo(String teacherNo);

	Map findAllTeacher(int curPage);

	List findAllTeacher();

	List findAllTeacherByMostCon(String teacherNo, String teacherName,
								 Integer professional, String phone, String subject,String email);

	Map findAllTeacherByMostCon(String teacherNo, String teacherName,
								Integer professional, String phone, String subject,String email, int curPage);

	Teacher login(String teacherNo, String password);
}
