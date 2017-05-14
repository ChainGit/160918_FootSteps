package com.chapter5;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Word extends HttpServlet {

	private static final long serialVersionUID = 9117683284702616940L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body><h2>实验5_1：分解句子</h2>");
		String str = request.getParameter("english");
		if (str == null || str.length() == 0)
			return;
		String[] word = str.split("[^a-zA-Z]+");
		int n = 0;
		try {
			for (int i = 0; i < word.length; i++) {
				if (word[i].length() >= 1) {
					n++;
					out.print("<br/>" + word[i]);
				}
			}
		} catch (NumberFormatException e) {
			out.print(out.printf(" ", e));
		}
		out.print("<h2>NUM:" + n);
		out.println("</body></html>");
	}
}
