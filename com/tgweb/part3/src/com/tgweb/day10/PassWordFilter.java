package com.tgweb.day10;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class PassWordFilter implements Filter {

	private FilterConfig fConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.fConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String paramPassWord = request.getParameter("password");
		String initPassWord = fConfig.getInitParameter("password");

		if (paramPassWord == null || initPassWord == null || paramPassWord.length() < 1 || initPassWord.length() < 1) {
			forwardToLoginPage(request, response);
			return;
		}

		if (initPassWord.equals(paramPassWord))
			chain.doFilter(request, response);
		else
			forwardToLoginPage(request, response);
	}

	private void forwardToLoginPage(ServletRequest request, ServletResponse response) {
		// TODO Auto-generated method stub
		MassageInfo msg = new MassageInfo();
		msg.setPassword("ÃÜÂë²»ÕýÈ·");
		request.setAttribute("msg", msg);
		RequestDispatcher rd = request.getRequestDispatcher("/filter/login.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
