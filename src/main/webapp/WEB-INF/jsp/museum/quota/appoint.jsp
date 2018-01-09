<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>博物馆运行评估系统</title>
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
        <section class="content" style="background: #fff">
            <div class="panel panel-default">
                <div class="panel-heading text-center">
                    <h2>评审专家列表</h2>
                </div>
                <div class="panel-body" >
                    <div>
                        <a class = "btn btn-primary" href="/museum/quota/list/1" target="_self">返回</a>
                        <h5></h5>
                    </div>
                    <div class="input-group">
                        <select name="yearKey" id = "yearKey" type = "commit" style="height:30px;">
                            <option value="${yearKey+4}">${yearKey+4}</option>
                            <option value="${yearKey+3}">${yearKey+3}</option>
                            <option value="${yearKey+2}">${yearKey+2}</option>
                            <option value="${yearKey+1}">${yearKey+1}</option>
                            <option value="${yearKey}">${yearKey}</option>
                            <option value="${yearKey-1}">${yearKey-1}</option>
                            <option value="${yearKey-2}">${yearKey-2}</option>
                            <option value="${yearKey-3}">${yearKey-3}</option>
                            <option value="${yearKey-4}">${yearKey-4}</option>
                            <option value="${yearKey-5}">${yearKey-5}</option>
                            <c:if test="${yearKey!=null}">
                                <script>
                                    $('#yearKey').val("${yearKey}");
                                </script>
                            </c:if>
                        </select>
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default" onclick="searchQuota()">搜索</button>
                            <a class="btn btn-primary" href="/museum/quota/download/${yearKey}" target="_self">下载</a>
                         </span>
                    </div>

                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>序号  </th>
                            <th>专家姓名</th>
                            <th>一级指标</th>
                            <th> 操作 </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${list.size() != 0}">
                                <%
                                    int s = 0;
                                %>
                                <c:forEach items="${list}" var="quota">
                                    <tr><%
                                        s++;
                                    %>
                                        <td><%=s%></td>
                                        <td>${quota.name}</td>
                                        <td>${quota.quotaId}</td>
                                        <td>
                                            <a class="btn btn-danger" onclick="delQuota('${yearKey}','${quota.name}')" target="_blank">取消</a>
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
                        <button class="btn btn-default btn-sm" onclick="front()"><i class="glyphicon glyphicon glyphicon-home"></i></button>
                        <button class="btn btn-default btn-sm" onclick="prev()"><i class="glyphicon glyphicon-chevron-left"></i></button>
                        <button class="btn btn-default btn-sm" onclick="next()"><i class="glyphicon glyphicon-chevron-right"></i></button>
                        <button class="btn btn-default btn-sm" onclick="refresh()"><i class="glyphicon glyphicon-refresh"></i></button>
                        <h5>共${pages}页</h5>
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
    /**首页**/
    function front(){
        window.location.href="/museum/quota/appoint/1?yearKey=${yearKey}";
    }
    /**上一页**/
    function prev(){
        if(${page} == 1 ) {
            alert("当前已是第一页");
        }
    else{
            window.location.href="/museum/quota/appoint/${page-1}?yearKey=${yearKey}";
        }
    }
    /**下一页**/
    function next(){
        if(${pages} == ${page}){
            alert("当前已是最后一页");
        } else{
            window.location.href="/museum/quota/appoint/${page+1}?yearKey=${yearKey}";
        }
    }
    /**刷新**/
    function refresh(){
        window.location.reload();
    }
    function delQuota(yearKey ,name){
        var year = yearKey;
        var name = name;
        var url = "/museum/quota/cancel";
        if(confirm("确定要取消该专家吗？")) {
            $.ajax({
                type: 'POST',
                url: url,
                dataType: 'json',
                contentType: 'application/json;charset=UTF-8',
                data: JSON.stringify({
                    name: name,
                    year:year
                }),  //提交json字符串数组
                success:function(data){
                    if(data=="SUCCESS"){
                        alert("取消成功");
                        window.location.reload();
                    }
                    else if (data == "POINT_FAIL"){
                        alert("请删除专家打分表再进行此操作");
                    }else{
                        alert("未知的错误")
                    }
                },
                error: function (textStatus, errorThrown) {
                    console.log(textStatus);
                    alert("删除失败");
                }
            });
        }
    }
    function searchQuota() {
        var yearKey = $('#yearKey').val();
        window.location.href="/museum/quota/appoint/1?yearKey="+yearKey;
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

    function getTime() {
        var ydate = new Data();
        var yyyy = ydate.getFullYear();
        var ye=document.getElementById("yearKey");
        ye.innerHTML = yyyy;
    }
    function backList(){
        var searchKey = "";
        document.cookie="expertProperty="+yearKey+";path= /museum/quota/";
        window.location.href="/museum/quota/appoint/1";
    }

</script>
</body>
</html>
