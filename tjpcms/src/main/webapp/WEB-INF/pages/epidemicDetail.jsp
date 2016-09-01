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
                <div class="col-md-4">
                    <div class="panel">
                        <div class="panel-body">
                            <ul class="p-info">
                                <li>
                                    <div class="title">疫情名称</div>
                                    <div class="desk">${epidemicAppear.epidemic.epidemicName}</div>
                                </li>
                                <li>
                                    <div class="title">地域</div>
                                    <div class="desk">${epidemicAppear.region.regionCn}</div>
                                </li>
                                <li>
                                    <div class="title">发生次数</div>
                                    <div class="desk">${epidemicAppear.appearTimes}</div>
                                </li>
                                <li>
                                    <div class="title">爆发时间</div>
                                    <div class="desk">${epidemicAppear.appearDate}</div>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <div class="panel">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <h4>疫情百科</h4>
                                </div>
                                <div class="col-md-12">
                                    <div class="blog-img-sm">
                                        <img id="epidemicBaiKeImg" src="#"
                                             alt="">
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <p id="baikeSummary">

                                    </p>
                                    <a id="baikeContentUrl" href="#" class="more">查看原文</a>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>

                <div class="col-md-8">
                    <div class="panel">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <h4>信息来源</h4>
                                </div>
                                <div class="col-md-12">
                                    <h1 class=""><a id="epidemicSourceTitle" href="#"></a></h1>
                                    <p id="epidemicSourceTime" class=" auth-row">
                                    </p>

                                    <p id="epidemicSourceContent">
                                    </p>
                                    <a id="epidemicSourceUrl" href="${epidemicAppear.rowKey}" class="more">查看来源</a>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="panel">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <h4>信息分词</h4>
                                </div>
                                <div class="col-md-12">
                                    <button class="btn btn-default" type="button">Default</button>
                                    <button class="btn btn-default" type="button">Default</button>
                                    <button class="btn btn-default" type="button">Default</button>
                                    <button class="btn btn-default" type="button">Default</button>
                                    <button class="btn btn-default" type="button">Default</button>
                                    <button class="btn btn-default" type="button">Default</button>
                                    <button class="btn btn-default" type="button">Default</button>
                                    <button class="btn btn-default" type="button">Default</button>
                                    <button class="btn btn-default" type="button">Default</button>
                                    <button class="btn btn-default" type="button">Default</button>
                                    <button class="btn btn-default" type="button">Default</button>
                                    <button class="btn btn-default" type="button">Default</button>
                                    <button class="btn btn-default" type="button">Default</button>
                                    <button class="btn btn-default" type="button">Default</button>
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

<script>
    $(document).ready(function () {
        $.post('${pageContext.request.contextPath}/hbaseController/epidemicBaike.do', {'rowKey': '${epidemicAppear.epidemic.id}'}, function (data) {
            var json = data;
            $("#epidemicBaiKeImg").attr("src", json.imgUrl);
            $("#baikeSummary").text(json.summary);
            $("#baikeContentUrl").attr("href", json.contentUrl);
        }, 'json');


        $.post('${pageContext.request.contextPath}/hbaseController/epidemicSource.do', {'rowKey': '${epidemicAppear.rowKey}'}, function (data) {
            var json = data;
            $("#epidemicSourceTitle").text(json.epidemicName);
            $("#epidemicSourceTime").text(json.time);
            $("#epidemicSourceContent").append(json.content);
        }, 'json');
    });
</script>


</body>
</html>
