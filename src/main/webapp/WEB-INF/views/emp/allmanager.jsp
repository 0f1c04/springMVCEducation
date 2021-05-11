<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>모든 매니저들의 리스트</h1>
<ol>
    <c:forEach items="${mlist}" var="m">
        <li><a href="empDetail.do?empid=${m.manager_id}">${m.manager_id}</a>---------${m.fullname}</li>
    </c:forEach>
</ol>
</body>
</html>
