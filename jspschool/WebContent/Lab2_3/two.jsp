<%@page contentType="text/html;charset=utf-8"%>
<html>
<head>
<meta charset="utf-8">
<title>实验2_3：输入的数字判断</title>
</head>

<body>

	<h2>实验2_3：输入的数字判断</h2>
	<h4>TWO PAGE</h4>

	<jsp:include page="head.txt" />


	<%
		out.println("request的参数值:" + request.getParameter("num"));
	%>
</body>
</html>