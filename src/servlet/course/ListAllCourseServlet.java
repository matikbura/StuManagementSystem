package servlet.course;

import exception.DaoException;
import exception.ServiceException;
import service.CourseIService;
import service.Impl.CourseServiceImpl;
import utils.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ListAllCourseServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CourseIService service = new CourseServiceImpl();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int curPage=1;
		String temp=request.getParameter("curPage");
		if(temp!=null){
			curPage=Integer.parseInt(request.getParameter("curPage"));
		}
		try{
			Map map=service.findAllCourse(curPage);
			ArrayList list=(ArrayList) map.get("list");
			Page pa=(Page) map.get("pa");
	  		request.setAttribute("curPage", pa.getCurPage());
	  		request.setAttribute("pageCount",pa.getPageCount());
	  		request.setAttribute("list", list);
	  		request.getRequestDispatcher("/base/course/courseList.jsp").forward(request, response);
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
