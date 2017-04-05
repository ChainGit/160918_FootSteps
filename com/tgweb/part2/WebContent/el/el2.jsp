<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EL TEST 2</title>
</head>
<body>

	<jsp:useBean id="person" class="com.tgweb.day07.Person" scope="session"></jsp:useBean>
	<jsp:setProperty name="person" property="name" value="xiaoming" />
	<jsp:setProperty name="person" property="age" value="23" />

	<jsp:getProperty name="person" property="name" />
	<jsp:getProperty name="person" property="age" />
	<br/>
	
	<%
		out.println(person.getName()+" "+person.getAge());
		out.println("<br/>");
	%>
	
	${person.name } ${person.age }

</body>
</html>