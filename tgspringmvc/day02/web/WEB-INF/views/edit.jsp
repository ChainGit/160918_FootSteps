<%--
  Created by IntelliJ IDEA.
  User: Chain
  Date: 2017/8/13
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>EDIT</title>
</head>
<body>

<h4>增加或修改员工</h4>

<form:form action="${pageContext.request.contextPath}/day02/e" method="POST" modelAttribute="employee">
    <form:hidden path="id"/>
    <input type="hidden" name="_method" value="${requestScope.employee.id==0?'POST':'PUT'}"/>
    姓名：<form:input path="name"/><br/>
    员工：<form:select path="department.id" items="${requestScope.departments}" itemLabel="name" itemValue="id"/><br/>
    <input type="submit" value="提交"/>
</form:form>

</body>
</html>
