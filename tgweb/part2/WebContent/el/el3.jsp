<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EL TEST 3</title>
</head>
<body>

	${sessionScope.person.name } ${sessionScope.person.age + 20 }

	<br />

	<%
		com.tgweb.day07.Person person = (com.tgweb.day07.Person) session.getAttribute("person");
		out.println(person.getName() + " " + person.getAge() + 20);
		out.println("<br/>");
	%>

</body>
</html>