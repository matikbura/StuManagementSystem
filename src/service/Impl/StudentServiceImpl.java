package service.Impl;

import dao.Impl.StudentDaoImpl;
import dao.StudentIDao;
import domain.Student;
import exception.DaoException;
import exception.ServiceException;
import service.StudentIService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements StudentIService {
    StudentIDao studentDao =new StudentDaoImpl();


    @Override
    public void addStudent(Student student) throws ServiceException, ServiceException, ServiceException {
        try{
            studentDao.addStudent(student);
        }catch(DaoException e){
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
    }

    @Override
    public void delStudent(String studentNo) throws ServiceException {
        try{
            studentDao.delStudent(studentNo);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
    }

    @Override
    public void updateStudent(Student student) throws ServiceException {
        try{
            studentDao.updateStudent(student);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
    }

    @Override
    public Student findStudentByStudentNo(String studentNo) throws ServiceException {
        Student student=null;
        try{
            student = studentDao.findStudentByStudentNo(studentNo);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return student;
    }

    @Override
    public Map findAllStudent(int curPage) throws ServiceException {
        Map map=null;
        try{
            map = studentDao.findAllStudent(curPage);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return map;
    }

    @Override
    public List findAllStudent() throws ServiceException {
        List list=new ArrayList();
        try{
            list = studentDao.findAllStudent();
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return list;
    }

    @Override
    public List findAllStudentByMostCon(String studentNo, String name, String address, String phone, String email, String classNo) throws ServiceException {
        List list = null;
        try{
            list = studentDao.findAllStudentByMostCon(studentNo, name, address, phone, email,classNo);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return list;
    }

    @Override
    public Map findAllStudentByMostCon(String studentNo, String name, String address, String phone, String email, String classNo, int curPage) throws ServiceException {
        Map map=null;
        try{
            map = studentDao.findAllStudentByMostCon(studentNo, name, address, phone, email,classNo, curPage);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return map;
    }

    @Override
    public Student login(String studentNo, String password) throws ServiceException {
        Student student = null;
        try{
            student = studentDao.login(studentNo, password);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return student;
    }
}
