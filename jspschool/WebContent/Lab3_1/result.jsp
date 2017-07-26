<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>实验3_1：计算器</title>
</head>

<body>
	<h2>实验3_1：计算器</h2>
	<%
		String numberOne = request.getParameter("numberOne");
		String numberTwo = request.getParameter("numberTwo");
		String operator = request.getParameter("operator");
		if (numberOne == null) {
			numberOne = "0";
		}
		if (numberTwo == null) {
			numberTwo = "0";
		}
		try {
			double a = Double.parseDouble(numberOne);
			double b = Double.parseDouble(numberTwo);
			double r = 0;
			if (operator.equals("+"))
				r = a + b;
			if (operator.equals("-"))
				r = a - b;
			if (operator.equals("*"))
				r = a * b;
			if (operator.equals("/"))
				r = a / b;
			out.println(a + "" + operator + "" + b + "=" + r);
		} catch (Exception e) {
			out.println("请输入数字字符");
		}
	%>
</body>
</html>
