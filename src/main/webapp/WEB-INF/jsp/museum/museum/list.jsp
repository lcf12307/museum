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
        <%@include file="../../common/sidebar.jsp"%>
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
                <h2>博物馆列表</h2>
            </div>
            <div>
                <a class = "btn btn-primary" href="/museum/museum/insertInfo" target="_self">添加博物馆</a>
            </div>
            <br>
            <div class="input-group">
                <input type="text" class="form-control" placeholder="请输入博物馆名称" id="nameKey" name="nameKey" value=${nameKey}>
                <span class="input-group-btn">
                        <button type="button" class="btn btn-default" onclick="searchMuseum()">搜索</button>
                </span>
            </div>
            <div class="panel-body" >
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>序号  </th>
                        <th>博物馆名称</th>
                        <th>博物馆类型</th>
                        <th>博物馆级别</th>
                        <th>评审年份</th>
                        <th> 操作 </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${list.size() != 0}">
                            <%
                                int s = 0;
                            %>
                            <c:forEach var="museum" items="${list}" >
                                <tr><%
                                    s++;
                                %>
                                    <td><%=s%></td>
                                    <td>${museum.name}</td>
                                    <td>${museum.category}</td>
                                    <td>${museum.level}</td>
                                    <td>${museum.year}</td>
                                    <td><a class="btn btn-info" href="/museum/museum/detail/${museum.id}" target="_self">详细信息</a>
                                        <a class="btn btn-info" href="/museum/museum/edit/${museum.id}" target="_self">修改</a>
                                        <a class="btn btn-danger" onclick="delMuseum('${museum.id}')" target="_blank">删除</a>
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
                    <button class="btn btn-default btn-sm" onclick="front()"><i class="glyphicon glyphicon glyphicon-home"></i>首页</button>
                    <button class="btn btn-default btn-sm" onclick="prev()"><i class="glyphicon glyphicon-chevron-left"></i>上一页</button>
                    <button class="btn btn-default btn-sm" onclick="next()"><i class="glyphicon glyphicon-chevron-right"></i>下一页</button>
                    <button class="btn btn-default btn-sm" onclick="refresh()"><i class="glyphicon glyphicon-refresh"></i>刷新</button>
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
<script type="text/javascript">
    /**首页**/
    function front(){
        window.location.href="/museum/museum/list/1";
    }
    /**上一页**/
    function prev(){
        var nameKey = request('nameKey');
        if (nameKey == undefined){
            nameKey = "";
        }
        if(1 ==${page} ) {
            alert("当前已是第一页");
        }
        else{
            window.location.href="/museum/museum/list/${page-1}";
        }
    }

    /**下一页**/
    function next(){
        var nameKey = request('nameKey');
        if (nameKey == undefined){
            nameKey = "";
        }
        if(${pages} == ${page}){
            alert("当前已是最后一页");
        } else{
            window.location.href="/museum/museum/list/${page+1}";
        }
    }
    /**刷新**/
    function refresh(){
        window.location.reload();
    }
    function delMuseum(id){
        var url = "/museum/museum/delete";

        var id = id ;
        if(confirm("确定要删除该博物馆吗？")){
            $.ajax({
                type: 'POST',
                url: url,
                dataType: 'json',
                contentType:'application/json;charset=UTF-8',
                data:JSON.stringify({
                    id:id
                }),  //提交json字符串数组
                success:function(){
                    window.location.reload();
                    alert("删除成功！")
                },
                error:function(){
                    alert("删除失败");
                }
            });
        }
    }
    function searchMuseum() {
        var nameKey = $('#nameKey').val();
        window.location.href="/museum/museum/list/1?nameKey="+nameKey;
    }

    function request(strParame) {
        var args = new Object( );
        var query = location.search.substring(1);
        var pairs = query.split("&"); // Break at ampersand
        for(var i = 0; i < pairs.length; i++) {
            var pos = pairs[i].indexOf('=');
            if (pos == -1) continue;
            var argname = pairs[i].substring(0,pos);
            var value = pairs[i].substring(pos+1);
            value = decodeURIComponent(value);
            args[argname] = value;
        }
        return args[strParame];
    }

</script>
</body>
</html>
