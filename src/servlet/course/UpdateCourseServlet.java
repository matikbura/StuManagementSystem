package servlet.course;

import domain.Course;
import exception.DaoException;
import exception.ServiceException;
import service.CourseIService;
import service.Impl.CourseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateCourseServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CourseIService service = new CourseServiceImpl();
		HttpSession session=request.getSession();
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
		try{
			Course course = new Course(courseNo,courseName,studyTime,grade,courseType,term);
			service.updateCourse(course);
	  		session.setAttribute("message", "修改课程信息成功！");
	  		request.getRequestDispatcher("ListAllCourseServlet").forward(request, response);
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
