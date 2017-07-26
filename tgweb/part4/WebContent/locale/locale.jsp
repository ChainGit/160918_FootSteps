<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LOCALE TEST</title>
</head>
<body>

	<c:set var="name" value="小明" scope="session"></c:set>
	<c:set var="salary" value="1234.56" scope="session"></c:set>
	<c:set var="date" value="<%=new Date()%>" scope="session"></c:set>

	<c:if test="${param.locale != null}">
		<c:set var="locale" value="${param.locale }" scope="session"></c:set>
		<fmt:setLocale value="${sessionScope.locale}" />
	</c:if>

	<fmt:setBundle basename="lang" />
	<fmt:message key="name"></fmt:message>:&nbsp;
	<c:out value="${name }"></c:out><br >
	<fmt:message key="salary"></fmt:message>:&nbsp;
	<fmt:formatNumber value="${salary }" type="currency"></fmt:formatNumber><br />
	<fmt:message key="date"></fmt:message>:&nbsp;
	<fmt:formatDate value="${date }" dateStyle="LONG"></fmt:formatDate><br />
	
	<br />
	<a href="locale.jsp?locale=zh-CN">中文</a>
	<span>|</span>
	<a href="locale.jsp?locale=en-US">ENGLISH</a>

</body>
</html>