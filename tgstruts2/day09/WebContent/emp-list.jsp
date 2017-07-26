<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LIST</title>
</head>
<body>

	<div>
		<s:debug></s:debug>
	</div>

	<div>
		<s:form action="emp-add.action">
			<s:textfield name="empName" label="姓名"></s:textfield>
			<s:textfield name="empEmail" label="邮箱"></s:textfield>
			<s:submit></s:submit>
		</s:form>
	</div>

	<hr />

	<div>
		<table cellspacing="0" cellpadding="10" border="1">
			<thead>
				<tr>
					<td>员工ID</td>
					<td>员工姓名</td>
					<td>员工邮箱</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.emps" var="emp">
					<tr>
						<td>${emp.empId }</td>
						<td>${emp.empName }</td>
						<td>${emp.empEmail }</td>
						<td><a href="emp-edit.action?empId=${emp.empId }">修改</a>&nbsp;<a
							href="emp-delete.action?empId=${emp.empId }">删除</a></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
</body>
</html>