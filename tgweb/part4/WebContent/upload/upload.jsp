<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UPLOAD FILE TEST</title>
</head>
<body>

	<form action="<%=request.getContextPath() %>/uploadFileServlet" method="post"
		enctype="multipart/form-data">
		<input type="file" name="file" /><br />
		<input type="text" name="desc" /><br />
		<input type="submit"/><br />
	</form>

</body>
</html>