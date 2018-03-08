layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['jquery', 'router', 'table', 'layer', 'util'], function () {
    var $ = layui.jquery, router = layui.router, layer = layui.layer, form = layui.form;

    var urls = {addData: "/userSupport/addData"};

    form.on('submit(btnAdd)', function (data) {
        router.post({
            url: urls.addData,
            data: data.field,
            success: function () {
                layer.closeAll("iframe");
                parent.location.reload();
            }
        });
        return false;
    });
    $("#btnClose").click(function () {
        layer.closeAll("iframe");
        parent.location.reload();
    })

});