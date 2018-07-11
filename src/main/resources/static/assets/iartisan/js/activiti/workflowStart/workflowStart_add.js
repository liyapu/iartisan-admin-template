layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['jquery', 'laydate','router'], function () {
    var laydate = layui.laydate,router=layui.router;

    var urls = {addData: "/activiti/workflowStart/addData"};

    laydate.render({elem: '#beginTime'});
    laydate.render({elem: '#endTime'});

    form.on("submit(addData)", function (data) {
        router.post({
            url: urls.addData,
            data: data.field,
            success: function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            }
        });
        return false;
    });

});