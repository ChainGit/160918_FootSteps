<%@page import="java.util.Date"%>
<%@page import="com.tgweb.struts2.day05.PersonComparatorByAge"%>
<%@page import="com.tgweb.struts2.day05.PersonComparatorByName"%>
<%@page import="com.tgweb.struts2.day05.Person"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SHOW</title>
</head>
<body>

	<div>
		<s:debug></s:debug>
	</div>

	<div>
		<p>名称：${name }</p>
		<p>
			名称：
			<s:property value="[0].name"></s:property>
		</p>
		<p>
			名称：
			<s:property value="[1].name"></s:property>
		</p>
		<p>价格：${price }</p>
		<p>备注：${desc }</p>
		<p>
			备注：
			<s:property value="desc"></s:property>
		</p>
		<p>
			备注：
			<s:property value="[0].desc"></s:property>
		</p>
		<p>
			备注：
			<s:property value="[0]['desc']"></s:property>
		</p>
		<p>
			备注：
			<s:property value="[0][\"desc\"]"></s:property>
		</p>
	</div>

	<div>
		<p>名称：${sessionScope.test.name }</p>
		<p>
			名称：
			<s:property value="#session.test.name"></s:property>
		</p>
		<p>
			名称：
			<s:property value="setName('okok')"></s:property>
		</p>
		<p>
			名称：
			<s:property value="#session['test'].name"></s:property>
		</p>
	</div>

	<div>
		<s:property value="@java.lang.Math@PI" />
		<br />
		<s:property value="@java.lang.Math@sin(90)" />
	</div>

	<%
		String[] strs = new String[] { "aaa", "bbb" };
		request.setAttribute("abcd", strs);
	%>

	<s:property value="#attr.abcd[0]" />

	<%
		Map<String, String> map = new HashMap<String, String>();
		request.setAttribute("map2", map);
		map.put("AA", "aa");
	%>

	<s:property value="#attr.map2['AA']" />

	<br />
	<br />
	<br />

	<s:url value="testUrl" var="url1">
	</s:url>

	<s:property value="url1" />
	${url1 }
	<br />

	<s:url value="/testUrl" var="url2">
		<s:param value="9999" name="id"></s:param>
	</s:url>
	${url2 }
	<br />

	<s:url value="/testUrl" var="url3">
		<s:param value="id" name="id"></s:param>
	</s:url>
	${url3 }
	<br />

	<s:url value="/testUrl" var="url4">
		<s:param value="'abcde'" name="id"></s:param>
	</s:url>
	${url4 }
	<br />

	<s:url action="testAction" includeParams="all" value="/testUrl"
		namespace="/aaa" var="url5">
	</s:url>
	${url5 }
	<br />


	<s:set value="123" name="age" scope="request" var="age"></s:set>
	<s:property value="#request.age" />
	${age }
	<br />

	<%
		int i = 29;
		request.setAttribute("iii", i);
	%>
	<s:push value="#request.iii">
		${iii }
	</s:push>

	${iii }

	<br />

	<s:if test="#request.iii < 10">
		<s:property value="'小于10'" />
	</s:if>
	<s:elseif test="#request.iii < 20">
		<span>大于等于10且小于20</span>
	</s:elseif>
	<s:else>
		<span>大于20</span>
	</s:else>
	<br />

	<%
		List<Integer> lst = new ArrayList<Integer>();
		lst.add(100);
		lst.add(101);
		lst.add(102);
		request.setAttribute("lst", lst);
	%>

	<s:iterator value="#request.lst" var="i">
		${i }
	</s:iterator>
	<br />

	<%
		List<Person> lst2 = new ArrayList<Person>();
		lst2.add(new Person("Abc", 14));
		lst2.add(new Person("abc", 12));
		lst2.add(new Person("bcd", 13));
		lst2.add(new Person("cde", 15));
		request.setAttribute("lst2", lst2);
	%>
	<s:iterator value="#request.lst2" var="i">
		${i }<br />
	</s:iterator>
	<s:iterator value="#request.lst2">
		${name } ${age }<br />
	</s:iterator>
	<br />
	<s:iterator value="#request.lst2" status="stat">
		${stat.index } ${stat.count } ${name } ${age }<br />
	</s:iterator>
	<br />

	<%
		PersonComparatorByName pcbn = new PersonComparatorByName();
		PersonComparatorByAge pcba = new PersonComparatorByAge();
		request.setAttribute("personComparatorByName", pcbn);
		request.setAttribute("personComparatorByAge", pcba);
	%>

	<s:sort comparator="#request.personComparatorByName"
		source="#request.lst2" var="persons1"></s:sort>
	<s:sort comparator="#request.personComparatorByAge"
		source="#request.lst2" var="persons2"></s:sort>

	<s:iterator value="#attr.persons1" var="i">
		${i.name }  - ${i.age } <br />
	</s:iterator>
	<br />

	<s:iterator value="#attr.persons2" var="i">
		${i.name }  - ${i.age } <br />
	</s:iterator>
	<br />

	<%
		request.getSession().setAttribute("date", new Date());
	%>

	${date }
	<br />
	<s:date name="#session.date" format="yyyy-MM-dd hh:mm:ss" var="date2"></s:date>
	<br /> ${date2 }
	<br />

	<br />
	<s:iterator value="#request.lst2">
		<s:a href="get.action?name=%{name}">${name }</s:a>
		<br />
	</s:iterator>

</body>
</html>