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

	<s:form action="test-show.action">
		<s:textfield name="deptName" label="部门名称"></s:textfield>
		<s:textfield name="mgr.mgrName" label="经理姓名"></s:textfield>
		<s:textfield name="mgr.mgrEmail" label="经理邮箱"></s:textfield>
		<s:textfield name="mgr.mgrBirth" label="经理生日"></s:textfield>
		<s:textfield name="emps[0].empName" label="员工1姓名"></s:textfield>
		<s:textfield name="emps[0].empEmail" label="员工1邮箱"></s:textfield>
		<s:textfield name="emps[0].empBirth" label="员工1生日"></s:textfield>
		<s:textfield name="emps[1].empName" label="员工2姓名"></s:textfield>
		<s:textfield name="emps[1].empEmail" label="员工2邮箱"></s:textfield>
		<s:textfield name="emps[1].empBirth" label="员工2生日"></s:textfield>
		<s:textfield name="emps[2].empName" label="员工3姓名"></s:textfield>
		<s:textfield name="emps[2].empEmail" label="员工3邮箱"></s:textfield>
		<s:textfield name="emps[2].empBirth" label="员工3生日"></s:textfield>
		<s:submit></s:submit>
	</s:form>

</body>
</html>