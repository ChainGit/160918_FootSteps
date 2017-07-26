package com.tgweb.basic.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddBook
 */
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddBook() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		BookCartBean cart = (BookCartBean) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new BookCartBean();
			request.getSession().setAttribute("cart", cart);
		}

		String bookName = request.getParameter("name");
		String bookPrice = request.getParameter("price");

		BookItem item = cart.getBookItem(bookName);
		int countOld = 0;
		if (item != null)
			countOld = item.getCount();
		cart.setBookItem(bookName, new BookItem(Float.parseFloat(bookPrice)));
		if (cart.getBookItem(bookName).getCount() == countOld + 1)
			response.getWriter().write("{msg:1}");
		else
			response.getWriter().write("{msg:0}");
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
