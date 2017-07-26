package com.tgweb.day04;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServletA extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("TestServletA doGet");

		// 四个域对象
		// HttpServlet没有pageContext

		// request
		req.setAttribute("testRequestAttribute", "valueRequestAttribute");
		// session
		req.getSession().setAttribute("testSessionAttribute", "valueSessionAttribute");
		// application
		this.getServletContext().setAttribute("testContextAttribute", "valueContextAttribute");

		// 两次请求：一次返回重定向302，一次连接另一个
		// 地址栏有变化
		// 访问的是当前WEB站点的根目录
		// 前后request不是同一个对象
		try {
			res.sendRedirect("testServletC");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
