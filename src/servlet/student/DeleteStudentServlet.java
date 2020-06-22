package servlet.student;

import exception.DaoException;
import exception.ServiceException;
import service.Impl.StudentServiceImpl;
import service.StudentIService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DeleteTeacherServlet")
public class DeleteStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentIService service = new StudentServiceImpl();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String studentNo = request.getParameter("studentNo");
        try {
            if(service.findStudentByStudentNo(studentNo)!=null){
                HttpSession session = request.getSession();
                service.delStudent(studentNo);
                session.setAttribute("message","删除学生成功");
                request.getRequestDispatcher("ListAllStudentServlet").forward(request,response);

            }
            else {
                HttpSession session = request.getSession();
                session.setAttribute("message","你要删除的学生不存在");
                request.getRequestDispatcher("ListAllStudentServlet").forward(request,response);
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
