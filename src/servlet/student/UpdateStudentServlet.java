package servlet.student;

import domain.Student;
import exception.DaoException;
import exception.ServiceException;
import service.Impl.StudentServiceImpl;
import service.StudentIService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateStudentServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentIService service = new StudentServiceImpl();
		HttpSession session=request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String studentNo = request.getParameter("studentNo");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String classNo = request.getParameter("classNo");
		try{
	  		Student student = service.findStudentByStudentNo(studentNo);
	  		student.setClassNo(studentNo);
	  		student.setName(name);
	  		student.setPassword(password);
	  		student.setAddress(address);
	  		student.setPhone(phone);
	  		student.setEmail(email);
	  		student.setClassNo(classNo);
	  		service.updateStudent(student);
	  		session.setAttribute("message", "修改学生信息成功！");
	  		request.getRequestDispatcher("ListAllStudentServlet").forward(request, response);
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
