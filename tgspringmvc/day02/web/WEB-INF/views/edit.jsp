<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %><%--
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
    姓名：<form:input path="name"/><form:errors path="name"/> <br/>
    <%
        Map<String, String> genderMap = new HashMap<String, String>();
        genderMap.put("0", "男");
        genderMap.put("1", "女");
        pageContext.setAttribute("genderMap", genderMap);
    %>
    性别：<form:radiobuttons path="gender" items="${genderMap}"/><br/>
    生日：<form:input path="birth"/><form:errors path="birth"/><br/>
    薪水：<form:input path="salary"/><form:errors path="salary"/><br/>
    部门：<form:select path="department.id" items="${requestScope.departments}" itemLabel="name" itemValue="id"/><br/>
    <input type="submit" value="提交"/>
</form:form>

</body>
</html>
