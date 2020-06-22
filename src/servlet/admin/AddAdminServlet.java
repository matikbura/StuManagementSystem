package servlet.admin;

import domain.Admin;
import exception.DaoException;
import exception.ServiceException;
import service.AdminIService;
import service.Impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddAdminServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminIService service = new AdminServiceImpl();
		HttpSession session=request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String loginName=request.getParameter("loginName");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		try{
			if(service.findAdminByLoginName(loginName)!=null){
				session.setAttribute("message", "所要添加的管理员已存在！");
				request.getRequestDispatcher("/base/admin/index.jsp").forward(request, response);
			}else{
				Admin admin=new Admin(loginName,name,password );
				service.addAdmin(admin);
		  		session.setAttribute("message", "管理员信息添加成功！");
		  		request.getRequestDispatcher("ListAllAdminServlet").forward(request, response);
			}
		}catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute("se", e);
			request.getRequestDispatcher("/serviceException.jsp").forward(request, response);
		} catch (DaoException e) {
			e.printStackTrace();
			request.setAttribute("de", e);
			request.getRequestDispatcher("/daoException.jsp").forward(request, response);
		}
	}
}
