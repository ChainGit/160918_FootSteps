<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 动态引入 --%>
<jsp:include page="include_in.jsp">
	<jsp:param name="testParam" value="valueParam" />
</jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>PAGE CCC</h2>

	<%-- 
	<%=p%>
	--%>

	<jsp:forward page="include_in2.jsp">
		<jsp:param name="testParam2" value="valueParam2" />
	</jsp:forward>

</body>
</html>