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
    <title>教务管理系统</title>
    <%@include file="../../common/head.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <!--页首-->
    <header class="main-header">
        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">导航开关</span>
            </a>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">

                <ul class="nav navbar-nav">
                    <!-- User Account Menu -->
                    <li class="dropdown user user-menu">
                        <!-- Menu Toggle Button -->
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="/dist/img/avatar_ani.jpg" class="user-image" alt="User Image">
                            <span class="hidden-xs">${sessionScope.get("userid")}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- The user image in the menu -->
                            <li class="user-header">
                                <img src="/dist/img/avatar_ani.jpg" class="img-circle" alt="User Image">
                                <p>
                                    Talk is cheap show me the code.
                                </p>
                            </li>
                            <!-- Menu Body -->

                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-right">
                                    <a href="#" class="btn btn-default btn-flat">登出</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>

    <!--侧边-->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">

            <div class="user-panel">
                <div class="pull-left image">
                    <img src="/dist/img/avatar_ani.jpg" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>${sessionScope.get("userid")}</p>
                </div>
            </div>

            <!-- Sidebar Menu -->
            <ul class="sidebar-menu">
                <li class="header">排课系统</li>
                <li><a href="/admin/${sessionScope.get("userid")}"><i class="fa fa-link"></i><span>主页</span></a></li>
                <li><a href="/admin/${sessionScope.get("userid")}/addopen"><i class="fa fa-link"></i><span>增加开设信息</span></a></li>
                <li><a href="/admin/${sessionScope.get("userid")}/open"><i class="fa fa-link"></i><span>课程/教学活动安排</span></a></li>
                <li><a href="/admin/${sessionScope.get("userid")}/room"><i class="fa fa-link"></i><span>空教室查询</span></a></li>
                <li><a href="/admin/${sessionScope.get("userid")}/teacherinfo"><i class="fa fa-link"></i><span>查询教师课表</span></a></li>
                <li><a href="/admin/teacher/list"><i class="fa fa-link"></i><span>教师表管理</span></a></li>
                <li><a href="/admin/classroom/list"><i class="fa fa-link"></i><span>教室表管理</span></a></li>
                <li><a href="/admin/student/list"><i class="fa fa-link"></i><span>学生表管理</span></a></li>
                <li><a href="/admin/course/list"><i class="fa fa-link"></i><span>课程表管理</span></a></li>
                <li><a href="/admin/term/list"><i class="fa fa-link"></i><span>学期表管理</span></a></li>
                <li><a href="/admin/info/list"><i class="fa fa-link"></i><span>通知表管理</span></a></li>
                <li><a href="/admin/admin/list"><i class="fa fa-link"></i><span>管理员表管理</span></a></li>
                <li><a href="/admin/${sessionScope.get("userid")}/grade"><i class="fa fa-link" ></i><span>成绩统计</span></a></li>
                <li class="header">选课系统</li>
                <li><a href="/admin/${sessionScope.get("userid")}/search"><i class="fa fa-link"></i><span>信息查询</span></a></li>
                <li><a href="/admin/${sessionScope.get("userid")}/detail"><i class="fa fa-link"></i><span>情况统计</span></a></li>
            </ul>
            <!-- /.sidebar-menu -->
        </section>
        <!-- /.sidebar -->
    </aside>

    <!--核心-->
    <div class="content-wrapper">
        <!--标题-->
        <section class="content-header">
            <h1>
                北京化工大学教务管理系统
                <small>后台系统</small>
            </h1>
            <!--位置导航-->
            <ol class="breadcrumb">
                <li><a href="#">Level</a></li>
                <li class="active">Here</li>
            </ol>
        </section>

        <!--网页正文-->
        <section class="content">
            <div class="panel-heading text-center">
                <h2>博物馆列表</h2>
            </div>
            <div>
                <a class = "btn btn-primary" href="/museum/museum/insertInfo" target="_self">添加博物馆</a>
            </div>
            <br>
            <div class="input-group">
                <input type="text" class="form-control" placeholder="请输入博物馆名称" id="nameKey" name="nameKey" value=${nameKey}>
                <span class="input-group-btn">
                        <button type="button" class="btn btn-default" onclick="searchMuseum()">搜索</button>
                </span>
            </div>
            <div class="panel-body" >
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>序号  </th>
                        <th>博物馆名称</th>
                        <th>博物馆类型</th>
                        <th>博物馆级别</th>
                        <th>评审年份</th>
                        <th> 操作 </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${list.size() != 0}">
                            <%
                                int s = 0;
                            %>
                            <c:forEach var="museum" items="${list}" >
                                <tr><%
                                    s++;
                                %>
                                    <td><%=s%></td>
                                    <td>${museum.id}</td>
                                    <td>${museum.name}</td>
                                    <td>${museum.category}</td>
                                    <td>${museum.level}</td>
                                    <td>${museum.year}</td>
                                    <td><a class="btn btn-info" href="/museum/museum/detail/${museum.id}" target="_self">详细信息</a>
                                        <a class="btn btn-info" href="/museum/museum/edit/${museum.id}" target="_self">修改</a>
                                        <a class="btn btn-danger" onclick="delQuota('${museum.id}')" target="_blank">删除</a>
                                    </td>
                                </tr>

                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <td colspan="4" align="center">对不起，没有相关数据</td>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
                <div align="right">
                    <a class="btn btn-primary" href="/museum/museum/list/1" target="_self">第一页</a>
                    <a class="btn btn-primary" href="#" onclick="ahead()" target="_self">前一页</a>
                    <a class="btn btn-primary" href="#" onclick="behind()" target="_self">下一页</a>
                </div>
            </div>





            <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
            <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<!--页脚-->
<footer class="main-footer">
    <!-- To the right -->
    <div class="pull-right hidden-xs">
        各位大佬强力驱动
    </div>
    <strong>Copyright &copy; 2017 <a href="#">CS1404</a>.</strong> All rights reserved.
</footer>


</div>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script type="text/javascript">
    function ahead(){
        var nameKey = request('nameKey');
        if (nameKey == undefined){
            nameKey = "";
        }
        if(${page} == 1 ) {
            alert("当前已是第一页");
        }
    else{
            window.location.href="/museum/quota/list/${page-1}";
        }
    }
    function behind(){
        var nameKey = request('nameKey');
        if (nameKey == undefined){
            nameKey = "";
        }
        if(${pages} == ${page}){
            alert("当前已是最后一页");
        } else{
            window.location.href="/museum/quota/list/${page+1}";
        }
    }
    function delQuota(name){
        var page = ${page};
        var name = name;
        var url = "/museum/quota/delete";
        if(confirm("确定要删除该博物馆吗？")) {
            $.ajax({
                type: 'POST',
                url: url,
                dataType: 'json',
                contentType: 'application/json;charset=UTF-8',
                data: JSON.stringify({
                    name: name,
                    page: page,
                }),  //提交json字符串数组
                success:function(data){
                    if(data=="SUCCESS"){
                        window.location.reload();
                    }
                    else{
                        alert("请删除专家打分表和取消专家评审资格再进行此操作");
                    }
                },
                error: function (textStatus, errorThrown) {
                    console.log(textStatus);
                    alert("删除失败");
                }
            });
        }
    }
    function searchQuota() {
        var nameKey = $('#nameKey').val();
        window.location.href="/museum/quota/list/1?nameKey="+nameKey;
    }

    function request(strParame) {
        var args = new Object( );
        var query = location.search.substring(1);
        var pairs = query.split("&"); // Break at ampersand
        for(var i = 0; i < pairs.length; i++) {
            var pos = pairs[i].indexOf('=');
            if (pos == -1) continue;
            var argname = pairs[i].substring(0,pos);
            var value = pairs[i].substring(pos+1);
            value = decodeURIComponent(value);
            args[argname] = value;
        }
        return args[strParame];
    }
    function backList(){
        var searchKey = "";
        document.cookie="expertProperty="+nameKey+";path= /museum/quota/";
        window.location.href="/museum/quota/list/1";
    }
</script>
</body>
</html>
