package servlet.score;

import domain.Score;
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
import java.io.IOException;
import java.util.List;

@WebServlet("/ShowScoreServlet")
public class ShowScoreServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ScoreIService scoreService = new ScoreServiceImpl();
		String id = request.getParameter("id");
		StudentIService studentService = new StudentServiceImpl();
		CourseArrangeIService courseArrangeService = new CourseArrangeServiceImpl();
		try{
			Score score = scoreService.findScoreById(id);
			List studentList = studentService.findAllStudent();
			List courseArrangeList = courseArrangeService.findAllCourseArrange();
			request.setAttribute("studentList",studentList);
			request.setAttribute("courseArrangeList",courseArrangeList);
			request.setAttribute("score",score);
			request.getRequestDispatcher("/score/scoreShow.jsp").forward(request, response);
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
