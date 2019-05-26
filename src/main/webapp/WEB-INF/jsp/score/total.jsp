<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>图书馆运行评估系统</title>
    <%@include file="../common/head.jsp"%>
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
            <!--页面写在这里-->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h2>${year}年定性定量总分统计表</h2>
                </div>
                <div class="panel-body" >
                    <div class="">
                        <select name="year" id="yearSelect">
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
                        <button onclick="jump()">查询</button>
                    </div>
                    <table class="table table-hover" >
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>博物馆名称</th>
                            <th>年份</th>
                            <th>总分</th>
                            <th>定性总分</th>
                            <th>定量总分</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${totalScoreList.size()!=0}">
                                <%int i=0;%>
                                <c:forEach items="${totalScoreList}" var="score">
                                    <%i++;%>
                                    <tr>
                                        <td><%=i%></td>
                                        <td>${score.name}</td>
                                        <td>${score.year}</td>
                                        <td>${score.total}</td>
                                        <td>${score.dingxing}</td>
                                        <td>${score.dingliang}</td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <td colspan="4" align="center">对不起，没有相关数据</td>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                </div>
                <div class="panel-footer" >
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
    function jump() {
        var year = $('#yearSelect').val();
        location.href="/score/total/"+year;
    }
</script>
</body>
</html>
