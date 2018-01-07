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
                <li class="active">修改个人信息</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content" style="background:#fff;">
            <!--页面写在这里-->
            <div class="panel panel-default">
                <div class="panel-heading">修改个人信息</div>
                <div class="panel-body">
                    <form class="form-horizontal" method="post" id="edit_my_info_form">
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-10">
                                <input type="text" id="name" name="name" class="form-control" value="${member.name}" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="age" class="col-sm-2 control-label">年龄</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="age" name="age" type="number" min="1" max="99" value="${member.age}" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="phone" class="col-sm-2 control-label">手机号</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="phone" name="phone" type="text" value="${member.phone}" required />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">邮箱</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="email" name="email" type="email" value="${member.email}" required />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" id="sub" class="btn btn-default">保存</button>
                            </div>
                        </div>
                    </form>
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
    $(document).ready(function () {
        $('#edit_my_info_form').bootstrapValidator({
            fields: {
                phone:{
                    validators:{
                        regexp:{
                            regexp: /^1[3|5|7|8][0-9]{9}$/,
                            message: '手机号格式有误'
                        }
                    }
                }
            }
        });
        $('#edit_my_info_form').on('click','#sub',function(e){
            //post提交
            e.preventDefault();//阻止提交
            $("#edit_my_info_form").data('bootstrapValidator').validate();
            var flag = $("#edit_my_info_form").data('bootstrapValidator').isValid();
            if(!flag)
                return;
            $.ajax({
                type: "POST",
                url: "/member/editMyInfoProcess",
                data: $('#edit_my_info_form').serializeArray(),
                dataType: "json",
                success: function(result){
                    if(result.code == 0){
                        alert('修改成功！');
                    }else{
                        alert(result.msg);
                    }
                },
                error: function () {
                    alert('修改失败，请稍后再试！');
                }
            });
        });
    });
</script>
</body>
</html>
