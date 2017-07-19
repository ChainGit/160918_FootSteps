<%--
  Created by IntelliJ IDEA.
  User: Chain
  Date: 2017/7/17
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>HOME NORMAL</title>
</head>
<body>

<a href="/">返回INDEX</a>

<s:debug/>

<h2>SSH-02 NORMAL</h2>
<h4>公司员工信息管理</h4>

<a href="home.normal.editUi.action">增加员工</a>
<br/>

<s:if test="#request.emps == null || #request.emps.size() == 0">
    <span>没有任何员工信息</span>
</s:if>
<s:else>
    <table border="1" cellpadding="10" cellspacing="0">
        <thead>
        <tr>
            <th>员工ID</th>
            <th>员工姓名</th>
            <th>员工邮箱</th>
            <th>员工年龄</th>
            <th>员工出生日期</th>
            <th>员工注册时间</th>
            <th>员工所在部门</th>
            <th>员工操作</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="#request.emps">
            <tr>
                <td>${employeeId}</td>
                <td>${employeeName}</td>
                <td>${employeeEmail}</td>
                <td>${employeeAge}</td>
                <td>${employeeBirth}</td>
                <td>${employeeCreateTime}</td>
                <td>${department.departmentName}</td>
                <td><a href="home.normal.delete.action?id=${id}">删除</a>&nbsp;<a
                        href="home.normal.editUi.action?id=${id}">修改</a></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</s:else>

</body>
</html>
