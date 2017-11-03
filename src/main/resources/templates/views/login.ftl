<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登录--layui后台管理模板</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="/assets/iartisan/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/assets/iartisan/css/login.css" media="all"/>
</head>
<body>
<div class="login">
    <h1>layuiCMS-管理登录</h1>
    <form class="layui-form" action="/authenticate" id="formLogin" autocomplete="off" method="post">
        <div class="layui-form-item">
            <input class="layui-input" name="userName" placeholder="用户名" lay-verify="required" type="text"
                   autocomplete="off">
        </div>
        <div class="layui-form-item">
            <input class="layui-input" name="userPwd" placeholder="密码" lay-verify="required" type="password"
                   autocomplete="off">
        </div>
	<#-- <div class="layui-form-item form_code">
         <input class="layui-input" name="code" placeholder="验证码" lay-verify="required" type="text"
                autocomplete="off">
         &lt;#&ndash;<div class="code"><img src="../../images/code.jpg" width="116" height="36"></div>&ndash;&gt;
     </div>-->
        <button class="layui-btn login_btn" lay-submit="" lay-filter="login">登录</button>
    </form>
</div>
<script type="text/javascript" src="/assets/iartisan/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/assets/iartisan/js/login.js"></script>
</body>
</html>