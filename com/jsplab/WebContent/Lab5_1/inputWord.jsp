<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>实验5_1：分解句子</title>
</head>

<body>
	<h2>实验5_1：分解句子</h2>
	<form action="<%=request.getContextPath()%>/word" method="post">
		输入英文句子：<br />
		<textarea name="english" rows=5 cols=30>
  		</textarea>
		<br /> <input type="submit" value="提交">
	</form>

</body>
</html>
