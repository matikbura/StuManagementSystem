package servlet.teacher;

import exception.DaoException;
import exception.ServiceException;
import service.Impl.TeacherServiceImpl;
import service.TeacherIService;
import utils.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class CheckTeacherServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TeacherIService service = new TeacherServiceImpl();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String teacherNo=request.getParameter("teacherNo");
		String teacherName=request.getParameter("teacherName");
		Integer professional=null;
		if(request.getParameter("professional")!=null&&!request.getParameter("professional").equals("")){
			professional=new Integer(request.getParameter("professional"));
		}
		String phone=request.getParameter("phone");
		String subject=request.getParameter("subject");
		String email = request.getParameter("email");
		int curPage=1;
		String temp=request.getParameter("curPage");
		if(temp!=null){
			curPage=Integer.parseInt(request.getParameter("curPage"));
		}
		try{
			Map map = service.findAllTeacherByMostCon(teacherNo, teacherName, professional,phone,subject,email,curPage);
	  		ArrayList list=(ArrayList) map.get("list");
			Page pa=(Page) map.get("pa");
			if((teacherNo!=null&&!teacherNo.equals(""))||(teacherName!=null&&!teacherName.equals(""))||professional!=null||
					(phone!=null&&!phone.equals(""))||(subject!=null&&!subject.equals(""))||(email!=null&&!email.equals(""))){
				request.setAttribute("teacherNo",teacherNo);
				request.setAttribute("teacherName",teacherName);
				request.setAttribute("professional",professional);
				request.setAttribute("phone",phone);
				request.setAttribute("subject",subject);
				request.setAttribute("email",email);
			}
	  		request.setAttribute("curPage", pa.getCurPage());
	  		request.setAttribute("pageCount",pa.getPageCount());
	  		request.setAttribute("list", list);
	  		request.getRequestDispatcher("/base/teacher/teacherList.jsp").forward(request, response);
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
