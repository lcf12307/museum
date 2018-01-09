<%--
  Created by IntelliJ IDEA.
  User: 21441
  Date: 2018/1/4
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>图书馆运行评估系统</title>
    <%@include file="../../common/head.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper" id="rrapp">
    <header class="main-header">
        <a href="javascript:void(0);" class="logo">
            <span class="logo-mini"><b>博</b></span>
            <span class="logo-lg"><b>博物馆运行评估系统</b></span>
        </a>
        <nav class="navbar navbar-static-top" role="navigation">
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <div style="float:left;color:#fff;padding:15px 10px;">欢迎</div>
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li><a href="/logout"><i class="fa fa-sign-out"></i> &nbsp;退出系统</a></li>
                </ul>
            </div>
        </nav>
    </header>
    <aside class="main-sidebar">
        <jsp:include page="../../common/sidebar.jsp"/>
    </aside>
    <div class="content-wrapper">
        <section class="content-header">
            <ol class="breadcrumb" id="nav_title" style="position:static;float:none;">
                <li class="active"><i class="fa fa-home" style="font-size:20px;position:relative;top:2px;left:-3px;"></i> &nbsp; 博物馆运行评估系统</li>
                <li class="active">位置导航</li>
            </ol>
        </section>
        <section class="content" style="background:#fff;">
            <!--页面写在这里-->
            <div class="panel-heading text-center">
                <h2>博物馆详情</h2>

                <div class="panel-body" >
                    <table class="table table-hover">
                        <tr>
                            <th>序号  </th> <td>${museumEntity.id}</td>
                        </tr>
                        <tr>
                            <th>博物馆名称</th> <td>${museumEntity.name}</td>
                        </tr>
                        <tr>
                            <th>博物馆类型</th> <td>${museumEntity.category}</td>
                        </tr>
                        <tr>
                            <th>博物馆级别</th> <td>${museumEntity.level}</td>
                        </tr>
                        <tr>
                            <th>评审年份</th> <td>${museumEntity.year}</td>
                        </tr>
                        <tr>
                            <th> 详细资料 </th> <td>${museumEntity.description}</td>
                        </tr>
                    </table>
                    <button type="button" class="btn btn-default" onclick="history.back()">返回</button>
                </div>
            </div>
        </section>
    </div>

    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            Version 1.0.0
        </div>
        Copyright &copy; 2017 <b>museum</b> All Rights Reserved
    </footer>

    <div class="control-sidebar-bg"></div>
</div>
<script>

</script>
</body>
</html>
