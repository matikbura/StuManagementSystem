package servlet.courseArrange;

import domain.User;
import exception.DaoException;
import exception.ServiceException;
import service.ClassIService;
import service.CourseArrangeIService;
import service.CourseIService;
import service.Impl.ClassServiceImpl;
import service.Impl.CourseArrangeServiceImpl;
import service.Impl.CourseServiceImpl;
import service.Impl.TeacherServiceImpl;
import service.TeacherIService;
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

public class CheckCourseArrangeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CourseArrangeIService courseArrangeService = new CourseArrangeServiceImpl();
		CourseIService courseService = new CourseServiceImpl();
		ClassIService classService = new ClassServiceImpl();
		TeacherIService teacherService = new TeacherServiceImpl();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String arrangeNo = request.getParameter("arrangeNo");
		String courseNo = request.getParameter("courseNo");
		String classNo = request.getParameter("classNo");
		String teacherNo = request.getParameter("teacherNo");
		System.out.println(arrangeNo);
		int curPage=1;
		String temp=request.getParameter("curPage");
		if(temp!=null){
			curPage=Integer.parseInt(request.getParameter("curPage"));
		}
		try{
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			if((arrangeNo!=null&&!arrangeNo.equals(""))||(courseNo!=null&&!courseNo.equals(""))
					||(classNo!=null&&!classNo.equals(""))||(teacherNo!=null&&!teacherNo.equals(""))){
				request.setAttribute("arrangeNo",arrangeNo);
				request.setAttribute("courseNo",courseNo);
				request.setAttribute("classNo",classNo);
				request.setAttribute("teacherNo",teacherNo);

			}
			if(user!=null&&user.getUserType()==0){
				Map map = courseArrangeService.findAllCourseArrangeByMostCon(arrangeNo,courseNo,classNo,teacherNo,curPage);
				ArrayList list=(ArrayList) map.get("list");
				Page pa=(Page) map.get("pa");
				request.setAttribute("curPage", pa.getCurPage());
				request.setAttribute("pageCount",pa.getPageCount());
				request.setAttribute("list", list);
				List courseList = courseService.findAllCourse();
				List teacherList = teacherService.findAllTeacher();
				List classList = classService.findAllClasstbl();
				List courseArrangeList = courseArrangeService.findAllCourseArrange();
				request.setAttribute("courseList",courseList);
				request.setAttribute("teacherList",teacherList);
				request.setAttribute("classList",classList);
				request.setAttribute("courseArrangeList",courseArrangeList);
				request.getRequestDispatcher("/courseArrange/courseArrangeList.jsp").forward(request, response);
			}
	  		else if(user!=null&&user.getUserType()==1){
				request.setAttribute("teacherNo",user.getLoginName());
				Map map = courseArrangeService.findAllCourseArrangeByMostCon(arrangeNo,courseNo,classNo,teacherNo,curPage);
				ArrayList list=(ArrayList) map.get("list");
				Page pa=(Page) map.get("pa");
				request.setAttribute("curPage", pa.getCurPage());
				request.setAttribute("pageCount",pa.getPageCount());
				request.setAttribute("list", list);
				List courseArrangeList = courseArrangeService.findAllCourseArrangeByTeacherNo(user.getLoginName());
				List courseList = courseArrangeService.findAllCoursesByTeacherNo(user.getLoginName());
				List classList = courseArrangeService.findAllClassTblsByTeacherNo(user.getLoginName());
				request.setAttribute("courseList",courseList);
				request.setAttribute("classList",classList);
				request.setAttribute("courseArrangeList",courseArrangeList);
				request.setAttribute("teacherNo",user.getLoginName());
				request.getRequestDispatcher("/courseArrange/courseArrangeListByTeacherNo.jsp").forward(request, response);
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
