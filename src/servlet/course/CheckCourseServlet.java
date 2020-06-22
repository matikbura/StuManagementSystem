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

public class CheckCourseServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CourseIService service = new CourseServiceImpl();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String courseNo = request.getParameter("courseNo");
		String courseName = request.getParameter("courseName");
		String studyTime = request.getParameter("studyTime");
		String grade = request.getParameter("grade");
		int courseType = 0;
		int term = 0;
		if(!request.getParameter("courseType").equals("")){
			courseType =new Integer(request.getParameter("courseType"));
		}
		if(!request.getParameter("term").equals("")){
			term =new  Integer(request.getParameter("term"));
		}
		int curPage=1;
		String temp=request.getParameter("curPage");
		if(temp!=null){
			curPage=Integer.parseInt(request.getParameter("curPage"));
		}
		try{
			Map map = service.findAllCourseByMostCon(courseNo,courseName,studyTime,grade,courseType,term,curPage);
	  		ArrayList list=(ArrayList) map.get("list");
			Page pa=(Page) map.get("pa");
	  		request.setAttribute("curPage", pa.getCurPage());
	  		request.setAttribute("pageCount",pa.getPageCount());
	  		request.setAttribute("list", list);
			if((courseNo!=null&&courseNo.equals(""))||(courseName!=null&&courseName.equals(""))
					||(studyTime!=null&&studyTime.equals(""))||(grade!=null&&grade.equals(""))||courseType!=0||term!=0){
				request.setAttribute("courseNo",courseNo);
				request.setAttribute("courseName",courseName);
				request.setAttribute("studyTime",studyTime);
				request.setAttribute("grade",grade);
				request.setAttribute("courseType",courseType);
				request.setAttribute("term",term);
			}
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
