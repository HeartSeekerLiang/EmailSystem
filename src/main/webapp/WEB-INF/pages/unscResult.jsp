<%--
  Created by IntelliJ IDEA.
  User: 智康
  Date: 2015/6/24 0024
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>退订页面</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>

    <![endif]-->
</head>
<body>
<div class="container">
    <div class="row" style="margin-top: 100px">
        <div class="col-md-4 col-md-offset-4">
            <div class="row">
                <div class="col-md-12">
                    <a href="http://www.grandcloud.cn">
                        <img src="/image/grandcloud.png">
                    </a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-body">
                            <h3>退订结果</h3>
                            <hr style="border-color: #0000cc"/>
                            <c:if test="${result == 'true'}">
                                <h4>退订成功</h4>
                            </c:if>
                            <c:if test="${result == 'false'}">
                                <h4>退订失败</h4>
                            </c:if>
                            <hr style="border-color: #0000cc"/>
                            <button type="button" class="btn btn-default" onclick="closeWin()">关闭</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<script>
    function closeWin() {
        window.opener = null;
        window.open("", "_self");
        window.close();
    }
</script>
</body>
</html>
