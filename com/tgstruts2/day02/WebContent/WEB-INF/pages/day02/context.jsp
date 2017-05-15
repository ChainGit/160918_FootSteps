<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Context</title>
</head>
<body>

	<%
		//经过Struts2的修饰后的request
		out.println(request);
	%>


	<p>${request }</p>

	<br /> name: ${parameters.name[0] } ; ${param.name }
	<br /> age: ${parameters.age[0] } ; ${param.age }
	<br /> address: ${address } ; ${requestScope.address } ;
	<br />
	<%
		Enumeration em = request.getAttributeNames();
		while (em.hasMoreElements())
			out.println(em.nextElement() + "<br/>");
	%>
	<br /> myparam: ${parameters.myparam } ; ${myparam }
	<br />
	<br /> date: ${date } ; ${applicationScope.date }
	<br /> reqKey: ${reqKey } ; ${requestScope.reqKey }
	<br /> sessKey: ${sessKey } ; ${sessionScope.sessKey }
	<br /> appKey: ${appKey } ; ${applicationScope.appKey }
	<br />
</body>
</html>