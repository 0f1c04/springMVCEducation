<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>sample1</h1>
<h1>${title }</h1>


<form action="sample3.do" method="get">
아이디:<input type="text" name="userid" value="abc"><br>
비밀번호:<input type="text" name="userpass" value="1234"><br>
이메일:<input type="text" name="email" value="shonebad223@naver.com"><br>
<input type="submit" value="서버전송(sample.do....get)">
</form>

</body>
</html>