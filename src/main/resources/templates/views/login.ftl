<!DOCTYPE html>
<html class="loginHtml">
<head>
    <meta charset="utf-8">
    <title>登录--layui后台管理模板 2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="../../favicon.ico">

    <link rel="stylesheet" href="/assets/iartisan/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/assets/iartisan/css/public.css" media="all"/>
</head>
<body class="loginBody">
<form class="layui-form" autocomplete="off">
    <div class="login_face"><img src="/assets/iartisan/images/face.jpg" class="userAvatar" style="margin-top: 15px;">
    </div>
    <div class="layui-form-item input-item">
        <label for="userName">用户名</label>
        <input type="text" placeholder="请输入用户名" id="userName" name="userName" class="layui-input" lay-verify="required"
               autocomplete="off">
    </div>
    <div class="layui-form-item input-item">
        <label for="password">密码</label>
        <input type="password" placeholder="请输入密码" id="userPwd" name="userPwd" class="layui-input" lay-verify="required"
               autocomplete="off">
    </div>
    <div class="layui-form-item input-item" id="imgCode">
        <label for="code">验证码</label>
        <input type="text" placeholder="请输入验证码" id="code" class="layui-input">
        <img src="../../images/code.jpg">
    </div>
    <div class="layui-form-item">
        <button class="layui-btn layui-block" lay-filter="login" lay-submit>登录</button>
    </div>
</form>
<script type="text/javascript" src="/assets/iartisan/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/assets/iartisan/js/login.js"></script>
</body>
</html>