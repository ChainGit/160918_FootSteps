<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>INDEX</title>
</head>
<body>

	<a href="user.login.action">Login</a>
	<br />
	<a href="user.exit.action">Exit</a>
	<br />
	<a href="user.update.action">Update</a>
	<br />

	<%
		request.setAttribute("username1", "jack1");
		session.setAttribute("username2", "jack2");
		application.setAttribute("username3", "jack3");
	%>

</body>
</html>