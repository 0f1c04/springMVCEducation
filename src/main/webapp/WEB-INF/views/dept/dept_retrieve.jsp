<%@page import="com.kosta.model.DeptVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<style type="text/css">
  table, td{border:1px solid black; border-collapse: collapse;}
  td{padding: 5px;}
  tr:first-of-type { background-color: hotpink;}
</style>
</head>
<body>

<h1>부서목록</h1>

<br>

<a href="deptInsert.do">신규등록</a>


<hr>
<table>
  <tr>
    <td>부서코드</td><td>이름</td><td>매니져</td><td>지역코드</td>
  </tr>
  <c:forEach var="dept" items="${deptall}">
	  <tr>
	  	<!-- a태그는 무조건 get방식!! 그리고 get방식으로 할때는 ?를 붙여줄것 -->
	    <td><a href="deptDetail.do?deptid=${dept.department_id }">${dept.department_id }</a></td>
	    <td>${dept.department_name }</td>
	    <td>${dept.manager_id }</td>
	    <td>${dept.location_id }</td>  
	    <td><button onclick="location.href='deptDelete.do?deptid=${dept.department_id }'">삭제</button></td>
	  </tr>
  </c:forEach>
</table>

<hr>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>
<form action="${cpath}/emp/selectByDeptMany.do">
	<select name="deptlist" multiple="multiple">
		<c:forEach items="${deptall }" var="dept">
			<option value="${dept.department_id }">${dept.department_name }</option>
		</c:forEach>
	</select>
	<input type="submit" value="여러 부서의 직원들 조회">
</form>

</body>
</html>


