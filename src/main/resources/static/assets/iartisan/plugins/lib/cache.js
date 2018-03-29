var cacheStr = window.sessionStorage.getItem("cache"),
    oneLoginStr = window.sessionStorage.getItem("oneLogin");
layui.use(['form', 'jquery', "layer"], function () {
    var $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    var content = '<div style="padding: 20px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">' +
        'iartisan-admin-template基于spring-boot、layui、layuiCMS等众多优秀开源框架！<br><br>' +
        '<span style="color: #FF5722">重要提醒：作者不懂前端</span><br><br>' +
        '专注于后台管理系统的实现，提供基础的权限模块，并不实现具体业务代码！<br><br>' +
        '您有任何问题或者建议，都可以和作者联系' +
        '</div>';

    //公告层
    function showNotice() {
        layer.open({
            type: 1,
            title: "系统公告",
            area: '300px',
            shade: 0.8,
            id: 'LAY_layuipro',
            btn: ['关闭'],
            moveType: 1,
            content: content,
            success: function (layero) {
                var btn = layero.find('.layui-layer-btn');
                btn.css('text-align', 'center');
                btn.on("click", function () {
                    tipsShow();
                });
            },
            cancel: function (index, layero) {
                tipsShow();
            }
        });
    }

    function tipsShow() {
        window.sessionStorage.setItem("showNotice", "true");
        if ($(window).width() > 432) {  //如果页面宽度不足以显示顶部“系统公告”按钮，则不提示
            layer.tips('系统公告躲在了这里', '#userInfo', {
                tips: 3,
                time: 1000
            });
        }
    }

    $(".showNotice").on("click", function () {
        showNotice();
    });
    if (window.sessionStorage.getItem("showNotice") != "true") {
        showNotice();
    }

});