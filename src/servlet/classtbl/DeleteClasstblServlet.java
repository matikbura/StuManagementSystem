package servlet.classtbl;

import exception.DaoException;
import exception.ServiceException;
import service.ClassIService;
import service.Impl.ClassServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DeleteTeacherServlet")
public class DeleteClasstblServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClassIService service = new ClassServiceImpl();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String classNo = request.getParameter("classNo");
        try {
            if(service.findClasstblByClassNo(classNo)!=null){
                HttpSession session = request.getSession();
                service.delClasstbl(classNo);
                session.setAttribute("message","删除班级成功");
                request.getRequestDispatcher("ListAllClasstblServlet").forward(request,response);

            }
            else {
                HttpSession session = request.getSession();
                session.setAttribute("message","你要删除的班级不存在");
                request.getRequestDispatcher("ListAllClasstblServlet").forward(request,response);
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
