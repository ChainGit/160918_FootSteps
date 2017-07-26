<%@page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>

<jsp:useBean id="recordBean" class="com.chapter7.OneBean"
	scope="session" />

<html>
<head>
<meta charset="utf-8">
<title>实验7_1：查询数据</title>
</head>
<body>
	<h2>实验7_1：查询数据</h2>
	<h4>钱帮全</h4>
	<form action="<%=request.getContextPath()%>/oneServlet" method=post>
		数据库：<input type="text" name="dataBase" size=22value=warehouse>
		<br>表名：<input type="text" name="tableName" size=22value=product>
		<br>用户名(默认root)：<input type="text" name="user" size=10value=root>
		<br>用户密码（默认空）：<input type="text" name="password" size=10>
		<br> <input type="submit" name="b" value="提交">
	</form>

	<table border=1>
		<%
			String[][] table = recordBean.getTableRecord();
			if (table == null) {
				out.print("没有记录");
				return;
			}

			String[] columnName = recordBean.getColumnName();
			if (columnName != null) {
				out.print("<tr>");
				for (int i = 0; i < columnName.length; i++) {
					out.print("<th>" + columnName[i] + "</th>");
				}
				out.print("</tr>");
			}
			out.println("全部记录数 " + table.length);
			for (int i = 0; i < table.length; i++) {
				out.print("<tr>");
				for (int j = 0; j < columnName.length; j++) {
					out.print("<td>" + table[i][j] + "</td>");
				}
				out.print("</tr>");
			}
		%>

	</table>
</body>
</html>
