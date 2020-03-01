<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/2/27
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<h1>Hello!</h1>
<h2>当前时间：<%=session.getAttribute("currentTime")%></h2>

</body>
</html>