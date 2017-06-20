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

	<s:form action="test-success.action">
		<!-- 重复提交解决方案 -->
		<s:token></s:token>
		<s:fielderror></s:fielderror>
		<s:actionerror></s:actionerror>
		<s:textfield name="username" key="username"></s:textfield>
		<s:submit></s:submit>
	</s:form>

</body>
</html>