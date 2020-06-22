package servlet.courseArrange;

import dao.CourseArrangeIDao;
import dao.CourseIDao;
import dao.Impl.CourseArrangeDaoImpl;
import dao.Impl.CourseDaoImpl;
import dao.Impl.TeacherDaoImpl;
import dao.TeacherIDao;
import domain.User;
import exception.DaoException;
import exception.ServiceException;
import service.ClassIService;
import service.Impl.ClassServiceImpl;
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

@WebServlet(name = "ListAllCourseArrangeServlet")
public class ListAllCourseArrangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        CourseArrangeIDao courseArrangeService = new CourseArrangeDaoImpl();
        CourseIDao courseService = new CourseDaoImpl();
        TeacherIDao teacherService = new TeacherDaoImpl();
        ClassIService classService = new ClassServiceImpl();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        int curPage=1;
        String temp=request.getParameter("curPage");
        if(temp!=null){
            curPage=Integer.parseInt(request.getParameter("curPage"));
        }
        try{
            Map map=courseArrangeService.findAllCourseArrange(curPage);
            ArrayList list=(ArrayList) map.get("list");


            Page pa=(Page) map.get("pa");
            request.setAttribute("curPage", pa.getCurPage());
            request.setAttribute("pageCount",pa.getPageCount());
            request.setAttribute("list", list);

            if(user!=null&&user.getUserType()==0){
                List courseList = courseService.findAllCourse();
                List teacherList = teacherService.findAllTeacher();
                List classList = classService.findAllClasstbl();
                request.setAttribute("courseList",courseList);
                request.setAttribute("teacherList",teacherList);
                request.setAttribute("classList",classList);
                List courseArrangeList = courseArrangeService.findAllCourseArrange();
                request.setAttribute("courseArrangeList",courseArrangeList);
                request.getRequestDispatcher("/courseArrange/courseArrangeList.jsp").forward(request, response);
            }else if(user!=null&&user.getUserType()==1){
                List courseArrangeList = courseArrangeService.findAllCourseArrangeByTeacherNo(user.getLoginName());
                List courseList = courseArrangeService.findAllCoursesByTeacherNo(user.getLoginName());
                List classList = courseArrangeService.findAllClassTblsByTeacherNo(user.getLoginName());
                request.setAttribute("courseList",courseList);
                request.setAttribute("classList",classList);
                request.setAttribute("courseArrangeList",courseArrangeList);
                request.setAttribute("teacherNo",user.getLoginName());
                request.getRequestDispatcher("/courseArrange/courseArrangeListByTeacherNo.jsp").forward(request, response);
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
