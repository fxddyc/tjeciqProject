<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 16/7/14
  Time: 下午8:13
  To change this template use File | Settings | File Templates.
--%>
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

    <title>AdminX</title>

    <!--dashboard calendar-->
    <link href="${pageContext.request.contextPath}/adminex/css/clndr.css" rel="stylesheet">

    <!--pickers css-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/adminex/js/bootstrap-datepicker/css/datepicker-custom.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/adminex/js/bootstrap-timepicker/css/timepicker.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/adminex/js/bootstrap-colorpicker/css/colorpicker.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/adminex/js/bootstrap-daterangepicker/daterangepicker-bs3.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/adminex/js/bootstrap-datetimepicker/css/datetimepicker-custom.css"/>


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
        <!--body wrapper start-->
        <div class="wrapper">
            <div class="row">
                <div class="col-sm-12">
                    <div class="panel">
                        <header class="panel-heading">
                            查询
                        </header>
                        <div class="panel-body" style="display: block;">
                            <table style="text-align: center;">
                                <tr>
                                    <td><label>地域:&nbsp;&nbsp;&nbsp;</label></td>
                                    <td>

                                        <div class="input-group">
                                              <span class="input-group-btn">
                                                <button type="button" class="form-control btn btn-default" data-toggle="modal" href="#myModal2"><i class="fa fa-search"></i></button>
                                              </span>
                                            <input type="text" class="form-control">
                                        </div>
                                        </td>


                                        <%--<select id="region" class="form-control">--%>
                                        <%--<option value="">全部</option>--%>
                                        <%--{% for region in regions %}--%>
                                        <%--<option value="{{ region.key }}">{{ region.value }}</option>--%>
                                        <%--{% endfor %}--%>
                                    <%--</select></td>--%>
                                    <td><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间段:&nbsp;&nbsp;&nbsp;</label></td>
                                    <td>
                                        <div data-date-minviewmode="months" data-date-viewmode="years"
                                             class="input-group input-large custom-date-range" data-date="102/2012"
                                             data-date-format="yyyy-mm-dd">
                                            <input type="text" id="fromDate" class="form-control dpd1" name="from">
                                            <span class="input-group-addon">到</span>
                                            <input type="text" id="endDate" class="form-control dpd2" name="to">
                                        </div>
                                    </td>
                                    <td>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <button class="btn btn-primary" onclick="clearAll()">重置条件</button>
                                    </td>
                                    <td>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <button class="btn btn-info" onclick="findYQChartsCloud()">查询</button>
                                    </td>
                                </tr>

                            </table>

                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-12">
                    <div class="panel">
                        <header class="panel-heading">
                            疫情字符云
                                    <span class="tools pull-right">
                                        <a href="javascript:;" class="fa fa-chevron-down"></a>
                                     </span>
                        </header>
                        <div class="panel-body" style="display: block;">
                            <div id="main" style="height:400px"></div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <!--body wrapper end-->
    </div>
    <!-- main content end-->




    <!-- Modal -->
    <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">Datepicker in Modal</h4>
                </div>
                <div class="modal-body">
                    <form action="#" class="form-horizontal ">
                        <div class="form-group">
                            <label class="control-label col-md-4"> Date time picker</label>
                            <div class="col-md-6">
                                <input size="16" type="text" value="2012-06-15 14:45" readonly=""
                                       class="form_datetime form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4">Default Datepicker</label>
                            <div class="col-md-6 col-xs-11">
                                <input class="form-control form-control-inline input-medium default-date-picker"
                                       size="16" type="text" value="">
                                <span class="help-block">Select date</span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4">Start with years viewMode</label>
                            <div class="col-md-6 col-xs-11">

                                <div data-date-viewmode="years" data-date-format="dd-mm-yyyy" data-date="12-02-2012"
                                     class="input-append date dpYears">
                                    <input type="text" readonly="" value="12-02-2012" size="16" class="form-control">
                                                                      <span class="input-group-btn add-on">
                                                                        <button class="btn btn-primary" type="button"><i
                                                                                class="fa fa-calendar"></i></button>
                                                                      </span>
                                </div>
                                <span class="help-block">Select date</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-4">Months Only</label>
                            <div class="col-md-6 col-xs-11">
                                <div data-date-minviewmode="months" data-date-viewmode="years"
                                     data-date-format="mm/yyyy" data-date="102/2012" class="input-append date dpMonths">
                                    <input type="text" readonly="" value="02/2012" size="16" class="form-control">
                                                                      <span class="input-group-btn add-on">
                                                                        <button class="btn btn-primary" type="button"><i
                                                                                class="fa fa-calendar"></i></button>
                                                                      </span>
                                </div>


                                <span class="help-block">Select month only</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-4">Date Range</label>
                            <div class="col-md-6">
                                <div class="input-group input-large custom-date-range" data-date="13/07/2013"
                                     data-date-format="mm/dd/yyyy">
                                    <input type="text" class="form-control dpd1" name="from">
                                    <span class="input-group-addon">To</span>
                                    <input type="text" class="form-control dpd2" name="to">
                                </div>
                                <span class="help-block">Select date range</span>
                            </div>
                        </div>
                    </form>
                </div>


                <form class="form-horizontal  " action="#">
                    <div class="form-group">
                        <label class="control-label col-md-4">Default Timepicker</label>
                        <div class="col-md-6">
                            <div class="input-group bootstrap-timepicker">
                                <input type="text" class="form-control timepicker-default">
                                                            <span class="input-group-btn">
                                                            <button class="btn btn-default" type="button"><i
                                                                    class="fa fa-clock-o"></i></button>
                                                            </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">24hr Timepicker</label>
                        <div class="col-md-6">
                            <div class="input-group bootstrap-timepicker">
                                <input type="text" class="form-control timepicker-24">
                                                            <span class="input-group-btn">
                                                            <button class="btn btn-default" type="button"><i
                                                                    class="fa fa-clock-o"></i></button>
                                                            </span>
                            </div>
                        </div>
                    </div>
                </form>


                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-primary" type="button">Close</button>
                </div>
            </div>
        </div>
    </div>
    <!-- modal -->
</section>
<!-- Placed js at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/adminex/js/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/modernizr.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/jquery.nicescroll.js"></script>

<!--common scripts for all pages-->
<script src="${pageContext.request.contextPath}/adminex/js/scripts.js"></script>


<!--pickers plugins-->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/adminex/js/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/adminex/js/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/adminex/js/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/adminex/js/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/adminex/js/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/adminex/js/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>

<!--pickers initialization-->
<script src="${pageContext.request.contextPath}/adminex/js/pickers-init.js"></script>


<script src="${pageContext.request.contextPath}/echarts/build/dist/echarts-all.js"></script>


<script>
    
    function chosseRegion() {
        alert('aaaa');
        
    }

    function createRandomItemStyle() {
        return {
            normal: {
                color: 'rgb(' + [
                    Math.round(Math.random() * 160),
                    Math.round(Math.random() * 160),
                    Math.round(Math.random() * 160)
                ].join(',') + ')'
            }
        };
    }

    function clearAll() {
        $("input").attr("value", "");
        $("#region").attr("value", "");
        findYQChartsCloud()

    }


    function findYQChartsCloud() {
        //设置数据
        var option = {
            tooltip: {
                show: true
            },
            series: [{
                name: '疫情字符云',
                type: 'wordCloud',
                size: ['80%', '80%'],
                textRotation: [0, 45, 90, -45],
                textPadding: 0,
                autoSize: {
                    enable: true,
                    minSize: 14
                },
                data: []
            }]
        };
        $.post("/yqChartsCloud/yqChartsCloudList", {
                    'region': $('#region option:selected').val(),
                    'fromDate': $('#fromDate').val(),
                    'endDate': $('#endDate').val()
                },
                function (data) {
                    var json = data;
                    option.series[0].data = []
                    for (var i = 0; i < json.length; i++) {
                        json[i]['itemStyle'] = createRandomItemStyle()
                        option.series[0].data.push(json[i])
                    }
                    var myChart = echarts.init(document.getElementById('main'));
                    myChart.setOption(option);
                },
                "json");
    }


    $(document).ready(function () {
        findYQChartsCloud()
    });


</script>
</body>
</html>
