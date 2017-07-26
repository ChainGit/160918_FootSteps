<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CHARSET TEST</title>
</head>
<body>

	<!-- 字符集和提交方法GET/POST的相互影响 -->
	<form action="charset_to.jsp" method="get">
		<input type="text" name="username" /> 
		<br /> 
		<input type="submit" name="submit" />
	</form>
</body>
</html>