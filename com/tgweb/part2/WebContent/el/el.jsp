<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EL TEST</title>
</head>
<body>

	<form action="el.jsp" method="post">
		<input type="text" name="input" value="<%=request.getParameter("input") == null ? "" : request.getParameter("input")%>" />
		<br /> 
		<input type="text" name="input" value="${param.input}" /> 
		<br /> 
		<input type="submit" />
	</form>
	
	<%
		out.println(request.getParameter("input"));
		out.println("<br/>");
	%>
	
	${param.input }

</body>
</html>