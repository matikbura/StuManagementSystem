package servlet.courseArrange;

import domain.Classtbl;
import domain.Course;
import domain.CourseArrange;
import domain.Teacher;
import exception.DaoException;
import exception.ServiceException;
import service.CourseArrangeIService;
import service.Impl.CourseArrangeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateCourseArrangeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CourseArrangeIService service = new CourseArrangeServiceImpl();
		HttpSession session=request.getSession();
		Classtbl classtbl = new Classtbl();
		Teacher teacher = new Teacher();
		Course course = new Course();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String arrangeNo = request.getParameter("arrangeNo");
		String courseNo = request.getParameter("courseNo");
		String classNo = request.getParameter("classNo");
		String teacherNo= request.getParameter("teacherNo");
		String studyRoom = request.getParameter("studyRoom");
		teacher.setTeacherNo(teacherNo);
		course.setCourseNo(courseNo);
		classtbl.setClassNo(classNo);
		try{
			CourseArrange courseArrange = new CourseArrange(arrangeNo,course,classtbl,teacher,studyRoom);
			service.updateCourseArrange(courseArrange);
	  		session.setAttribute("message", "修改课程安排信息成功！");
	  		request.getRequestDispatcher("ListAllCourseArrangeServlet").forward(request, response);
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
