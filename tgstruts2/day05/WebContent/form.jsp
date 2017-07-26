<%@page import="com.tgweb.struts2.day06.City"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FORM</title>
</head>
<body>

	<%
		List<City> lst = new ArrayList<City>();
		lst.add(new City(1001, "北京"));
		lst.add(new City(1002, "上海"));
		lst.add(new City(1003, "香港"));
		lst.add(new City(1004, "杭州"));
		request.setAttribute("cities", lst);
	%>

	<s:form action="form.action" method="post">
		<s:hidden name="id"></s:hidden>
		<s:textfield name="name" label="账户"></s:textfield>
		<s:password name="pass" label="密码" showPassword="true"></s:password>
		<s:checkbox name="married" label="已结婚"></s:checkbox>
		<s:radio name="gender" label="性别" list="#{'0':'男','1':'女' }"></s:radio>
		<s:checkboxlist list="#request.cities" listKey="id" listValue="name"
			label="城市" name="cities"></s:checkboxlist>
		<s:select list="{101,102,103,104}" headerKey="" headerValue="请选择"
			name="age" label="年龄">
			<s:optgroup list="#{11:11,222:333 }" label="AAA"></s:optgroup>
			<s:optgroup list="#{21:21}" label="BBB"></s:optgroup>
		</s:select>
		<s:submit value="提交"></s:submit>
	</s:form>

</body>
</html>