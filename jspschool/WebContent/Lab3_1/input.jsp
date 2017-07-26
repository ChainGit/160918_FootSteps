<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>实验3_1：计算器</title>
</head>

<body>
	<h2>实验3_1：计算器</h2>
	<form action="result.jsp" method=post name=form>
		输入运算数、选择运算符号：<br /> <input type="text" name="numberOne" size=6 /> <select
			name="operator">
			<option value="+">加
			<option value="-">减
			<option value="*">乘
			<option value="/">除
		</select> <input type="text" name="numberTwo" size=6> <br /> <input
			type="submit" value="提交" name="submit">
	</form>
</body>
</html>
