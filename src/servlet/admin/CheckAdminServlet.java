package servlet.admin;

import exception.DaoException;
import exception.ServiceException;
import service.AdminIService;
import service.Impl.AdminServiceImpl;
import utils.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class CheckAdminServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminIService service = new AdminServiceImpl();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String loginName=request.getParameter("loginName");
		String name=request.getParameter("name");
		int curPage=1;
		String temp=request.getParameter("curPage");
		if(temp!=null){
			curPage=Integer.parseInt(request.getParameter("curPage"));
		}
		try{
			Map map = service.findAllAdminByMostCon(loginName,name,curPage);
	  		ArrayList list=(ArrayList) map.get("list");
			Page pa=(Page) map.get("pa");
	  		request.setAttribute("curPage", pa.getCurPage());
	  		request.setAttribute("pageCount",pa.getPageCount());
	  		request.setAttribute("list", list);
			if((loginName!=null&&loginName.equals(""))||(name!=null&&name.equals(""))){
				request.setAttribute("loginName",loginName);
				request.setAttribute("name",name);
			}
	  		request.getRequestDispatcher("/base/admin/adminList.jsp").forward(request, response);
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
