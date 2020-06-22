package servlet.score;

import domain.CourseArrange;
import domain.Score;
import domain.Student;
import exception.DaoException;
import exception.ServiceException;
import service.Impl.ScoreServiceImpl;
import service.ScoreIService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/UpdateScoreServlet")
public class UpdateScoreServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		ScoreIService scoreService = new ScoreServiceImpl();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Integer id = null ;
		if(!request.getParameter("id").equals("")){
			id = new Integer(request.getParameter("id"));
		}
		String studentNo = request.getParameter("studentNo");
		String arrangeNo= request.getParameter("arrangeNo");
		Integer scorex =null;
		if(!request.getParameter("score").equals("")){
			scorex = new Integer(request.getParameter("score"));
		}
		Student student = new Student();
		CourseArrange courseArrange = new CourseArrange();

		try{
			student.setStudentNo(studentNo);
			courseArrange.setArrangeNo(arrangeNo);
			Score score = new Score(id,student,courseArrange,scorex);
			scoreService.updateScore(score);
	  		session.setAttribute("message", "修改成绩信息成功！");
	  		request.getRequestDispatcher("ListAllScoreServlet").forward(request, response);
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
