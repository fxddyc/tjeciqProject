<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 16/7/14
  Time: 上午11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="#" type="image/png">

    <title>登陆</title>

    <link href="${pageContext.request.contextPath}/adminex/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/adminex/css/style-responsive.css" rel="stylesheet">
</head>
<body class="login-body">

<div class="container">

    <form class="form-signin" id='loginForm'>
        <div class="form-signin-heading text-center">
            <h1 class="sign-title">天津检验检疫局疫情监测平台</h1>
            <img src="${pageContext.request.contextPath}/adminex/images/line_chart.png" alt=""/>
        </div>
        <div class="login-wrap">
            <input type="text" name="userId" id='userId'class="form-control" placeholder="请输入您的用户名..." value=""
                   autofocus>
            <input type="password" name="passWd" id='passWd' class="form-control" placeholder="请输入您的密码...">
            <button class="btn btn-lg btn-login btn-block" type="submit">
                <i class="fa fa-check"></i>
            </button>
        </div>
        <div class="alert alert-danger" style="text-align:center;display:none" id="alertInfo">用户名或密码错误</div>

    </form>

</div>


<!-- Placed js at the end of the document so the pages load faster -->

<!-- Placed js at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/adminex/js/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/modernizr.min.js"></script>
<script>

        $('#loginForm').submit(function (e) {
            e.preventDefault();
            var userId = $('#userId').val();
            var passWd = $('#passWd').val();
            $.post('${pageContext.request.contextPath}/system/loginSubmit.do',{'userId':userId,'passWd':passWd},function (data) {
                console.info(data);
                if (data!==null){
                    if(data.result===true){
                        window.location.href="${pageContext.request.contextPath}/system/systemMenu.do"
                    }else {
                        $("#alertInfo").show();
                    }
                }
            }, 'json')
        })

        $('#userId').focus(function () {
            $("#alertInfo").hide();
        });
        $('#passWd').focus(function () {
            $("#alertInfo").hide();
        });


</script>
</body>
</html>
