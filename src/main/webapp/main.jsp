<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="icon" href="img/favicon.ico">
    <link rel="stylesheet" href="boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="jqgrid/css/jquery-ui.css">
    <link rel="stylesheet" href="jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="boot/js/jquery-2.2.1.min.js"></script>
    <script src="boot/js/bootstrap.min.js"></script>
    <script src="jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="boot/js/ajaxfileupload.js"></script>
    <script src="kindeditor/kindeditor-all-min.js"></script>
    <script src="kindeditor/lang/zh-CN.js"></script>
    <script src="./echarts/echarts.min.js"></script>
    <script src="./echarts/china.js"></script>
    <script>
        $(function () {
            $("#myCarousel").carousel({
                interval: 3000,  //毫秒
            })
        })

    </script>


</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"><strong>持 明 法 州 管 理 系 统</strong></a>
        </div>
        <div class="navbar-right nav navbar-nav navbar-brand">
            欢迎: <span style="color: red">${sessionScope.name}</span> &nbsp;&nbsp;
            <a href="${path}/admin/out">
                <span>退出登陆</span>&nbsp;<span class="glyphicon glyphicon-log-out"></span></a>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <!--左侧手风琴-->
        <div class="col-sm-2">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne">
                                用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse ">
                        <div class="panel-body">
                            <a href="javascript:$('#rrr').load('${path}/map.jsp')">地图</a>
                        </div>
                        <div class="panel-body">
                            <a href="javascript:$('#rrr').load('${path}/line.jsp')">用户注册折线统计图</a>
                        </div>

                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo">
                                上师管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="">上师列表</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree">
                                文章管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="javascript:$('#rrr').load('${path}/article.jsp')">文章列表</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFour">
                                专辑管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="javascript:$('#rrr').load('${path}/ablum.jsp')">专辑列表</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFive">
                                轮播图管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="javascript:$('#rrr').load('${path}/lunbo.jsp')">轮播图列表</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-10" id="rrr">
            <div class="jumbotron" style="padding: 10px;">
                <h3 style="text-align: center">欢迎来到持明法州后台管理系统</h3>
            </div>
            <!--右侧默认轮播图-->
            <div id="myCarousel" class="carousel slide">
                <!-- 轮播（Carousel）指标 -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                </ol>
                <!-- 轮播（Carousel）项目 -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="img/shouye.jpg" style="height: 409px;width: 1366px" alt="Second slide">
                    </div>
                    <div class="item">
                        <img src="img/A2.jpg " style="height: 409px;width: 1366px" alt="First slide">
                    </div>
                </div>
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>
</div>
<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="container" style="text-align: center">
        <span>@百知教育baizhi@zparkhr.com.cn</span>
    </div>
</nav>
</body>
</html>