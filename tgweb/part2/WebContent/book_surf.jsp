<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BOOK SURF</title>
</head>
<body>

	<%
		String bookId = request.getParameter("b");
		if (bookId == null || bookId.length() < 0) {
			response.sendRedirect("book.jsp");
			return;
		}

		Cookie[] cookies = request.getCookies();
		String books = null;
		Cookie bookCookie = null;
		if (cookies != null && cookies.length > 0)
			for (Cookie c : cookies)
				if (c.getName().equals("bookcookie")) {
					books = c.getValue();
					bookCookie = c;
				}

		if (books == null || bookCookie == null) {
			Cookie cookie = new Cookie("bookcookie", "");
			response.addCookie(cookie);
			response.sendRedirect("book.jsp");
			return;
		}

		String[] booksArr = books.split("&");
		int hasSuried = -1;
		if (booksArr.length == 5)
			hasSuried = 0;

		for (int i = 0; i < booksArr.length; i++)
			if (booksArr[i].equals(bookId)) {
				hasSuried = i;
				break;
			}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < booksArr.length; i++) {
			if (i == hasSuried)
				continue;
			String bookElement = booksArr[i];
			if (bookElement.length() > 0) {
				sb.append(bookElement);
				sb.append('&');
			}
		}
		sb.append(bookId);
		bookCookie.setValue(sb.toString());
		response.addCookie(bookCookie);
		response.sendRedirect("book.jsp");
	%>
</body>
</html>