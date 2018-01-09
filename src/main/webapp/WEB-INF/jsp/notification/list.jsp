<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>申报书管理


    </title>
    <%@include file="../common/head.jsp" %>
    <script type="text/javascript">

        $(document).ready(function(){
            if ( 'success' === getUrlParam("upload") ){
                alert("上传成功");
            }
            if ( 'fail' === getUrlParam("upload") ){
                alert("上传失败");
            }
        });
        //获取url中的参数
        function getUrlParam(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg);  //匹配目标参数
            if (r != null) return unescape(r[2]); return null; //返回参数值
        }
        function  addFile() {
            var input = '<input type=\"file\" name="file">';
            $("#addDiv").append(input);
        }
        function refresh() {
            window.location.re
        }
        function ahead(){
            name = typeof (request('name'))=="undefined"?"":request('name');
            year = typeof (request('year'))=="undefined"?"":request('year');
            if( 1 == ${page} ) {
                alert("当前已是第一页");
            } else{
                window.location.href="/notification/list/${page-1}?name="+name+"&year="+year;
            }
        }
        function behind(){
            name = typeof (request('name'))=="undefined"?"":request('name');
            year = typeof (request('year'))=="undefined"?"":request('year');
            if(${pages} == ${page}){
                alert("当前已是最后一页");
            } else{
                window.location.href="/notification/list/${page+1}?name="+name+"&year="+year;
            }
        }
        function delAttachment(id){
            var page = ${page};
            var id = id;
            var url = "/notification/delete";
            if(confirm("确定要删除该附件吗")){
                $.ajax({
                    type: 'POST',
                    url: url,
                    dataType: 'json',
                    contentType:'application/json;charset=UTF-8',
                    data:JSON.stringify({
                        id:id,
                        page:page
                    }),  //提交json字符串数组
                    success:function(){
                        window.location.reload();
                    },
                    error:function(){
                        alert("删除失败");
                    }
                });
            }

        }

        function search() {
            var name = $('#searchName').val();
            var year = $('#searchYear').val();
            console.log(year);
            window.location.href="/notification/list/1?name="+name+"&year="+year;
        }
        function searchByName() {
            var name = $('#searchName').val();
            window.location.href="/notification/list/1?name="+name;
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
            <!-- 模态框（Modal） -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header" align="right">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">上传申报书</h4>
                        </div>
                        <div class="modal-footer">
                            <form id="insertForm" action="/notification/upload" method="POST" enctype="multipart/form-data" align="left">
                                <label>博物馆名称：</label>
                                <input type="text" name="uploadName" id="uploadName" placeholder="博物馆名字">
                                <div align="left">
                                    <label>请选择年份：</label>
                                    <select id="uploadYear" name="uploadYear" form="insertForm">
                                        <option value="2018">2018</option>
                                        <option value="2017">2017</option>
                                        <option value="2016">2016</option>
                                        <option value="2015">2015</option>
                                        <option value="2014">2014</option>
                                        <option value="2013">2013</option>
                                        <option value="2012">2012</option>
                                        <option value="2011">2011</option>
                                        <option value="2010">2010</option>
                                    </select>
                                </div>
                                <div id="addDiv"   >
                                    <input type="file" name="file"/>
                                </div>
                                <div align="right">
                                    <button name="addUpload" type="button" onclick="addFile()">添加文件</button>
                                </div>                    <br>
                                <button  class="btn btn-primary" name="submitBtn" id="submitBtn" >提交更改</button>
                            </form>

                        </div>

                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>
            <div class="panel panel-default">
                <div class="panel-heading text-center">
                    <h2>申报书列表</h2>
                </div>
                <div class="panel-body" >
                    <form class="navbar-form navbar-left" role="search">
                        <div>
                            <div class="form-group">
                                <label class="col-5">按博物馆名称进行搜索</label>
                                <input type="text" class="form-control col-6 col-lg-offset-0" placeholder="输入" id="searchName" name="searchName">
                            </div>
                            <button type="button" class="btn btn-default" onclick="searchByName()">搜索</button>
                        </div>
                        <div>
                            <div class="form-group">
                                <label class="col-5">请选择年份</label>
                                <select id="searchYear" name="searchYear">
                                    <option value="2018">2018</option>
                                    <option value="2017">2017</option>
                                    <option value="2016">2016</option>
                                    <option value="2015">2015</option>
                                    <option value="2014">2014</option>
                                    <option value="2013">2013</option>
                                    <option value="2012">2012</option>
                                    <option value="2011">2011</option>
                                    <option value="2010">2010</option>
                                </select>
                            </div>
                            <button type="button" class="btn btn-default" onclick="search()">搜索</button>
                        </div>
                    </form>
                    <a class = "btn btn-primary btn-default pull-right"  data-toggle="modal" data-target="#myModal"><li class="fa fa-upload"></li>上传申报书</a>
                    <table class="table table-hover" border="1">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>博物馆名称</th>
                            <th>上传时间</th>
                            <th>参评年份</th>
                            <th> 操作 </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="attachment">
                            <tr>
                                <td>${attachment.id}</td>
                                <td>${attachment.name}</td>
                                <td>${attachment.addtime}</td>
                                <td>${attachment.year}</td>
                                <td><a class="btn btn-info" href="/notification/listFile?dir=${attachment.name}_${attachment.year}" target="_blank">下载申报书</a>
                                        <a class="btn btn-info" href="/notification/listFile?dir=${attachment.name}_${attachment.year}&add=1"  target="_blank">文件管理</a>
                                    <a class="btn btn-info" onclick="delAttachment(${attachment.id})" target="_blank">删除申报书</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
                <div class="panel-footer" >
                    <!--分页-->
                    <div class="mailbox-controls">
                        <button class="btn btn-default btn-sm" href="/nofitication/list/1"><i class="glyphicon glyphicon glyphicon-home"></i></button>
                        <button class="btn btn-default btn-sm" onclick="ahead()"><i class="glyphicon glyphicon-chevron-left"></i></button>
                        <button class="btn btn-default btn-sm" onclick="behind()"><i class="glyphicon glyphicon-chevron-right"></i></button>
                        <button class="btn btn-default btn-sm" onclick="refresh()"><i class="glyphicon glyphicon-refresh"></i></button>
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
</body>
</html>