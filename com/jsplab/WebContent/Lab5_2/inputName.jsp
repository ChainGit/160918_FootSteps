<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.io.File"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实验5_2：转发测试</title>
</head>
<body>
	<h2>实验5_2：转发测试</h2>
	<form action="<%=request.getContextPath()%>/postname" method="post">
		输入名字： <input type="text" name="name" /> <input type="submit" />
	</form>
</body>
</html>