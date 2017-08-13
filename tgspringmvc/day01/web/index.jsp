<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>INDEX</title>
</head>
<body>

<h4>Hello World</h4>

<a href="day01/helloworld">HelloWorld</a>

<form action="day01/123456/testpost" method="post">
    <input type="text" name="username"/>
    <input type="password" name="password"/>
    <input type="submit" value="Test Post Method"/>
</form>

<h4>各种传参</h4>

<a href="day01/testvariable/123">Test Variable</a>

<a href="/day01/testrequestparams?username=jack&password=123">Test Request Params</a>

<a href="/day01/testrequestheader">Test Request Header</a>

<a href="/day01/testcookievalue">Test Cookie Value</a>

<form action="/day01/testpojoparam" method="post">
    <input type="hidden" name="id" value="123"/>
    <span>姓名：</span><input type="text" name="name"/><br/>
    <span>年龄：</span><input type="text" name="age"/><br/>
    <span>语文：</span><input type="text" name="score.chinese"/><br/>
    <span>数学：</span><input type="text" name="score.math"/><br/>
    <span>英语：</span><input type="text" name="score.english"/><br/>
    <input type="submit" value="Test POJO Param"/>
</form>

<a href="/day01/testnativeservletapi">Test Native Servlet API</a>

<h4>处理模型数据</h4>

<a href="/day01/testmodelandview">Test ModelAndView</a>

<a href="/day01/testmodelmap">Test ModelMap</a>

<a href="/day01/testmodel">Test Model</a>

<form action="/day01/testmodelattribute" method="post">
    <input type="hidden" name="id" value="123"/>
    <input type="text" name="name" value="jack"/>
    <input type="submit" value="Test ModelAttribute"/>
</form>

<h4>视图和视图解析器</h4>

<a href="/day01/testjstlview">Test JSTL View</a>

<a href="/day01/testmyview">Test MyView</a>

<a href="/day01/testredirect">Test Redirect</a>

<h4>REST风格</h4>

<a href="day01/order/123">order Get</a>

<form action="/day01/order" method="post">
    <input type="submit" value="order Post"/>
</form>

<form action="/day01/order/123" method="post">
    <input type="submit" value="order Delete"/>
    <input type="hidden" name="_method" value="DELETE"/>
</form>

<form action="/day01/order/123" method="post">
    <input type="submit" value="order Put"/>
    <input type="hidden" name="_method" value="PUT"/>
</form>

</body>
</html>
