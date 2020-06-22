package dao;

import domain.Score;

import java.util.List;
import java.util.Map;
public interface ScoreIDao {

    void addScore(Score score);

    void delScore(String id);

    void updateScore(Score score);

    Score findScoreByValues(String studentNo,String arrangeNo);

    Map findAllScore(int curPage);

    List findAllScore();

    List findAllCourseArrange();

    List findAllCourseArrangeByStudentNo(String studentNo);

    List findAllCourseArrangeByTeacherNo(String teacherNo);

    List findAllStudent();

    List findAllCourse();

    List findAllCourseByStudentNo(String studentNo);

    List findAllCourseTeacherNo(String teacherNo);

    List findAllClasstbl();

    List findAllClasstblByStudentNo(String studentNo);

    List findAllClasstblByTeacherNo(String teacherNo);

    Map findAllScoreByMostCon(String courseNo, String classNo, String teacherNo, String teacherName, String studentNo, String name, Integer scoreMin, Integer scoreMax, int curPage);

    Score findScoreById(String id);
}
