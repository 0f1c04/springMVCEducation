<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
  .logininfo{border: 5px dotted green; float:right;}
</style>
</head>
<body>

${"aa" == "aa" }<br>
${"aa" eq "bb" }<br>


<div class="logininfo">
	<c:if test="${emp != null }">
		<P>${username}가 로그인하였습니다.</P>
		<p><a href="../login/logout.kosta">로그아웃</a></p>
	</c:if>
	<c:if test="${emp == null }">
		<P>손님</P>
		<p><a href="../login/loginChk.kosta">로그아웃</a></p>
	</c:if>
	
	<hr>
	<c:choose>
		<c:when test = "${emp==null }">
			<P>손님</P>
			<p><a href="../login/loginChk.kosta">로그아웃</a></p>
		</c:when>
		<c:otherwise>
			<P>${username}가 로그인하였습니다.</P>
			<p><a href="../login/logout.kosta">로그아웃</a></p>
		</c:otherwise>
	</c:choose>

</div>
</body>
</html>