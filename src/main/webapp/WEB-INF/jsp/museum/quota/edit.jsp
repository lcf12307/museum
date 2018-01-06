<%--
  Created by IntelliJ IDEA.
  User: yangming
  Date: 2017/12/29/0029
  Time: 9:39
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

            <div>
                <h2>修改信息</h2>
                <c:choose>
                    <c:when test ="${quota.year.length()!=0}">
                        <table border="1" cellpadding="3" cellspacing="0" style="width: 60%;margin:auto">
                            <tr>
                                <td>
                                    <h5>专家名字：</h5>
                                </td>
                                <td><input name="name" id="name" maxlength='15' value=${quota.name} readonly="true" class="form-control"></td>
                                 <input name="id" id="id" type="hidden" value=${quota.id} readonly="true" class="form-control">
                            </tr>
                            <tr>
                                <td>
                                    <h5>指标名字：</h5>
                                </td>
                                <td>
                                    <input name="quotaId" id="quotaId"  type="text"  value=${quota.quotaId}    readonly="true" style="height:50px;"class="form-control">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h5>专家简介：</h5>
                                </td>
                                <td>
                                    <textarea  name ="description" id="description" style="height:100px;" class="form-control">${quota.description}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h5></h5>
                                </td>
                                <td>
                                    <div align="center">
                                        <button class="btn btn-primary edit-btn" onclick="updateQuota();">确认编辑</button>
                                        <a class="btn btn-primary" href="/museum/quota/list/1" target="_self">返回</a>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </c:when>
                    <c:when test="${quota.year.length() == 0}">
                        <table border="1" cellpadding="3" cellspacing="0" style="width: 60%;margin:auto">
                            <tr>
                                <td>
                                    <h5>专家名字：</h5>
                                </td>
                                <td><input name="name" id="name1" maxlength='15' value=${quota.name} class="form-control"></td>
                                <input name="id" id="id1" type="hidden" value=${quota.id} readonly="true" class="form-control">
                            </tr>
                            <tr>
                                <td>
                                    <h5>指标名字：</h5>
                                </td>
                                <td>
                                    <select name="quotaId" id = "quotaId1" type = "commit" style="height:40px;">
                                        <option>${quota.quotaId}</option>
                                        <option>科学研究</option>
                                        <option>藏品管理</option>
                                        <option>公共关系与服务</option>
                                        <option>陈列展览与社会教育</option>
                                        <option>博物馆管理发展建设</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h5>专家简介：</h5>
                                </td>
                                <td>
                                    <textarea  name ="description" id="description1" style="height:100px;" class="form-control">${quota.description}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h5>操作</h5>
                                </td>
                                <td>

                                    <div align="center">
                                        <button class="btn btn-primary edit-btn" onclick="updateQuota1();">确认编辑</button>
                                        <a class="btn btn-primary" href="/museum/quota/list/1" target="_self">返回</a>
                                    </div>
                                </td>

                            </tr>
                        </table>
                    </c:when>
                </c:choose>
            </div>

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

<script src="/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/dist/js/app.min.js"></script>
<script type="text/javascript">
    function updateQuota(){
        var id = $('#id').val();
        var name = $('#name').val();
        var quotaId = $('#quotaId').val();
        var description = $('#description').val();

        if (name == "") {
            alert("专家名字不能为空");
        } else if (description == "") {
            alert("专家简介不能为空");
        } else {

            $.ajax(
                {
                    type: 'POST',
                    url: '/museum/quota/updateExpert',
                    dataType: 'json',
                    contentType: 'application/json;charset=UTF-8',
                    data: JSON.stringify({
                        id:id,
                        name: name,
                        quotaId: quotaId,
                        description: description
                    }),  //提交json字符串数组
                    success: function (data) {
                        if (data == "SUCCESS") {
                            alert("修改成功");
                            window.location.href = "/museum/quota/list";
                        }
                        else if (data == "NAME_FAIL") {
                            alert("专家名字重复");
                        }
                        else if(data =="ID_FAIL"){
                            alert("修改失败");
                        }else {
                            alert("未知的错误");
                        }
                    },
                    error: function () {
                        alert("修改数据失败");
                    }
                });
        }
    }

    function updateQuota1(){
        var id = $('#id1').val();
        var name = $('#name1').val();
        var quotaId = $('#quotaId1').val();
        var description = $('#description1').val();

        if (name == "") {
            alert("专家名字不能为空");
        } else if (description == "") {
            alert("专家简介不能为空");
        } else {

            $.ajax(
                {
                    type: 'POST',
                    url: '/museum/quota/updateExpert',
                    dataType: 'json',
                    contentType: 'application/json;charset=UTF-8',
                    data: JSON.stringify({
                        id:id,
                        name: name,
                        quotaId: quotaId,
                        description: description
                    }),  //提交json字符串数组
                    success: function (data) {
                        if (data == "SUCCESS") {
                            alert("修改成功");
                            window.location.href = "/museum/quota/list";
                        }
                        else if (data == "NAME_FAIL") {
                            alert("专家名字重复");
                        }
                        else if(data =="ID_FAIL"){
                            alert("修改失败");
                        }else {
                            alert("未知的错误");
                        }
                    },
                    error: function () {
                        alert("修改数据失败");
                    }
                });
        }
    }
</script>
</body>
</html>
