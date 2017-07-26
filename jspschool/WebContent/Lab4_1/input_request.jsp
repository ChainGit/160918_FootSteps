<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实验4_1：汽车信息</title>
</head>
<body>

	<h2>实验4_1：汽车信息</h2>
	<h4>REQUEST</h4>

	<jsp:useBean id="car" class="com.chapter4.Car" scope="request" />

	<form action="" method="post">
		汽车牌照： <input type="text" name="number" /> <br /> 汽车名称： <input
			type="text" name="name" /> <br /> 汽车生产日期： <input type="text"
			name="madeTime" /> <br /> <input type="submit" /> <br />
	</form>

	<jsp:setProperty property="*" name="car" />


	<c:if test="${param.number!=null}">
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
	</c:if>
</body>
</html>