<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tgweb.day07.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set var="age" value="11" scope="page"></c:set>
	<c:if test="${pageScope.age < 18}">未成年</c:if>
	<c:if test="${pageScope.age >= 18}">成年</c:if>
	<c:if test="${param.age < 18 }">未成年</c:if>
	<c:if test="${param.age >= 18 }">成年</c:if>
	<c:if var="isOk" scope="page" test="true">OKOK</c:if>
	<c:out value="${pageScope.isOk }"></c:out>

	<br />

	<%
		List<Person> pers = new ArrayList<Person>();
		pers.add(new Person("赵强", 22));//0
		pers.add(new Person("王刚", 21));//1
		pers.add(new Person("李明", 23));//2
		pers.add(new Person("孙高", 19));//3
		pers.add(new Person("周天", 22));//4
		request.setAttribute("pers", pers);

		Map<String, Person> pers2 = new HashMap<String, Person>();
		pers2.put("a", pers.get(0));
		pers2.put("b", pers.get(1));
		pers2.put("c", pers.get(2));
		pers2.put("d", pers.get(3));
		pers2.put("e", pers.get(4));
		request.setAttribute("pers2", pers2);

		String[] strs = new String[] { "a", "b", "c" };
		request.setAttribute("strs", strs);
	%>

	<c:forEach items="${requestScope.pers }" var="i" begin="1" end="4"
		step="2" varStatus="s">
		${s.index } ${s.count } ${s.first } ${s.last } ${i.name } ${i.age } <br />
	</c:forEach>

	<c:forEach items="${requestScope.pers2 }" var="i" begin="1" end="4"
		step="2" varStatus="s">
		${s.index } ${s.count } ${s.first } ${s.last } ${i.key } ${i.value.name } ${i.value.age } <br />
	</c:forEach>

	<c:forEach items="${requestScope.strs }" var="i">
		${i }
	</c:forEach>

	<br />

	<c:forEach items="${pageContext.request.attributeNames }" var="i">
		${i } <br />
	</c:forEach>

	<%-- 类似String.split() --%>
	<c:set value="a,b,c;d.e,f;g,h,i;j" var="tokens" scope="request"></c:set>
	<c:forTokens var="i" items="${requestScope.tokens }" delims=";">
		${i }<br />
	</c:forTokens>
</body>
</html>