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

	<s:debug></s:debug>

	<!-- 年龄在20到50之间 -->
	<!-- 体重在60到100之间 -->
	<s:form action="show.action" theme="simple">
		<s:text name="age"></s:text>
		<s:textfield name="age" key="age"></s:textfield>
		<s:text name="weight"></s:text>
		<s:textfield name="weight" key="weight"></s:textfield>
		<s:text name="height"></s:text>
		<s:textfield name="height" key="height"></s:textfield>
		<s:text name="password"></s:text>
		<s:password name="password" key="password"></s:password>
		<s:text name="password2"></s:text>
		<s:password name="password2" key="password2"></s:password>
		<s:fielderror></s:fielderror>
		<s:actionerror></s:actionerror>
		<s:submit></s:submit>
	</s:form>

</body>
</html>