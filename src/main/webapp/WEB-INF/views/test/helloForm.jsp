<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${title}</h1>
<p>${dept}</p>
<form method="post" action="hello.do">
    아이디:<input type="text" name="userid" value="abcd"><br>
    비밀번호:<input type="text" name="userpass" value="1234"><br>
    이메일:<input type="text" name="email" value="1234@naver.com"><br>
    <input type="submit" value="서버전송(post)">
</form>
</body>
</html>
