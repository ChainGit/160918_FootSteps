<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 年龄在20到50之间 -->
	<!-- 体重在60到100之间 -->
	<s:form action="show.action">
		<s:textfield name="age" key="age"></s:textfield>
		<s:textfield name="weight" key="weight"></s:textfield>
		<s:submit></s:submit>
	</s:form>

</body>
</html>