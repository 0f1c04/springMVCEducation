<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>모든 부서들 리스트</h1>
<ol>
    <c:forEach items="${dlist}" var="dept">
        <li>
            <a href="empByDept.do?deptid=${dept.department_id}">${dept.department_name}</a>
        </li>
    </c:forEach>
</ol>
</body>
</html>
