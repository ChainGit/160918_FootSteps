package com.tgweb.day11;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -2684496515923472382L;

	private String initUserName;

	@Override
	public void init() throws ServletException {
		this.initUserName = getServletConfig().getInitParameter("username");
		if (initUserName == null || initUserName.length() < 1)
			initUserName = "TEST";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");

		String paramUserName = req.getParameter("username");
		if (paramUserName != null && paramUserName.equals(initUserName))
			req.getSession().setAttribute("LOGINSTATUS", "TRUE");
		else
			req.getSession().setAttribute("LOGINSTATUS", "FALSE");

		resp.sendRedirect(getServletContext().getContextPath() + "/filter2/index.html");
	}
}
