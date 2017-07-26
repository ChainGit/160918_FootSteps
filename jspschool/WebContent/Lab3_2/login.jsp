<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实验3_2：登陆后查看</title>
</head>
<body>
	<h2>实验3_2：登陆后查看</h2>
	<h4>LOGIN</h4>

	<c:if test="${sessionScope.loginName!=null}">
		<c:redirect url="show.jsp"></c:redirect>
	</c:if>

	<form action="" method="post">
		输入名字登陆： <input type="text" name="name" /> <input type="submit">
	</form>

	<c:set scope="session" var="loginName" value="${param.name}"></c:set>

</body>
</html>