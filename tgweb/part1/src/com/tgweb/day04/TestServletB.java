package com.tgweb.day04;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServletB extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("TestServletB doGet");

		System.out.println(req.getAttribute("testRequestAttribute"));
		System.out.println(req.getSession().getAttribute("testSessionAttribute"));
		System.out.println(this.getServletContext().getAttribute("testContextAttribute"));

		// 一次请求：地址栏没变化
		// 可以请求任意资源（包括外网资源）
		// 根目录是指：当前WEB"应用"的根目录
		// 前后两次request对象是同一个对象
		RequestDispatcher rd = req.getRequestDispatcher("/testServletC");
		try {
			rd.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
