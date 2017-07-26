<%@page contentType="text/html;charset=utf-8"%>
<html>
<head>
<meta charset="utf-8">
<title>实验2_3：输入的数字判断</title>
</head>

<body>
	<h2>实验2_3：输入的数字判断</h2>
	<h4>ONE PAGE</h4>

	<jsp:include page="head.txt" />

	<form action="one.jsp" method="post">
		请输入1-100之间的整数： <input type="text" name="num" /> <br /> <input
			type="submit" />
	</form>


	<%
		String num = request.getParameter("num");
		if (num != null)
			try {
				int n = Integer.parseInt(num);
				if (n >= 1 && n <= 50)
					request.getRequestDispatcher("two.jsp?num=" + n).forward(request, response);
				else if (n > 50 && n <= 100)
					request.getRequestDispatcher("three.jsp?num=" + n).forward(request, response);
				else
					request.getRequestDispatcher("error.jsp?num=" + n).forward(request, response);
			} catch (Exception e) {
				request.getRequestDispatcher("error.jsp?num=-1").forward(request, response);
			}
	%>

</body>
</html>