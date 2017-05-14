<%@page contentType="text/html;charset=utf-8"%>
<html>
<head>
<meta charset="utf-8">
<title>实验2_3：输入的数字判断E</title>
</head>

<body>
	<h2>实验2_3：输入的数字判断</h2>
	<h4>ERROR PAGE</h4>

	<%
		out.println("request的参数值:" + request.getParameter("num"));
	%>
</body>
</html>