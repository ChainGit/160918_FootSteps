<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.tgweb.day12.Person"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		session.setMaxInactiveInterval(1000);
		System.out.println(session.getId());
		Object obj = session.getAttribute("person");
		Person p = null;
		if (obj == null) {
			p = new Person("小明", 21);
			session.setAttribute("person", p);
			System.out.println("person created");
		} else {
			p = (Person) obj;
			System.out.println("person readed");
		}

		System.out.println(p);
	%>


</body>
</html>