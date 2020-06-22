package servlet.person;

import domain.Admin;
import domain.Student;
import domain.Teacher;
import domain.User;
import exception.DaoException;
import exception.ServiceException;
import service.AdminIService;
import service.ClassIService;
import service.Impl.AdminServiceImpl;
import service.Impl.ClassServiceImpl;
import service.Impl.StudentServiceImpl;
import service.Impl.TeacherServiceImpl;
import service.StudentIService;
import service.TeacherIService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
@WebServlet("/ShowPersonalMessageServlet")
public class ShowPersonalMessageServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		try{
			if(user!=null&&user.getUserType()==0){
				AdminIService service = new AdminServiceImpl();
				Admin admin=service.findAdminByLoginName(user.getLoginName());
				request.setAttribute("admin",admin);
				request.getRequestDispatcher("/person/adminShow.jsp").forward(request, response);
			}
			else if(user!=null&&user.getUserType()==1){
				TeacherIService service = new TeacherServiceImpl();
				Teacher teacher=service.findTeacherByTeacherNo(user.getLoginName());
				request.setAttribute("teacher",teacher);
				request.getRequestDispatcher("/person/teacherShow.jsp").forward(request, response);
			}
			else if(user!=null&&user.getUserType()==2){
				StudentIService service = new StudentServiceImpl();
				ClassIService classService = new ClassServiceImpl();
				List classList = classService.findAllClasstbl();
				Student student=service.findStudentByStudentNo(user.getLoginName());
				request.setAttribute("student",student);
				request.setAttribute("classList",classList);
				request.getRequestDispatcher("/person/studentShow.jsp").forward(request, response);
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
