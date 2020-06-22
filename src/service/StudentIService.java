package service;
import exception.ServiceException;
import domain.Student;

import java.util.List;
import java.util.Map;
public interface StudentIService {

    void addStudent(Student student) throws ServiceException, ServiceException;

    void delStudent(String studentNo) throws ServiceException;

    void updateStudent(Student student) throws ServiceException;

    Student findStudentByStudentNo(String studentNo) throws ServiceException;

    Map findAllStudent(int curPage) throws ServiceException;

    List findAllStudent() throws ServiceException;

    List findAllStudentByMostCon(String studentNo, String name, String address, String phone,
                                 String email, String classNo) throws ServiceException;

    Map findAllStudentByMostCon(String studentNo, String name, String address, String phone,
                                String email, String classNo, int curPage) throws ServiceException;

    Student login(String studentNo, String password) throws ServiceException;
}
