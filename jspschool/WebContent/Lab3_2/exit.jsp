<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EXIT PAGE</title>
</head>
<body>
	<h2>实验3_2：登陆后查看</h2>
	<h4>EXIT</h4>

	<%
		session.invalidate();
	%>

	<c:out value="您已退出"></c:out>

</body>
</html>