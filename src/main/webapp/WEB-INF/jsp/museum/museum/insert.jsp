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
    <title>图书馆运行评估系统</title>
    <%@include file="../../common/head.jsp"%>
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
        <jsp:include page="../../common/sidebar.jsp"/>
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
            <div class="panel-heading text-center">
                <h2>新建博物馆</h2>
            </div>
            <form action="../insert" role = "form" name="museumForm" method="post" style="font-size:18px;line-height:2">

                <div class="form-group" style="width: 50%">
                    <label for="name">博物馆名称</label>
                    <input id = "name" name="name" type="text" class="form-control" >
                </div>

                <%--</div>--%>
                <div class="form-group" style="width: 50%">
                    <label>博物馆类型</label>
                    <select name="category" id="category">
                        <option value="历史文化与综合类">历史文化及综合类</option>
                        <option value="自然、科技与专题类">自然、科技与专题类</option>
                        <option value="纪念类">纪念类</option>
                    </select>
                </div>

                <div class="form-group" style="width: 50%">
                    <label>博物馆级别</label>
                    <select name="level" id="level">
                        <option value="省级及省级以上">省级及省级以上</option>
                        <option value="其他">其他</option>
                    </select>
                </div>
                <div>
                    <label>评审年份</label>
                    <label><input name="year" type="checkbox" value="2018" />2018 </label>
                    <label><input name="year" type="checkbox" value="2017" />2017 </label>
                    <label><input name="year" type="checkbox" value="2016" />2016 </label>
                    <label><input name="year" type="checkbox" value="2015" />2015 </label>
                    <label><input name="year" type="checkbox" value="2014" />2014 </label>
                    <label><input name="year" type="checkbox" value="2013" />2013 </label>
                    <label><input name="year" type="checkbox" value="2012" />2012 </label>
                    <label><input name="year" type="checkbox" value="2011" />2011 </label>
                    <label><input name="year" type="checkbox" value="2010" />2010 </label>
                    <label><input name="year" type="checkbox" value="2009" />2009 </label>
                    <label><input name="year" type="checkbox" value="2008" />2008 </label>
                </div>
                <div>
                    <label>详细资料</label>
                    <textarea name="description" id="description" rows="8" class="form-control">${getMuseum.description}</textarea>
                </div>
                <!-- <div align="left">
                     <input class="btn btn-success" type="submit" value="提交" >
                 </div>-->
                <div>
                    <button type="button" class="btn btn-default" onclick="insertMuseum()">提交</button>
                </div>
            </form>
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
<script type="text/javascript">
        function handleCheck() {
            var str="";
            var checkBoxs = document.getElementsByName("year");
            for (var i = 0; i < checkBoxs .length; i++) {
                if (checkBoxs[i].checked) {
                    str = str + checkBoxs[i].value + ',';
                }
            }
            return str;
        }
        function insertMuseum() {
            var name = $('#name').val();
            var category = $('#category').val();
            var level = $('#level').val();
            var year = handleCheck();
            var description = $('#description').val();

            if (name == "") {
                alert("博物馆名字不能为空");
            }
            else {

                $.ajax(
                    {
                        type: 'POST',
                        url: '/museum/museum/insert',
                        dataType: 'json',
                        contentType: 'application/json;charset=UTF-8',
                        data:JSON.stringify({
                            name:name,
                            category:category,
                            level:level,
                            year:year,
                            description:description
                        }),  //提交json字符串数组

                        success: function (data) {
                            if (data == "SUCCESS") {
                                alert("添加成功");
                                window.location.href = "/museum/museum/list";
                            }
                            else if(data == "NAME_FAIL"){
                                alert("名错");
                            }
                            else if(data =="YEAR_FAIL"){
                                alert("年错");
                            }
                            else if(data =="CATEGORY_FAIL"){
                                alert("类错");
                            }
                            else if(data =="LEVEL_FAIL"){
                                alert("级错");
                            }
                            else {
                                alert("添加失败");
                            }
                        },
                        error: function () {
                            alert("插入数据失败");
                        }
                    });
            }
        }

</script>

</body>
</html>



