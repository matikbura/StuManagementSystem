package service.Impl;

import dao.Impl.ScoreDaoImpl;
import dao.ScoreIDao;
import domain.Score;
import exception.DaoException;
import exception.ServiceException;
import service.ScoreIService;

import java.util.List;
import java.util.Map;

public class ScoreServiceImpl implements ScoreIService {
    ScoreIDao scoreDao = new ScoreDaoImpl();
    @Override
    public void addScore(Score score) throws ServiceException {
        try{
            scoreDao.addScore(score);
        }catch(DaoException e){
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
    }

    @Override
    public void delScore(String id) throws ServiceException {
        try{
            scoreDao.delScore(id);
        }catch(DaoException e){
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
    }

    @Override
    public void updateScore(Score score) throws ServiceException {
        try{
            scoreDao.updateScore(score);
        }catch(DaoException e){
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
    }

    @Override
    public Score findScoreById(String id) throws ServiceException {
        Score score;
        try{
            score=scoreDao.findScoreById(id);
        }catch(DaoException e){
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return score;
    }

    @Override
    public Score findScoreByValues(String studentNo,String arrangeNo) throws ServiceException {
        Score score;
        try{
            score=scoreDao.findScoreByValues(studentNo,arrangeNo);
        }catch(DaoException e){
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return score;
    }

    @Override
    public Map findAllScore(int curPage) throws ServiceException {
        Map map;
        try{
            map=scoreDao.findAllScore(curPage);
        }catch(DaoException e){
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return map;
    }

    @Override
    public List findAllScore() throws ServiceException {
        List list;
        try{
            list = scoreDao.findAllScore();
        }catch(DaoException e){
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return list;
    }

    @Override
    public List findAllCourseArrange() {
        List list;
        try{
            list=scoreDao.findAllCourseArrange();
        }catch(DaoException e){
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return list;
    }

    @Override
    public List findAllCourseArrangeByStudentNo(String studentNo) {
        List list;
        try{
            list=scoreDao.findAllCourseArrangeByStudentNo(studentNo);
        }catch(DaoException e){
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return list;
    }

    @Override
    public List findAllCourseArrangeTeacherNo(String teacherNo) {
        List list;
        try{
            list=scoreDao.findAllCourseArrangeByTeacherNo(teacherNo);
        }catch(DaoException e){
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return list;
    }

    @Override
    public List findAllCourse() {
        List list;
        try{
            list=scoreDao.findAllCourse();
        }catch(DaoException e){
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return list;
    }

    @Override
    public List findAllCourseByStudentNo(String studentNo) {
        List list;
        try{
            list=scoreDao.findAllCourseByStudentNo(studentNo);
        }catch(DaoException e){
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return list;
    }

    @Override
    public List findAllCourseTeacherNo(String teacherNo) {
        List list;
        try{
            list=scoreDao.findAllCourseTeacherNo(teacherNo);
        }catch(DaoException e){
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return list;
    }

    @Override
    public List findAllClasstbl() {
        List list;
        try{
            list=scoreDao.findAllClasstbl();
        }catch(DaoException e){
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return list;
    }

    @Override
    public List findAllClasstblByStudentNo(String studentNo) {
        List list;
        try{
            list=scoreDao.findAllClasstblByStudentNo(studentNo);
        }catch(DaoException e){
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return list;
    }

    @Override
    public List findAllClasstblByTeacherNo(String teacherNo) {
        List list;
        try{
            list=scoreDao.findAllClasstblByTeacherNo(teacherNo);
        }catch(DaoException e){
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return list;
    }

    @Override
    public Map findAllScoreByMostCon(String courseNo, String classNo, String teacherNo, String teacherName, String studentNo, String name, Integer scoreMin, Integer scoreMax, int curPage) {
        Map map;
        try{
            map=scoreDao.findAllScoreByMostCon(courseNo,classNo,teacherNo,teacherName,studentNo,name,scoreMin,scoreMax,curPage);
        }catch(DaoException e){
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return map;
    }

    @Override
    public List findAllStudent() {
        List list;
        try {
            list = scoreDao.findAllStudent();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(), e);
        }
        return list;
    }
}
