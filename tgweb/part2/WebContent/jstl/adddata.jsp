<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="java.util.List,java.util.ArrayList,com.tgweb.day07.Person"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		List<Person> pers = (List<Person>) request.getAttribute("persons");

		if (pers == null) {
			pers = new ArrayList<Person>();
			pers.add(new Person("小芳", 22));
			pers.add(new Person("小明", 21));
			pers.add(new Person("小刚", 23));
			request.setAttribute("persons", pers);
		}

		// String path = "/jstl/showdata.jsp";
		// String path = "/jstl/jstlhello.jsp";
		String path = "/jstl/jstldata.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	%>

</body>
</html>