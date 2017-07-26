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

		// һ�����󣺵�ַ��û�仯
		// ��������������Դ������������Դ��
		// ��Ŀ¼��ָ����ǰWEB"Ӧ��"�ĸ�Ŀ¼
		// ǰ������request������ͬһ������
		RequestDispatcher rd = req.getRequestDispatcher("/testServletC");
		try {
			rd.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
