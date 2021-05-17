<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>수많은 작업들이 있지</title>
    <style>
        div {
            text-align: center;
        }

        .deactive {
            display: none;
        }

        .active {
            display: block;
        }
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <script>
        $(function () {

            $("input[name='jobSelect']").on("change", function () {
                var _this = $(this).val();
                $("div[class]").each(function (index, item) {
                    if ($(item).attr("id") == _this) {
                        $(item).removeClass("deactive");
                        $(item).addClass("active");
                    } else {
                        $(item).removeClass("active");
                        $(item).addClass("deactive");
                    }
                });
            });

            /* $("input[name='jobSelect']").click(function() {
               var check = $("input[name='jobSelect']:checked").val();
               $("div[class]").attr("class", "deactive");
               $('#' + check).attr("class", "active");
            }); */
        });
    </script>

    <script>
        $(function () {
            $("#loginButton").click(function () {
                /*var obj={"userid":$("#userid").val(), "userpass":$("#userpass").val(), "address":$("#address").val(), "phone":$("#phone").val()};
                $.ajax({
                   url:"


                ${pageContext.request.contextPath}/emp2/login3.do",
            type: "post",
            data: JSON.stringify(obj),
            contentType:"application/json",
            success: function(responseData){
               console.log(responseData);
               $("#here").html(JSON.stringify(responseData));
            }
         }); */

                var param = jQuery("#userid").val() + "/";
                param += jQuery("#userpass").val() + "/";
                param += jQuery("#address").val() + "/";
                param += jQuery("#phone").val();
                //alert(param);
                $.ajax({
                    url: "${pageContext.request.contextPath}/emp2/login3.do/" + param,
                    type: "get",
                    success: function (responseData) {
                        console.log(responseData);
                        $("#here").html(JSON.stringify(responseData));
                    }
                });
            });
        });
    </script>

    <script>
        function resultPrint(responseData) {
            var output = "<ul>";
            $.each(responseData, function (index, item) {
                output += "<li>" + item["first_name"] + "</li>";
            });
            output += "</ul>";
            $("#here").html(output);
        }

        $(function () {
            $("#salButton").click(function () {
                var minsal = $("#minsal").val();
                var maxsal = $("#maxsal").val();
                console.log(minsal, maxsal);
                $.ajax({
                    url: "${pageContext.request.contextPath}/emp2/empBySal.do/" + minsal + "/" + maxsal,
                    type: "get",
                    success: function (responseData) {
                        resultPrint(responseData);
                    }
                });
            });
        });

        $(function () {
            $("#hireDateBtn").click(function () {
                var sdate = $("#sdate").val();
                var edate = $("#edate").val();
                console.log(sdate, edate);
                $.ajax({
                    url: "${pageContext.request.contextPath}/emp2/empByDate.do/" + sdate + "/" + edate,
                    type: "get",
                    success: function (responseData) {
                        resultPrint(responseData);
                    }
                });
            });
        });

        $(function () {
            $("#hireDateBtn2").click(function () {
                var sdate2 = $("#sdate2").val();
                var edate2 = $("#edate2").val();
                console.log(sdate2, edate2);
                $.ajax({
                    url: "${pageContext.request.contextPath}/emp2/empByDate2.do/" + sdate2 + "/" + edate2,
                    type: "get",
                    success: function (responseData) {
                        resultPrint(responseData);
                    }
                });
            });
        });

        $(function () {
            $("#dynamicBtn").click(function () {
                var deptid2 = $("#deptid2").val();
                if(deptid2=="")deptid2 = 0;
                var jobid2 = $("#jobid2").val();
                if(jobid2=="")jobid2 = null;
                var sal2 = $("#sal2").val();
                if(sal2=="")sal2 = 0;
                var hdate2 = $("#hdate2").val();
                var chk2 = $("#chk2:checked").val();

                console.log(deptid2, jobid2, sal2, hdate2, chk2);
                $.ajax({
                    url: "${pageContext.request.contextPath}/emp2/empByCondition.do/"
                        + deptid2 + "/" + jobid2 + "/" + sal2 + "/" + hdate2 + "/" + chk2,
                    type: "get",
                    success: resultPrint
                });
            });
        });
    </script>

    <script>
        $(function () {
            $("#deptButton").click(function () {
                var deptid = $("input[name='deptid']").first().val();
                $.ajax({
                    url: "${pageContext.request.contextPath}/emp2/empByDept.do/" + deptid,
                    type: "get",
                    success: function (responseData) {
                        console.log(responseData);
                        var output = "<ul>";
                        $.each(responseData, function (index, item) {
                            output += "<li>" + item["first_name"] + "</li>";
                        });
                        output += "</ul>";
                        $("#here").html(output);
                    }
                });
            });
        });
    </script>
</head>
<body>
<div>
    <fieldset>
        <legend>여러가지 작업들</legend>
        <input type="radio" name="jobSelect" value="login" checked="checked"> 로그인하기
        <input type="radio" name="jobSelect" value="dept"> 부서 조회
        <input type="radio" name="jobSelect" value="sal"> 급여 조회
        <input type="radio" name="jobSelect" value="hiredate"> 입사일 조회 (String)
        <input type="radio" name="jobSelect" value="hiredate2"> 입사일 조회 (date)
        <input type="radio" name="jobSelect" value="dynamic"> 동적 조회
    </fieldset>
</div>

<c:set var="cpath" value="${pageContext.request.contextPath}"/>

<div id="login" class="active">
    <h1>로그인하기</h1>
    <form>
        아이디:<input type="number" name="userid" id="userid" value="100"><br>
        비밀번호:<input type="password" name="userpass" id="userpass" value="SKING"><br>
        <input type="hidden" name="address" id="address" value="서울시"><br>
        <input type="hidden" name="phone" id="phone" value="010-0000-0000"><br>
        <input type="button" value="로그인" id="loginButton">
    </form>
</div>

<div id="dept" class="deactive">
    <h1>부서로 조회하기</h1>
    <form>
        <input type="number" name="deptid" value="60"><br>
        <input type="button" value="부서로 조회" id="deptButton">
    </form>
</div>

<div id="sal" class="deactive">
    <h1>salary로 조회하기</h1>
    <form>
        <input type="number" id="minsal" value="15000"><br>
        <input type="number" id="maxsal" value="20000"><br>
        <input type="button" value="급여로 조회" id="salButton">
    </form>
</div>


<div id="hiredate" class="deactive">
    <h1>일자로 조회하기</h1>
    <form>
        시작일 : <input type="date" id="sdate" value="2005-01-01"><br>
        종료일 : <input type="date" id="edate" value="2005-12-31"><br>
        <input type="button" value="일자로 조회" id="hireDateBtn">
    </form>
</div>

<div id="hiredate2" class="deactive">
    <h1>일자로 조회하기</h1>
    <form>
        시작일 : <input type="date" id="sdate2" value="2005-01-01"><br>
        종료일 : <input type="date" id="edate2" value="2005-12-31"><br>
        <input type="button" value="일자로 조회" id="hireDateBtn2">
    </form>
</div>

<div id="dynamic" class="deactive">
    <h1>동적으로 조회하기</h1>
    <form>
        부서 : <input type="number" id="deptid2" value="60"><br>
        직책 : <input type="text" id="jobid2" value="IT_PROG"><br>
        급여 : <input type="number" id="sal2" value="10000"><br>
        시작일 : <input type="date" id="hdate2" value="2005-01-01">
        <input type="checkbox" id="chk2" checked="checked"/> <br>
        일자조회여부
        <input type="button" value="동적 조회" id="dynamicBtn">
    </form>
</div>
<div id="here"></div>

</body>
</html>