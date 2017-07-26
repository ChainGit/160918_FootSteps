<%@ page contentType="text/html" pageEncoding="UTf-8"%>
<html>
<head>
<meta http-equiv="ContentType" content="text/html;charset=utf-8">
<title>实验2_2：INCLUDE测试</title>
</head>

<body>
	<h2>实验2_2：INCLUDE测试</h2>

	<jsp:include page="hello.txt"></jsp:include>
	<br />
	<%@include file="hello.txt"%>
</body>

</html>