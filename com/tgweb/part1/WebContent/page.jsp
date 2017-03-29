<%@ page language="java"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page pageEncoding="utf-8"%>
<%@ page import="java.util.Date"%>
<%-- 
<%@ page errorPage="errortest.jsp"%>
--%>
<html>
<head>
<meta charset="utf-8">
<title>PAGE TEST</title>
</head>

<body>

	<%=new Date()%>

	<%
		int a = 10 / 0;
	%>
</body>
</html>
