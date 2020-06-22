package servlet.score;

import domain.CourseArrange;
import domain.Score;
import domain.Student;
import exception.DaoException;
import exception.ServiceException;
import service.CourseArrangeIService;
import service.Impl.CourseArrangeServiceImpl;
import service.Impl.ScoreServiceImpl;
import service.Impl.StudentServiceImpl;
import service.ScoreIService;
import service.StudentIService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/AddScoreServlet")
public class AddScoreServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String studentNo = request.getParameter("studentNo");
		String arrangeNo = request.getParameter("arrangeNo");
		Integer scorev = null;
		if(!request.getParameter("score").equals("")){
			scorev = new Integer(request.getParameter("score"));
		}
		ScoreIService scoreService = new ScoreServiceImpl();
		try{
			if(scoreService.findScoreByValues(studentNo,arrangeNo)!=null){
				StudentIService studentService = new StudentServiceImpl();
				CourseArrangeIService courseArrangeService = new CourseArrangeServiceImpl();
				List studentList = studentService.findAllStudent();
				List courseArrangeList = courseArrangeService.findAllCourseArrange();
				request.setAttribute("studentList",studentList);
				request.setAttribute("courseArrangeList",courseArrangeList);
				session.setAttribute("message", "所要添加的成绩已存在！");
				request.getRequestDispatcher("/score/addScore.jsp").forward(request, response);
			}else{
				Student student = new Student();
				CourseArrange courseArrange = new CourseArrange();
				student.setStudentNo(studentNo);
				courseArrange.setArrangeNo(arrangeNo);
				Score score = new Score(null,student,courseArrange,scorev);
				scoreService.addScore(score);
		  		session.setAttribute("message", "成绩信息添加成功！");
		  		request.getRequestDispatcher("ListAllScoreServlet").forward(request, response);
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
