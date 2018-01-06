<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>下载文件显示页面</title>
    <%@include file="../common/head.jsp" %>
    <script type="text/javascript">

        function  addFile() {
            var input = '<input type=\"file\" name="file">';
            $("#addDiv").append(input);
        }

        function deleteAttachment(name) {
            var dir = request('dir');
            var url = "/notification/deleteFile";
            if(confirm("确定要删除该附件吗")){
                $.ajax({
                    type: 'POST',
                    url: url,
                    dataType: 'json',
                    contentType:'application/json;charset=UTF-8',
                    data:JSON.stringify({
                        name:name,
                        id:dir
                    }),  //提交json字符串数组
                    success:function(data){
                        if (data.page===1){
                            window.location.reload();
                        }else{
                            alert("删除失败");
                        }
                    },
                    error:function(){
                    }
                });
            }
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

<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>下载列表</h2>
        </div>
        <div class="panel-body" >
            <c:if test="${add==1}">
                <form id="insertForm" action="/notification/upload" method="POST" enctype="multipart/form-data" align="left">
                    <label>博物馆名称：</label>
                    <input type="hidden" name="uploadName" id="uploadName" value="${name}">
                    <input type="hidden" name="uploadYear" id="uploadYear" value="${year}">
                    <input type="hidden" name="add" id="add" value="1">
                    <div align="left">
                        <button name="addUpload" type="button" onclick="addFile()">添加文件</button><br>
                    </div>
                    <div id="addDiv" >
                        <input type="file" name="file"/>
                    </div>
                    <br>
                    <div align="left">
                        <button  class="btn btn-primary" name="submitBtn" id="submitBtn" >上传</button>
                        <a class="btn btn-primary" href="/notification/list/1">返回</a>
                    </div>
                </form>
                <label>该博物馆的附件：</label><br>
            </c:if>
            <table class="table table-hover">
                <thead>
                <th>文件名  </th>
                <th>操作</th>
                </thead>
                <tbody>
                <c:forEach var="me" items="${fileNameMap}">

                    <tr>
                    <c:url value="/notification/downFile" var="downurl">
                        <c:param name="filename" value="${me.key}"></c:param>
                        <c:param name="dir" value="${dir}"></c:param>
                    </c:url>
                        <td><label>${me.value}</label></td>
                        <td>
                            <a class="btn btn-info" href="${downurl}">下载</a>
                            <c:if test="${add==1}">
                                <a class="btn btn-info" onclick="deleteAttachment('${me.key}')">删除</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
