layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['router', "util", 'layer', 'upload', 'table'], function () {
    var router = layui.router, util = layui.util, layer = layui.layer,
        upload = layui.upload, table = layui.table;


    var urls = {
        queryPageData: "/bpm/deployment/queryPageData",
        toDesign: "/bpm/deployment/toDesign",
        upload: "/bpm/deployment/upload",
        deleteData: "/bpm/deployment/deleteData"
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
                },
                {
                    title: '操作',
                    templet: function () {
                        var html = "<a class='layui-btn layui-btn-danger layui-btn-xs' lay-event='del'>删除</a>";
                        return html;
                    }
                }
            ]]
        });
    }

    table.on('tool(dataList)', function (obj) {
        var layEvent = obj.event, data = obj.data;
        if (layEvent == 'del') {
            layer.confirm("确定删除吗",{async:true}, function (index) {
                layer.close(index);
                router.post({
                    url: urls.deleteData,
                    data: {deploymentId: data.id},
                    success: function () {
                        tableIns.reload();
                    }
                });
            });
        }
    });

    upload.render({
        elem: '#btnUpload',
        url: urls.upload,
        accept: "file",
        done: function (res) {
            tableIns.reload();
        }
    });

    $("#btnDesign").on("click", function () {
        var index = layer.open({
                type: 2,
                maxmin: true,
                anim: 1,
                title: '流程设计',
                content: '/modler/index.html#/processes'
            }
        );
        layer.full(index);
    });
});