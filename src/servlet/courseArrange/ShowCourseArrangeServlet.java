package servlet.courseArrange;

import dao.CourseArrangeIDao;
import dao.CourseIDao;
import dao.Impl.CourseArrangeDaoImpl;
import dao.Impl.CourseDaoImpl;
import dao.Impl.TeacherDaoImpl;
import dao.TeacherIDao;
import domain.CourseArrange;
import exception.DaoException;
import exception.ServiceException;
import service.ClassIService;
import service.Impl.ClassServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowCourseArrangeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CourseArrangeIDao courseArrangeService = new CourseArrangeDaoImpl();
		CourseIDao courseService = new CourseDaoImpl();
		TeacherIDao teacherService = new TeacherDaoImpl();
		ClassIService classService = new ClassServiceImpl();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String arrangeNo=request.getParameter("arrangeNo");
		try{
			CourseArrange courseArrange=courseArrangeService.findCourseArrangeByArrangeNo(arrangeNo);
			List courseList = courseService.findAllCourse();
			List teacherList = teacherService.findAllTeacher();
			List classList = classService.findAllClasstbl();
			request.setAttribute("courseArrange",courseArrange);
			request.setAttribute("courseList",courseList);
			request.setAttribute("teacherList",teacherList);
			request.setAttribute("classList",classList);
			request.getRequestDispatcher("/courseArrange/courseArrangeShow.jsp").forward(request, response);
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
