package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class EncodeFilter implements Filter {
	public void destroy() {
		/*
		 * 包含初始化Filter时需要执行的代码，该代码执行一次
		 * */
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		request.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		chain.doFilter(request,response);

		res.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	}

	public void init(FilterConfig config) throws ServletException {
		/*
		 * 包含资源释放的代码，通常对init()中的初始化的资源执行收尾工作；
		 * */
	}
}
