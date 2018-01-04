<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>下载文件显示页面</title>
    <%@include file="../common/head.jsp" %>
    <script src="http://code.jquery.com/jquery-2.0.0.min.js"></script>
    <script src="http://lib.sinaapp.com/js/jquery/1.9.1/jquery-1.9.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/jquery.form/4.2.1/jquery.form.min.js"
            integrity="sha384-tIwI8+qJdZBtYYCKwRkjxBGQVZS3gGozr3CtI+5JF/oL1JmPEHzCEnIKbDbLTCer"
            crossorigin="anonymous"></script>
    <script type="text/javascript">

        function  addFile() {
            var input = '<input type=\"file\" name="file">';
            $("#addDiv").append(input);
        }

        $(document).ready(function () {
            if ( "success" !== ${message}){
                alert(${message});
            }
        })

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
                <th>
                    <tr>文件名</tr>
                    <tr>操作</tr>
                </th>
                </thead>
                <tbody>
                <c:forEach var="me" items="${fileNameMap}">

                    <tr>
                    <c:url value="/notification/downFile" var="downurl">
                        <c:param name="filename" value="${me.key}"></c:param>
                        <c:param name="dir" value="${dir}"></c:param>
                    </c:url>
                        <td><label>${me.value}</label></td>
                        <td><a class="btn btn-info" href="${downurl}">下载</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>
