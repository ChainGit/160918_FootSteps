package com.tgweb.basic.ajax;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetBook
 */
public class GetBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetBook() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		BookCartBean cart = (BookCartBean) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new BookCartBean();
			request.getSession().setAttribute("cart", cart);
		}

		StringBuffer sb = new StringBuffer("{\"books\":[");
		int mount = 0;
		int total = 0;
		for (Map.Entry<String, BookItem> p : cart.getBooks().entrySet()) {
			float price = p.getValue().getPrice();
			int count = p.getValue().getCount();
			mount += count;
			total += count * price;
			sb.append("{");
			sb.append("\"name\":");
			sb.append("\"" + p.getKey() + "\"");
			sb.append(",");
			sb.append("\"info\":");
			sb.append("[{");
			sb.append("\"price\":");
			sb.append("\"" + price + "\"");
			sb.append("},{");
			sb.append("\"count\":");
			sb.append(count);
			sb.append("}]");
			sb.append("},");
		}
		sb.append("],");
		sb.append("length:" + cart.getBooks().size() + ",");
		sb.append("mount:" + mount + ",");
		sb.append("total:" + total);
		sb.append("}");

		response.getWriter().write(sb.toString());
	}

}
