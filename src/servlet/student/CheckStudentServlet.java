package servlet.student;

import exception.DaoException;
import exception.ServiceException;
import service.Impl.StudentServiceImpl;
import service.StudentIService;
import utils.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class CheckStudentServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentIService service = new StudentServiceImpl();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String studentNo = request.getParameter("studentNo");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String classNo = request.getParameter("classNo");
		int curPage=1;
		String temp=request.getParameter("curPage");
		if(temp!=null){
			curPage=Integer.parseInt(request.getParameter("curPage"));
		}
		try{
			Map map = service.findAllStudentByMostCon(studentNo,name,address,phone,email,classNo,curPage);
	  		ArrayList list=(ArrayList) map.get("list");
			Page pa=(Page) map.get("pa");
	  		request.setAttribute("curPage", pa.getCurPage());
	  		request.setAttribute("pageCount",pa.getPageCount());
	  		request.setAttribute("list", list);
			if((studentNo!=null&&!studentNo.equals(""))||(name!=null&&!name.equals(""))||(address!=null&&!address.equals(""))||
					(phone!=null&&!phone.equals(""))||(email!=null&&!email.equals(""))||(classNo!=null&&!classNo.equals(""))){
				System.out.println(classNo);
				request.setAttribute("studentNo",studentNo);
				request.setAttribute("name",name);
				request.setAttribute("address",address);
				request.setAttribute("phone",phone);
				request.setAttribute("email",email);
				request.setAttribute("classNo",classNo);
			}
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
