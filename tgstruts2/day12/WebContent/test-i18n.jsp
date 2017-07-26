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

	<!-- 注意请求不能直接是jsp,需要经过struts2 -->
	<a href="test-i18n.action?request_locale=en_US">ENGLISH</a>
	<br />
	<a href="test-i18n.action?request_locale=zh_CN">中文</a>
	<br />

	<s:text name="time">
		<s:param value="date"></s:param>
	</s:text>

	<br />
	<s:text name="time2"></s:text>

	<s:form action="">
		<s:text name="username"></s:text>
		<s:textfield name="username" label="%{getText('username')}"></s:textfield>
		<s:textfield name="username" label="UserName"></s:textfield>
		<s:textfield name="username" key="username"></s:textfield>
		<s:textfield name="password" label="password"></s:textfield>
		<s:textfield name="password" key="password"></s:textfield>
		<s:submit key="submit"></s:submit>
	</s:form>

</body>
</html>