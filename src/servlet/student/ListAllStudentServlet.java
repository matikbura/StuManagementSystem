package servlet.student;

import exception.DaoException;
import exception.ServiceException;
import service.ClassIService;
import service.Impl.ClassServiceImpl;
import service.Impl.StudentServiceImpl;
import service.StudentIService;
import utils.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListAllStudentServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentIService service = new StudentServiceImpl();
		ClassIService classService = new ClassServiceImpl();
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int curPage=1;
		String temp=request.getParameter("curPage");
		if(temp!=null){
			curPage=Integer.parseInt(request.getParameter("curPage"));
		}
		try{
			Map map=service.findAllStudent(curPage);
			List classList = classService.findAllClasstbl();
			ArrayList list=(ArrayList) map.get("list");
			Page pa=(Page) map.get("pa");
			session.setAttribute("classList",classList);
	  		request.setAttribute("curPage", pa.getCurPage());
	  		request.setAttribute("pageCount",pa.getPageCount());
	  		request.setAttribute("list", list);
	  		request.getRequestDispatcher("/base/student/studentList.jsp").forward(request, response);
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
