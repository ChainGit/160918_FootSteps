package com.tgweb.day10;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class UserNameFilter
 */
public class UserNameFilter implements Filter {

	private FilterConfig fConfig;

	/**
	 * Default constructor.
	 */
	public UserNameFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		String paramUserName = request.getParameter("username");
		String initUserName = fConfig.getInitParameter("username");

		if (paramUserName == null || initUserName == null || paramUserName.length() < 1 || initUserName.length() < 1) {
			forwardToLoginPage(request, response);
			return;
		}

		if (initUserName.equals(paramUserName))
			chain.doFilter(request, response);
		else
			forwardToLoginPage(request, response);
	}

	private void forwardToLoginPage(ServletRequest request, ServletResponse response) {
		// TODO Auto-generated method stub
		MassageInfo msg = new MassageInfo();
		msg.setUsername("用户名不正确");
		request.setAttribute("msg", msg);
		RequestDispatcher rd = request.getRequestDispatcher("/filter/login.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.fConfig = fConfig;
	}

}
