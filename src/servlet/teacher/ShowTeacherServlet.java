package servlet.teacher;

import domain.Teacher;
import exception.DaoException;
import exception.ServiceException;
import service.Impl.TeacherServiceImpl;
import service.TeacherIService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowTeacherServlet extends HttpServlet {
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
		try{
			Teacher teacher=service.findTeacherByTeacherNo(teacherNo);
			request.setAttribute("teacher",teacher);
			request.getRequestDispatcher("/base/teacher/teacherShow.jsp").forward(request, response);
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
