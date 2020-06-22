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

@WebServlet("/ListAllScoreServlet")
public class ListAllScoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ScoreIService scoreService = new ScoreServiceImpl();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        int curPage=1;
        String temp=request.getParameter("curPage");
        if(temp!=null){
            curPage=Integer.parseInt(request.getParameter("curPage"));
        }
        try{
            if(user!=null&&user.getUserType()==0){
                Map map=scoreService.findAllScore(curPage);
                ArrayList list=(ArrayList) map.get("list");
               List courseList = scoreService.findAllCourse();
                List classtblList = scoreService.findAllClasstbl();
                Page pa=(Page) map.get("pa");
                request.setAttribute("curPage", pa.getCurPage());
                request.setAttribute("pageCount",pa.getPageCount());
                request.setAttribute("list", list);
                request.setAttribute("courseList",courseList);
                request.setAttribute("classtblList",classtblList);
                request.getRequestDispatcher("/score/scoreList.jsp").forward(request, response);
            }else if(user!=null&&user.getUserType()==1){
                Map map = scoreService.findAllScoreByMostCon(null,null,user.getLoginName(),null, null,null,null,null,curPage);
                ArrayList list=(ArrayList) map.get("list");
                List courseList = scoreService.findAllCourseTeacherNo(user.getLoginName());
                List classtblList = scoreService.findAllClasstblByTeacherNo(user.getLoginName());
                Page pa=(Page) map.get("pa");
                request.setAttribute("curPage", pa.getCurPage());
                request.setAttribute("pageCount",pa.getPageCount());
                request.setAttribute("list", list);
                request.setAttribute("teacherNo",user.getLoginName());
                request.setAttribute("courseList",courseList);
                request.setAttribute("classtblList",classtblList);
                request.getRequestDispatcher("/score/scoreListByTeacherNo.jsp").forward(request, response);
            }else if(user!=null&&user.getUserType()==2){
                Map map = scoreService.findAllScoreByMostCon(null,null,null,null,user.getLoginName(),null,null,null,curPage);
                ArrayList list=(ArrayList) map.get("list");
                List courseList = scoreService.findAllCourseByStudentNo(user.getLoginName());
                Page pa=(Page) map.get("pa");
                request.setAttribute("curPage", pa.getCurPage());
                request.setAttribute("pageCount",pa.getPageCount());
                request.setAttribute("list", list);
                request.setAttribute("studentNo",user.getLoginName());
                request.setAttribute("courseList",courseList);
                request.getRequestDispatcher("/score/scoreListByStudentNo.jsp").forward(request, response);
            }else {
                request.setAttribute("message","发生未知错误");
                request.getRequestDispatcher("/index").forward(request,response);
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
