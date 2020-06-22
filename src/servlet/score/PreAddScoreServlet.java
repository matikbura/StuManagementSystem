package servlet.score;

import service.CourseArrangeIService;
import service.Impl.CourseArrangeServiceImpl;
import service.Impl.StudentServiceImpl;
import service.StudentIService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/PreAddScoreServlet")
public class PreAddScoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        StudentIService studentService = new StudentServiceImpl();
        CourseArrangeIService courseArrangeService = new CourseArrangeServiceImpl();
        try {
            List studentList = studentService.findAllStudent();
            List courseArrangeList = courseArrangeService.findAllCourseArrange();
            request.setAttribute("studentList",studentList);
            request.setAttribute("courseArrangeList",courseArrangeList);
            request.getRequestDispatcher("/score/addScore.jsp").forward(request, response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
