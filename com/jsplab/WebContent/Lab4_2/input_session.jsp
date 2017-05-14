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

	<form action="" method="post">
		汽车牌照： <input type="text" name="number" /> <br /> 汽车名称： <input
			type="text" name="name" /> <br /> 汽车生产日期： <input type="text"
			name="madeTime" /> <br /> <input type="submit" /> <br />
	</form>

	<jsp:setProperty property="*" name="car" />

	<a href="show_session.jsp">显示信息</a>



</body>
</html>