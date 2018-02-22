layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['form', 'layer', 'jquery', 'router', 'md5'], function () {
    var form = layui.form,
        router = layui.router,
        md = layui.md5,
        $ = layui.jquery;
    //登录按钮
    form.on("submit(login)", function (data) {
        var _that = $(this);
        _that.text("登录中...").attr("disabled", "disabled").addClass("layui-disabled");
        //密码md5
        var pwd = $.md5("00");
        router.post({
                url: "/authenticate",
                data: {userName: data.field["userName"], userPwd: pwd},
                success: function (res) {
                    location.href = res.message;
                },
                error: function () {
                    _that.text("登录").removeAttr("disabled").removeClass("layui-disabled");
                }
            }
        );
        return false;
    });

    //表单输入效果
    $(".loginBody .input-item").click(function (e) {
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function () {
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function () {
        $(this).parent().removeClass("layui-input-focus");
        if ($(this).val() != '') {
            $(this).parent().addClass("layui-input-active");
        } else {
            $(this).parent().removeClass("layui-input-active");
        }
    })
})
