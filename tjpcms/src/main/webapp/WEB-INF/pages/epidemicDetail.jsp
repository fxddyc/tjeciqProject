<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 16/8/10
  Time: 下午2:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>疫情详情</title>

    <link href="${pageContext.request.contextPath}/adminex/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/adminex/css/style-responsive.css" rel="stylesheet">
</head>
<body class="sticky-header">

<section>
    <%@include file="menu.jsp" %>
    <!-- main content start-->
    <div class="main-content">
        <%@include file="header.jsp" %>
        <!--body wrapper start-->
        <div class="wrapper">
            <div class="row blog">
                <div class="col-md-12">
                    <div class="blog">
                        <div class="single-blog">
                            <div class="panel">
                                <div class="panel-body">
                                    <h1 class="text-center mtop35"><a href="#">Neque porro quisquam est qui dolo rem
                                        ipsum
                                        quio</a></h1>
                                    <p class="text-center auth-row">
                                        By <a href="#">Anthony Jones</a> | 27 December 2014 | <a href="#">5 Comments</a>
                                    </p>
                                    <div class="blog-img-wide">
                                        <img src="${pageContext.request.contextPath}/adminex/images/blog/blog-wide-img.jpg" alt="">
                                    </div>
                                    <p>
                                        Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium
                                        doloremque
                                        laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et
                                        quasi
                                        architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia
                                        voluptas
                                        sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui
                                        ratione
                                        voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia
                                        dolor
                                        sit
                                        amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora
                                        incidunt
                                        ut
                                        labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam,
                                        quis
                                        nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea
                                        commodi
                                        consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit
                                        esse
                                        quam
                                        nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla
                                        pariatur?
                                    </p>
                                    <p>
                                        Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed
                                        quia
                                        consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque
                                        porro
                                        quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci
                                        velit,
                                        sed
                                        quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam
                                        quaerat
                                        voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis
                                        suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel
                                        eum
                                        iure
                                        reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur,
                                        vel
                                        illum
                                        qui dolorem eum fugiat quo voluptas nulla pariatur?
                                    </p>

                                    <blockquote>
                                        <p>
                                            Vestibulum id ligula porta felis euismod semper. Sed posuere consectetur est
                                            at
                                            lobortis. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis
                                            vestibulum. Duis mollis, est non commodo luctus, nisi erat port titor
                                            ligula,
                                            eget
                                            lacinia odio sem nec elit.
                                        </p>
                                    </blockquote>

                                    <p>
                                        consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut
                                        labore
                                        et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis
                                        nostrum
                                        exercitationem ullam corporis suscipit laboriosam.

                                    </p>

                                    <div class="blog-tags">
                                        TAGS <a href="#">photoshop</a> <a href="#">illustrator</a> <a href="#">adobe</a>
                                        <a
                                                href="#">theme</a>
                                        <div class="pull-right tag-social">
                                            <a href="#" class="btn btn-share pull-right">Share</a>

                                            <ul class="pull-right">
                                                <li>
                                                    <a href="#">
                                                        <i class="fa fa-facebook"></i>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <i class="fa fa-twitter"></i>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <i class="fa fa-google-plus"></i>
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <!--body wrapper end-->
</section>

<!-- Placed js at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/adminex/js/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/modernizr.min.js"></script>


<!--common scripts for all pages-->
<script src="${pageContext.request.contextPath}/adminex/js/scripts.js"></script>

</body>
</html>
