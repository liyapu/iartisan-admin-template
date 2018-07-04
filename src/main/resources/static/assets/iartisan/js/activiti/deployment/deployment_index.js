layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['router', "util", 'layer'], function () {
    var router = layui.router, util = layui.util, layer = layui.layer;

    var urls = {
        queryPageData: "/activiti/deployment/queryPageData",
        toDesign: "/activiti/deployment/toDesign"
    };
    queryPageData();

    var tableIns;

    function queryPageData() {
        tableIns = router.table({
            elem: "#dataList",
            url: urls.queryPageData,
            type: 'post',
            cols: [[
                {
                    title: 'id',
                    field: 'id',
                    align: 'center'
                },
                {
                    title: '流程名称',
                    field: 'name',
                    align: 'center'
                },
                {
                    title: 'category',
                    field: 'category',
                    align: 'center'
                },
                {
                    title: 'tenantId',
                    field: 'tenantId',
                    align: 'center'
                },
                {
                    title: '部署时间',
                    templet: function (d) {
                        return util.toDateString(d.startTime);
                    },
                    align: 'center'
                }
            ]]
        });
    }

    $("#btdDesign").on("click", function () {
        var index = layer.open({
                type: 2,
                maxmin: true,
                anim: 1,
                title: '流程设计',
                content: urls.toDesign
            }
        );
       layer.full(index);
    });
});