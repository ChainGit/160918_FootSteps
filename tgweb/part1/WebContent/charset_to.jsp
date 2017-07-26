<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CHARSET TO TEST</title>
</head>
<body>
	<h2>CHARSET</h2>

	<%
		request.setCharacterEncoding("utf-8");
	%>

	<%
		String username = request.getParameter("username");
		out.write(username);
		out.println("<br/>");
		String username2 = new String(username.getBytes("ISO-8859-1"), "UTF-8");
		out.write(username2);
		out.println("<br/>");
	%>
</body>
</html>