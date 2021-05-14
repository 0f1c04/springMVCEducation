<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>친구들리스트입니다.</h1>
<ul>
<c:forEach items="${flist }" var="friend">
	<li>${friend }</li>
</c:forEach>
</ul>
</body>
</html>