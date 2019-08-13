<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>${_title!''}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="favicon.ico">
    <link rel="stylesheet" href="/assets/iartisan/css/font/font.css" media="all"/>
    <link rel="stylesheet" href="/assets/iartisan/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/assets/iartisan/css/index.css" media="all"/>
    <link rel="stylesheet" href="/assets/iartisan/css/layui-theme-custom.css" media="all"/>
</head>
<body class="layui-layout-body layui-layout-admin">
<!-- 头部 -->
<div class="layui-header">
    <div class="layui-logo"><img src="/assets/iartisan/images/face.jpg"/>${_title!''}</div>
    <ul class="layui-nav layui-layout-left">
        <li class="layui-nav-item">
            <a href="javascript:;" id="switchNav"><i class="layui-icon">&#xe668;</i></a>
        </li>
    <#-- <li class="layui-nav-item"><a href="">最新活动</a></li>-->
    </ul>
    <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item" pc>
            <a href="javascript:;" lay-tips="点击清除缓存" id="clearCache"><i class="layui-icon">&#xe65f;</i></a>
        </li>
        <li class="layui-nav-item">
            <a href="javascript:void(0);" id="btnFull">
                <i class="fa layui-icon layui-icon-screen-full"></i>
            </a>
        </li>
        <li class="layui-nav-item" id="userInfo">
            <a href="javascript:;"><img src="/assets/iartisan/images/face.jpg" class="layui-nav-img userAvatar"
                                        width="35" height="35"><cite
                    class="adminName">${_user.userName!''}</cite></a>
            <dl class="layui-nav-child">
                <dd><a href="javascript:;" id="ziliao" attr-data="${_user.userId!''}"><i class="seraph icon-ziliao" data-icon="icon-ziliao"></i><cite>个人资料</cite></a>
                </dd>
                <dd><a href="javascript:;" data-url="/userSupport/modifyPwdPage"><i class="seraph icon-xiugai"
                                                                                  data-icon="icon-xiugai"></i><cite>修改密码</cite></a>
                </dd>
                <dd><a href="javascript:;" class="showNotice"><i
                        class="layui-icon">&#xe645;</i><cite>系统公告</cite><#--<span class="layui-badge-dot"></span>-->
                </a></dd>
                <dd><a href="javascript:;" class="changeTheme"><i
                        class="layui-icon">&#xe66a;</i><cite>切换主题</cite>
                </a></dd>
                <dd pc><a href="javascript:;" class="functionSetting"><i class="layui-icon">&#xe620;</i><cite>功能设定</cite><span class="layui-badge-dot"></span></a></dd>
                <dd><a href="/logout" class="signOut"><i class="seraph icon-tuichu"></i><cite>退出</cite></a></dd>
            </dl>
        </li>
    </ul>
</div>
<!-- 侧边栏 -->
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll navBar">
        <ul class="layui-nav layui-nav-tree" id="index-nav" lay-filter="index-nav">
            <li class="layui-nav-item layui-this">
                <a href="javascript:;" data-url="${context.contextPath}/main">
                    <i class="layui-icon" data-icon=""></i>
                    <span>首页</span>
                </a>
            </li>
        </ul>
    </div>
</div>
<!-- 右侧内容 -->
<div class="layui-body">
    <div class="layui-tab mag0" lay-filter="bodyTab" id="top_tabs_box" style="margin: 0px;">
        <ul class="layui-tab-title top_tab" id="top_tabs">
            <li class="layui-this" lay-id=""><i class="layui-icon">&#xe68e;</i> <cite>首页</cite></li>
        </ul>
        <ul class="layui-nav closeBox">
            <li class="layui-nav-item">
                <a href="javascript:;"><i class="layui-icon caozuo">&#xe643;</i>
                    <span style="margin-left: 6px;">页面操作</span></a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" class="refresh refreshThis"><i class="layui-icon">&#x1002;</i>
                        刷新当前</a></dd>
                    <dd><a href="javascript:;" class="closePageOther"><i class="seraph icon-prohibit"></i> 关闭其他</a>
                    </dd>
                    <dd><a href="javascript:;" class="closePageAll"><i class="seraph icon-guanbi"></i> 关闭全部</a></dd>
                </dl>
            </li>
        </ul>
        <div class="layui-tab-content clildFrame">
            <div class="layui-tab-item layui-show">
                <iframe src="${context.contextPath!''}/main"></iframe>
            </div>
        </div>
    </div>
</div>
<div class="layui-footer footer" style="background-color: #f9f9f9;border-top: 1px solid #f9f9f9;">
    <p><span style="color: #bab0a0;">2019 &copy; powerd by <a
            href="https://gitee.com/iartisan/iartisan-admin-template" style="color: #01AAED;" target="_blank">iartisan-admin-template</a></span>
        <a href="javascript:void(0);" class="layui-btn layui-btn-sm  layui-btn-disabled" id="donation">捐赠作者</a>
        <a href="mailto:${_authorEmail!''}" class="layui-btn layui-btn-sm layui-btn-warm">给作者提意见</a>
    </p>
</div>
<!-- 手机屏幕遮罩层 -->
<div class="site-mobile-shade"></div>
<script type="text/javascript" src="/assets/iartisan/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/assets/iartisan/js/index.js?${staticVerison!'1'}"></script>
<script type="text/javascript" src="/assets/iartisan/plugins/lib/cache.js"></script>
</body>

</html>