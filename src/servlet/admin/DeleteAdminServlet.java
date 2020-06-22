package servlet.admin;

import exception.DaoException;
import exception.ServiceException;
import service.AdminIService;
import service.Impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DeleteTeacherServlet")
public class DeleteAdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminIService service = new AdminServiceImpl();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String loginName = request.getParameter("loginName");
        try {
            if(service.findAdminByLoginName(loginName)!=null){
                HttpSession session = request.getSession();
                service.delAdmin(loginName);
                session.setAttribute("message","删除管理员成功");
                request.getRequestDispatcher("ListAllAdminServlet").forward(request,response);

            }
            else {
                HttpSession session = request.getSession();
                session.setAttribute("message","你要删除的管理员不存在");
                request.getRequestDispatcher("ListAllAdminServlet").forward(request,response);
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
