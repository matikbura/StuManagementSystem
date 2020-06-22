package servlet.teacher;

import exception.DaoException;
import exception.ServiceException;
import service.Impl.TeacherServiceImpl;
import service.TeacherIService;
import domain.Teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
public class AddTeacherServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TeacherIService service = new TeacherServiceImpl();
		HttpSession session=request.getSession();
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
		try{
			if(service.findTeacherByTeacherNo(teacherNo)!=null){
				session.setAttribute("message", "所要添加的教师已存在！");
				request.getRequestDispatcher("/base/teacher/addTeacher.jsp").forward(request, response);
			}else{
				Teacher teacher=new Teacher(teacherNo,teacherName,password,
						professional,education,address,phone,email,subject);
				service.addTeacher(teacher);
		  		session.setAttribute("message", "教师信息添加成功！");
		  		request.getRequestDispatcher("ListAllTeacherServlet").forward(request, response);
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
