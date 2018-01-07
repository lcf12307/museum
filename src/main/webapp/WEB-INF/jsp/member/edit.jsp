<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>图书馆运行评估系统</title>
    <%@include file="../common/head.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<iframe id="hiddenFrame" style="display:none;">
    <!--吧表单提交到这里不刷新页面-->
</iframe>
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
                    新增用户
                </div>
                <div class="panel-body" >
                    <form class="form-horizontal" id="edit_member_from" method="post" target="hiddenFrame">
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label"><font color="red">*</font>用户名</label>
                            <div class="col-sm-10">
                                <input type="text" id="name" name="name" class="form-control" value="${member.name}" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="age" class="col-sm-2 control-label"><font color="red">*</font>年龄</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="age" name="age" type="number" min="1" max="99" value="${member.age}" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <c:if test="${roles==null||roles.size()==0}">
                                <script>
                                    alert("请先增加角色！");
                                    location.href="/role/list";
                                </script>
                            </c:if>
                            <label for="role" class="col-sm-2 control-label"><font color="red">*</font>用户类别</label>
                            <select name="role" id="role" class="form-control col-sm-10">
                                <c:forEach items="${roles}" var="role">
                                    <option value="${role.id}" <c:if test="${role.id==member.role}">selected</c:if> >${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label"><font color="red">*</font>密码</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="password" name="password" type="password" placeholder="密码"  required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="confirmpassword" class="col-sm-2 control-label"><font color="red">*</font>确认密码</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="confirmpassword" name="confirmpassword" type="password" placeholder="确认密码" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="phone" class="col-sm-2 control-label"><font color="red">*</font>手机号</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="phone" name="phone" type="text" value="${member.phone}" required />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label"><font color="red">*</font>邮箱</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="email" name="email" type="email" value="${member.email}" required />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10"  style="padding-top: 10px;text-align: center;">
                                    <button type="submit" id="sub" class="btn btn-primary">保存</button>
                                    <button onclick="javascript :history.back();" class="btn btn-primary">返回</button>
                                </div>
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
        $('#edit_member_from').bootstrapValidator({
            fields: {
                phone:{
                    validators:{
                        regexp:{
                            regexp: /^1[3|5|7|8][0-9]{9}$/,
                            message: '手机号格式有误'
                        }
                    }
                },
                password: {
                    validators: {
                        regexp: {
                            regexp: /^(?!\D+$)(?![^a-z]+$)[a-zA-Z\d]{6,20}$/i,
                            message: '请输入6-18位混合的字母与数字密码'
                        }
                    }
                },
                confirmpassword: {
                    validators: {
                        regexp: {
                            regexp: /^(?!\D+$)(?![^a-z]+$)[a-zA-Z\d]{6,20}$/i,
                            message: '请输入6-18位混合的字母与数字密码'
                        },
                        identical: {
                            message: '确认新密码与新密码不一致',
                            field: 'password'
                        }
                    }
                }
            }
        });
        $('#edit_member_from').on('click','#sub',function(e){
            //post提交
            e.preventDefault();//阻止提交
            $("#edit_member_from").data('bootstrapValidator').validate();
            var flag = $("#edit_member_from").data('bootstrapValidator').isValid();
            if(!flag)
                return;
            $.ajax({
                type: "POST",
                url: "/member/editProcess/${member.id}",
                dataType: "json",
                data:JSON.stringify({
                    name:$('#name').val(),
                    age:$('#age').val(),
                    role:$('#role').val(),
                    password:$('#password').val(),
                    phone:$('#phone').val(),
                    email:$('#email').val()
                }),
                contentType: 'application/json;charset=UTF-8',
                success: function(result){
                    if(result.code == 0){
                        alert('修改成功！');
                        window.location.href = document.referrer;
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
