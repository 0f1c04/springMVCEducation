<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1>로그인하기</h1>
<form action="login3.do" method="post">
    아이디:<input type="number" name="userid" value="100"><br>
    비밀번호:<input type="password" name="userpw" value="SKING"><br>
    <input type="hidden" name="address" value="서울시 ">
    <input type="hidden" name="phone" value="010-1234-5678">
    <input type="submit" value="로그인">

    <hr>
    <c:set var="cpath" value="${pageContext.request.contextPath}"/>
    <h1>일자로 조회하기</h1>
    <form action="${cpath}/emp/selectByDate.do">
        시작일:<input type="date" name="sdate" value="2005-01-01"><br>
        종료일:<input type="date" name="edate" value="2005-12-31"><br>
        <input type="submit" value="일자로 조회"><br>
    </form>

    <hr>
    <h1>일자로 조회하기</h1>
    <form action="${cpath}/emp/selectByDate2.do">
        시작일:<input type="date" name="sdate" value="2005-01-01"><br>
        종료일:<input type="date" name="edate" value="2005-12-31"><br>
        <input type="submit" value="일자로 조회"><br>
    </form>

    <hr>
    <h1>부서로 조회하기</h1>
    <form action="${cpath}/emp/selectByDept.do">
        <input type="number" name="deptid" value="10"><br>
        <input type="submit" value="부서로 조회"><br>
    </form>

    <hr>
    <h1>급여로 조회하기</h1>
    <form action="${cpath}/emp/selectBySalary.do">
        <input type="number" name="minsal" value="15000"><br>
        <input type="number" name="maxsal" value="20000"><br>
        <input type="submit" value="급여로 조회"><br>
    </form>

    <hr>
    <h1>동적으로 조회하기</h1>
    <form action="${cpath}/emp/selectByCondition.do">
        부서:<input type="number" name="deptid" value="60"><br>
        직책:<input type="text" name="jobid" value="IT_PROG"><br>
        급여:<input type="number" name="sal" value="12000"><br>
        시작일:<input type="date" name="hdate" value="2005-01-01">
        <input type="checkbox" name="chk" checked="checked"/>일자조회여부<br>
        <input type="submit" value="동적으로 조회"><br>
    </form>
</form>
</body>
</html>