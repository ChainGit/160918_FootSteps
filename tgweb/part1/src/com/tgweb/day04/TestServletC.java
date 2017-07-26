package com.tgweb.day04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServletC extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("TestServletC doGet");

		System.out.println(req.getAttribute("testRequestAttribute"));
		System.out.println(req.getSession().getAttribute("testSessionAttribute"));
		System.out.println(this.getServletContext().getAttribute("testContextAttribute"));

		try {
			PrintWriter pw = res.getWriter();
			pw.write("testServletC");
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
