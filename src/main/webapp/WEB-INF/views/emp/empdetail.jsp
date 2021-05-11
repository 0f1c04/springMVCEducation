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
<h1>직원정보 상세보기 </h1>
<h1>${appInfo }</h1>

<jsp:include page="../common/header.jsp"></jsp:include>


<form id="myfrm" action="/emp/empDetail.do" method="post">
직원번호:<input type="text" name="employee_id" value="${emp.employee_id}"><br>
성:<input type="text" name="last_name" value="${emp.last_name}"><br>
이름:<input type="text" name="first_name" value="${emp.first_name}"><br>
급여:<input type="number" name="salary" value="${emp.salary}"><br>
부서:
<select name="department_id" >
    <c:forEach items="${dlist}" var="dept">
        <option ${emp.department_id == dept.department_id ? "selected":""} value=${dept.department_id}>
            ${dept.department_name}
        </option>
    </c:forEach>

</select>


<br>
메니져:
<select name="manager_id">
    <c:forEach items="${mlist}" var="m">
        <option ${emp.manager_id == m.manager_id?"selected":""} value="${m.manager_id}">
            ${m.fullname}
        </option>
    </c:forEach>
</select>

전화번호:<input type="text" name="phone_number" value="${emp.phone_number}"><br>
커미션:<input type="text" name="commission_pct" value="${emp.commission_pct}"><br>
입사일:<input type="text" name="hire_date" value="${emp.hire_date}"><br>
직책:
<select name="job_id">
    <c:forEach items="${jlist}" var="j">
        <option ${emp.job_id == j.job_id?"selected":""} value="${j.job_id}">
            ${j.job_title}
        </option>
    </c:forEach>
</select>


이메일:<input type="text" name="email" value="${emp.email}"><br>
<input type="submit" value="수정하기">
<input type="button" id="btnUpdate" value="수정하기2">
<input type="button" id="btnRetrieve"  value="조회하기">
<input type="button" id="btnDelete"  value="삭제하기"  mydata="${emp.employee_id}">
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(function(){
	$("#btnUpdate").on("click", function(){		
		$("#myfrm").submit();
	});
   $("#btnRetrieve").on("click", function(){
		location.href = "emplist.do";
	});
   $("#btnDelete").on("click", function(){
	   alert($(this).attr("mydata"));
		location.href = "empDelete.do?empid=" + $(this).attr("mydata");
	});
});
</script>
</body>
</html>




