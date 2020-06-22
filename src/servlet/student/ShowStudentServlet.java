package servlet.student;

import domain.Student;
import exception.DaoException;
import exception.ServiceException;
import service.ClassIService;
import service.Impl.ClassServiceImpl;
import service.Impl.StudentServiceImpl;
import service.StudentIService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowStudentServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ClassIService classService = new ClassServiceImpl();
		StudentIService service = new StudentServiceImpl();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String studentNo=request.getParameter("studentNo");
		try{
			List classList = classService.findAllClasstbl();
			Student student=service.findStudentByStudentNo(studentNo);
			request.setAttribute("student",student);
			request.setAttribute("classList",classList);
			request.getRequestDispatcher("/base/student/studentShow.jsp").forward(request, response);
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
