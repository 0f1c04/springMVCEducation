<%@page import="com.kosta.model.JobVO"%>

<%@page import="com.kosta.model.ManagerVO"%>
<%@page import="com.kosta.model.EmpVO"%>
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
<h1>직원정보 상세보기</h1>
<!-- 일반적으로 저장은 post로 함 -->
<h1>${appInfo}</h1>

<jsp:include page="../common/header.jsp"></jsp:include>

<form id="myfrm" action="empDetail.do" method="post">
직원번호:<input type="text" name="employee_id" value="${emp.employee_id}"><br>
성:<input type="text" name="last_name" value="${emp.last_name}"><br>
이름:<input type="text" name="first_name" value="${emp.first_name}"><br>
급여:<input type="number" name="salary" value="${emp.salary}"><br>
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
전화번호:<input type="text" name="phone_number" value="${emp.phone_number}"><br>
커미션:<input type="text" name="commission_pct" value="${emp.commission_pct}"><br>
입사일:<input type="text" name="hire_date" value="${emp.hire_date}"><br>
직책:
<select name="job_id">
	<c:forEach items="${jlist }" var="j">
		<option ${emp.job_id==j.job_id?"selected":"" } value="${j.job_id }">
		  ${j.job_title }
		</option>
	</c:forEach>
</select>



<br>
이메일:<input type="text" name="email" value="${emp.email}"><br>

<input type="submit" value="수정하기">
<input type="button" id="btnUpdate" value="수정하기2">
<input type="button" id="btnUpdate2" value="수정하기2(REST)">
<input type="button" id="btnRetrieve" value="조회하기">
<input type="button" id="btnDelete" value="삭제하기" mydata="${emp.employee_id}">
<input type="button" id="btnDelete2" value="삭제하기REST" mydata="${emp.employee_id}">
</form>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function(){
	$("#btnUpdate").on("click", function(){
		$("#myfrm").submit();
	});
	$("#btnUpdate2").on("click", function(){
		var emp = $("#myfrm").serializeArray();
		var object = {};
		for (var i = 0; i < emp.length; i++){
			object[emp[i]['name']] = emp[i]['value'];
		}
		var json = JSON.stringify(object);
		$.ajax({
			url:"${pageContext.request.contextPath}/emp2/empUpdate.do",
			type:"put",
			data: json,
			contentType:"application/json",
			success:function(responseData){
				alert(responseData);
			}
		});
	});

	$("#btnRetrieve").on("click", function(){
		location.href = "emplist.do";
	});
	$("#btnDelete").on("click", function(){
		alert($(this).attr("mydata"));
		location.href = "empDelete.do?empid="+ $(this).attr("mydata");
	});
	$("#btnDelete2").on("click", function(){
		alert($(this).attr("mydata"));
		var url = "${pageContext.request.contextPath}/emp2/empDelete.do/" + $(this).attr("mydata");
		$.ajax({
			"url": url,
			type: "delete",
			success:function (responseData){
				alert(responseData);
			}
		})
	});
});
</script>
</body>
</html>





