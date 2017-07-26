<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>实验2_1：有多少人有人浏览</title>

</head>

<body>
	<h2>实验2_1：有多少人有人浏览</h2>

	<font size=3> <%!int count;
	StringBuffer personList;

	public void judge() {
		if (count == 0) {
			personList = new StringBuffer();
		}
	}

	public void addPerson(String p) {
		if (count == 0)
			personList.append(p);
		else
			personList.append(',' + p);
		count++;
	}%> <%
 	String name = request.getParameter("name");
 	if (name == null || name.length() == 0 || name.length() > 10) {
 %> <jsp:forward page="inputName.jsp" /> <%
 	}
 	byte bb[] = name.getBytes("iso-8859-1");
 	name = new String(bb, "utf-8");
 	judge();
 	addPerson(name);
 %> <br /> 目前共有<%=count%>人浏览了该页面，他们的名字是: <br /> <%=personList%>
	</font>
</body>
</html>
