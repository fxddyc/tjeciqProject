<%--
  Created by IntelliJ IDEA.
  User: guoji
  Date: 2017/10/11 0011
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>动物疫情监测与预警系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Free HTML5 Template by FreeHTML5.co" />
    <meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />



    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <link rel="shortcut icon" href="favicon.ico">


    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminex/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/loginPage/animate.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/loginPage/style.css">


    <!-- Modernizr JS -->
    <script src="${pageContext.request.contextPath}/adminex/js/modernizr.min.js"></script>
    <!-- FOR IE9 below -->
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/loginPage/respond.min.js"></script>
    <![endif]-->

</head>
<body style='font-family: "Lucida Grande", Lucida Sans Unicode, Hiragino Sans GB, WenQuanYi Micro Hei, Verdana, Aril, sans-serif;'>

<div class="container">
    <div style="margin-top: 50px">
        <div style="height: 112px;width: 116px;text-align:center;margin:auto">
            <img src="${pageContext.request.contextPath}/images/ciq_logo.png" width="90%" height="90%"/>
        </div>
        <div class="text-center" >

            <h1 style="margin: 0;color: #080e1f">动物疫情监测与预警系统</h1>
                <%--<h1 style="margin: 0;color: #fff">动物疫情监测与预警系统</h1>--%>
        </div>
    </div>



    <div class="row">
        <div class="col-md-4 col-md-offset-4">


            <!-- Start Sign In Form -->
            <form action="#" class="fh5co-form animate-box" data-animate-effect="fadeIn" id='loginForm'>
                <h2>用户登录</h2>
                <div class="form-group">
                    <label for="userId" class="sr-only">Username</label>
                    <input type="text" class="form-control" id='userId' placeholder="请输入用户名" autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="passWd" class="sr-only">Password</label>
                    <input type="password" class="form-control" id='passWd' placeholder="请输入密码" autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="remember"><input type="checkbox" id="remember"> 记住用户名</label>
                </div>
                <div class="form-group text-center">
                    <input type="submit" value="  登录  " class="btn btn-primary btn-lg">
                </div>
                <div class="alert alert-danger" style="text-align:center;display:none" id="alertInfo">用户名或密码错误</div>
            </form>
            <!-- END Sign In Form -->

        </div>
    </div>

</div>

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/adminex/js/jquery-1.10.2.min.js"></script>
<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/adminex/js/bootstrap.min.js"></script>
<!-- Placeholder -->
<script src="${pageContext.request.contextPath}/loginPage/jquery.placeholder.min.js"></script>
<!-- Waypoints -->
<script src="${pageContext.request.contextPath}/loginPage/jquery.waypoints.min.js"></script>
<!-- Main JS -->
<script src="${pageContext.request.contextPath}/loginPage/main.js"></script>
<script>

    $('#loginForm').submit(function (e) {
        e.preventDefault();
        var userId = $('#userId').val();
        var passWd = $('#passWd').val();
        $.post('${pageContext.request.contextPath}/system/loginSubmit.do',{'userId':userId,'passWd':passWd},function (data) {
            if (data!==null){
                if(data.result===true){
                    window.location.href="${pageContext.request.contextPath}/oieDashboard/toOIEDashboardPage.do"
                }else {
                    $("#alertInfo").show();
                }
            }
        }, 'json')
    });

    $('#userId').focus(function () {
        $("#alertInfo").hide();
    });
    $('#passWd').focus(function () {
        $("#alertInfo").hide();
    });


</script>
</body>
</html>


