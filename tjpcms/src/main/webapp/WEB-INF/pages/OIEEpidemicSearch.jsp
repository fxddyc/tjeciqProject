<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>OIE信息综合查询</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="keywords"
          content="admin, dashboard, bootstrap, template, flat, modern, theme, responsive, fluid, retina, backend, html5, css, css3">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="#" type="image/png">

    <title>AdminX</title>


    <!--common-->
    <link href="${pageContext.request.contextPath}/adminex/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/adminex/css/style-responsive.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/adminex/fonts/css/font-awesome.min.css" rel="stylesheet">


    <!--pickers css-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/adminex/js/bootstrap-datepicker/css/datepicker-custom.css"/>





</head>
<body class="sticky-header">


<section>
    <%@include file="menu.jsp" %>
    <!-- main content start-->
    <div class="main-content">
        <%@include file="header.jsp" %>
        <!--body wrapper start-->
        <div class="wrapper">


            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            OIE信息综合查询
                        </div>

                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-3 form-group">
                                    <label class="control-label">疫病名称</label>
                                    <input type="text" id='epidemicName' class="form-control" placeholder="疫病名称">
                                </div>
                                <div class="col-lg-3 form-group">
                                    <label class="control-label">疫病类型</label>
                                    <input type="text" id='epidemicClass' class="form-control" placeholder="疫病类型">
                                </div>
                                <div class="col-lg-3 form-group">
                                    <label class="control-label">地域名称</label>
                                    <input type="text" id="region" class="form-control" placeholder="地域名称">
                                </div>
                                <div class="col-lg-3 form-group">
                                    <label class="control-label">时间段</label>
                                    <div data-date-minviewmode="months" data-date-viewmode="years"
                                         class="input-group input-large custom-date-range " data-date="102/2012"
                                         data-date-format="yyyy-mm-dd">
                                        <input type="text" id="startDate" class="form-control dpd1" name="from">
                                        <span class="input-group-addon">到</span>
                                        <input type="text" id="endDate" class="form-control dpd2" name="to">
                                    </div>

                                </div>
                            </div>

                                <button class="btn btn-primary" onclick="search();">查询</button>


                        </div>

                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <section class="panel">
                                <header class="panel-heading">
                                    查询结果
                                    <span class="tools pull-right">
                                <button class="btn btn-primary" onclick="exExcel()">
                                导出excel
                                </button>
                            </span>
                                </header>
                                <div class="panel-body" style="display: block;">
                                    <section id="unseen">
                                        <table class="table table-bordered table-striped table-condensed">
                                            <thead>
                                            <tr>
                                                <th>疫病名称</th>
                                                <th>疫病分类</th>
                                                <th>国家名称</th>
                                                <th class="numeric">公布时间</th>
                                                <th class="numeric">公布理由</th>
                                                <th class="numeric">爆发次数</th>
                                                <th class="numeric">临床表现</th>
                                                <th class="numeric">解决时间</th>
                                            </tr>
                                            </thead>
                                            <tbody align="center" id="epidmicData">
                                            <%--<tr>--%>
                                                <%--<td><p>牛结节性皮肤病</p><p>Lumpy skin disease</p></td>--%>
                                                <%--<td >A类</td>--%>
                                                <%--<td class="numeric"><p>俄罗斯</p><p>Russia</p></td>--%>
                                                <%--<td class="numeric">2017-06-13</td>--%>
                                                <%--<td class="numeric">Recurrence</td>--%>
                                                <%--<td class="numeric">1</td>--%>
                                                <%--<td class="numeric">Clinical disease</td>--%>
                                                <%--<td class="numeric">Continuing</td>--%>
                                                <%--<td class="numeric">--%>
                                                    <%--<button class='btn'>详细信息</button>--%>
                                                    <%--<button class='btn'>原文链接</button>--%>
                                                <%--</td>--%>
                                            <%--</tr>--%>
                                            <%--<tr>--%>
                                                <%--<td><p>甲型高致病性流感</p><p>Highly pathogenic influenza A viruses</p></td>--%>
                                                <%--<td >B类</td>--%>
                                                <%--<td class="numeric"><p>俄罗斯</p><p>Russia</p></td>--%>
                                                <%--<td class="numeric">2017-01-16</td>--%>
                                                <%--<td class="numeric">Recurrence</td>--%>
                                                <%--<td class="numeric">3</td>--%>
                                                <%--<td class="numeric">Clinical disease</td>--%>
                                                <%--<td class="numeric">29/03/2017</td>--%>
                                                <%--<td class="numeric">--%>
                                                    <%--<button class='btn'>详细信息</button>--%>
                                                    <%--<button class='btn'>原文链接</button>--%>
                                                <%--</td>--%>
                                            <%--</tr>--%>
                                            <%--<tr>--%>
                                                <%--<td><p>牛结节性皮肤病</p><p>Lumpy skin disease</p></td>--%>
                                                <%--<td >A类</td>--%>
                                                <%--<td class="numeric"><p>俄罗斯</p><p>Russia</p></td>--%>
                                                <%--<td class="numeric">2016-05-30</td>--%>
                                                <%--<td class="numeric">Recurrence</td>--%>
                                                <%--<td class="numeric">313</td>--%>
                                                <%--<td class="numeric">Clinical disease</td>--%>
                                                <%--<td class="numeric">15/02/2017</td>--%>
                                                <%--<td class="numeric">--%>
                                                    <%--<button class='btn'>详细信息</button>--%>
                                                    <%--<button class='btn'>原文链接</button>--%>
                                                <%--</td>--%>
                                            <%--</tr>--%>
                                            <%--<tr>--%>
                                                <%--<td><p>高致病性禽流感</p><p>Highly path. avian influenza</p></td>--%>
                                                <%--<td >A类</td>--%>
                                                <%--<td class="numeric"><p>俄罗斯</p><p>Russia</p></td>--%>
                                                <%--<td class="numeric">2016-11-23</td>--%>
                                                <%--<td class="numeric">Recurrence</td>--%>
                                                <%--<td class="numeric">2</td>--%>
                                                <%--<td class="numeric">Clinical disease</td>--%>
                                                <%--<td class="numeric">30/12/2016</td>--%>
                                                <%--<td class="numeric">--%>
                                                    <%--<button class='btn'>详细信息</button>--%>
                                                    <%--<button class='btn'>原文链接</button>--%>
                                                <%--</td>--%>
                                            <%--</tr>--%>



                                            </tbody>
                                            <tfoot>
                                            <tr>
                                                <div class="dataTables_paginate paging_bootstrap pagination">
                                                    <ul id="demo1"></ul>
                                                </div>
                                            </tr>
                                            </tfoot>
                                        </table>
                                    </section>
                                </div>
                            </section>
                        </div>
                    </div>

                </div>
            </div>

            <%--</div>--%>


            <!--body wrapper end-->
        </div>
    </div>
    <!-- main content end-->
</section>

<!-- Placed js at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/adminex/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/typeahead/bootstrap3-typeahead.min.js"/>
<script src="${pageContext.request.contextPath}/adminex/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/modernizr.min.js"></script>
<!--common scripts for all pages -->
<%--<script src="${pageContext.request.contextPath}/adminex/js/scripts.js"></script>--%>
<script src="${pageContext.request.contextPath}/echarts/build/dist/echarts-all.js"></script>
<script src="${pageContext.request.contextPath}/angularjs/angular.min.js"></script>

<%--<!--pickers plugins-->--%>
<%--<script type="text/javascript"--%>
<script src="${pageContext.request.contextPath}/adminex/js/bootstrap-datepicker/js/bootstrap-datepicker.js?v=1"></script>

<%--<!--pickers initialization-->--%>
<script src="${pageContext.request.contextPath}/adminex/js/pickers-init.js"></script>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/jqPaginator/dist/1.2.0/jqPaginator.min.js"></script>




<script type="text/javascript">
    $("td").css("vertical-align","middle");

    var conditionEpidemicName = '';
    var conditionRegion = '';
    var conditionEpidemicClass = '';
    var conditionStartDate = '';
    var conditionEndDate = '';

    function pageUtils() {
        $.post('${pageContext.request.contextPath}/oieEpidemicSearch/epidemicEventList.do', {
            'epidemicName':conditionEpidemicName,
            'epidemicClass':conditionEpidemicClass,
            'region': conditionRegion,
            'startDate': conditionStartDate,
            'endDate': conditionEndDate
        }, function (data) {
            $("#demo1").jqPaginator({
                totalPages: Math.ceil(data.epidemicEventListCount / 10),
                visiblePages: 10,
                currentPage: 1,
                first: '<li class="first"><a href="javascript:void(0);">首页<\/a><\/li>',
                prev: '<li class="prev"><a href="javascript:void(0);">上一页<\/a><\/li>',
                next: '<li class="next"><a href="javascript:void(0);">下一页<\/a><\/li>',
                last: '<li class="last"><a href="javascript:void(0);">尾页<\/a><\/li>',
                page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
                onPageChange: function (n) {
                    $("#epidmicData").empty();
                    $.post('${pageContext.request.contextPath}/oieEpidemicSearch/epidemicEventList.do', {
                        'epidemicName':conditionEpidemicName,
                        'epidemicClass':conditionEpidemicClass,
                        'region': conditionRegion,
                        'startDate': conditionStartDate,
                        'endDate': conditionEndDate
                    }, function (data) {
                        var epidemicAppearList = data.epidemicEventList;
                        for (var i = 0; i < epidemicAppearList.length; i++) {
                            var tr="";

                            if(epidemicAppearList[i].epidemicNameCn !=null){
                                var td = "<td style=''><p>"+epidemicAppearList[i].epidemicNameCn + "</p><p>" + epidemicAppearList[i].epidemicNameEng+"</p></td>";
                                tr = tr+td;
                            }else {
                                var td= "<td style=''><p>" + epidemicAppearList[i].disease + "</p></td>";
                                tr = tr+td;
                            }
                            tr = tr+"<td style=''><p>" + epidemicAppearList[i].diseaseClass + "</p></td>";
                            if (epidemicAppearList[i].regionNameCn!= null){
                                var td = "<td style=''><p>" + epidemicAppearList[i].regionNameCn + "</p><p>" + epidemicAppearList[i].regionNameEng +"</p></td>"
                                tr = tr+td;
                            }else {
                                var td= "<td style=''><p>" + epidemicAppearList[i].country + "</p></td>";
                                tr = tr+td;
                            }
                            tr = tr+"<td style=''><p>" + epidemicAppearList[i].date + "</p></td>" +
                                "<td style=''><p>" + epidemicAppearList[i].reason + "</p></td>" +
                                "<td style=''><p>" + epidemicAppearList[i].outbreaks + "</p></td>" +
                                "<td style=''><p>" + epidemicAppearList[i].manifestation + "</p></td>" +
                                "<td style=''>" + "<a href='${pageContext.request.contextPath}/epidemic/epidemicDetail.do?reportId=" + epidemicAppearList[i].report + "'><button class='btn btn-primary'>详细信息</button></a>" + "</td>" + "</tr>";
                            $("#epidmicData").append("<tr>"+tr+"</tr>");
                            <%--$("#epidmicData").append("<tr><td style=''><p>" + epidemicAppearList[i].epidemicNameCn + "</p><p>" + epidemicAppearList[i].epidemicNameEng +"</p></td>" +--%>
                                <%--"<td style=''><p>" + epidemicAppearList[i].diseaseClass + "</p></td>" +--%>
                                <%--"<td style=''><p>" + epidemicAppearList[i].regionNameCn + "</p><p>" + epidemicAppearList[i].regionNameEng +"</p></td>" +--%>
                                <%--"<td style=''><p>" + epidemicAppearList[i].date + "</p></td>" +--%>
                                <%--"<td style=''><p>" + epidemicAppearList[i].reason + "</p></td>" +--%>
                                <%--"<td style=''><p>" + epidemicAppearList[i].outbreaks + "</p></td>" +--%>
                                <%--"<td style=''><p>" + epidemicAppearList[i].manifestation + "</p></td>" +--%>
                                <%--"<td style=''>" + "<a href='${pageContext.request.contextPath}/epidemic/epidemicDetail.do?reportId=" + epidemicAppearList[i].report + "'><button class='btn btn-primary'>详细信息</button></a>" + "</td>" + "</tr>"--%>
                            <%--);--%>
                            $("td").css("vertical-align","middle");
                        }
                    }, 'json');
                }
            });
        }, 'json');
    }

    function initConditions() {
        conditionEpidemicName = '';
        conditionRegion = '';
        conditionStartDate = '';
        conditionEndDate = '';
        conditionEpidemicClass = '';
    }

    function search() {
        initConditions();
        conditionEndDate = $('#endDate').val();
        conditionEpidemicName = $('#epidemicName').val();
        conditionRegion = $('#region').val();
        conditionStartDate = $('#startDate').val();
        conditionEpidemicClass = $('#epidemicClass').val();
        pageUtils();
    }

    function checkNews(){
        swal({
                title: "有新增疫情报告，是否查看？",
                text: "俄罗斯 新增 A类 牛结节性皮肤病 \n\r 荷兰 新增 B类 美洲蜂幼虫腐臭病 \n\r",
                type: "info",
                showCancelButton: true,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "查看详情",
                closeOnConfirm: false
            },
            function(){
                window.location.href="${pageContext.request.contextPath}/oieEpidemicSearch//toOIEEpidemicSearchPage.do";
            });
    };

    $(document).ready(function () {
        checkNews();
        var flag = '${flag}';
        if (flag != null && flag != '') {

        } else {
            pageUtils();
        }

        $.post('${pageContext.request.contextPath}/oieEpidemicSearch/regionList.do', null, function (data) {
            $('#region').typeahead({source: data})
        }, 'json');
        $.post('${pageContext.request.contextPath}/oieEpidemicSearch/epidemicKindList.do', null, function (data) {
            $('#epidemicClass').typeahead({source: data})
        }, 'json');

        $.post('${pageContext.request.contextPath}/oieEpidemicSearch/epidemicNameList.do', null, function (data) {
            $('#epidemicName').typeahead({source: data})
        }, 'json');



    });
</script>
</body>
</html>