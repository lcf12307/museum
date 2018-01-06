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
                    <li><a href="logout"><i class="fa fa-sign-out"></i> &nbsp;退出系统</a></li>
                </ul>
            </div>
        </nav>
    </header>
    <aside class="main-sidebar">
        <jsp:include page="../common/sidebar.jsp"/>
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
                <div class="panel-heading">新增角色</div>
                <div class="panel-body">
                    <form class="form-horizontal" method="post" id="add_role_form" target="hiddenFrame">
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label"><font color="red">*</font>角色名称</label>
                            <div class="col-sm-10">
                                <input type="text" id="name" name="name" class="form-control" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description" class="col-sm-2 control-label"><font color="red">*</font>角色描述</label>
                            <div class="col-sm-10">
                                <input type="text" id="description" name="description" class="form-control" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"><font color="red">*</font>角色权限</label>
                            <div class="col-sm-14" style="margin-left: 130px">
                                <label class="checkbox-inline">
                                    <input type="checkbox" id="inlineCheckbox1" name="authority" value="1"> 申报书管理
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" id="inlineCheckbox2" name="authority" value="2"> 打分表管理
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" id="inlineCheckbox3" name="authority" value="4"> 计算管理
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" id="inlineCheckbox4" name="authority" value="8"> 统计表管理
                                </label>
                            </div>
                            <div class="col-sm-14" style="margin-left: 130px">
                                <label class="checkbox-inline">
                                    <input type="checkbox" id="inlineCheckbox5" name="authority" value="16"> 排名管理
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" id="inlineCheckbox6" name="authority" value="32"> 博物馆管理
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" id="inlineCheckbox7" name="authority" value="64"> 专家管理
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" id="inlineCheckbox8" name="authority" value="128"> 用户管理
                                </label>
                            </div>
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
        <!-- /.content -->
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
        $('#add_role_form').bootstrapValidator({
            fields: {
                name:{
                    validators:{
                        regexp:{
                            regexp: /^([\u4e00-\u9fa5]){2,8}$/,
                            message: '只能输入2-8个中文数字'
                        }
                    }
                },
				description:{
					validators:{
						regexp:{
							regexp:/^[^'"]*$/,
							message:'非法字符'
						},
						stringLength: {
                            max: 100,
                            message: '长度不能超过100'
                        },
					}
				}
            }
        });
        $('#add_role_form').on('click','#sub',function(e){
            //post提交
            e.preventDefault();//阻止提交
            $("#add_role_form").data('bootstrapValidator').validate();
            var flag = $("#add_role_form").data('bootstrapValidator').isValid();
            if(!flag)
                return;
            var postVar = $('#add_role_form').serializeArray();
            for(e in postVar){
                if(e.name=='authority') {
                    flag = true;
                    break;
                }
            }
            if(!flag){
                alert('至少需要选择一个权限');
                return;
            }
            $.ajax({
                type: "POST",
                url: "/role/addProcess",
                data: postVar,
                dataType: "json",
                success: function(result){
                    if(result.code == 0){
                        alert('新增成功！');
                        //返回并刷新上一页
                        window.location.href = document.referrer;
                    }else{
                        alert(result.msg);
                    }
                },
                error: function () {
                    alert('新增失败，请稍后再试！');
                }
            });
        });
    });

</script>
</body>
</html>
