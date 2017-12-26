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
            <ul class="sidebar-menu">
                <li class="header">管理系统</li>
                <li><a href="/student/${sessionScope.get("userid")}/course"><i class="fa fa-link"></i><span>个人课表</span></a></li>
                <li><a href="/student/${sessionScope.get("userid")}/recomcourse"><i class="fa fa-link"></i><span>专业推荐课表</span></a></li>
                <li><a href="/student/${sessionScope.get("userid")}/grade"><i class="fa fa-link"></i><span>查看成绩</span></a></li>
                <li class="header">毕业设计系统</li>
                <li><a href="/student/${sessionScope.get("userid")}/CheckConfirmedSubject"><i class="fa fa-link"></i><span>已通过的课题</span></a></li>
                <li><a href="/student/${sessionScope.get("userid")}/CheckNotConfirmedSubject"><i class="fa fa-link"></i><span>未通过的课题</span></a></li>
                <li><a href="/student/${sessionScope.get("userid")}/list/1"><i class="fa fa-link"></i><span>可申请的课题</span></a></li>
                <li class="header">选课系统</li>
                <li><a href="/student/${sessionScope.get("userid")}/courselist"><i class="fa fa-link"></i><span>查看全部课程</span></a></li>
                <li><a href="/student/${sessionScope.get("userid")}/selectlist"><i class="fa fa-link"></i><span>查看已选课程</span></a></li>
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
            <div >
                <div>
                    <h2 align="center">成绩统计</h2>
                </div>
                <div >
                    <div class="col-md-6 column">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th></th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>GPA</td>
                                <td>${stat.gpa}</td>
                            </tr>
                            <tr>
                                <td>必修已获得学分</td>
                                <td>${stat.creditGotCom}</td>
                            </tr>
                            <tr>
                                <td>选修已获得学分</td>
                                <td>${stat.creditGotEle}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-6 column">
                    </div>
                </div>
                <div  id="failDiv">
                    <c:if test="${stat.failList.size()!=0}">
                        <div class="col-md-12 column">
                            <h4>
                                你还有以下课程未通过：
                            </h4>
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>课程ID</th>
                                    <th>课程名称</th>
                                    <th>学分</th>
                                    <th>性质</th>
                                    <th>学年</th>
                                    <th>学期</th>
                                    <th>成绩</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="course" items="${stat.failList}">
                                    <td>${course.courseId}</td>
                                    <td>${course.courseName}</td>
                                    <td>${course.credit}</td>
                                    <td>${course.nature}</td>
                                    <td>${course.schoolYear}</td>
                                    <td>${course.term}</td>
                                    <td>${course.grade}</td>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                </div>
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

</body>
</html>
