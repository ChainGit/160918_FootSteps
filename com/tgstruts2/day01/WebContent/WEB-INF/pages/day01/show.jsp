<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show</title>
</head>
<body>

	商品ID：${requestScope.good.goodId }
	<br /> 商品名称：${requestScope.good.goodName }
	<br /> 商品价格：${requestScope.good.goodPrice }
	<br /> 商品描述：${requestScope.good.goodDesc }
	<br />

</body>
</html>