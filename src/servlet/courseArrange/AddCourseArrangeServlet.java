package servlet.courseArrange;

import dao.CourseIDao;
import dao.Impl.CourseDaoImpl;
import dao.Impl.TeacherDaoImpl;
import dao.TeacherIDao;
import domain.Classtbl;
import domain.Course;
import domain.CourseArrange;
import domain.Teacher;
import exception.DaoException;
import exception.ServiceException;
import service.ClassIService;
import service.CourseArrangeIService;
import service.Impl.ClassServiceImpl;
import service.Impl.CourseArrangeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AddCourseArrangeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CourseArrangeIService courseArrangeService = new CourseArrangeServiceImpl();
		Classtbl classtbl = new Classtbl();
		Teacher teacher = new Teacher();
		Course course = new Course();
		HttpSession session=request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String arrangeNo = request.getParameter("arrangeNo");
		String courseNo = request.getParameter("courseNo");
		String classNo = request.getParameter("classNo");
		String teacherNo = request.getParameter("teacherNo");
		String studyRoom = request.getParameter("studyRoom");
		teacher.setTeacherNo(teacherNo);
		course.setCourseNo(courseNo);
		classtbl.setClassNo(classNo);

		try{
			if(courseArrangeService.findCourseArrangeByArrangeNo(arrangeNo)!=null){
				session.setAttribute("message", "所要添加的课程安排已存在！");
				CourseIDao courseService = new CourseDaoImpl();
				TeacherIDao teacherService = new TeacherDaoImpl();
				ClassIService classService = new ClassServiceImpl();
					List courseList = courseService.findAllCourse();
					List teacherList = teacherService.findAllTeacher();
					List classList = classService.findAllClasstbl();
					request.setAttribute("courseList",courseList);
					request.setAttribute("teacherList",teacherList);
					request.setAttribute("classList",classList);
				request.getRequestDispatcher("/courseArrange/addCourseArrange.jsp").forward(request, response);
			}else{
				CourseArrange courseArrange = new CourseArrange(arrangeNo,course,classtbl,teacher,studyRoom);
				courseArrangeService.addCourseArrange(courseArrange);
		  		session.setAttribute("message", "课程安排信息添加成功！");
		  		request.getRequestDispatcher("ListAllCourseArrangeServlet").forward(request, response);
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
