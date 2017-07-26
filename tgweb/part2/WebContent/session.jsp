<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SESSION TEST</title>
</head>
<body>

	<%
		out.println("已创建session,可以被同应用的其他JSP和Servlet共享");
		out.println(session.getId());
		out.println(session.getMaxInactiveInterval());
	%>

</body>
</html>