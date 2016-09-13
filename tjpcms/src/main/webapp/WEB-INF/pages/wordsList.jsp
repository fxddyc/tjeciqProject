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
    <!--dropzone css-->
    <link href="${pageContext.request.contextPath}/adminex/js/dropzone/css/dropzone.css" rel="stylesheet"/>

</head>
<body class="sticky-header">


<section>
    <%@include file="menu.jsp" %>
    <!-- main content start-->
    <div class="main-content">
        <%@include file="header.jsp" %>

        <!--body wrapper start-->
        <div class="wrapper">
            <section class="panel">
                <header class="panel-heading">
                    分词文件上传
                </header>
                <div class="panel-body">
                    <form role="form" enctype="multipart/form-data" method="post"
                          action="${pageContext.request.contextPath}/words/uploadWordsFile.do">
                        <div class="form-group">
                            <label for="exampleInputFile">请修改或添加分词后上传</label>
                            <input type="file" name="upfile" id="exampleInputFile">
                        </div>
                        <button type="submit" class="btn btn-primary">上传分词文件</button>
                        <p style="color: red">${upfileStatus}</p>
                    </form>
                    <button class="btn btn-primary" onclick="downloadWords();">下载分词</button>
                    <a href="/words/downLoadWordsFile.do">下载分词</a>
                </div>
            </section>
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


<!--dropzone-->
<script src="${pageContext.request.contextPath}/adminex/js/dropzone/dropzone.js"></script>

<script src="${pageContext.request.contextPath}/adminex/js/scripts.js"></script>

<script type="text/javascript">
    function downloadWords() {
        window.href = '${pageContext.request.contextPath}/words/downLoadWordsFile.do';
    }

</script>
</body>

</html>
