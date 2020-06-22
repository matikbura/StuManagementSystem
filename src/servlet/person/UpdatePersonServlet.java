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

@WebServlet("/UpdatePersonServlet")
public class UpdatePersonServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		User user = (User)session.getAttribute("user");
		try{
			if(user!=null&&user.getUserType()==0){
				AdminIService service = new AdminServiceImpl();
				String loginName=request.getParameter("loginName");
				String name=request.getParameter("name");
				String password=request.getParameter("password");
				Admin admin = service.findAdminByLoginName(loginName);
				admin.setLoginName(loginName);
				admin.setName(name);
				admin.setPassword(password);
				service.updateAdmin(admin);
				request.setAttribute("message","信息修改成功");
			}
			else if(user!=null&&user.getUserType()==1){
				TeacherIService service = new TeacherServiceImpl();
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				String teacherNo=request.getParameter("teacherNo");
				String teacherName=request.getParameter("teacherName");
				String password=request.getParameter("password");
				Integer professional=new Integer(request.getParameter("professional"));
				String education=request.getParameter("education");
				String address=request.getParameter("address");
				String phone=request.getParameter("phone");
				String email=request.getParameter("email");
				String subject=request.getParameter("subject");
					Teacher teacher=service.findTeacherByTeacherNo(teacherNo);
					teacher.setTeacherNo(teacherNo);
					teacher.setTeacherName(teacherName);
					teacher.setPassword(password);
					teacher.setProfessional(professional);
					teacher.setEducation(education);
					teacher.setAddress(address);
					teacher.setPhone(phone);
					teacher.setEmail(email);
					teacher.setSubject(subject);
					service.updateTeacher(teacher);
					session.setAttribute("message", "修改信息成功！");
			}
			else if(user!=null&&user.getUserType()==2){
				StudentIService service = new StudentServiceImpl();
				ClassIService classService = new ClassServiceImpl();
				List classList = classService.findAllClasstbl();
				Student student=service.findStudentByStudentNo(user.getLoginName());
				String studentNo = request.getParameter("studentNo");
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String address = request.getParameter("address");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				student.setStudentNo(studentNo);
				student.setName(name);
				student.setPassword(password);
				student.setAddress(address);
				student.setPhone(phone);
				student.setEmail(email);
				service.updateStudent(student);
				student=service.findStudentByStudentNo(user.getLoginName());
				session.setAttribute("message", "修改信息成功！");
				request.setAttribute("student",student);
				request.setAttribute("classList",classList);
			}
	  		request.getRequestDispatcher("ShowPersonalMessageServlet").forward(request, response);
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
