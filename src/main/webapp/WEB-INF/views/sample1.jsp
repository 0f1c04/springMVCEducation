
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>sample1</h1>
<h1>${title}</h1>

<form method="get" action="sample3.do">
    아이디:<input type="text" name="userid" value="abc"><br>
    비밀번호:<input type="text" name="userpass" value="1234"><br>
    이메일:<input type="text" name="email" value="1234@naver.com"><br>
    <input type="submit" value="서버전송(get)">
</form>

</body>
</html>
