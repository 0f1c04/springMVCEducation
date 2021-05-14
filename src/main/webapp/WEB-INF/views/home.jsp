<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="utf-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world! 첫 번째 JSP파일
</h1>

<P>  The time on the server is ${serverTime}. </P>
<form >
	직원번호:<input type="number" id="empid" value="100"><br>
	<input id="btn1" type="button" value="rest로 보내기" >
</form>
<hr>

<h1>직원 신규등록</h1>
<form id="empForm">
직원번호:<input type="text" name="employee_id" ><br>
성:<input type="text" name="last_name" ><br>
이름:<input type="text" name="first_name" ><br>
급여:<input type="number" name="salary" value="1200"><br>
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
전화번호:<input type="text" name="phone_number" value="010"><br>
커미션:<input type="text" name="commission_pct" value="0.1"><br>
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
<input id="btn2" type="button" value="입력하기">

</form>



<div id="here">여기!!</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
  $(function(){
	  $("#btn2").click(function(){
		  var fdata = $("#empForm").serialize();
		  //alert(fdata); //key=val&key2=val2
		  var emp = $("#empForm").serializeArray(); //[key,val],[key,val]
		  var object = {};
		  for (var i = 0; i < emp.length; i++){
		      object[emp[i]['name']] = emp[i]['value'];
		  }
		  var json = JSON.stringify(object);
		  $.ajax({
			  url:"emp2/empinsert.do",
			  type:"post", 
			  data: json, 
			  contentType:"application/json",
		      success:function(responseData){
		    	  alert(responseData);
		      }
		  	});
		  }); 
	  
	  
	  
	  $("#btn1").click(function(){
		  $.ajax({
			  url:"emp2/emp5.do/" + $("#empid").val(),
			  type:"get",
			  success:function(responseData){
				  alert(responseData);
				  console.log(responseData);
				  $("#here").html(responseData["first_name"]);
			  }
			  
		  });
		  
	  });
  });
</script>

</body>
</html>
