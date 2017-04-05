<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BEAN TEST</title>
</head>
<body>

	<!-- JAVABEAN 是利用反射机制 -->
	<jsp:useBean id="person" class="com.tgweb.day07.Person" scope="session"></jsp:useBean>

	<%--
		com.tgweb.day07.Person person3 = new com.tgweb.day07.Person();
		person3.setName("小强");
		person3.setAge(22);
		session.setAttribute("person3", person3);
	--%>

	<%--
		com.tgweb.day07.Person person3 = (com.tgweb.day07.Person) Class.forName("com.tgweb.day07.Person")
				.newInstance();
		person3.setName("小强");
		person3.setAge(22);
		session.setAttribute("person3", person3);
	--%>

	<jsp:useBean id="person2" beanName="com.tgweb.day07.Person"
		type="com.tgweb.day07.Person" scope="session"></jsp:useBean>

	<jsp:setProperty name="person" property="name" value="小明" />
	<jsp:setProperty name="person" property="age" value="19" />

	<jsp:setProperty name="person2" property="*" />

	person1:
	<br /> name:<jsp:getProperty name="person" property="name" />
	age:<jsp:getProperty name="person" property="age" />

	<br /> person2:
	<br /> name:<jsp:getProperty name="person2" property="name" />
	age:<jsp:getProperty name="person2" property="age" />

	<%-- 
	<br /> person3:
	<br /> name:<jsp:getProperty name="person3" property="name" />
	age:<jsp:getProperty name="person3" property="age" />
	 --%>
</body>
</html>