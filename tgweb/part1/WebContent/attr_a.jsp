<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="utf-8" import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>ATTR_A</title>
</head>

<body>
	<h2>
		ATTR_A:&nbsp;<%=new Date()%>
	</h2>
	<br>

	<%
		pageContext.setAttribute("testPageAttribute2", "valuePageAttribute2");
		request.setAttribute("testRequestAttribute2", "valueRequestAttribute2");
		session.setAttribute("testSessionAttribute2", "valueSessionAttribute2");
		application.setAttribute("testContextAttribute2", "valueContextAttribute2");
	%>

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

	<%
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/attr_c.jsp");
		rd.forward(request, response);
	%>

</body>
</html>