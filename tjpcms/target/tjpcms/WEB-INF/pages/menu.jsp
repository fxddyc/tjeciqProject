<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 16/7/14
  Time: 下午12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- left side start-->
<div class="left-side sticky-left-side">

    <!--logo and iconic logo start-->
    <div class="logo">
        <a href="index.html"><img src="${pageContext.request.contextPath}/adminex/images/logo.png" alt=""></a>
    </div>

    <div class="logo-icon text-center">
        <a href="index.html"><img src="${pageContext.request.contextPath}/adminex/images/logo_icon.png" alt=""></a>
    </div>
    <!--logo and iconic logo end-->

    <div class="left-side-inner">

        <!-- visible to small devices only -->
        <div class="visible-xs hidden-sm hidden-md hidden-lg">
            <div class="media logged-user">
                <img alt="" src="${pageContext.request.contextPath}/adminex/images/photos/user-avatar.png"
                     class="media-object">
                <div class="media-body">
                    <h4><a href="#">John Doe</a></h4>
                    <span>"Hello There..."</span>
                </div>
            </div>

            <h5 class="left-nav-title">Account Information</h5>
            <ul class="nav nav-pills nav-stacked custom-nav">
                <li><a href="#"><i class="fa fa-user"></i> <span>Profile</span></a></li>
                <li><a href="#"><i class="fa fa-cog"></i> <span>Settings</span></a></li>
                <li><a href="#"><i class="fa fa-sign-out"></i> <span>Sign Out</span></a></li>
            </ul>
        </div>

        <!--sidebar nav start-->
        <ul class="nav nav-pills nav-stacked custom-nav">
            <li class="menu-list nav-active"><a href="index.html"><i class="fa fa-home"></i> <span>控制面板</span></a>
                <ul class="sub-menu-list">
                    <li><a href="${pageContext.request.contextPath}/index/indexPage.do">仪表盘</a></li>
                    <li><a href="${pageContext.request.contextPath}/epidemicCloud/epidemicCloudPage.do">疫情字符云</a></li>
                    <%--<li><a href="/yqChartsCloud/yqHistory">疫情历史</a></li>--%>
                </ul>
                <ul class="sub-menu-list">
                    <li><a href="${pageContext.request.contextPath}/wordRegion/wordRegionPage.do">疫情地域分布</a></li>
                </ul>
                <ul class="sub-menu-list">
                    <li><a href="${pageContext.request.contextPath}/epidemicBaike/epidemicBaikePage.do">疫情百科</a></li>
                </ul>
                <ul class="sub-menu-list">
                    <li><a href="${pageContext.request.contextPath}/epidemic/epidemicListPage.do">疫情综合查询</a></li>
                </ul>

                <ul class="sub-menu-list">
                    <li><a href="${pageContext.request.contextPath}/epidemic/epidemicListPage.do">数据中心</a></li>
                </ul>

                <ul class="sub-menu-list">
                    <li><a href="${pageContext.request.contextPath}/words/toWordsPage.do">分词器</a></li>
                </ul>
            </li>
        </ul>
        <!--sidebar nav end-->

    </div>
</div>
<!-- left side end-->
