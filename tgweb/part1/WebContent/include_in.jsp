<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.tgweb.day01.Person"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>PAGE BBB</h2>

	<%
		Person p = new Person("小刚", 19);
	%>

	<%=request.getParameter("testParam")%>
	<%=request.getParameter("testParam2")%>
</body>
</html>