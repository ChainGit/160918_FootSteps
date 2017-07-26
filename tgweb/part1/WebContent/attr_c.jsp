<%@page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8" import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>ATTR_C</title>
</head>

<body>
	<h2>
		ATTR_C:&nbsp;<%=new Date()%>
	</h2>
	<br>

	<%
		out.println(pageContext.getAttribute("testPageAttribute2"));
		out.println("<br>");
		out.println(request.getAttribute("testRequestAttribute2"));
		out.println("<br>");
		out.println(session.getAttribute("testSessionAttribute2"));
		out.println("<br>");
		out.println(application.getAttribute("testContextAttribute2"));
		out.println("<br>");
	%>


</body>
</html>