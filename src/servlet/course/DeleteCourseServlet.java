package servlet.course;

import exception.DaoException;
import exception.ServiceException;
import service.CourseIService;
import service.Impl.CourseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DeleteTeacherServlet")
public class DeleteCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CourseIService service = new CourseServiceImpl();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String courseNo = request.getParameter("courseNo");
        try {
            if(service.findCourseByCourseNo(courseNo)!=null){
                HttpSession session = request.getSession();
                service.delCourse(courseNo);
                session.setAttribute("message","删除课程成功");
                request.getRequestDispatcher("ListAllCourseServlet").forward(request,response);

            }
            else {
                HttpSession session = request.getSession();
                session.setAttribute("message","你要删除的课程不存在");
                request.getRequestDispatcher("ListAllCourseServlet").forward(request,response);
            }

        } catch (ServiceException e) {
            HttpSession session = request.getSession();
            session.setAttribute("e",e);
            request.getRequestDispatcher("serviceException");
        } catch (DaoException e){
            HttpSession session = request.getSession();
            session.setAttribute("e",e);
            request.getRequestDispatcher("daoException");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
