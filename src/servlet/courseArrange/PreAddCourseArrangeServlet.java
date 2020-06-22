package servlet.courseArrange;

import dao.CourseIDao;
import dao.Impl.CourseDaoImpl;
import dao.Impl.TeacherDaoImpl;
import dao.TeacherIDao;
import service.ClassIService;
import service.Impl.ClassServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PreAddCourseArrangeServlet")
public class PreAddCourseArrangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        CourseIDao courseService = new CourseDaoImpl();
        TeacherIDao teacherService = new TeacherDaoImpl();
        ClassIService classService = new ClassServiceImpl();
        try {
            List courseList = courseService.findAllCourse();
            List teacherList = teacherService.findAllTeacher();
            List classList = classService.findAllClasstbl();
            request.setAttribute("courseList",courseList);
            request.setAttribute("teacherList",teacherList);
            request.setAttribute("classList",classList);
            request.getRequestDispatcher("/courseArrange/addCourseArrange.jsp").forward(request, response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
