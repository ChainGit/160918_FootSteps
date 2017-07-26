package com.tgweb.day06;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TokenVerify
 */
public class TokenVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TokenVerify() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String contextPath = request.getContextPath();
		response.getWriter().append("Served at: ").append(contextPath);

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String resultPath = contextPath + "/token/result.jsp";

		String value = request.getParameter("input");
		if (value == null || value.equals("")) {
			response.sendRedirect(resultPath + "?r=false");
			return;
		}

		HttpSession cliSession = request.getSession(false);
		if (cliSession == null) {
			response.sendRedirect(resultPath + "?r=false");
			return;
		}

		Object verifySess = cliSession.getAttribute("verify");
		String verifyPara = request.getParameter("verify");

		System.out.println(verifySess);
		System.out.println(verifyPara);

		if (verifySess == null || verifyPara == null || !verifyPara.equals(verifySess)) {
			response.sendRedirect(resultPath + "?r=false");
			return;
		}

		Object tokenSess = cliSession.getAttribute("token");
		String tokenPara = request.getParameter("token");

		System.out.println(tokenSess);
		System.out.println(tokenPara);

		if (tokenSess != null && tokenPara != null && tokenPara.equals(tokenSess))
			response.sendRedirect(resultPath + "?r=true");
		else
			response.sendRedirect(resultPath + "?r=false");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
