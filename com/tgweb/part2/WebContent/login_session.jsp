<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LOGIN SESSION TEST</title>
</head>
<body>

	<%
		HttpSession session = request.getSession(false);

		String loginOut = request.getParameter("out");
		if (loginOut != null && loginOut.equals("true"))
			session.invalidate();

		if (session != null) {
			String url = "result_session.jsp";
			String eUrl = response.encodeURL(url);
			out.println(eUrl);
			out.println("<br/>");
			response.sendRedirect(eUrl);
			return;
		}

		session = request.getSession();
	%>

	<table>
		<tbody>
			<tr>
				<td>请输入用户名</td>
				<td><input type="text" id="username" /></td>
			</tr>
			<tr>
				<td>请输入密码</td>
				<td><input type="password" id="password" /></td>
			</tr>
			<tr>
				<td><button name="reset" onclick="reset()">重置</button></td>
				<td><button name="login" onclick="login()">登陆</button></td>
			</tr>
		</tbody>
	</table>

	<script type="text/javascript">
		var username = document.getElementById("username");
		var password = document.getElementById("password");

		function reset() {
			username.value = "";
			password.value = "";
		}

		function login() {
			var uContent = username.value;
			var pContent = password.value;
			if (uContent == null || uContent.length < 1 || pContent == null
					|| pContent.length < 1)
				return;

			window.location.href = "result_session.jsp";
		}
	</script>

</body>
</html>