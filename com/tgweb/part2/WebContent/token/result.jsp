<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TOKEN RESULT</title>
</head>
<body>

	<%
		String result = request.getParameter("r");
		session.removeAttribute("token");
		if (result != null && result.equals("true")) {
			out.println("OK");
		} else
			out.println("ERR");
	%>

</body>
</html>