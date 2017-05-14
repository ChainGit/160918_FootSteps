<%@page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>

<jsp:useBean id="resultBean" class="com.chapter7.TwoBean"
	scope="request" />

<html>
<body>
	<h2>实验7_2：添加数据</h2>

	<table border=1>



		<%
			String[] columnName = resultBean.getColumnName();
		%>

		<tr>

			<%
				for (String s : columnName) {
			%><th><%=s%></th>

			<%
				}
			%>
		</tr>

		<%
			String[][] record = resultBean.getTableRecord();

			for (int i = 0; i < record.length; i++) {
		%><tr>

			<%
				for (int j = 0; j < record[i].length; j++) {
			%><td><%=record[i][j]%></td>

			<%
				}
			%>
		</tr>

		<%
			}
		%>

	</table>
</body>
</html>
