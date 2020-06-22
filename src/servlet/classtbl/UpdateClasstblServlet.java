package servlet.classtbl;

import domain.Classtbl;
import exception.DaoException;
import exception.ServiceException;
import service.ClassIService;
import service.Impl.ClassServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateClasstblServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ClassIService service = new ClassServiceImpl();
		HttpSession session=request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String classNo = request.getParameter("classNo");
		String className = request.getParameter("className");
		String college = request.getParameter("college");
		try{
			Classtbl classtbl = new Classtbl(classNo,className,college);
	  		service.updateClasstbl(classtbl);
	  		session.setAttribute("message", "修改班级信息成功！");
	  		request.getRequestDispatcher("ListAllClasstblServlet").forward(request, response);
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
