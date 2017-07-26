<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实验4_2：汽车信息</title>
</head>
<body>
	<h2>实验4_2：汽车信息</h2>
	<h4>SESSION</h4>

	<jsp:useBean id="car" class="com.chapter4.Car" scope="session" />

	<table border="1">
		<tr>
			<th>汽车牌号</th>
			<th>汽车名称</th>
			<th>汽车生产日期</th>
		</tr>
		<tr>
			<td><jsp:getProperty name="car" property="number" /></td>
			<td><jsp:getProperty name="car" property="name" /></td>
			<td><jsp:getProperty name="car" property="madeTime" /></td>
		</tr>
	</table>
</body>
</html>