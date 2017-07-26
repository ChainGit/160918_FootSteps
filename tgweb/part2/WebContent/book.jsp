<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Arrays" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BOOK SURIED</title>
</head>
<body>

	<a href="book_surf.jsp?b=1001">1001:Java</a>
	<br />
	<a href="book_surf.jsp?b=1002">1002:C</a>
	<br />
	<a href="book_surf.jsp?b=1003">1003:CPP</a>
	<br />
	<a href="book_surf.jsp?b=1004">1004:Python</a>
	<br />
	<a href="book_surf.jsp?b=1005">1005:JavaScript</a>
	<br />
	<a href="book_surf.jsp?b=1006">1006:JQuery</a>
	<br />
	<a href="book_surf.jsp?b=1007">1007:ObjectC</a>
	<br />
	<a href="book_surf.jsp?b=1008">1008:Ruby</a>
	<br />
	<br />
	<br />

	<%
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0)
			for (Cookie c : cookies)
				if (c.getName().equals("bookcookie")) {
					String cookieValue = c.getValue();
					if (cookieValue != null && cookieValue.length() > 0) {
						String[] books = cookieValue.split("&");
						out.println(Arrays.asList(books));
						out.println("<br/>");
					}
				}
	%>

</body>
</html>