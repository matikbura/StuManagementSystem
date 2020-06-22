package dao;

import domain.CourseArrange;

import java.util.List;
import java.util.Map;

public interface CourseArrangeIDao {
    void addCourseArrange(CourseArrange courseArrange);

    void delCourseArrange(String arrangeNo);

    void updateCourseArrange(CourseArrange courseArrange);

    CourseArrange findCourseArrangeByArrangeNo(String arrangeNo);

    Map findAllCourseArrange(int curPage);

    List findAllCourseArrangeByMostCon(String arrangeNo, String courseNo, String classNo, String teacherNo);

    List findAllCourseArrangeByNo(String courseNo, String classNo, String teacherNo);

    Map findAllCourseArrangeByMostCon(String arrangeNo, String courseNo, String classNo, String teacherNo, int curPage);

    List findAllCourseArrangeByTeacherNo(String teacherNo);

    List findAllCourseArrange();

    List findAllCoursesByTeacherNo(String teacherNo);

    List findAllClassTblsByTeacherNo(String teacherNo);
}
