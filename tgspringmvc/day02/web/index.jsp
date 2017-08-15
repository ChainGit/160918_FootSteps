<%--
  Created by IntelliJ IDEA.
  User: Chain
  Date: 2017/8/13
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SPRINGMVC DAY02</title>
</head>
<body>

<h4>Restful风格的CRUD练习</h4>

<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
%>

<a href="${ctx}/day02/list">列表</a>

<a href="${ctx}/day02/testjson">列表(JSON)</a>

<a href="${ctx}/day02/testi18n?locale=zh_CH">测试国际化(中文)</a>

<a href="${ctx}/day02/testi18n?locale=en_US">测试国际化(英文)</a>

</body>
</html>
