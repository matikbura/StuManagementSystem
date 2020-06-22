package servlet.teacher;

import exception.DaoException;
import exception.ServiceException;
import service.Impl.TeacherServiceImpl;
import service.TeacherIService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DeleteTeacherServlet")
public class DeleteTeacherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TeacherIService service = new TeacherServiceImpl();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String teacherNo = request.getParameter("teacherNo");
        System.out.println(teacherNo);
        try {
            if(service.findTeacherByTeacherNo(teacherNo)!=null){
                HttpSession session = request.getSession();
                service.delTeacher(teacherNo);
                session.setAttribute("message","删除教师成功");
                request.getRequestDispatcher("ListAllTeacherServlet").forward(request,response);

            }
            else {
                HttpSession session = request.getSession();
                session.setAttribute("message","你要删除的教师不存在");
                request.getRequestDispatcher("ListAllTeacherServlet").forward(request,response);
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
