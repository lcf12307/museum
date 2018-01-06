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
                            <span class="hidden-xs">${sessionScope.get("teacherId")}</span>
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
                    <p>${sessionScope.get("teacherId")}</p>
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
                <small>毕设系统</small>
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
                <h2>新建博物馆</h2>
            </div>
            <form action="./insert" role = "form" name="museumForm" method="post" style="font-size:18px;line-height:2">

                <div class="form-group" style="width: 50%">
                    <label for="museumName">博物馆名称</label>
                    <input id = "museumName" name="museumName" type="text" class="form-control" >
                </div>

                <%--</div>--%>
                <div class="form-group" style="width: 50%">
                    <label>博物馆类型</label>
                    <select>
                        <option value=1>历史、文化及综合类</option>
                        <option value=2>自然、科技与专题类</option>
                        <option value=3>纪念类</option>
                    </select>
                </div>

                <div class="form-group" style="width: 50%">
                    <label>博物馆级别</label>
                    <select>
                        <option value=1>省级及省级以上</option>
                        <option value=2>其他</option>
                    </select>
                </div>
                <div>
                    <label>评审年份</label>
                    <label><input name="2012" type="checkbox" value="2012" />2012 </label>
                    <label><input name="2013" type="checkbox" value="2013" />2013 </label>
                    <label><input name="2014" type="checkbox" value="2014" />2014 </label>
                    <label><input name="2015" type="checkbox" value="2015" />2015 </label>
                    <label><input name="2016" type="checkbox" value="2016" />2016 </label>
                    <label><input name="2017" type="checkbox" value="2017" />2017 </label>
                </div>
                <div>
                    <label>详细资料</label>
                    <textarea name="description" id="description" rows="8" class="form-control">${getMuseum.description}</textarea>
                </div>
                <div align="left">
                    <input class="btn btn-success" type="submit" value="提交" >
                </div>
            </form>

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
</body>
</html>


