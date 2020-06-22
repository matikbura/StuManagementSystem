package dao;

import domain.Course;

import java.util.List;
import java.util.Map;

public interface CourseIDao {

	void addCourse(Course course);

	void delCourse(String courseNo);

	void updateCourse(Course course);

	Course findCourseByCourseNo(String courseNo);

	Map findAllCourse(int curPage);

	List findAllCourse();

	List findAllCourseByMostCon(String courseNo, String courseName,
								String studyTime, String grade, int courseType, int term);

	Map findAllCourseByMostCon(String courseNo, String courseName, String studyTime, String grade,
							   int courseType, int term, int curPage);

}
