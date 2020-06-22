package servlet;

import exception.DaoException;
import exception.ServiceException;
import service.AdminIService;
import service.Impl.AdminServiceImpl;
import service.Impl.StudentServiceImpl;
import service.Impl.TeacherServiceImpl;
import service.TeacherIService;
import service.StudentIService;
import domain.Admin;
import domain.Student;
import domain.Teacher;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminIService adminService = new AdminServiceImpl();
		TeacherIService teacherIService = new TeacherServiceImpl();
		StudentIService studentService = new StudentServiceImpl();
		HttpSession session=request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

  		String loginName=request.getParameter("loginName");
  		String password=request.getParameter("password");
  		try{
	  		Admin admin=adminService.login(loginName,password);
	  		Teacher teacher= teacherIService.login(loginName, password);
	  		Student student= studentService.login(loginName, password);
	  		User user=null;

	  		if(admin!=null||teacher!=null||student!=null){
	  			if(admin!=null){
	  				user=new User();
	  				user.setLoginName(admin.getLoginName());
	  				user.setName(admin.getName());
	  				user.setUserType(0);
	  			}else{
		  			if(teacher!=null){
		  				user=new User();
		  				user.setLoginName(teacher.getTeacherNo());
		  				user.setName(teacher.getTeacherName());
		  				user.setUserType(1);
		  			}else{
		  				user=new User();
		  				user.setLoginName(student.getStudentNo());
		  				user.setName(student.getName());
		  				user.setUserType(2);
		  			}
	  			}

				session.setAttribute("user",user);

				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}else{

				session.setAttribute("message","登录信息有误，请重新登录！！！");

				request.getRequestDispatcher("/login.jsp").forward(request, response);
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
