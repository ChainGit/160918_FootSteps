<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LOGIN SESSION RESULT</title>
</head>
<body>

	<%
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login_session.jsp");
			return;
		}
		Object username = session.getAttribute("username");
		Object password = session.getAttribute("password");
	%>

	<table>
		<tbody>
			<tr>
				<td>会话状态</td>
				<td><%=session.isNew() ? "新的会话" : "旧的会话"%></td>
			</tr>
			<tr>
				<td>会话ID</td>
				<td><%=session.getId()%></td>
			</tr>
			<tr>
				<td>创建时间</td>
				<td><%=new Date(session.getCreationTime())%></td>
			</tr>
			<tr>
				<td>上次访问时间</td>
				<td><%=new Date(session.getLastAccessedTime())%></td>
			</tr>
			<tr>
				<td>最大不活动时间间隔</td>
				<td><%=session.getMaxInactiveInterval()%></td>
			</tr>
			<tr>
				<td>用户名</td>
				<td><%=username == null ? "" : username%></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><%=password == null ? "" : password%></td>
			</tr>
			<tr>
				<td><button name="signout" onclick="signout()">重新登陆</button></td>
			</tr>
		</tbody>
	</table>

	<script type="text/javascript">
		function signout() {
			window.location.href = "login_session.jsp?out=true";
		}
	</script>
</body>
</html>