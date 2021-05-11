<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        form {
            border: 5px dotted green;
        }
    </style>
</head>
<body>
<h1>부서 신규 등록</h1>
<jsp:include page="../common/header.jsp"></jsp:include>
<form action="deptInsert.do" method="post">
    부서번호:<input type="number" name="department_id"><br>
    부서이름:<input type="text" name="department_name"><br>
    메니져ID 선택:
    <select name="manager_id">
        <c:forEach items="${managerlist}" var="m">
            <option value="${m.manager_id}">${m.fullname}
            </option>
        </c:forEach>
    </select>

    LOcationID 선택:
    <select name="location_id">
        <c:forEach items="${loclist}" var="loc">
            <option value="${loc.location_id}">${loc.city}
            </option>
        </c:forEach>

    </select>


    <input type="submit" value="입력하기">
</form>

</body>
</html>



