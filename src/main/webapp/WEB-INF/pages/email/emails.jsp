<%--
  Created by IntelliJ IDEA.
  User: 智康
  Date: 2015/6/23 0023
  Time: 12:07
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
    <title>邮件管理页面</title>

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
    <div style="margin-top: 30px"></div>
    <ul class="nav nav-pills">
        <li role="presentation"><a href="/">首页</a></li>
        <li role="presentation"><a href="/users">用户管理</a></li>
        <li role="presentation" class="active"><a href="/emails">邮件管理</a></li>
    </ul>

    <hr style="border-color: cadetblue"/>

    <div class="row">
        <div class="col-md-12">

            <h1>邮件管理
                <a href="/emails/add" type="button" class="btn btn-success btn-sm">添加邮件</a>
            </h1>
            <hr style="border-color: cadetblue"/>

            <c:if test="${empty emailList}">
                <div class="alert alert-danger" role="alert">数据库中暂无邮件，请添加。</div>
            </c:if>

            <c:if test="${!empty emailList}">
                <table class="table table-bordered table-striped">
                    <tr>
                        <th>发布日期</th>
                        <th>标题</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${emailList}" var="email">
                        <tr>
                            <td>${email.pubDate}</td>
                            <td>${email.subject}</td>
                            <td>
                                <a href="/emails/detail/${email.eid}" type="button"
                                   class="btn btn-success btn-sm">详情</a>
                                <a href="/emails/update/${email.eid}" type="button"
                                   class="btn btn-warning btn-sm">编辑</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>

        </div>
    </div>

</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</body>
</html>

