<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>LOGIN PAGE</h2>
	<h4>Filter Test</h4>

	<form action="hello.jsp" method="post">
		<input type="text" name="username" value="${param.username }"/>
		${msg.username }
		<br/>
		<input type="password" name="password" value="${param.password }"/>
		${msg.password }
		<br/>
		<input type="submit" />
	</form>
</body>
</html>