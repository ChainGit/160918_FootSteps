<%--
  Created by IntelliJ IDEA.
  User: Chain
  Date: 2017/8/12
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>DATE</title>
</head>
<body>

S_Date：${s_date}<br/>
M_Date：${m_date}<br/>
D_Date：${requestScope.d_date}<br/>
D_Date2：${sessionScope.d_date}<br/>

<h4>JSTL fmt 国际化</h4>

<fmt:message key="username"></fmt:message>
<fmt:message key="password"></fmt:message>


</body>
</html>
