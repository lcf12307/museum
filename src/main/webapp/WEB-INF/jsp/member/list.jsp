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
                <li class="active">位置导航</li>
            </ol>
        </section>
        <section class="content" style="background:#fff;">
            <!--页面写在这里-->
            <div class="panel panel-default">
                <div class="panel-heading">
                    用户列表
                </div>
                <div class="panel-body" >
                    <div class="">
                        <div class="form-group col-sm-2">
                            <input type="text" placeholder="姓名" value="${nameKey}" id="nameKey" class="form-control">
                        </div>
                        <a class="btn btn-primary btn-sm" onclick="find()" ><i class="fa fa-search"></i>查询</a>
                        <a class="btn btn-primary btn-sm" href="/member/add"><i class="fa fa-plus"></i>新增</a>
                    </div>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>用户名</th>
                            <th>用户类别</th>
                            <th>年龄</th>
                            <th>email</th>
                            <th>手机号码</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${memberList.size() != 0}">
                                <c:if test="${roles==null||roles.size()==0}">
                                    <script>
                                        alert("参数错误，请添加角色！");
                                        location.href="/role/list";
                                    </script>
                                </c:if>
                                <c:forEach items="${memberList}" var="member">
                                    <tr>
                                        <td>${member.id}</td>
                                        <td>${member.name}</td>
                                        <td>${roles[member.role]}</td>
                                        <td>${member.age}</td>
                                        <td>${member.email}</td>
                                        <td>${member.phone}</td>
                                        <td>
                                            <a class="btn btn-info btn-sm" href="/member/edit/${member.id}"><i class="fa fa-edit"></i>修改</a>
                                            <a class="btn btn-danger btn-sm" onclick="deleteMember(${member.id})" ><i class="fa fa-remove"></i>删除</a>
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
                <div class="panel-footer" >
                    <!--分页-->
                    <div class="mailbox-controls">
                        <a class="btn btn-default btn-sm" onclick="front()" ><i class="glyphicon glyphicon glyphicon-home"></i></a>
                        <a class="btn btn-default btn-sm" onclick="prev()"><i class="glyphicon glyphicon-chevron-left"></i></a>
                        <a class="btn btn-default btn-sm" onclick="next()"><i class="glyphicon glyphicon-chevron-right"></i></a>
                        <a class="btn btn-default btn-sm" onclick="refresh()"><i class="glyphicon glyphicon-refresh"></i></a>
                    </div>
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
    var nameKey="${nemeKey}";
    function find() {
        window.location.href="/member/list/1?nameKey="+$("#nameKey").val();
    }
    function deleteMember(id){
        if(confirm('确定要删除吗？')) {
            $.ajax({
                type: "POST",
                url: "/member/delete",
                data: 'id=' + id,
                dataType: "json",
                success: function (result) {
                    if (result.code == 0) {
                        alert('删除成功！');
                        location.reload();
                    } else {
                        alert(result.msg);
                    }
                },
                error: function () {
                    alert('删除失败，请稍后再试！');
                }
            });
        }
    }
    /**首页**/
    function front(){

        window.location.href="/member/list/1";
    }
    /**上一页**/
    function prev(){
        if(${page==1}){
            alert("当前已是第一页");
        }else{
            window.location.href="/member/list/${page-1}?nameKey="+nameKey;
        }
    }
    /**下一页**/
    function next(){
        if(${pages} == ${page}){
            alert("当前已是最后一页");
        } else{
            window.location.href="/museum/quota/list/${page+1}?nameKey="+nameKey;
        }
    }
    /**刷新**/
    function refresh(){
        window.location.reload();
    }
</script>
</body>
</html>
