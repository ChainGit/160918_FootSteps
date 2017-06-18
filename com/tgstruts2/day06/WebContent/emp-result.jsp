<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>
		<s:debug></s:debug>
	</div>

	<div>
		<span>姓名：${name }</span><br /> <span>密码：${password }</span><br /> <span>性别：${gender }</span><br />
		<span>部门：${dept }</span><br /> <span>角色：<s:iterator
				value="#request.roles" var="i">${i } </s:iterator>
		</span><br />
	</div>

</body>
</html>