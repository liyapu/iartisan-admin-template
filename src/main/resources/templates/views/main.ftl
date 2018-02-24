<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>首页--layui后台管理模板 2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="/assets/iartisan/css/font/font.css" media="all"/>
    <link rel="stylesheet" href="/assets/iartisan/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/assets/iartisan/css/public.css" media="all"/>
</head>
<body class="childrenBody" style="background-color: #f2f2f2">
<blockquote class="layui-elem-quote layui-bg-green" style="border-radius: 6px;">
    <div id="nowTime">亲爱的${_user.userName!''}， 欢迎使用：${_title!''} </div>
</blockquote>

<div class="layui-row layui-col-space10">
    <div class="layui-col-lg6 layui-col-md12">
        <div class="layui-card">
            <div class="layui-card-header">版本信息</div>
            <div class="layui-card-body layui-text">
                <table class="layui-table">
                    <colgroup>
                        <col width="100">
                        <col>
                    </colgroup>
                    <tbody>
                    <tr>
                        <td>当前版本</td>
                        <td>
                            <script type="text/html" template="">
                                v{{ layui.admin.v }}
                                <a href="http://fly.layui.com/docs/3/" target="_blank"
                                   style="padding-left: 15px;">更新日志</a>
                            </script>
                            v1.0.0-beta6 <a href="http://fly.layui.com/docs/3/" target="_blank"
                                            style="padding-left: 15px;">更新日志</a>
                        </td>
                    </tr>
                    <tr>
                        <td>基于框架</td>
                        <td>
                            <script type="text/html" template="">
                                layui-v{{ layui.v }}
                            </script>
                            layui-v2.2.6-rc2
                        </td>
                    </tr>
                    <tr>
                        <td>主要特色</td>
                        <td>响应式 / 清爽 / 极简</td>
                    </tr>
                    <tr>
                        <td>获取渠道</td>
                        <td style="padding-bottom: 0;">
                            <div class="layui-btn-container">
                                <a href="http://www.layui.com/admin/" target="_blank"
                                   class="layui-btn layui-btn-danger">获取授权</a>
                                <a href="http://fly.layui.com/download/layuiAdmin/" target="_blank" class="layui-btn">立即下载</a>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="/assets/iartisan/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/assets/iartisan/js/main.js"></script>
</body>
</html>