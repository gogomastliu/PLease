<%--
  Created by Pandora Company Limited.
     User: Liubin
     Date: 2017/10/27 10:37
  Comment: 注册用户
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册用户</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <link href="${pageContext.request.contextPath}/beforelib/bs3/css/bootstrap.css" rel="stylesheet" type="text/css">
    <script type='text/javascript' src="${pageContext.request.contextPath}/beforelib/jquery/jquery-1.12.3.js"></script>
    <script type='text/javascript' src="${pageContext.request.contextPath}/beforelib/bs3/js/bootstrap.js"></script>
    <script type='text/javascript' src="${pageContext.request.contextPath}/js/manager/registuser.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-4">账户名：</div>
        <div class="col-lg-8"><input id="txt_loginName" type="text" placeholder="请输入账号名"></div>
    </div>
    <div class="row">
        <div class="col-lg-4">登陆密码：</div>
        <div class="col-lg-8"><input id="txt_passWord" type="password" placeholder="请输入密码"></div>
        密码强弱等级
    </div>
    <div class="row">
        <div class="col-lg-4">确认密码：</div>
        <div class="col-lg-8"><input id="txt_passWord_confirm" type="password" placeholder="请输入确认密码"></div>
    </div>
    <div class="row">
        <div class="col-lg-4">验证码：</div>
        <div class="col-lg-8"><input id="txt_verifyCode" type="password" placeholder="请输入验证码"></div>
    </div>
    <div class="row">
        <div class="col-lg-4"><input id="ckb_isAgree" type="checkbox">同意 潘多拉服务条款</div>
    </div>
    <div class="row">
        <input id="btn_submit" class="btn btn-default" type="button" value="免费注册" onclick="regUser();">
    </div>
</div>
</body>
</html>
