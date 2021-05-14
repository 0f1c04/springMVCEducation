<%@page import="com.kosta.model.ManagerVO"%>
<%@page import="com.kosta.model.LocationVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
  form{border: 5px dotted green;}
</style>
</head>
<body>
<h1>부서 신규 등록</h1>
<form action="deptInsert.do" method="post">
부서번호:<input type="number"  name="department_id"><br>
부서이름:<input type="text"  name="department_name"><br>
매니져ID:
<select name="manager_id">

<c:forEach items="${mlist}" var="m">
	<option value="${m.manager_id }">${m.fullname}</option>
</c:forEach>

</select>
<br>
LocationID:
<select name="location_id">
<c:forEach items="${loclist}" var="l">
	<option value="${l.location_id }">${l.city}</option>
</c:forEach>

</select>
<br>

<input type="submit" value="입력하기" >

</form>
</body>
</html>