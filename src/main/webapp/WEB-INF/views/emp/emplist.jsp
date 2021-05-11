<%@page import="com.kosta.model.EmpVO" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        table, td {
            border: 1px solid black;
            border-collapse: collapse;
        }

        td {
            padding: 5px;
        }

        tr:first-of-type {
            background-color: skyblue;
        }
    </style>
</head>
<body>
<h1>직원목록</h1>
<jsp:include page="../common/header.jsp"></jsp:include>

<a href="empInsert.do">신규등록</a>
<br><br>
<ul>
    <li>문자값:${myname }</li>
    <li>숫자값:<%=request.getAttribute("myscore") %>
    </li>
    <li>emp객체:${ myemp}</li>
    <li>info객체:${ info}</li>
</ul>


<table>
    <tr>
        <th>사원번호</th>
        <th>이름</th>
        <th>성</th>
        <th>급여</th>
        <th>입사일</th>
        <th>부서번호</th>
        <th>이메일</th>
        <th>성과급</th>
        <th>직책</th>
        <th>전화번호</th>
        <th>매니저</th>
        <th>삭제</th>
    </tr>
    <c:forEach var="emp" items="${emp_all}">
        <c:url value="empDetail.do" var="empD">
            <c:param name="empid" value="${emp.employee_id}"></c:param>
        </c:url>
        <tr>
            <td><a href="${empD}">${emp.employee_id}</a></td>
            <td>${fn:toUpperCase(emp.first_name)}</td>
            <td>${emp.last_name}</td>
            <td><fmt:formatNumber type="currency" currencySymbol="$" groupingUsed="true" value="${emp.salary}"/></td>
            <td><fmt:formatDate pattern="yyyy/MM/dd hh:mm:ss" value="${emp.hire_date}"/></td>
            <td>${emp.department_id}</td>
            <td>${emp.email}</td>
            <td>${emp.commission_pct}</td>
            <td>${emp.job_id}</td>
            <td>${emp.phone_number}</td>
            <td>${emp.manager_id}</td>
            <td>
                <button onclick="call(${emp.employee_id});">삭제</button>
            </td>
        </tr>
    </c:forEach>
</table>


<script>
    function call(empid) {
        location.href = "empDelete.do?empid=" + empid;
    }
</script>
</body>
</html>




