<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.tgweb.day01.Person"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>INDEX | JAVA WEB</title>
</head>
<body>

	<!-- JSP语法：模板元素、JSP表达式、JSP脚本片段、JSP声明、注释、异常 -->
	<%!int a = 0;%>

	<%
		Person p = new Person("小明", 21);
	%>

	<%-- JSP表达式,等价于out.print(new Date()) --%>
	<!--
	//输出的内容会被注释，但是语句仍然执行  
	<%=p.toString()%><br>
 	<%=new Date()%>
	-->

	<%
		//JSP的9个隐藏对象
		out.println("<br>");

		String name = request.getParameter("name");
		out.println(name);
		out.println("<br>");

		response.setCharacterEncoding("UTF-8");

		//可以获得其他8个内置隐藏对象
		//pageContext

		out.write(session.getId());
		out.println("<br>");

		out.println(application.getInitParameter("driver"));
		out.println("<br>");

		out.println(config.getInitParameter("param"));
		out.println("<br>");

		out.println(page.toString());
		out.println("<br>");

		out.println(pageContext.getAttribute("testPageAttribute"));
		out.println("<br>");
		out.println(request.getAttribute("testRequestAttribute"));
		out.println("<br>");
		out.println(session.getAttribute("testSessionAttribute"));
		out.println("<br>");
		out.println(application.getAttribute("testContextAttribute"));
		out.println("<br>");

		out.println(pageContext.getAttribute("testPageAttribute2"));
		out.println("<br>");
		out.println(request.getAttribute("testRequestAttribute2"));
		out.println("<br>");
		out.println(session.getAttribute("testSessionAttribute2"));
		out.println("<br>");
		out.println(application.getAttribute("testContextAttribute2"));
		out.println("<br>");

		//exception
	%>
</body>
</html>