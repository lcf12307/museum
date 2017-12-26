<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <%@include file="../common/head.jsp"%>
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
                                    <a href="#" class="btn btn-default btn-flat" onclick="location.href='http://localhost:8080'">登出</a>
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
            <ul class="sidebar-menu" id="sidebraMenu">
                <li class="header">查询系统</li>
                <li><a href="/teacher/${sessionScope.get("userid")}/teacherinfo"><i class="fa fa-link"></i><span>查看课表</span></a></li>
                <li><a href="/teacher/${sessionScope.get("userid")}/grade/list"><i class="fa fa-link"></i><span>查看成绩</span></a></li>
                <li class="header">毕设系统</li>
                <li><a href="/teacher/insertSubject.action"><i class="fa fa-link"></i><span>添加新课题</span> </a></li>
                <li><a href="/teacher/${sessionScope.get("userid")}/subjects"><i class="fa fa-link"></i><span>查看已发布课题</span> </a></li>
                <li class="header">选课系统</li>
                <li><a href="/teacher/${sessionScope.get("userid")}/tchcourselist"><i class="fa fa-link"></i><span>查看课表</span></a></li>
                <li><a href="/teacher/${sessionScope.get("userid")}/coursestulist"><i class="fa fa-link"></i><span>查看学生信息</span></a></li>
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
            <c:if test="${isOver.equals('yes')}">
                <div>
                    <div align="center">
                        <h2>查看成绩</h2>
                        <small>成绩提交截止时间已过，不在提供修改功能</small>
                    </div>
                </div>
                <div class="row clearfix">
                <div class="col-md-6 column">
                    <table class="table table-bordered" >
                        <thead>
                        <tr>
                            <th>学号</th>
                            <th>姓名</th>
                            <th>成绩</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${gradeInsert}" var="gradeInsert" varStatus="i">
                            <tr>
                                <td>${gradeInsert.studentId}</td>
                                <td>${gradeInsert.studentName}</td>
                                <td>${gradeInsert.grade}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                </div>
            </c:if>
            <c:if test="${isOver.equals('no')}">
            <div>
                <div class="toHide">
                    <h2 align="center">插入/删除</h2>
                </div>
                <div>
                    <div align="right" id="btnDiv">
                        <a class="btn btn-primary" href="/teacher/${teacherId}/grade/${openId}/downloadDemo">下载报表模板</a>
                        <a class="btn btn-primary" id="insert" href="/teacher/${teacherId}/grade/${openId}/upload">批量输入学生</a>
                        <a class="btn btn-primary" id="print">打印</a>
                    </div>
                    <form id="insetForm" action="/teacher/${teacherId}/grade/${openId}/insert/update">
                        <div class="btnGroup" align="right">
                            <button type="submit" class="btn btn-info" onclick="if(confirm('你确定提交吗')==false)return false;else alert('提交成功!')">提交</button>
                        </div>
                        <table class="table table-bordered" >
                            <thead>
                            <tr>
                                <th>学号</th>
                                <th>姓名</th>
                                <th>成绩</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${gradeInsert}" var="gradeInsert" varStatus="i">
                                <tr>
                                    <td><input type="hidden" value="${gradeInsert.studentId}" name="stuGradeList[${i.index}].studentId">${gradeInsert.studentId}</td>
                                    <td>${gradeInsert.studentName}</td>
                                    <!-- onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"-->
                                    <!--   -->
                                    <td><input onchange="if(!/(^0$)|(^100$)|(^\d{1,2}$)/.test(value)){value='';alert('输入格式不正确!');}" value="${gradeInsert.grade}" name="stuGradeList[${i.index}].grade"></td>
                                    <td><input type="hidden" value="${gradeInsert.openId}" name="stuGradeList[${i.index}].openId"></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
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

<script src="/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/dist/js/app.min.js"></script>
<script>
    $('#print').click(function () {
        $('.btn').hide();
        $('#insetH').hide();
        $('#topDiv').hide();
        $('.btnGroup').hide();
        $('.toHide').hide();
        window.print();
        $('.tohide').show();
        $('.btn').show();
        $('#printH').hide();
        $('#topDiv').show();
        $('.btnGroup').show();
    });
</script>
</body>
</html>
