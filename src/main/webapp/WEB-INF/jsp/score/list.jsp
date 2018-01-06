<%@page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>专家打分表管理</title>
    <%@include file="../common/head.jsp" %>
    <script type="text/javascript">

        $(document).ready(function(){
            $("#submitBtn").on("click", function(){
                $("#addForm").ajaxSubmit({
                    success : function(data){
                        alert(data);
                    },
                    error : function(){
                        alert("请求错误");
                    }
                });
            });

        });
        function  addFile() {
            var input = '<input type=\"file\">';
            document.getElementById("addDiv").append(input);
        }
        function ahead(){
            name = request('name');
            year = request("year");
            if(${pages} == 1 ) {
                alert("当前已是第一页");
            } else{
                window.location.href="/notification/list/${page-1}?name="+name+"&year="+year;
            }
        }
        function behind(){
            name = request('name');
            year = request("year");
            if(${pages} == ${page}){
                alert("当前已是最后一页");
            } else{
                window.location.href="/notification/list/${page-1}?name="+name+"&year="+year;
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
<body>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">上传专家定性评估意见书</h4>
            </div>
            <div class="modal-footer">
                <form id="insertForm" action="/notification/insert" align="left" enctype="multipart/form-data">
                    <label>博物馆名称：</label>
                    <input type="text" name="uploadName" placeholder="专家名字">
                    <div align="left">
                        <label>请选择年份：</label>
                        <select id="uploadYear" name="uploadYear" form="insertForm">
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
                        <input type="file" name="file1"/>
                    </div>                   <br>
                    <button type="button" class="btn btn-primary" name="submitBtn">提交</button>
                </form>

            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>附件列表</h2>
        </div>

        <form class="navbar-form navbar-left" role="search">
            <div>
                <div class="form-group">
                    <label class="col-5">按专家名称进行搜索</label>
                    <input type="text" class="form-control col-6 col-lg-offset-0" placeholder="输入" id="searchName" name="searchName">
                </div>
                <button type="button" class="btn btn-default" onclick="searchByName()">搜索</button>
            </div>
            <div>
                <div class="form-group">
                    <label class="col-5">请选择年份</label>
                    <select id="searchYear" name="searchYear">
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

        <div class="panel-body" >
            <div class="panel-body" align="left">
                <td><a class = "btn btn-primary btn-lg"  data-toggle="modal" data-target="#myModal">上传专家定性评估意见书</a></td>
                </tr>
            </div>
            <table class="table table-hover">
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
                        <td><a class="btn btn-info" href="" target="_blank">下载打分表</a>
                                <a class="btn btn-info" href="/score/detail"  target="_blank">详细信息</a>
                            <a class="btn btn-info" onclick="delAttachment(${attachment.id})" target="_blank">删除申报书</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div align="right">
                <td><a class="btn btn-info" href="/notification/list/1" target="_self">第一页</a></td>
                <td><a class="btn btn-info" href="#" onclick="ahead()" target="_self">前一页</a></td>
                <td><a class="btn btn-info" href="#" onclick="behind()" target="_self">下一页</a></td>
                </tr>
            </div>
        </div>
    </div>
</div>


</body>
</html>