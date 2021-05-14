
<%@page import="com.kosta.model.JobVO"%>
<%@page import="com.kosta.model.ManagerVO"%>
<%@page import="com.kosta.model.DeptVO"%>
<%@page import="java.util.List"%>
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
<h1>${appInfo }</h1>
<h1>직원 신규등록</h1>
<jsp:include page="../common/header.jsp"></jsp:include>
<!-- 일반적으로 저장은 post로 함 -->
<form action="empInsert.do" method="post">
직원번호:<input type="text" name="employee_id" ><br>
성:<input type="text" name="last_name" ><br>
이름:<input type="text" name="first_name" ><br>
급여:<input type="number" name="salary" value="0"><br>
부서:
<select name="department_id">
  <c:forEach items="${dlist }" var="dept">
	<option ${emp.department_id==dept.department_id?"selected":"" } value="${dept.department_id }">
	  ${dept.department_name }
	</option>
  </c:forEach>
</select>


<br>

매니저:
<select name="manager_id">
	<c:forEach items="${mlist }" var="m">
		<option ${emp.manager_id==m.manager_id?"selected":"" } value="${m.manager_id }">
		  ${m.fullname }
		</option>
	</c:forEach>
</select>

<br>
전화번호:<input type="text" name="phone_number" ><br>
커미션:<input type="text" name="commission_pct" ><br>
입사일:<input type="text" name="hire_date" value="2020-09-23"><br>
직책:
<select name="job_id">
	<c:forEach items="${jlist }" var="j">
		<option ${emp.job_id==j.job_id?"selected":"" } value="${j.job_id }">
		  ${j.job_title }
		</option>
	</c:forEach>
</select>
<br>
이메일:<input type="text" name="email" ><br>
<input type="submit" value="입력하기">

</form>
</body>
</html>