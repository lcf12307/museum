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
            <div class="panel panel-default">
                <div class="panel-heading">角色列表</div>
                <div class="panel-body" >
                    <div>
                        <a class="btn btn-primary btn-sm" href="/role/add"><i class="fa fa-plus"></i>新增角色</a>
                    </div>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>角色名称</th>
                            <th>角色说明</th>
                            <th>权限</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${roleJsons.size() != 0}">
                                <c:forEach items="${roleJsons}" var="role">
                                    <tr>
                                        <td>${role.id}</td>
                                        <td>${role.name}</td>
                                        <td>${role.description}</td>
                                        <td>${role.authorityNames}</td>
                                        <td>
                                            <a class="btn btn-info btn-sm" href="/role/edit/${role.id}"><i class="fa fa-edit"></i>修改</a>
                                            <a class="btn btn-danger btn-sm" onclick="deleteRole(${role.id})"><i class="fa fa-remove"></i>删除</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <td colspan="4" align="center">对不起，没有相关数据</td>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
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
	function deleteRole(id){
		if(confirm('确定要删除吗？')){
            $.ajax({
                type: "POST",
                url: "/role/delete",
                data: 'id='+id,
                dataType: "json",
                success: function(result){
                    if(result.code == 0){
                        alert('删除成功！');
                        location.reload();
                    }else{
                        alert(result.msg);
                    }
                },
                error: function () {
                    alert('删除失败，请稍后再试！');
                }
            });
        }
	}
</script>
</body>
</html>
