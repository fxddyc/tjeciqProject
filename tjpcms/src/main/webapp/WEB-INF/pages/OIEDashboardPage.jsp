
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="keywords"
          content="admin, dashboard, bootstrap, template, flat, modern, theme, responsive, fluid, retina, backend, html5, css, css3">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="#" type="image/png">

    <title>OIE疫情概览</title>

    <!--dashboard calendar-->
    <link href="${pageContext.request.contextPath}/adminex/css/clndr.css" rel="stylesheet">
    <!--common-->
    <link href="${pageContext.request.contextPath}/adminex/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/adminex/css/style-responsive.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/adminex/fonts/css/font-awesome.min.css" rel="stylesheet">
</head>
<body class="sticky-header">
<section>

    <%@include file="menu.jsp" %>
    <!-- main content start-->
    <div class="main-content">
        <%@include file="header.jsp" %>
        <div class="page-heading"></div>
        <!--body wrapper start-->
        <div class="wrapper">
            <div class="row">
                <div class="col-md-6">
                    <!--statistics start-->
                    <div class="row state-overview">
                        <div class="col-md-6 col-xs-12 col-sm-6">
                            <div class="panel purple">
                                <div class="symbol">
                                    <i class="fa fa-gavel"></i>
                                </div>
                                <div class="state-value">
                                    <div class="value">0</div>
                                    <div class="title">昨日疫情爆发次数</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-xs-12 col-sm-6">
                            <div class="panel red">
                                <div class="symbol">
                                    <i class="fa fa-tags"></i>
                                </div>
                                <div class="state-value">
                                    <div class="value">0</div>
                                    <div class="title">昨日新增报告数</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row state-overview">
                        <div class="col-md-6 col-xs-12 col-sm-6">
                            <div class="panel blue">
                                <div class="symbol">
                                    <i class="fa fa-money"></i>
                                </div>
                                <div class="state-value">
                                    <div class="value">2</div>
                                    <div class="title">7日内累计爆发次数</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-xs-12 col-sm-6">
                            <div class="panel green">
                                <div class="symbol">
                                    <i class="fa fa-eye"></i>
                                </div>
                                <div class="state-value">
                                    <div class="value">22</div>
                                    <div class="title">30日内累计爆发次数</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--statistics end-->
                </div>

                <div class="col-md-6"  >
                    <div class="panel" id="diseaseClassPie" style="height:260px"></div>
                </div>
            </div>
            <!--row end-->

            <div class="row">
                <div class="col-md-12" >
                    <div class="panel panel-default" >
                        <div class="panel-body" >
                            <div id="calendarHeatMap" style="height:650px;"></div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div>
            <button class="btn btn-info" onclick="openDetailMod()"></button>
        </div>
        <div class="modal fade col-lg-12" id="detailMod" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="width:1000px">
                <div class="modal-content">
                    <div class="modal-header" style="background-color: #2a6496">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" id="myModalLabel">
                            疫情列表
                        </h4>
                    </div>
                    <div>
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
                                    <th class="numeric">操作</th>
                                </tr>
                                </thead>
                                <tbody align="center" id="epidmicData">
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
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>


        <!--body wrapper end-->
    </div>
    <!-- main content end-->
</section>

<!-- Placed js at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/adminex/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/typeahead/bootstrap3-typeahead.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/modernizr.min.js"></script>

<script src="${pageContext.request.contextPath}/echarts/build/dist/echarts3-7-1.min.js"></script>

<script src="${pageContext.request.contextPath}/hcharts/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/hcharts/highcharts-3d.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/jqPaginator/dist/1.2.0/jqPaginator.min.js"></script>

</body>

<script type="application/javascript">
    var dateIntervalCondition = 30;

    function openDetailMod(startDate,endDate,epidemicClass) {
        getEpidemicEventList(startDate,endDate,epidemicClass);
    }


    var chart = null;
    var diseaseClassPieOption = {
        chart: {
            type:'pie',
            plotBackgroundColor:'#49586e',
            plotBorderWidth: null,
            plotShadow: false,
            spacing : [0, 0 , 0, 0],
            options3d: {
                enabled: true
            },
            height: 260

        },
        credits: {
            enabled: false
        },
        legend:{
            verticalAlign:'middle',
            backgroundColor:'#49586e',
            layout:"vertical",
            floating:true,
            itemStyle: {
                color: '#ffffff',
                fontWeight: 'normal',
                fontSize:'18px'
            },
            itemDistance:50,
            x:200
        },
        title: {
            floating:true,
            align:'center',
            y:1000,
            text:'上周各类疫情爆发占比',
            style:{
                color:'#ffffff',
                fontSize:'20px',
                fontWeight: "bold"
            },
//            useHTML:true,
            widthAdjust: -1000
        },
        tooltip: {
            pointFormat: '占比 <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                innerSize: "70%",
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend:true,
                point: {
                    events: {
                        mouseOver: function(e) {
                            chart.setTitle({
                                text: e.target.name+ '疫情共爆发'+ e.target.y + ' 起\n',
                                style:{
                                    color:'#ffffff',
                                    fontSize:'20px',
                                    fontWeight: "bold"
                                },
                                widthAdjust: -300
                            });
                        }
                        ,
                         click: function(e) { // 同样的可以在点击事件里处理
                             openDetailMod(null,null,e.point.name);
                         }
                    }
                }
            }
        },
        series: [{
            type:'pie',
            data: []
        }]
    };


    var calendarHeatMapOption={
        tooltip: {
            position: 'top',
            formatter: function (params) {
                return params.marker+params.value[0]+' 共爆发 ' + params.value[1]+' 例疫情 ';
            }
        },
        visualMap: {
            min: 0,
            max: 500,
            calculable: true,
            orient: 'horizontal',
            left: 'center',
            top: 'top'
        },

        calendar: [

            {
                range: '2015',
                cellSize: ['auto',20],
                dayLabel: {
                    firstDay: 1,
                    nameMap: 'cn'
                },
                monthLabel:{
                    nameMap: 'cn'
                }
            },
            {
                top: 260,
                range: '2016',
                cellSize: setCalendarSize(),
                dayLabel: {
                    firstDay: 1,
                    nameMap: 'cn'
                },
                monthLabel:{
                    nameMap: 'cn'
                }
            },
            {
                top: 450,
                range: '2017',
                cellSize:setCalendarSize() ,
                dayLabel: {
                    firstDay: 1,
                    nameMap: 'cn'
                },monthLabel:{
                nameMap: 'cn'
            }
            }],

        series: []
    };

    for(var i=0;i<calendarHeatMapOption.calendar.length;i++){
        calendarHeatMapOption.calendar[i].cellSize=setCalendarSize();
    }
    
    function setCalendarSize() {
        var wn = document.body.clientWidth;
        if(wn>=1450){
            return [20,20]
        }else if (1450>wn&&wn>=1200){
            return ['auto',15]
        }else {
            return ['auto',10]
        }

    }



    function findDiseaseClassData() {
        $.post('${pageContext.request.contextPath}/oieDashboard/getDiseaseClassPieData.do', {
                'dateInterval': dateIntervalCondition
            },
            function (data) {
                if (data!=null&&data.length>0){
                    var dataList = [];
                    for (var i=0;i<data.length;i++){
                        var param = [];
                        diseaseName = data[i].diseaseClass;
                        param.push(diseaseName);
                        param.push(data[i].ttc);
                        dataList.push(param)
                    }
                    diseaseClassPieOption.series[0].data=dataList;
                    $('#diseaseClassPie').highcharts(diseaseClassPieOption,function(c) {
                        var centerY = c.series[0].center[1],
                            titleHeight = parseInt(c.title.styles.fontSize);
                        c.setTitle({
                            y:centerY + titleHeight/2
                        });
                        chart = c;
                    });
                }
            }, 'json');
    }

    function findCalendarHeatMapData() {
        $.post('${pageContext.request.contextPath}/oieDashboard/getCalendarHeatMapData.do',
                null,
            function (data) {
                if (data!=null){
                    for (var key in data){
                        var yearDataList = data[key];
                        var CHSeriesOption = {
                            type: 'heatmap',
                            coordinateSystem: 'calendar',
                            calendarIndex: 0,
                            data: []
                        };
                        CHSeriesOption.data=yearDataList;
                        CHSeriesOption.calendarIndex=calendarHeatMapOption.series.length;
                        calendarHeatMapOption.series.push(CHSeriesOption);
                    }
                }
                var calendarHeatMapChart = echarts.init(document.getElementById('calendarHeatMap'));
                calendarHeatMapChart.setOption(calendarHeatMapOption);
                calendarHeatMapChart.on('click', function (params) {
                    openDetailMod(params.value[0],params.value[0]);
                });

            }, 'json');
    }


    function getEpidemicEventList(startDate,endDate,epidemicClass) {
        $.post('${pageContext.request.contextPath}/oieDashboard/getDiseaseEventListData.do', {
            'startDate': startDate,
            'endDate': endDate,
            'epidemicClass':epidemicClass
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
                    $.post('${pageContext.request.contextPath}/oieDashboard/getDiseaseEventListData.do', {
                        'startDate': startDate,
                        'endDate': endDate,
                        'epidemicClass':epidemicClass,
                        'pageNo': n - 1
                    }, function (data) {
                        var epidemicAppearList = data.epidemicEventList;
                        for (var i = 0; i < epidemicAppearList.length; i++) {
                            var tr="";

                            if(epidemicAppearList[i].epidemicNameCn !=null&&epidemicAppearList[i].epidemicNameCn !=""){
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
                                "<td style=''><p>" + epidemicAppearList[i].dateRes + "</p></td>" +
                                "<td style=''>" + "<a href='${pageContext.request.contextPath}/oieEpidemicSearch/toOIEDetailPage.do?rowKey=" + epidemicAppearList[i].report + "'><button class='btn btn-primary'>详细信息</button></a>" + "</td>" + "</tr>";
                            $("#epidmicData").append("<tr>"+tr+"</tr>");
                            $("td").css("vertical-align","middle");
                            $('#detailMod').modal('show').css({
                                width: 'auto'
                            })
                        }
                    }, 'json');
                }
            });


        }, 'json');
    }

    $(document).ready(function () {
        findDiseaseClassData();
        findCalendarHeatMapData();

    });
</script>
</html>
<script src="${pageContext.request.contextPath}/adminex/js/jquery.nicescroll.js"></script>
<script src="${pageContext.request.contextPath}/js/menuScript.js"></script>
