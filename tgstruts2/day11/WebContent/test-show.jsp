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

	<h3>SUCCESS</h3>
	<s:debug></s:debug>

	<div>
		<p>部门名称：${deptName }</p>
		<p>经理姓名：${mgr.mgrName }</p>
		<p>经理邮箱：${mgr.mgrEmail }</p>
		<p>经理生日：${mgr.mgrBirth }</p>
		<s:iterator value="#request.emps" var="i">
			${i.empName } , ${i.empEmail } , ${i.empBirth } <br>
		</s:iterator>
	</div>

</body>
</html>