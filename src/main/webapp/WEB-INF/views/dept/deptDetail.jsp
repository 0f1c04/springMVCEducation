<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>부서의 상세보기...${myname}</h1>
<form action="deptDetail.do" method="post">
부서번호:<input type="number" value="${dept.department_id}" name="department_id"><br>
부서이름:<input type="text" value="${dept.department_name}" name="department_name"><br>
매니져ID:<input type="number" value="${dept.manager_id}" name="manager_id"><br>
LocationID:<input type="number" value="${dept.location_id}" name="lacation_id"><br>
<input type="submit" value="수정하기" >

</form>
</body>
</html>