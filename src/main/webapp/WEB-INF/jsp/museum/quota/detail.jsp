<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>博物馆运行评估系统</title>
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
        <%@include file="../../common/sidebar.jsp"%>
    </aside>
    <div class="content-wrapper">
        <section class="content-header">
            <ol class="breadcrumb" id="nav_title" style="position:static;float:none;">
                <li class="active"><i class="fa fa-home" style="font-size:20px;position:relative;top:2px;left:-3px;"></i> &nbsp; 博物馆运行评估系统</li>
                <li class="active">位置导航</li>
            </ol>
        </section>
        <section class="content" style="background: #fff">
            <div class="panel panel-default">
                <div class="panel-heading text-center">
                    <h2>专家信息</h2>
                </div>
                <div class="panel-body" >
                    <table border="0" cellpadding="3" cellspacing="0" style="width:60%;margin:auto">
                        <tr>
                            <td>
                                <h5> 专 家 名 字：</h5>
                            </td>
                            <td><input name="name" id="name" maxlength='15' value=${quota.name} readonly="true" class="form-control"></td>
                        </tr>
                        <tr>
                            <td>
                                <h5> 指 标 名 字：</h5>
                            </td>
                            <td>
                                <input name="quotaId" id="quotaId"  type="text"  value=${quota.quotaId}    readonly="true" style="height:50px;"class="form-control">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h5> 专 家 简 介：</h5>
                            </td>
                            <td>
                                <textarea  name ="description" id="description" style="height:100px;" class="form-control" readonly="true">${quota.description}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h5></h5>
                            </td>
                            <td>
                                <div align="center">
                                    <h5></h5>
                                    <a class="btn btn-primary" href="/museum/quota/list/${page}" target="_self">返回</a>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="panel-footer" >
                    <!--分页-->
                    <div class="mailbox-controls">
                        <button class="btn btn-default btn-sm" onclick="front()"><i class="glyphicon glyphicon glyphicon-home"></i></button>
                        <button class="btn btn-default btn-sm" onclick="refresh()"><i class="glyphicon glyphicon-refresh"></i></button>
                    </div>
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
    /**首页**/
    function front(){
        window.location.href="/museum/quota/list/1";
    }
    /**刷新**/
    function refresh(){
        window.location.reload();
    }


</script>
</body>
</html>
