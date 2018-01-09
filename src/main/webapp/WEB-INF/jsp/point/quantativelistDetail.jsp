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
                            <h4 class="modal-title" id="myModalLabel">定量数据排名</h4>
                        </div>


                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>
            <div class="panel panel-default">
                <div class="panel-heading text-center">
                    <h2>${year}年度${quantative.name}定量数据排名详情</h2>
                </div>
                <div class="panel-body" >

                    <table class="table table-hover" border="1">
                        <tr>
                            <td>博物馆名字</td>
                            <td>${quantative.name}</td>
                        </tr>
                        <tr>
                            <td>定量总分</td>
                            <td>${quantative.point1}</td>
                        </tr>
                        <tr>
                            <td>一级指标</td>
                        </tr>
                        <tr>
                            <td>藏品</td>
                            <td>科学研究</td>
                            <td>展览与教育</td>
                            <td>人才培养</td>
                        </tr>
                        <tr>
                            <td>${quantative.point11}</td>
                            <td>${quantative.point12}</td>
                            <td>${quantative.point13}</td>
                            <td>${quantative.point14}</td>
                        </tr>
                        <tr>
                            <td>二级指标</td>
                        </tr>
                        <tr>
                            <td>藏品搜集</td>
                            <td>藏品修复</td>
                            <td>承担项目</td>
                            <td>研究成果</td>
                            <td>学术会议</td>
                            <td>展览排名</td>
                            <td>教育项目</td>
                            <td>中青年人才引进培养</td>
                            <td>员工进修与培训</td>
                        </tr>
                        <tr>
                            <td>${quantative.point101}</td>
                            <td>${quantative.point102}</td>
                            <td>${quantative.point103}</td>
                            <td>${quantative.point104}</td>
                            <td>${quantative.point105}</td>
                            <td>${quantative.point106}</td>
                            <td>${quantative.point107}</td>
                            <td>${quantative.point108}</td>
                            <td>${quantative.point109}</td>
                        </tr>
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
