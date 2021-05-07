<%@page import="com.kosta.model.DeptVO"%>
<%@page import="com.kosta.model.ManagerVO"%>
<%@page import="com.kosta.model.JobVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${appInfo }</h1>
<jsp:include page="../common/header.jsp"></jsp:include>
<h1>직원 신규등록 </h1>
<form action="empInsert" method="post" enctype="multipart/form-data">
직원번호:<input type="text" name="employee_id"  ><br>
성:<input type="text" name="last_name"  ><br>
이름:<input type="text" name="first_name"  ><br>
급여:<input type="number" name="salary"  "><br>
부서:
<select name="department_id" >
   <%
       List<DeptVO> dlist = (List<DeptVO>)request.getAttribute("dlist"); 
       for(DeptVO dept:dlist){
    	   int d = dept.getDepartment_id();
   %>
     <option    value="<%=d%>"><%=dept.getDepartment_name()%></option>
   <%} %>
</select>

<br>
메니져:
<select name="manager_id">
<%
List<ManagerVO> mlist = (List<ManagerVO>)request.getAttribute("mlist");
for(ManagerVO m:mlist){
	out.print("<option   value='" + m.getManager_id() + "'>" + m.getFullname() + "</option>");
}
%>
</select>
전화번호:<input type="text" name="phone_number"  ><br>
<input type="file" name="photos">
커미션:<input type="text" name="commission_pct"  value="0.2"><br>
입사일:<input type="text" name="hire_date"  ><br>
직책:
<select name="job_id">
<%
List<JobVO> jlist = (List<JobVO>)request.getAttribute("jlist");
for(JobVO j:jlist){
	out.print("<option   value='" + j.getJob_id()+ "'>" + j.getJob_title() + "</option>");
}
%>
</select>

<br>
이메일:<input type="text" name="email"  ><br>
<input type="submit" value="입력하기">

</form>
</body>
</html>