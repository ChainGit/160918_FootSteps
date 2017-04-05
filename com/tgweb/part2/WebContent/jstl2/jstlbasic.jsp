<%@page import="com.tgweb.day07.Person"%>
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

	<%
		request.setAttribute("book", "<<JAVA>>");
	%>

	${requestScope.book }

	<c:out value="${requestScope.book }"></c:out>
	<c:out value="${requestScope.book }" escapeXml="true"></c:out>
	<c:out value="${requestScope.book }" escapeXml="false"></c:out>
	<c:out value="${param.book }" default="${requestScope.book }"></c:out>

	<br />

	<c:set var="name1" value="123" scope="page"></c:set>
	<%
		pageContext.setAttribute("name2", "abc");
	%>

	${pageSocpe.name1 } ${pageSocpe.name2 }

	<br />

	<%
		Person person = new Person("xiaoming", 22);
		request.setAttribute("person", person);
	%>
	${requestScope.person.name } ${requestScope.person.age }

	<c:set target="${requestScope.person }" property="name"
		value="${param.name }"></c:set>
	<c:set target="${requestScope.person }" property="age"
		value="${param.age }"></c:set>

	${requestScope.person.name } ${requestScope.person.age }

</body>
</html>