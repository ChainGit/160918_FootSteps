<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>COOKIE TEST LOGIN</title>
</head>
<body>

	<%
		Cookie[] cookies = request.getCookies();
		boolean hasLogined = false;
		if (cookies != null && cookies.length > 0)
			for (Cookie c : cookies) {
				String cookieName = c.getName();
				String cookieValue = c.getValue();
				out.println(cookieName + ":" + cookieValue);
				if (cookieName.equals("tgweb") && cookieValue.equals("tgwebcookie")) {
					hasLogined = true;
					break;
				}
			}

		if (hasLogined) {
			out.println("正在跳转..");
			response.sendRedirect("login_result.jsp");
		} else {
			out.println("没有Cookie,正在创建..");
			Cookie cookie = new Cookie("tgweb", "tgwebcookie");
			cookie.setMaxAge(30);
			//cookie.setPath(request.getServletPath());
			response.addCookie(cookie);
		}
	%>

</body>
</html>