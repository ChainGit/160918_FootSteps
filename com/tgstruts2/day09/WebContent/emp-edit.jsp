<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EDIT</title>
</head>
<body>

	<s:debug></s:debug>

	<s:form action="emp-update.action">
		<s:hidden name="empId"></s:hidden>
		<s:textfield name="empName" label="员工姓名"></s:textfield>
		<s:textfield name="empEmail" label="员工邮箱"></s:textfield>
		<s:submit></s:submit>
	</s:form>

</body>
</html>