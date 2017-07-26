package com.tgweb.day02;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class MyHttpServlet extends MyGenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		HttpServletRequest hsreq = null;
		HttpServletResponse hsres = null;
		if (req instanceof HttpServletRequest)
			hsreq = (HttpServletRequest) req;
		if (res instanceof HttpServletResponse)
			hsres = (HttpServletResponse) res;

		if (hsreq != null && hsres != null)
			service(hsreq, hsres);
	}

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (req.getMethod().equals("GET"))
			doGet(req, res);
		else if (req.getMethod().equals("POST"))
			doPost(req, res);

	}

	public abstract void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;

	public abstract void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;

}
