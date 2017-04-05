<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EL TEST 4</title>
</head>
<body>

	<%-- EL表达式主要是读取和判断功能 --%>
	
	${paramValues.name[0] }
	${paramValues.name[1] }
	${paramValues.name[2] }
	<%-- ${paramValues.name.3 } --%>
	
	${param.score>60 ? "及格" : "不及格" }
	
	${empty paramValues.name }
	
	
</body>
</html>