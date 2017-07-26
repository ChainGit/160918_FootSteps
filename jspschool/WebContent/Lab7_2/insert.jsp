<%@page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>

<html>
<head>
<meta charset="utf-8">
<title>实验7_2：添加数据</title>
</head>
<body>
	<!--  
	<h2>实验7_2：添加数据</h2>
	<h4>钱帮全</h4>
	-->
	<form
		action="<%=request.getContextPath()%>/twoServlet?dataBase=test&tableName=stu_info"
		method=post>

		<b>添加新纪录：</b> <br> 学号： <input type="text" name="number" size=20>

		<br> 姓名： <input type="text" name="name" size=22> <br>
		年龄： <input type="text" name="age" size=18> <br> <input
			type="submit" name="b" value="提交">

	</form>
</body>
</html>

