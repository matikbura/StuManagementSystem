package servlet.courseArrange;

import exception.DaoException;
import exception.ServiceException;
import service.CourseArrangeIService;
import service.Impl.CourseArrangeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DeleteTeacherServlet")
public class DeleteCourseArrangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CourseArrangeIService service = new CourseArrangeServiceImpl();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String arrangeNo = request.getParameter("arrangeNo");
        try {
            if(service.findCourseArrangeByArrangeNo(arrangeNo)!=null){
                HttpSession session = request.getSession();
                service.delCourseArrange(arrangeNo);
                session.setAttribute("message","删除课程安排信息成功");
                request.getRequestDispatcher("ListAllCourseArrangeServlet").forward(request,response);
            }
            else {
                HttpSession session = request.getSession();
                session.setAttribute("message","你要删除的课程安排不存在");
                request.getRequestDispatcher("ListAllCourseArrangeServlet").forward(request,response);
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
