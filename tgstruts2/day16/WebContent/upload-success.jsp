<%@page import="java.util.Arrays"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>SUCCESS</h2>

	<s:debug></s:debug>

	${file }

	<h4>存储的列表</h4>

	<%
		String storesPath = request.getRealPath(File.separator + "stores");
		File stores = new File(storesPath);
		File[] files = stores.listFiles();
		for (File f : files)
			out.println(f);
	%>

</body>
</html>