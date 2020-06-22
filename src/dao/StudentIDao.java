package dao;

import domain.Student;

import java.util.List;
import java.util.Map;
public interface StudentIDao {

    void addStudent(Student student);

    void delStudent(String studentNo);

    void updateStudent(Student student);

    Student findStudentByStudentNo(String studentNo);

    Map findAllStudent(int curPage);

    List findAllStudent();

    List findAllStudentByMostCon(String studentNo, String name, String address, String phone,
                                 String email, String classNo);

    Map findAllStudentByMostCon(String studentNo, String name, String address, String phone,
                                String email, String classNo, int curPage);

    Student login(String studentNo, String password);
}
