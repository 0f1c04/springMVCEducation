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
<p>${dept }</p>


<form action="hello.do" method="post">
아이디:<input type="text" name="userid" value="abcd"><br>
비밀번호:<input type="text" name="userpass" value="1234"><br>
이메일:<input type="text" name="email" value="shonebad223@naver.com"><br>
<input type="submit" value="서버전송(post)">
</form>

</body>
</html>