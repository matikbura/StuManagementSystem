package servlet.score;

import domain.User;
import exception.DaoException;
import exception.ServiceException;
import service.Impl.ScoreServiceImpl;
import service.ScoreIService;
import utils.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@WebServlet("/CheckScoreServlet")
public class CheckScoreServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ScoreIService scoreService = new ScoreServiceImpl();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String courseNo=request.getParameter("courseNo");
		String classNo=request.getParameter("classNo");
		String teacherNo=request.getParameter("teacherNo");
		String teacherName=request.getParameter("teacherName");
		String studentNo=request.getParameter("studentNo");
		String name=request.getParameter("name");
		Integer scoreMin=null;
		Integer scoreMax=null;

		if(!request.getParameter("scoreMin").equals("")){
			scoreMin = new Integer(request.getParameter("scoreMin"));
		}
		if(!request.getParameter("scoreMax").equals("")){
			scoreMax = new Integer(request.getParameter("scoreMax"));
		}
		int curPage=1;
		String temp=request.getParameter("curPage");
		if(temp!=null){
			curPage=Integer.parseInt(request.getParameter("curPage"));
		}

		try{
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			if(user!=null&&user.getUserType()==0){
				List courseList = scoreService.findAllCourse();
				List classtblList = scoreService.findAllClasstbl();
				request.setAttribute("courseList",courseList);
				request.setAttribute("classtblList",classtblList);
				Map map = scoreService.findAllScoreByMostCon(courseNo,classNo,teacherNo,teacherName,studentNo,name,scoreMin,scoreMax,curPage);
				ArrayList list=(ArrayList) map.get("list");
				Page pa=(Page) map.get("pa");
				if((classNo!=null&&!classNo.equals(""))||(courseNo!=null&&!courseNo.equals(""))||(teacherNo!=null&&!teacherNo.equals(""))
				||(teacherName!=null&&!teacherName.equals(""))||(studentNo!=null&&!studentNo.equals(""))||(name!=null&&!name.equals(""))
				||(scoreMin!=null)||(scoreMax!=null)){
					request.setAttribute("courseNo",courseNo);
					request.setAttribute("classNo",classNo);
					request.setAttribute("teacherNo",teacherNo);
					request.setAttribute("teacherName",teacherName);
					request.setAttribute("studentNo",studentNo);
					request.setAttribute("name",name);
					request.setAttribute("scoreMin",scoreMin);
					request.setAttribute("scoreMax",scoreMax);
				}

				request.setAttribute("curPage", pa.getCurPage());
				request.setAttribute("pageCount",pa.getPageCount());
				request.setAttribute("list", list);
				request.getRequestDispatcher("/score/scoreList.jsp").forward(request, response);
			}
	  		else if(user!=null&&user.getUserType()==1){
				List courseList = scoreService.findAllCourseTeacherNo(user.getLoginName());
				List classtblList = scoreService.findAllClasstblByTeacherNo(user.getLoginName());
				request.setAttribute("courseList",courseList);
				request.setAttribute("classtblList",classtblList);
				Map map = scoreService.findAllScoreByMostCon(courseNo,classNo,user.getLoginName(),teacherName,studentNo,name,scoreMin,scoreMax,curPage);
				ArrayList list=(ArrayList) map.get("list");
				Page pa=(Page) map.get("pa");
				if((classNo!=null&&!classNo.equals(""))||(courseNo!=null&&!courseNo.equals(""))||(studentNo!=null&&!studentNo.equals(""))||(name!=null&&!name.equals(""))
						||(scoreMin!=null)||(scoreMax!=null)){
					request.setAttribute("courseNo",courseNo);
					request.setAttribute("classNo",classNo);
					request.setAttribute("studentNo",studentNo);
					request.setAttribute("name",name);
					request.setAttribute("scoreMin",scoreMin);
					request.setAttribute("scoreMax",scoreMax);
				}
				request.setAttribute("curPage", pa.getCurPage());
				request.setAttribute("pageCount",pa.getPageCount());
				request.setAttribute("list", list);
				request.getRequestDispatcher("/score/scoreListByTeacherNo.jsp").forward(request, response);
			}else if(user!=null&&user.getUserType()==2){
				if((courseNo!=null&&!courseNo.equals(""))||(teacherNo!=null&&!teacherNo.equals(""))||(teacherName!=null&&!teacherName.equals(""))||(scoreMin!=null)||(scoreMax!=null)){
					request.setAttribute("courseNo",courseNo);
					request.setAttribute("teacherNo",teacherNo);
					request.setAttribute("teacherName",teacherName);
					request.setAttribute("scoreMin",scoreMin);
					request.setAttribute("scoreMax",scoreMax);
				}
				List courseList = scoreService.findAllCourseByStudentNo(user.getLoginName());
				List classtblList = scoreService.findAllClasstblByStudentNo(user.getLoginName());
				request.setAttribute("courseList",courseList);
				request.setAttribute("classtblList",classtblList);
				Map map = scoreService.findAllScoreByMostCon(courseNo,classNo,teacherNo,teacherName,user.getLoginName(),name,scoreMin,scoreMax,curPage);
				ArrayList list=(ArrayList) map.get("list");
				Page pa=(Page) map.get("pa");
				request.setAttribute("curPage", pa.getCurPage());
				request.setAttribute("pageCount",pa.getPageCount());
				request.setAttribute("list", list);
				request.getRequestDispatcher("/score/scoreListByStudentNo.jsp").forward(request, response);
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
