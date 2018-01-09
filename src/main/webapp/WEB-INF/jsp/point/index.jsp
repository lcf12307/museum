<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>生成分数和排名</title>
    <%@include file="../common/head.jsp" %>
    <script type="text/javascript">
        function count111() {
            var data = 'year=' + $('#QuantitativeYear').val();
            var url = '/point/quantitativeinit';
            if (confirm("确定要生成该年的数据吗")) {
                $.ajax({
                    type: 'GET',
                    url: url,
                    dataType: 'json',
                    data:data,
                    success: function (result) {
                        alert("生成成功");
                    },
                    error: function () {
                        alert("生成失败");
                    }
                });
            }
        }
        function count133() {
            var data = 'year=' + $('#pointYear').val();
            var url = '/point/totalinit';
            if (confirm("确定要生成该年的数据吗")) {
                $.ajax({
                    type: 'GET',
                    url: url,
                    dataType: 'json',
                    data:data,
                    success: function (result) {
                        if (result.code == 0) {
                            alert(result.msg);
                        } else {
                            alert(result.msg);
                        }

                    },
                    error: function () {
                        alert("生成失败");
                    }
                });
            }
        }
        function count144() {
            var data = 'year=' + $('#pointListYear').val();
            var url = '/point/totallistinit';
            if (confirm("确定要生成该年的数据吗")) {
                $.ajax({
                    type: 'GET',
                    url: url,
                    dataType: 'json',
                    data:data,
                    success: function (result) {
                        if (result.code == 0) {
                            alert(result.msg);
                        } else {
                            alert(result.msg);
                        }

                    },
                    error: function () {
                        alert("生成失败");
                    }
                });
            }
        }
        function count122() {
            var data = 'year=' + $('#QuantitativeListYear').val();
            var url = '/point/quantitativelistinit';
            if (confirm("确定要生成该年的数据吗")) {
                $.ajax({
                    type: 'GET',
                    url: url,
                    dataType: 'json',
                    data:data,
                    success: function (result) {
                        if (result.code == 0) {
                            alert(result.msg);
                        } else {
                            alert(result.msg);
                        }

                    },
                    error: function () {
                        alert("生成失败");
                    }
                });
            }
        }
        function quantativeinitlist() {
            alert("生成成功")
        }
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
        function count2(url) {
            if (confirm("确定要生成该年的数据吗")) {
                var data = 'year='+$('#qualitativeYear').val();
                $.ajax({
                    type: "POST",
                    url: url,
                    dataType: "json",
                    data:data,
                    success: function (result) {
                        if (result.code == 0) {
                            alert(result.msg);
                        } else {
                            alert(result.msg);
                        }
                    },
                    error: function () {
                        alert('连接失败，请稍后再试！');
                    }
                });
            }
        }
        function count3(url) {
            //dingxingRank
            if (confirm("确定要生成该年的定性排名吗")) {
                var data = 'year='+$('#qualitativeListYear').val();
                $.ajax({
                    type: "POST",
                    url: url,
                    dataType: "json",
                    data:data,
                    success: function (result) {
                        if (result.code == 0) {
                            alert(result.msg);
                        } else {
                            alert(result.msg);
                        }
                    },
                    error: function () {
                        alert('连接失败，请稍后再试！');
                    }
                });
            }
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
    <h2>生成分数和排名</h2>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#liang" data-toggle="tab">
                计算定量得分排名
            </a>
        </li>
        <li><a href="#xing" data-toggle="tab">计算定性得分排名</a></li>
        <li><a href="#total" data-toggle="tab">计算总分得分排名</a></li>
        <li><a href="#cal" data-toggle="tab">计算全部</a></li>

    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="liang">

            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">定量数据计算</h3>
                </div>
                <div class="panel-body">
                    <label>按照年份生成：</label>
                    <select id="QuantitativeYear" name="QuantitativeYear">
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
                    <button class="btn btn-primary" name="QuantitativeButton" id="QuantitativeButton" onclick="count111()">生成</button>
                </div>
            </div>

            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">定量排名计算</h3>
                </div>
                <div class="panel-body">
                    <label>按照年份生成：</label>
                    <select id="QuantitativeListYear" name="QuantitativeYear">
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
                    <button class="btn btn-primary" name="QuantitativeButton" id="QuantitativeListButton" onclick="count122()">生成</button>
                </div>
            </div>
        </div>
        <div class="tab-pane fade" id="xing">

            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">定性得分计算</h3>
                </div>
                <div class="panel-body">
                    <label>按照年份生成：</label>
                    <select id="qualitativeYear" name="QuantitativeYear">
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
                    <button class="btn btn-primary" name="QuantitativeButton" id="qualitativeButton" onclick="count2('/score/dingxing')">计算定性得分</button>
                </div>
            </div>

            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">定性排名计算</h3>
                </div>
                <div class="panel-body">
                    <label>按照年份生成：</label>
                    <select id="qualitativeListYear" name="QuantitativeYear">
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
                    <button class="btn btn-primary" name="QuantitativeButton" id="qualitativeListButton" onclick="count3('/score/dingxingRank')">计算定性排名</button>
                </div>
            </div>
        </div>
        <div class="tab-pane fade" id="total">

            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">总分得分计算</h3>
                </div>
                <div class="panel-body">
                    <label>按照年份生成：</label>
                    <select id="pointYear" name="QuantitativeYear">
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
                    <button class="btn btn-primary" name="QuantitativeButton" id="PointButton" onclick="count133()">计算总分得分</button>
                </div>
            </div>

            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">总分排名计算</h3>
                </div>
                <div class="panel-body">
                    <label>按照年份生成：</label>
                    <select id="pointListYear" name="QuantitativeYear">
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
                    <button class="btn btn-primary" name="QuantitativeButton" id="PointListButton" onclick="count144()">计算总分排名</button>
                </div>
            </div>
        </div>
        <div class="tab-pane fade" id="cal">

            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">全部计算</h3>
                </div>
                <div class="panel-body">
                    <label>按照年份生成：</label>
                    <select id="allYear" name="QuantitativeYear">
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
                    <button class="btn btn-primary" name="QuantitativeButton" id="allButton" onclick="window.location.href(www.baidu.com)">生成</button>
                </div>
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
</div>
</body>
</html>