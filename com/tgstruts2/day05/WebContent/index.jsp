<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>INDEX</title>
</head>
<body>

	<s:debug></s:debug>

	<div>
		<form action="show.action" method="post">
			<div>
				<span>名称：</span><input type="text" name="name" />
			</div>
			<div>
				<span>价格：</span><input type="text" name="price" />
			</div>
			<div>
				<span>描述：</span><input type="text" name="desc" />
			</div>
			<div>
				<input type="submit" />
			</div>
		</form>
	</div>

</body>
</html>