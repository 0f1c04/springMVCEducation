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
<h1>Job List</h1>
<table border="1">
  <tr>
    <td>직책코드</td><td>직책이름</td>
  </tr>
  <c:forEach items="${jlist}" var="job">
    <tr>
      <td><a href="empByJob.do?jobid=${job.job_id }">${job.job_id }</a></td><td>${job.job_title }</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>