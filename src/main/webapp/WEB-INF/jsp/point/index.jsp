<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>生成分数和排名</title>
    <%@include file="../common/head.jsp" %>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://code.jquery.com/jquery-2.0.0.min.js"></script>
    <script type="text/javascript">

        function count(url) {
            if (confirm("确定要生成该年的数据吗")) {
                $.ajax({
                    type: 'POST',
                    url: url,
                    dataType: 'json',
                    contentType: 'application/json;charset=UTF-8',
                    data: JSON.stringify({
                        id: id,
                        page: page
                    }),  //提交json字符串数组
                    success: function () {
                        window.location.reload();
                    },
                    error: function () {
                        alert("生成失败");
                    }
                });
            }
        }
    </script>

</head>
<body>

    <h2>生成分数和排名</h2>

<div class="container">
    <div class="panel panel-default">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">定量数据计算</h3>
        </div>
        <div class="panel-body">
            <label>按照年份生成：</label>
            <select id="QuantitativeYear" name="QuantitativeYear">
                <option value="2017">2017</option>
                <option value="2016">2016</option>
                <option value="2015">2015</option>
                <option value="2014">2014</option>
                <option value="2013">2013</option>
                <option value="2012">2012</option>
                <option value="2011">2011</option>
                <option value="2010">2010</option>
            </select>
            <button class="btn btn-primary" name="QuantitativeButton" id="QuantitativeButton" onclick="window.location.href(www.baidu.com)">生成</button>
        </div>
    </div>

    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">定量排名计算</h3>
        </div>
        <div class="panel-body">
            <label>按照年份生成：</label>
            <select id="QuantitativeListYear" name="QuantitativeYear">
                <option value="2017">2017</option>
                <option value="2016">2016</option>
                <option value="2015">2015</option>
                <option value="2014">2014</option>
                <option value="2013">2013</option>
                <option value="2012">2012</option>
                <option value="2011">2011</option>
                <option value="2010">2010</option>
            </select>
            <button class="btn btn-primary" name="QuantitativeButton" id="QuantitativeListButton" onclick="window.location.href(www.baidu.com)">生成</button>
        </div>
    </div>

    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">定性得分计算</h3>
        </div>
        <div class="panel-body">
            <label>按照年份生成：</label>
            <select id="qualitativeYear" name="QuantitativeYear">
                <option value="2017">2017</option>
                <option value="2016">2016</option>
                <option value="2015">2015</option>
                <option value="2014">2014</option>
                <option value="2013">2013</option>
                <option value="2012">2012</option>
                <option value="2011">2011</option>
                <option value="2010">2010</option>
            </select>
            <button class="btn btn-primary" name="QuantitativeButton" id="qualitativeButton" onclick="window.location.href(www.baidu.com)">计算定性得分</button>
        </div>
    </div>

    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">定性排名计算</h3>
        </div>
        <div class="panel-body">
            <label>按照年份生成：</label>
            <select id="qualitativeListYear" name="QuantitativeYear">
                <option value="2017">2017</option>
                <option value="2016">2016</option>
                <option value="2015">2015</option>
                <option value="2014">2014</option>
                <option value="2013">2013</option>
                <option value="2012">2012</option>
                <option value="2011">2011</option>
                <option value="2010">2010</option>
            </select>
            <button class="btn btn-primary" name="QuantitativeButton" id="qualitativeListButton" onclick="window.location.href(www.baidu.com)">计算定性排名</button>
        </div>
    </div>

    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">总分得分计算</h3>
        </div>
        <div class="panel-body">
            <label>按照年份生成：</label>
            <select id="pointYear" name="QuantitativeYear">
                <option value="2017">2017</option>
                <option value="2016">2016</option>
                <option value="2015">2015</option>
                <option value="2014">2014</option>
                <option value="2013">2013</option>
                <option value="2012">2012</option>
                <option value="2011">2011</option>
                <option value="2010">2010</option>
            </select>
            <button class="btn btn-primary" name="QuantitativeButton" id="PointButton" onclick="window.location.href(www.baidu.com)">计算总分得分</button>
        </div>
    </div>

    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">总分排名计算</h3>
        </div>
        <div class="panel-body">
            <label>按照年份生成：</label>
            <select id="pointListYear" name="QuantitativeYear">
                <option value="2017">2017</option>
                <option value="2016">2016</option>
                <option value="2015">2015</option>
                <option value="2014">2014</option>
                <option value="2013">2013</option>
                <option value="2012">2012</option>
                <option value="2011">2011</option>
                <option value="2010">2010</option>
            </select>
            <button class="btn btn-primary" name="QuantitativeButton" id="PointListButton" onclick="window.location.href(www.baidu.com)">计算总分排名</button>
        </div>
    </div>

    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">全部计算</h3>
        </div>
        <div class="panel-body">
            <label>按照年份生成：</label>
            <select id="allYear" name="QuantitativeYear">
                <option value="2017">2017</option>
                <option value="2016">2016</option>
                <option value="2015">2015</option>
                <option value="2014">2014</option>
                <option value="2013">2013</option>
                <option value="2012">2012</option>
                <option value="2011">2011</option>
                <option value="2010">2010</option>
            </select>
            <button class="btn btn-primary" name="QuantitativeButton" id="allButton" onclick="window.location.href(www.baidu.com)">生成</button>
        </div>
    </div>
    </div>
</div>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>