package servlet.classtbl;

import exception.DaoException;
import exception.ServiceException;
import service.ClassIService;
import service.Impl.ClassServiceImpl;
import utils.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class CheckClasstblServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ClassIService service = new ClassServiceImpl();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String classNo = request.getParameter("classNo");
		String className = request.getParameter("className");
		String college = request.getParameter("college");
		int curPage=1;
		String temp=request.getParameter("curPage");
		if(temp!=null){
			curPage=Integer.parseInt(request.getParameter("curPage"));
		}
		try{
			Map map = service.findAllClasstblByMostCon(classNo,className,college,curPage);
			ArrayList list=(ArrayList) map.get("list");
			Page pa=(Page) map.get("pa");
			request.setAttribute("curPage", pa.getCurPage());
			request.setAttribute("pageCount",pa.getPageCount());
			request.setAttribute("list", list);
			if((classNo!=null&&!classNo.equals(""))||(className!=null&&!className.equals(""))||(college!=null&&!college.equals(""))){
				request.setAttribute("classNo",classNo);
				request.setAttribute("className",className);
				request.setAttribute("college",college);
			}
	  		request.getRequestDispatcher("/base/classtbl/classtblList.jsp").forward(request, response);
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
