<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 16/7/14
  Time: 下午12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-sweetalert-master/dist/sweetalert.css">
    <script src="${pageContext.request.contextPath}/bootstrap-sweetalert-master/dist/sweetalert.js"></script>
</head>
<body>
<!-- header section start-->
<div class="header-section">

    <!--toggle button start-->
    <a class="toggle-btn"><i class="fa fa-bars"></i></a>
    <!--toggle button end-->

    <!--search start-->
    <form class="searchform" action="http://view.jqueryfuns.com/2014/4/10/7_df25ceea231ba5f44f0fc060c943cdae/index.html" method="post">
    </form>
    <!--search end-->

    <!--notification menu start -->
    <div class="menu-right">
        <ul class="notification-menu">
            <li>
                <a href="#" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <img src="${pageContext.request.contextPath}/adminex/images/photos/user-avatar.png" alt="" />
                    admin
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu dropdown-menu-usermenu pull-right">
                    <li><a id="signout"><i class="fa fa-sign-out"></i>退出登录</a></li>
                </ul>
            </li>

        </ul>
    </div>
    <!--notification menu end -->

</div>
<!-- header section end-->
</body>
</html>
<script type="text/javascript">
    $('#signout').click(function () {
        swal({
                title: '是否退出登录',
                type: "info",
                showCancelButton: true,
                cancelButtonText:"否",
                confirmButtonColor:"#DD6B55",
                confirmButtonText: "是",
                closeOnConfirm: false
            },
            function(){
                $.post('${pageContext.request.contextPath}/system/signOut.do',null,function (data) {}, 'json').complete(function () {
                    window.location.href='${pageContext.request.contextPath}/system/login.do';
                });

            });

    });
</script>