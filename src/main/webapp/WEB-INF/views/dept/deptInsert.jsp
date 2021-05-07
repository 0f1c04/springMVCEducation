<%@page import="com.kosta.model.ManagerVO"%>
<%@page import="com.kosta.model.LocationVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
 form{ border : 5px dotted green;}
</style>
</head>
<body>
<h1>부서 신규 등록</h1>
<jsp:include page="../common/header.jsp"></jsp:include>
<form action="deptInsert" method="post">
부서번호:<input type="number" name="department_id"><br>
부서이름:<input type="text"  name="department_name"><br>
메니져ID 선택:
<select name="manager_id">
<%
 List<ManagerVO> mlist = (List<ManagerVO>)request.getAttribute("mlist");
for(ManagerVO m:mlist){
%>
   <option value="<%=m.getManager_id()%>"><%=m.getFullname() %></option>
<% }%>
</select>

LOcationID 선택:
<select name="location_id">
<%
 List<LocationVO> loclist = (List<LocationVO>)request.getAttribute("loclist");
for(LocationVO loc:loclist){
%>
   <option value="<%=loc.getLocation_id()%>"><%=loc.getCity() %></option>
<% }%>
</select>


<input type="submit" value="입력하기">
</form>

</body>
</html>



