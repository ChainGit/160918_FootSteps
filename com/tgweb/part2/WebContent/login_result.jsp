<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>COOKIE TEST RESULT</title>
</head>
<body>

	<%
		Cookie[] cookies = request.getCookies();
		boolean hasLogined = false;
		if (cookies != null && cookies.length > 0)
			for (Cookie c : cookies) {
				if (c.getName().equals("tgweb") && c.getValue().equals("tgwebcookie")) {
					hasLogined = true;
					break;
				}
			}

		if (hasLogined)
			out.println("您已登陆");
		else
			response.sendRedirect("login.jsp");
	%>
</body>
</html>