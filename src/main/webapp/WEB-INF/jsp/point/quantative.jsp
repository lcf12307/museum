<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>统计表


    </title>
    <%@include file="../common/head.jsp" %>
    <script type="text/javascript">


    </script>
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
        <%@include file="../common/sidebar.jsp"%>
    </aside>
    <div class="content-wrapper">
        <section class="content-header">
            <ol class="breadcrumb" id="nav_title" style="position:static;float:none;">
                <li class="active"><i class="fa fa-home" style="font-size:20px;position:relative;top:2px;left:-3px;"></i> &nbsp; 博物馆运行评估系统</li>
                <li class="active">位置导航</li>
            </ol>
        </section>
        <section class="content" style="background:#fff;">
            <!-- 模态框（Modal） -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header" align="right">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">定量数据统计表</h4>
                        </div>


                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>
            <div class="panel panel-default">
                <div class="panel-heading text-center">
                    <h2>申报书列表</h2>
                </div>
                <div class="panel-body" >
                    <form class="navbar-form navbar-left" role="search">
                        <div>
                            <div class="form-group">
                                <label class="col-5">按博物馆名称进行搜索</label>
                                <input type="text" class="form-control col-6 col-lg-offset-0" placeholder="输入" id="searchName" name="searchName">
                            </div>
                            <button type="button" class="btn btn-default" onclick="searchByName()">搜索</button>
                        </div>
                        <div>
                            <div class="form-group">
                                <label class="col-5">请选择年份</label>
                                <select id="searchYear" name="searchYear">
                                    <option value="2018">2018</option>
                                    <option value="2017">2017</option>
                                    <option value="2016">2016</option>
                                    <option value="2015">2015</option>
                                    <option value="2014">2014</option>
                                    <option value="2013">2013</option>
                                    <option value="2012">2012</option>
                                    <option value="2011">2011</option>
                                    <option value="2010">2010</option>
                                </select>
                            </div>
                            <button type="button" class="btn btn-default" onclick="search()">搜索</button>
                        </div>
                    </form>
                    <a class = "btn btn-primary btn-default pull-right"  data-toggle="modal" data-target="#myModal"><li class="fa fa-upload"></li>上传申报书</a>
                    <table class="table table-hover" border="1">
                        <thead>
                        <tr>
                            <th>博物馆名称</th>
                            <th>参评年份</th>
                            <th>总分</th>
                            <th>藏品</th>
                            <th>科学研究</th>
                            <th>展览与教育</th>
                            <th>人才培养</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${quantative}" var="point">
                            <tr>
                                <td>${point.name}</td>
                                <td>${attachment.addtime}</td>
                                <td>${attachment.year}</td>
                                <td><a class="btn btn-info" href="/notification/listFile?dir=${attachment.name}_${attachment.year}" target="_blank">下载申报书</a>
                                    <a class="btn btn-info" href="/notification/listFile?dir=${attachment.name}_${attachment.year}&add=1"  target="_blank">文件管理</a>
                                    <a class="btn btn-info" onclick="delAttachment(${attachment.id})" target="_blank">删除申报书</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

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
</body>
</html>
