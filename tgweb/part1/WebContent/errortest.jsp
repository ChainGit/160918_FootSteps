<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page isErrorPage="true"%>
<%@ page session="false"%>
<html>
<head>
<meta charset="utf-8">
<title>ERROR TEST PAGE</title>
</head>

<body>
	<center>
		<%=new Date()%>

		<h3>
			--ERROR INFO-- <br>
			<%=exception == null ? exception : exception.getMessage()%>
		</h3>
	</center>
</body>
</html>