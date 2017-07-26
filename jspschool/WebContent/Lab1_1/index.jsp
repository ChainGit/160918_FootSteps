<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="ContentType" content="text/html;charset=utf-8">
<title>实验1_1：打印26个字母</title>
</head>

<body>
	<h2>实验1_1：打印26个字母</h2>

	<%
		for (int i = 'A'; i <= 'M'; i++)
			out.println((char) i);

		out.println("<br/>");

		for (int i = 'N'; i <= 'Z'; i++)
			out.println((char) i);
	%>
</body>

</html>
