<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.fun.com/mytl/core" prefix="fun"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<fun:hello />

	<fun:loopPrint value="123" />

	<fun:loopPrint value="test" count="10" />

	<fun:loopPrint value="${param.value }" count="${param.count + 1 }" />

	<fun:max num1="123" num2="122" />

	<fun:max num1="abc" num2="122" />

	<%-- 
	<fun:fileRead path="E:\\More\\Temps\\test_trcolor.html" />
	--%>

	<fun:upperWord>abcdef</fun:upperWord>

	<fun:upperWord>${param.value }</fun:upperWord>

	<br />

	<fun:parent>
		<fun:child />
	</fun:parent>

	<fun:choose>
		<fun:when test="${param.score > 89 }">优秀</fun:when>
		<fun:when test="${param.score > 60 }">及格</fun:when>
		<fun:otherwise>不及格</fun:otherwise>
	</fun:choose>
	
	<br/>
	
	${fun:concat("123","abc") }

</body>
</html>