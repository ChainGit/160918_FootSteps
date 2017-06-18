<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<s:debug></s:debug>
	<br />
	<s:form action="test-show.action">
		<s:textfield name="age" label="年龄"></s:textfield>
		<s:submit></s:submit>
	</s:form>
	<br />
	<s:form action="test-show.action" theme="simple">
		${fieldErrors.age[0] }<br />
		<s:fielderror fieldName="age"></s:fielderror>
		<s:textfield name="age" label="年龄"></s:textfield>
		<s:submit></s:submit>
	</s:form>

</body>
</html>