<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/26
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功操作页面</title>
</head>
<body>

<%
    List<String> urlList = Arrays.asList("test.do","aaa.do","fuck.do","shit.do","yyy.do");
    request.setAttribute("urlList",urlList);
%>

<c:forEach items="${urlList}" var="u">
    <a href="/test_filter/${u}" target="_blank">功能 ${u}</a><br>
</c:forEach>



</body>
</html>
