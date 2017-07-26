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

		// �ĸ������
		// HttpServletû��pageContext

		// request
		req.setAttribute("testRequestAttribute", "valueRequestAttribute");
		// session
		req.getSession().setAttribute("testSessionAttribute", "valueSessionAttribute");
		// application
		this.getServletContext().setAttribute("testContextAttribute", "valueContextAttribute");

		// ��������һ�η����ض���302��һ��������һ��
		// ��ַ���б仯
		// ���ʵ��ǵ�ǰWEBվ��ĸ�Ŀ¼
		// ǰ��request����ͬһ������
		try {
			res.sendRedirect("testServletC");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
