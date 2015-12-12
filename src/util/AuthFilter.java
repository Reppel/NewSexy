package util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by m on 2015/6/24.
 */
public class AuthFilter implements Filter{
	public void destroy() {

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
						 FilterChain filterChain) throws IOException, ServletException {//1,doFilter

		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;

		String currentURL = request.getRequestURI();


		String targetURL = currentURL.substring(currentURL.indexOf("/", 0), currentURL.length());

		HttpSession session = request.getSession(false);

		if ((!"login.jsp".equals(targetURL))||(!"explore.jsp".equals(targetURL))) {
			if (session == null || session.getAttribute("user_state") !="login") {
				System.out.println("request.getContextPath()=" + request.getContextPath());
				response.sendRedirect(request.getContextPath() + "welcomeServlet");
				return;
			}
		}

		filterChain.doFilter(request, response);//.µ÷ÓÃFilterChain¶ÔÏóµÄdoFilter·½·¨¡£Filter½Ó¿ÚµÄdoFilter·½·¨È¡Ò»¸öFilterChain¶ÔÏó×÷ÎªËüµÄÒ»¸ö²ÎÊý¡£ÔÚµ÷ÓÃ´Ë¶ÔÏóµÄdoFilter·½·¨Ê±£¬¼¤»îÏÂÒ»¸öÏà¹ØµÄ¹ýÂËÆ÷¡£Èç¹ûÃ»ÓÐÁíÒ»¸ö¹ýÂËÆ÷Óëservlet»òJSPÒ³Ãæ¹ØÁª£¬Ôòservlet»òJSPÒ³Ãæ±»¼¤»î¡£

	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}
}
