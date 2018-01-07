<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>基本的警告信息实例</title>
    <meta name="description" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/bootstrap.min.css">
    <style type="text/css">
        body {
            padding: 50px;
        }
        .font{
            font-size:25px;
        }
    </style>
</head>
<body>

<div class="container font">
    <div class="row">
        <div class="span">
            <div class="alert">
                <strong>错误!</strong> 出现了一些错误！
                <br>
                <strong style="font-size: 20px">错误信息：</strong>
                <br><br>
                <!--可输出部分错误信息-->
                ${error}
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/statics/libs/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/libs/bootstrap.min.js"></script>
</body>
</html>