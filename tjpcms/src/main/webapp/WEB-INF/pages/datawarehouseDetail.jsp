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
                                    <div class="desk" id="epidemicName"></div>
                                </li>
                                <li>
                                    <div class="title">时间</div>
                                    <div class="desk" id="time"></div>
                                </li>
                            </ul>
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
                                    <h1 class=""><a id="title" href="#"></a></h1>
                                    <p id="time2" class=" auth-row">
                                    </p>

                                    <p id="content">
                                    </p>
                                    <a id="contentUrl" href="#" class="more">查看来源</a>
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
                                <div class="col-md-12" id="wordsBtn">

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
        $.post('${pageContext.request.contextPath}/dataWarehouse/datawarehouseDetail.do', {'rowKey': '${rowKey}'}, function (data) {
            var json = data;
            $("#epidemicName").text(json.epidemicName);
            $("#time").text(json.time);
            $("#time2").text(json.time);
            $("#content").html(json.content);
            $("#contentUrl").attr("href", json.contentUrl);
        }, 'json');


        $.post('${pageContext.request.contextPath}/dataWarehouse/datawarehouseWords.do', {'rowKey': '${rowKey}'}, function (data) {
            var json = data;
            for (var key in json) {
                $("#wordsBtn").append("<button class='btn btn-default' type='button'>" + key + "*" + json[key] + "</button>");
            }
        }, 'json');
    });
</script>


</body>
</html>
