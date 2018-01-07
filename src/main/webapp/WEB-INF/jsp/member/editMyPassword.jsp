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
        <section class="content" style="background:#fff;">
            <div class="panel panel-default">
                <div class="panel-heading">修改密码</div>
                <div class="panel-body">
                    <form class="form-horizontal" id="edit_password_form" method="post">
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-10">
                                <input type="text" id="name" class="form-control" value="${member.name}" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">原密码</label>
                            <div class="col-sm-10">
                                <input class="form-control" type="password" id="password" name="password" placeholder="原密码" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="newpassword" class="col-sm-2 control-label">新密码</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="newpassword" name="newpassword" type="password"placeholder="新密码"  required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="confirmpassword" class="col-sm-2 control-label">确认新密码</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="confirmpassword" name="confirmpassword" type="password" placeholder="确认密码" required/>
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
        $('#edit_password_form').bootstrapValidator({
			fields:{
				password: {
					validators: {
						regexp: {
							regexp: /[A-z0-9]/,
							message: '密码输入有误'
						}
					}
				},
				newpassword: {
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
							field: 'newpassword'
						}
					}
				}
			}
			/*submitHandler: function(form){
                //bootstrapValidator5.3+ 移除了submitHandler
				var data = "password="+ $("#password").val()+"&newPassword="+$("#newPassword").val();
				$.ajax({
					type: "POST",
					url: "member/password",
					data: data,
					dataType: "json",
					success: function(result){
						if(result.code == 0){
							alert('修改成功', function(index){
								location.reload();
							});
						}else{
							alert(result.msg);
						}
					}
				});
			}*/
		});

         $('#edit_password_form').on('click','#sub',function(e){
             //post提交
             e.preventDefault();//阻止提交
             $("#edit_password_form").data('bootstrapValidator').validate();
             var flag = $("#edit_password_form").data('bootstrapValidator').isValid();
             if(!flag)
                 return;
             var data = "password="+ $("#password").val()+"&newPassword="+$("#newpassword").val();
             $.ajax({
                 type: "POST",
                 url: "/member/password",
                 data: data,
                 dataType: "json",
                 success: function(result){
                     if(result.code == 0){
                         alert('修改成功，请重新登录！');
                         top.location.href='/logout';
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