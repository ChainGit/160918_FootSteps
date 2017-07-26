<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
</head>
<body>

	<a href="input.day02.action">测试一(Struts2 HelloWorld)</a>
	<br />
	<a href="context.day02.action?name=abc&age=10">测试二(ActionContext)</a>
	<br />
	<%
		//attribute和parameter不是一个东西
		request.setAttribute("address", "nanjing");
		out.println(request.getAttribute("address"));
		application.setAttribute("date", new Date());
	%>
	<br />
	<a href="aware.day02.action?name=abc&age=10">测试三(Aware)</a>&nbsp;
	<a href="aware2.day02.action?name=abc&age=10">测试三(Aware2)</a>
	<br />
	<a href="servlet.context.day02.action?name=abc&age=10">测试四(ServletActionContext)</a>
	<br />
	<a href="servlet.aware.day02.action?name=abc&age=10">测试五(ServletAware)</a>
	<br />


</body>
</html>