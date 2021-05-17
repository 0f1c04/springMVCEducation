<%@page import="com.kosta.model.EmpVO" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="../common/error500.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <style type="text/css">
        table, td {
            border: 1px solid black;
            border-collapse: collapse;
        }

        td {
            padding: 5px;
        }

        tr:first-of-type {
            background-color: gray;
        }
    </style>
</head>
<body>

<h2>직원목록...로그인정보 : ${loginEmail}</h2>


<!-- 표준액션: 컴파일 후 합친다. -->
<jsp:include page="../common/header.jsp"></jsp:include>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>
<img alt="이미지" src="${cpath}/images/duke.png" width="100px" height="100px">
<img alt="이미지2" src="${cpath}/images/duke2.png" width="100px" height="100px">

<br>
<a href="empInsert.do">신규등록</a>
<br>

<ul>
    <li>문자값:${myname}</li>
    <li>숫자값:<%=request.getAttribute("myscore") %>
    </li>
    <li>emp객체:${myemp}</li>
    <li>info객체:${info}</li>
</ul>

<hr>
<table>
    <tr>
        <td>직원번호</td>
        <td>성</td>
        <td>이름</td>
        <td>급여</td>
        <td>부서</td>
        <td>매니저</td>
        <td>전화번호</td>
        <td>커미션</td>
        <td>입사일</td>
        <td>JOB</td>
        <td>이메일</td>
        <td>삭제</td>
    </tr>
    <c:forEach var="emp" items="${emplist }">
        <c:url value="empDetail" var="empD">
            <c:param name="empid" value="${emp.employee_id }"></c:param>

        </c:url>

        <tr>
            <!-- a태그는 무조건 get방식!! 그리고 get방식으로 할때는 ?를 붙여줄것 -->
            <td>${emp.employee_id}</td>
            <td>${emp.last_name}</td>
            <td><a href="empDetail.do?empid=${emp.employee_id}">${emp.first_name}</a></td>
            <td>${emp.salary}</td>
            <td>${emp.department_id}</td>
            <td>${emp.manager_id}</td>
            <td>${emp.phone_number}</td>
            <td>${emp.commission_pct}</td>
            <td>${emp.hire_date}</td>
            <td>${emp.job_id}</td>
            <td>${emp.email}</td>
            <td>
                <button onclick="call(${emp.employee_id});">삭제</button>
            </td>
        </tr>
    </c:forEach>
</table>
<!-- 디렉티브태그: 합쳐서 컴파일한다. -->
<c:set var="cPath" value="${pageContext.request.contextPath }"/>
<!-- 컴파일 전에 경로에서 파일을 찾아서 합친다. -->
<%-- <%@ include file="${cPath}/common/footer.jsp" %> --%>

<%-- <jsp:include page="${cPath}/common.footer.jsp"></jsp:include> --%>

<script>
    function call(empid) {
        /* location.href는 주소창의 형식을 바꿔줌, get방식은 다 마찬가지 */
        location.href = "empDelete.do?empid=" + empid;
    }
</script>

</body>
</html>


