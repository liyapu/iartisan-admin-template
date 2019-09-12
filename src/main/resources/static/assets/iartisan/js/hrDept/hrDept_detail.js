layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['jquery', 'router', 'layer'], function () {
    let router = layui.router,
        urls = {
            deleteData: "/hrDept/deleteData"
        },
        layer = layui.layer;
    layer.config({
        skin: 'layui-layer-molv'
    });
    //删除
    $("#btnDel").on('click', function () {
        let deptId = $(this).attr("attr-data");
        top.layer.confirm('确认删除?', {icon: 3, title: '提示'}, function (index) {
            let load = layer.load(1);
            router.ajaxGet(urls.deleteData, {deptId: deptId}, {}, function () {
                layer.close(load);
                location.reload();
            })
        });
    })
});