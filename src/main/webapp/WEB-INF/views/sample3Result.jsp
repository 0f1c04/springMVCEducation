
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${title}</h1>
<hr>
파라미터 userid : ${id} <br>
파라미터 userpass : ${userpass} <br>
파라미터 email : ${email} <br>
파라미터 email2 : ${email2} <br>
map으로 받기: ${userinfo.userVO} <br>
VO로 받기(default): ${user} <br>
VO로 받기(request): ${requestScope.user} <br>
VO로 받기(session): ${sessionScope.user} <br>


</body>
</html>
