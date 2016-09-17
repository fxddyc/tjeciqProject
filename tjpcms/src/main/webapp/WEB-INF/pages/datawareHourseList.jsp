<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 16/7/14
  Time: 下午12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

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
            <div class="row">
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading">
                            查询条件
                            <span class="tools pull-right">
                                <a href="javascript:;" class="fa fa-chevron-down"></a>
                                <a href="javascript:;" class="fa fa-times"></a>
                             </span>
                        </header>
                        <div class="panel-body">
                            <table style="text-align: center;">
                                <tr>
                                    <td><label>分词:&nbsp;&nbsp;&nbsp;</label></td>
                                    <td>
                                        <div class="input-group">
                                            <input id="words" class="form-control" style="width: 200px"/>
                                        </div>
                                    </td>
                                    <td>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <button class="btn btn-primary" onclick="initInput();">重置条件</button>
                                    </td>
                                    <td>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <button class="btn btn-info" onclick="btnClick();">查询</button>
                                    </td>
                                </tr>

                            </table>

                        </div>
                    </section>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12" id="content">
                </div>
            </div>
        </div>
    </div>
    <!-- main content end-->


</section>


<!-- Placed js at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/adminex/js/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/adminex/js/modernizr.min.js"></script>

<script src="${pageContext.request.contextPath}/adminex/js/jquery.isotope.js"></script>

<!--common scripts for all pages-->
<script src="${pageContext.request.contextPath}/adminex/js/scripts.js"></script>

<script type="text/javascript">

    $(document).ready(function () {
        $.post('${pageContext.request.contextPath}/dataWarehouse/datawarehouseList.do', {'word': ''}, function (data) {
            var json = data;
            for (var i = 0; i < json.length; i++) {
                var htmlTag = "<div class='panel'> <div class='panel-body text-center'> <i class='fa fa-quote-left'></i> <h3 class=''><a href='${pageContext.request.contextPath}/dataWarehouse/datawarehouseDetailPage.do?rowKey=" + json[i].rowKey + "'>" + json[i].title + "</a></h3> </div> </div>";
                $("#content").append(htmlTag);
            }

        }, 'json');
    });

    function btnClick() {
        $.post('${pageContext.request.contextPath}/dataWarehouse/datawarehouseList.do', {'word': $('#words').val()}, function (data) {
            $("#content").empty();
            var json = data;
            for (var i = 0; i < json.length; i++) {
                var htmlTag = "<div class='panel'> <div class='panel-body text-center'> <i class='fa fa-quote-left'></i> <h3 class=''><a href='${pageContext.request.contextPath}/dataWarehouse/datawarehouseDetailPage.do?rowKey=" + json[i].rowKey + "'>" + json[i].title + "</a></h3> </div> </div>";
                $("#content").append(htmlTag);
            }

        }, 'json');
    }
    
    function initInput() {
        $("input").val("");
    }

</script>
</body>

</html>
