package service;

import domain.Score;
import exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface ScoreIService {
    void addScore(Score score) throws ServiceException;

    void delScore(String id) throws ServiceException;

    void updateScore(Score score) throws ServiceException;

    Score findScoreById(String id) throws ServiceException;

    Score findScoreByValues(String studentNo,String arrangeNo) throws ServiceException;

    Map findAllScore(int curPage) throws ServiceException;

    List findAllScore() throws ServiceException;

    List findAllCourseArrange();

    List findAllCourseArrangeByStudentNo(String studentNo);

    List findAllCourseArrangeTeacherNo(String teacherNo);

    List findAllCourse();

    List findAllCourseByStudentNo(String studentNo);

    List findAllCourseTeacherNo(String teacherNo);

    List findAllClasstbl();

    List findAllClasstblByStudentNo(String studentNo);

    List findAllClasstblByTeacherNo(String teacherNo);

    Map findAllScoreByMostCon(String courseNo, String classNo, String teacherNo, String teacherName, String studentNo, String name, Integer scoreMin, Integer scoreMax, int curPage);

    List findAllStudent();
}

