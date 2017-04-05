<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TOKEN SUBMIT</title>
</head>
<body>

	<%
		String token = Long.toString(new Date().getTime());
		session.setAttribute("token", token);
		session.setAttribute("verify", "123");
	%>

	<form action="<%=request.getContextPath()%>/tokenVerify" method="post">
		<input type="hidden" name="token" value="<%=token%>" /> <input
			type="text" name="input" /><br /> <input type="text" name="verify" /><br />
		<input type="submit" value="Submit"><br />
	</form>

</body>
</html>