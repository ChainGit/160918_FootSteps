package com.tgweb.day02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginServlet extends MyGenericServlet {

	String usercfg = null;
	String passcfg = null;

	@Override
	public void init() {
		usercfg = this.getInitParameter("username");
		passcfg = this.getInitParameter("password");
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		String usercli = req.getParameter("user");
		String passcli = req.getParameter("pass");

		System.out.println("CONFIG: " + usercfg + "[" + passcfg + "]");
		System.out.println("CLIENT: " + usercli + "[" + passcli + "]");

		res.setContentType("text/html");

		PrintWriter pw = res.getWriter();

		if (usercli != null && passcli != null && usercli.equals(usercfg) && passcli.equals(passcfg))
			pw.write("<center><font size=\"12px\">hello</font></center>");
		else
			pw.write("<center><font size=\"12px\" color=\"red\">sorry</font></center>");

		pw.close();
	}

}
