<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 
	<c:import url="https://www.baidu.com"></c:import>
	--%>

	<%-- 
	<c:redirect url="https://www.baidu.com"></c:redirect>
	--%>

	<%-- 
	<jsp:forward page="/jstl2/jstlbasic2.jsp"></jsp:forward>
	--%>

	<c:url value="/jstl2/jstlbasic2.jsp" scope="page" var="testUrl">
		<c:param name="age" value="100"></c:param>
	</c:url>

	<%-- 
	<jsp:forward page="/jstl2/jstlbasic2.jsp"></jsp:forward>
	<c:redirect url="${testUrl }"></c:redirect>
	--%>

</body>
</html>