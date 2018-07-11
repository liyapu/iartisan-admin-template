layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['jquery', 'layer'], function () {
    var $ = layui.jquery, layer = layui.layer;
    var urls = {addDataPage: "/activiti/workflowStart/addDataPage"};

    $("#btnStart").on('click', function () {
        layui.layer.open({
            type: 2,
            title: '发起请假',
            skin: 'layui-layer-molv',
            area: ['650px', '650px'],
            content: urls.addDataPage,
            btn: ['修改', '关闭'],
            btnAlign: 'c',
            yes: function (index, layero) {
                layero.find('iframe').contents().find("#formModify").find("#btnModify").click();
            },
            btn2: function (index) {
                layer.close(index);
            }
        });
    });
});