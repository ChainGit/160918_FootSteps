<%--
  Created by IntelliJ IDEA.
  User: Chain
  Date: 2017/7/18
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>EDIT NORMAL</title>
</head>
<body>

<a href="/">返回INDEX</a>

<s:debug/>

<h2>SSH-02 NORMAL EDIT</h2>
<h4>公司员工信息管理 - 编辑</h4>

<p>员工ID可用检测为非AJAX</p>

<s:form action="home.normal.input.action">
    <s:hidden name="id"/>
    <s:textfield name="employeeId" label="员工ID"/>
    <s:textfield name="employeeName" label="员工姓名"/>
    <s:textfield name="employeeBirth" label="员工出生日期"/>
    <s:hidden name="employeeCreateTime"/>
    <s:hidden name="employeeAge"/>
    <s:textfield name="employeeEmail" label="员工邮箱"/>
    <s:select list="#request.depts" label="员工所属部门" name="department.id" listKey="id" listValue="departmentName"/>
    <s:submit/>
</s:form>

</body>
</html>
