<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${title }</h1>
<hr>
파라메터 userid: ${id} <br>
파라메터 userpass: ${userpass} <br>
파라메터 email: ${email} <br>
파라메터 email2: ${email2} <br>
map으로 받기: ${userinfo.userVO }<br>
VO로 받기(default): ${user } <br>
VO로 받기(request): ${requestScope.user } <br>
VO로 받기(session): ${sessionScope.user } <br>
VO로 받기(userid): ${user.userid } <br>
</body>
</html>