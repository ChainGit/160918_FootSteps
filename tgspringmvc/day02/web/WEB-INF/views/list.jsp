<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>LIST</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".mvcdelete").click(function () {
                var url = this.href;
                $("#mvcdeleteform").attr("action", url).submit();
                return false;
            });
        });
    </script>
</head>
<body>

<form action="" method="post" id="mvcdeleteform">
    <input type="hidden" name="_method" value="DELETE"/>
</form>


<h4>Restful风格的CRUD练习</h4>

<p>员工列表：</p>

<c:if test="${empty requestScope.employees}">
    <c:out value="没有员工信息"/>
</c:if>
<c:if test="${!empty requestScope.employees}">
    <table border="1" cellpadding="10" cellspacing="0">
        <thead>
        <tr>
            <th>员工ID</th>
            <th>员工姓名</th>
            <th>员工性别</th>
            <th>员工生日</th>
            <th>员工薪水</th>
            <th>员工部门</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.employees}" var="e">
            <tr>
                <td>${e.id}</td>
                <td>${e.name}</td>
                <td>${e.gender==0?"男":"女"}</td>
                <td>${e.birth==null?"无":e.birth}</td>
                <td>${e.salary==null?"无":e.salary}</td>
                <td>${e.department==null?"无":e.department.name}</td>
                <td><a href="<%=request.getContextPath()%>/day02/edit?id=${e.id}">修改</a>&nbsp;
                    <a class="mvcdelete" href="<%=request.getContextPath()%>/day02/e/${e.id}">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<br/>
<a href="<%=request.getContextPath()%>/day02/add">增加员工</a>

<br/>
<a href="<%=request.getContextPath()%>/day02/add2">增加员工2</a>

</body>
</html>