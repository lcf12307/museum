<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>图书馆运行评估系统</title>
    <jsp:include page="common/head.jsp"/>
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
        <jsp:include page="common/sidebar.jsp"/>
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
            <div class="panel panel-default">
                <div class="panel-heading">
                    xxxxxxxxxxxxxx列表
                </div>
                <div class="panel-body" >
                    <div class="">
                        <div class="form-group col-sm-2">
                            <input type="text" placeholder="姓名" class="form-control">
                        </div>
                        <a class="btn btn-primary btn-sm" href="#" ><i class="fa fa-search"></i>查询</a>
                        <a class="btn btn-primary btn-sm" href="#" target="_self"><i class="fa fa-plus"></i>新增</a>
                    </div>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>姓名</th>
                            <th>生日</th>
                            <th>呵呵</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>黄哈才</td>
                                <td>1992-02-02</td>
                                <td>呵呵</td>
                                <td>
                                    <a class="btn btn-info btn-sm" href="#"><i class="fa fa-edit"></i>修改</a>
                                    <a class="btn btn-danger btn-sm" onclick="javascript:void(0);"><i class="fa fa-edit"></i>删除</a>
                                </td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>黄66</td>
                                <td>1992-02-02</td>
                                <td>呵呵</td>
                                <td>
                                    <a class="btn btn-info btn-sm" href="#"><i class="fa fa-edit"></i>修改</a>
                                    <a class="btn btn-danger btn-sm" onclick="javascript:void(0);"><i class="fa fa-edit"></i>删除</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="panel-footer" >
                    <!--分页-->
                    <div class="mailbox-controls">
                        <button class="btn btn-default btn-sm" onclick="front()"><i class="glyphicon glyphicon glyphicon-home"></i></button>
                        <button class="btn btn-default btn-sm" onclick="prev()"><i class="glyphicon glyphicon-chevron-left"></i></button>
                        <button class="btn btn-default btn-sm" onclick="next()"><i class="glyphicon glyphicon-chevron-right"></i></button>
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
        window.location.href="";
    }
    /**上一页**/
    function prev(){
        window.location.href="";
    }
    /**下一页**/
    function next(){
        window.location.href="";
    }
    /**刷新**/
    function refresh(){
        window.location.reload();
    }
</script>
</body>
</html>
