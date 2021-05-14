<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>모든 부서들 리스트</h1>

<ol>
  <c:forEach items="${dlist }" var="dept">
    <li><a href ="empByDept.do?deptid=${dept.department_id}">${dept.department_name }</a></li>
  </c:forEach>
</ol>

</body>
</html>