<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		application.setAttribute("name", "123");
		application.setAttribute("name", "abc");

		request.setAttribute("name", "123");
		request.setAttribute("name", "ABC");

		session.setAttribute("name", "123");
		session.setAttribute("name", "ppp");

		System.out.println("application:" + application.getAttribute("name"));
		System.out.println("session:" + session.getAttribute("name"));
		System.out.println("request:" + request.getAttribute("name"));

		request.removeAttribute("name");
		session.removeAttribute("name");
		application.removeAttribute("name");

		session.invalidate();
	%>

</body>
</html>