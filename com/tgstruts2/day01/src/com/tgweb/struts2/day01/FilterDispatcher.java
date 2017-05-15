package com.tgweb.struts2.day01;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FilterDispatcher
 */
public class FilterDispatcher implements Filter {

	/**
	 * Default constructor.
	 */
	public FilterDispatcher() {
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

		// 使用Filter做MVC控制器
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String servletPath = req.getServletPath();
		String newPath = null;

		// System.out.println(servletPath);

		switch (servletPath) {
		case "/input.do.day01":
			newPath = "/WEB-INF/pages/day01/input.jsp";
			break;
		case "/show.do.day01":
			newPath = "/WEB-INF/pages/day01/show.jsp";
			doShowDoDay01(req, res);
			break;
		default:
			newPath = "/WEB-INF/pages/day01/error.jsp";
			break;
		}

		if (newPath != null) {
			request.getRequestDispatcher(newPath).forward(request, response);
			return;
		}

		// pass the request along the filter chain
		// chain.doFilter(request, response);
	}

	private void doShowDoDay01(HttpServletRequest request, HttpServletResponse response) {
		String goodName = request.getParameter("gn");
		String goodPrice = request.getParameter("gp");
		String goodDesc = request.getParameter("gd");

		long goodId = 1000;

		Good good = new Good(goodId, goodName, new Double(goodPrice), goodDesc);

		request.setAttribute("good", good);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
