<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 16/7/14
  Time: 下午12:07
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


    <!--common-->
    <link href="${pageContext.request.contextPath}/adminex/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/adminex/css/style-responsive.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/adminex/fonts/css/font-awesome.min.css" rel="stylesheet">
</head>
<body class="sticky-header" ng-app="myApp">




<section>
    <%@include file="menu.jsp" %>
    <!-- main content start-->
    <div class="main-content">
        <%@include file="header.jsp" %>
        <!--body wrapper start-->
        <div class="wrapper">


            <div class="row" >
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            疫情综合查询
                        </div>

                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-4 form-group" ng-controller="epidemicName">
                                    <label class="control-label">疫情名称</label>
                                    <input type="text" class="form-control" placeholder="疫情名称" ng-model="eName" ng-keyup="getNameList()">
                                    <div class="row" style="height:0;">
                                        <ui id='enlu' class="col-md-12" style="float:left;background: white;height:0;">
                                            <li class="col-md-12 scl" ng-click="b()" ng-style="myStyle" ng-mouseover="c()" ng-mouseout="d()" ng-repeat="en in epidemicNameList">{{en}}</li>
                                        </ui>
                                    </div>
                                </div>
                                <div class="col-lg-4 form-group" ng-controller="epidemicRegion">
                                    <label class="control-label">地域名称</label>
                                    <input type="text" class="form-control" placeholder="地域名称" ng-model="eRegion" ng-keyup="getRegionList()">
                                    <div class="row" style="height:0;">
                                        <ui id='erlu' class="col-md-12" style="float:left;background: white;height:0;">
                                            <li class="col-md-12 scl" ng-click="b()" ng-style="myStyle" ng-mouseover="c()" ng-mouseout="d()" ng-repeat="er in epidemicRegionList">{{er}}</li>
                                        </ui>
                                    </div>
                                </div>
                                <div class="col-lg-4 form-group">
                                    <label class="control-label">时间段</label>
                                    <%--<td>--%>
                                        <%--<div data-date-minviewmode="months" data-date-viewmode="years"--%>
                                             <%--class="input-group input-large custom-date-range " data-date="102/2012"--%>
                                             <%--data-date-format="yyyy-mm-dd">--%>
                                            <%--<input type="text" id="startDate" class="form-control dpd1" name="from">--%>
                                            <%--<span class="input-group-addon">到</span>--%>
                                            <%--<input type="text" id="endDate" class="form-control dpd2" name="to">--%>
                                        <%--</div>--%>
                                    <%--</td>--%>
                                    <%--<input type="text" class="form-control" placeholder="" id="timeInput">--%>
                                </div>
                            </div>

                        </div>
                        <div class="panel-footer">
                            <button class="btn btn-primary">查询</button>
                        </div>
                    </div>

                </div>
            </div>

            <%--</div>--%>
            <div ng-controller="epidemicList" class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <table class="table table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th style="">
                                        <div class="th-inner ">序号</div>
                                        <div class="fht-cell"></div>
                                    </th>
                                    <th style="">
                                        <div class="th-inner ">疫情名称</div>
                                        <div class="fht-cell"></div>
                                    </th>
                                    <th style="">
                                        <div class="th-inner ">地区</div>
                                        <div class="fht-cell"></div>
                                    </th>
                                    <th style="">
                                        <div class="th-inner ">数量</div>
                                        <div class="fht-cell"></div>
                                    </th>
                                    <th style="">
                                        <div class="th-inner ">爆发时间</div>
                                        <div class="fht-cell"></div>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr ng-repeat="epidemicAppear in epidemicAppearList" data-index="0">
                                    <td>{{ $index + 1 }}</td>
                                    <td style="">{{epidemicAppear.epidemic.epidemicName}}</td>
                                    <td style="">{{epidemicAppear.region.regionCn}}</td>
                                    <td style="">{{epidemicAppear.appearTimes}}</td>
                                    <td style="">{{epidemicAppear.appearDate}}</td>
                                </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <div class="dataTables_paginate paging_bootstrap pagination">
                                            <ul>
                                                <li class="prev disabled"><a href="#" ng-click="pageUtils($event)">← 上一页</a></li>
                                                <li><a ng-click="pageUtils($event)">1</a></li>
                                                <li><a ng-click="pageUtils($event)">2</a></li>
                                                <li><a ng-click="pageUtils($event)">3</a></li>
                                                <li><a ng-click="pageUtils(4)">4</a></li>
                                                <li><a ng-click="pageUtils(5)">5</a></li>
                                                <li class="next"><a href="#">下一页 → </a></li>
                                            </ul>
                                        </div>
                                    </tr>
                                </tfoot>
                            </table>

                        </div>
                    </div>
                </div>
            </div>

            <!--body wrapper end-->
        </div>
    </div>
    <!-- main content end-->
</section>
<style type="text/css">
    .scl {float:left;clear:both;background:white;list-style-type:none;}
</style>

<!-- Placed js at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/adminex/js/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/modernizr.min.js"></script>
<!--common scripts for all pages -->
<script src="${pageContext.request.contextPath}/adminex/js/scripts.js"></script>
<script src="${pageContext.request.contextPath}/echarts/build/dist/echarts-all.js"></script>
<script src="${pageContext.request.contextPath}/angularjs/angular.min.js"></script>

<script src="${pageContext.request.contextPath}/adminex/js/bootstrap-datepicker/js/bootstrap-datepicker.js?v=1"></script>
<%--<!--pickers initialization-->--%>
<%--<script src="${pageContext.request.contextPath}/adminex/js/pickers-init.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/bootstrap/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>--%>

<script type="text/javascript">
    var app = angular.module('myApp', []);
    var ene = document.getElementById("enlu");
    var ere = document.getElementById("erlu");
    var initStyle = {"float":"left","clear":"both","background":"white","list-style-type":"none"};
    var focusStyle = {"float":"left","clear":"both","background":"#00FFFF","list-style-type":"none"};
    app.controller('epidemicName',function ($scope, $http){
        $scope.getNameList = function () {
            $http({ method:'post',url:"${pageContext.request.contextPath}/epidemic/epidemicNameList.do", params:{keyword:$scope.eName}
            }).success(function (response) {$scope.epidemicNameList = response[0];});
            ene.style.display='';
        };
        $scope.b = function () {
            $scope.eName = this.en;
            ene.style.display='none';
        };
        $scope.c = function () {
            this.myStyle = focusStyle;
        };
        $scope.d = function () {
            this.myStyle = initStyle;
        }
    });
    app.controller('epidemicRegion',function ($scope, $http){
        $scope.getRegionList = function () {
            $http({ method:'post',url:"${pageContext.request.contextPath}/epidemic/epidemicRegionList.do", params:{keyword:$scope.eRegion}
            }).success(function (response) {$scope.epidemicRegionList = response[0];});
            ere.style.display='';
        };
        $scope.b = function () {
            $scope.eRegion = this.er;
            ere.style.display='none';
        };
        $scope.c = function () {
            this.myStyle = focusStyle;
        };
        $scope.d = function () {
            this.myStyle = initStyle;
        }
    });
    document.onmousedown=function(ev){
        var oen = $(".scl");
        var oEvent=ev;
        var target=oEvent.target||oEvent.srcElement;
        if(oen.length >0 && $.inArray(target, oen)<0){
            ene.style.display='none';
            ere.style.display='none';
        }
    };

    app.controller('epidemicList', function ($scope, $http) {
        $http.get("${pageContext.request.contextPath}/epidemic/epidemicList.do")
                .success(function (response) {
                    $scope.epidemicAppearList = response.epidemicAppearList;
                });
        $scope.pageUtils = function (event) {

            $http.get("${pageContext.request.contextPath}/epidemic/epidemicList.do?pageNo=" +1).success(function (response) {
                var aaa = event.target;
                var bbb = event;
                alert(aaa);
                $scope.epidemicAppearList = response.epidemicAppearList;
            });
        }
    });

</script>
</body>
</html>
