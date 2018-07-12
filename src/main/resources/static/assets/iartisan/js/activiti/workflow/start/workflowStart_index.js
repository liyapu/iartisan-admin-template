layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['jquery', 'layer','router','util'], function () {
    var $ = layui.jquery, layer = layui.layer,router=layui.router,util=layui.util;
    var urls = {
        addDataPage: "/activiti/workflow/start/addDataPage",
        queryPageData: "/activiti/workflow/start/owner/queryPageData",
    };

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
                layero.find('iframe').contents().find("#formAdd").find("#btnAdd").click();
            },
            btn2: function (index) {
                layer.close(index);
            }
        });
    });
    //加载自己的流程列表
    queryPageData();

    var tableIns;

    function queryPageData() {
        tableIns = router.table({
            elem: "#dataList",
            url: urls.queryPageData,
            type: 'post',
            cols: [[
                {
                    title: '任务名称',
                    field: 'taskNodeName',
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
});