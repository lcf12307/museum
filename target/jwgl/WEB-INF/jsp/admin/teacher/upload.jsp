<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Phoenix
  Date: 2017/4/11
  Time: 下午9:29
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

<%
    String importMsg="";
    if(request.getSession().getAttribute("msg")!=null){
        importMsg=request.getSession().getAttribute("msg").toString();
    }
    request.getSession().setAttribute("msg", "");
%>
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
                <div class="panel-heading text-center"><h2>批量导入教师</h2></div>
                <div class="panel-body"  id = "add-div">
                    <form method="POST"  enctype="multipart/form-data" id="form1" onsubmit="return check()" action="/admin/teacher/uploadexcle">
                        <div class="form-group">
                            <a class="btn btn-info" href="/admin/student/list/1">返回列表页</a>
                        </div>
                        <div class="form-group">
                            <input  id="upfile" type="file" name="upfile" value="11" class="col-md-offset-3 col-md-3" onchange="selectFile(this)">
                            <div class="col-md-3">
                                <input  type="submit" href="#" value="导入Excel">
                            </div>
                            <font id="importMsg" color="red"><%=importMsg%></font><input type="hidden"/>
                        </div>
                    </form>
                </div>
            </div>

            <c:if test="${list != null}">
            <div class="callout callout-info lead">
                <h4>上传成功</h4>
            </div>
                <table class="table table-hove">
                    <thead>
                    <tr>
                        <th>工号</th>
                        <th>姓名</th>
                        <th>职称</th>
                    </tr>
                    </thead>
                    <c:forEach var="list" items="${list}">
                        <c:if test="${list.get(3) == '1'}">
                        <tr>
                            <td>${list.get(0)}</td>
                            <td>${list.get(1)}</td>
                            <td>${list.get(2)}</td>
                        </tr>
                        </c:if>
                    </c:forEach>
                </table>

            <div class="callout callout-danger lead">
                <h4>上传失败</h4>
            </div>
                <table class="table table-hove">
                    <thead>
                    <tr>
                        <th>工号</th>
                        <th>原因</th>
                    </tr>
                    </thead>
                    <c:forEach var="list" items="${list}">
                        <c:if test="${list.get(3) != '1'}">
                            <tr>
                                <td>${list.get(0)}</td>
                                <td>${list.get(3)}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </c:if>
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

<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/dist/js/app.min.js"></script>
<script type="text/javascript">
    var flag = 0;
    function selectFile(input) {
        var fileName = input.value;
        if (fileName.length > 1 && fileName) {
            var ldot = fileName.lastIndexOf(".");
            var type = fileName.substring(ldot + 1);
            if (type != "xls" && type != "xlsx") {
                alert("请选择Excel格式的文件导入！");
                //清除当前所选文件
                input.outerHTML = input.outerHTML.replace(/(value=\").+\"/i, "$1\"");
                flag = 0;
            } else {
                flag = 1;
            }
        }
    }
    $(document).ready(function () {
        var msg="";
        if($("#importMsg").text()!=null){
            msg=$("#importMsg").text();
        }
        if(msg!=""){
            alert(msg);
        }
    });
    function check(){
        if(flag == 0) {
            alert("请选择文件！");
            return false;
        } else {
            return true;
        }
    }



</script>

</body>
</html>
