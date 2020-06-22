package filter;

import domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
public class TeacherFilter  implements Filter{
	public void init(FilterConfig arg0) throws ServletException {
		/*
		 * 包含初始化Filter时需要执行的代码，该代码执行一次
		 * */
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session=req.getSession();

		User user=(User)session.getAttribute("user");

		if(user!=null&&(user.getUserType()==1||user.getUserType()==0)){
			chain.doFilter(req, res);
		}else if(user!=null&&user.getUserType()==2){

			session.setAttribute("message","对不起，只有管理员和教师才有操作该功能的权限！");
			res.sendRedirect(req.getContextPath() + "/index.jsp");
		}else{

			session.setAttribute("message","对不起，只有登录后才能访问系统！");
			res.sendRedirect(req.getContextPath() + "/login.jsp");
		}
	}
	public void destroy() {
		/*
		 * 包含资源释放的代码，通常对init()中的初始化的资源执行收尾工作；
		 * */
	}
}
