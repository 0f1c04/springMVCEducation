<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>오류입니다ㅠㅠ</h1>
<hr>
<%=exception.toString() %>
<hr>
오류메세지: <%=exception.getMessage() %>
<hr>
오류 printStackTrace: <% exception.printStackTrace(); %>
</body>
</html>