layui.use(['form', 'layer'], function () {
    var form = layui.form,
        //layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.$;

    //登录按钮事件
    form.on("submit(login)", function (data) {
        var userName = $("#userName").val();
        //var userPwd = $.md5($("#userPwd").val());
        $("#formLogin").submit();
    })
})
