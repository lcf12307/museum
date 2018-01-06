<%--
  Created by IntelliJ IDEA.
  User: yangming
  Date: 2017/12/29/0029
  Time: 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>教务管理系统</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/dist/css/skins/skin-blue.min.css">
    <script src="/plugins/jQuery/jquery-2.2.3.min.js"></script>
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
                <h2>专家列表</h2>
            </div>
            <div>
                <a class = "btn btn-primary" href="/museum/quota/list/1" target="_self">返回</a>
            </div>
            <br>
            <div class="input-group">
                <select name="yearKey" id = "yearKey" type = "commit" style="height:30px;">
                    <option value="">请选择</option>
                    <option value="2008">2008</option>
                    <option value="2009">2009</option>
                    <option value="2010">2010</option>
                    <option value="2011">2011</option>
                    <option value="2012">2012</option>
                    <c:if test="${yearKey!=null}">
                        <script>
                            $('#yearKey').val("${yearKey}");
                        </script>
                    </c:if>
                </select>
                <span class="input-group-btn">
                        <button type="button" class="btn btn-default" onclick="searchQuota()">搜索</button>
            </span>
            </div>
            <div class="panel-body" >
                <h2> </h2>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>序号  </th>
                        <th>专家姓名</th>
                        <th>一级指标</th>
                        <th> 操作 </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${list.size() != 0}">
                            <%
                                int s = 0;
                            %>
                            <c:forEach items="${list}" var="quota">
                                <tr><%
                                    s++;
                                %>
                                    <td><%=s%></td>
                                    <td>${quota.name}</td>
                                    <td>${quota.quotaId}</td>
                                    <td>
                                        <a class="btn btn-danger" onclick="addQuota('${yearKey}','${quota.name}')" target="_blank">指定</a>
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
                    <a class="btn btn-primary" href="/museum/quota/appoint2/1" target="_self">第一页</a>
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
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/dist/js/app.min.js"></script>
<script type="text/javascript">
    function ahead(){
        if(${page} == 1 ) {
            alert("当前已是第一页");
        }
    else{
            window.location.href="/museum/quota/appoint2/${page-1}?yearKey=${yearKey}";
        }
    }
    function behind(){
        if(${pages} == ${page}){
            alert("当前已是最后一页");
        } else{
            window.location.href="/museum/quota/appoint2/${page+1}?yearKey=${yearKey}";
        }
    }
    function addQuota(yearKey ,name){
        var year = yearKey;
        var name = name;
        var url = "/museum/quota/add";
        if(confirm("确定要添加该专家吗？")) {
            $.ajax({
                type: 'POST',
                url: url,
                dataType: 'json',
                contentType: 'application/json;charset=UTF-8',
                data: JSON.stringify({
                    name: name,
                    year:year
                }),  //提交json字符串数组
                success:function(data){
                    if(data=="SUCCESS"){
                        alert("添加成功");
                        window.location.reload();
                    }
                    else if (data == "NAME_FAIL"){
                        alert("添加失败");
                    }else{
                        alert("未知的错误")
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
        var yearKey = $('#yearKey').val();
        window.location.href="/museum/quota/appoint2/1?yearKey="+yearKey;
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
        document.cookie="expertProperty="+yearKey+";path= /museum/quota/";
        window.location.href="/museum/quota/appoint/1";
    }
</script>
</body>
</html>

