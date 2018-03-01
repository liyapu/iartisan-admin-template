layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['jquery', 'router', 'table', 'layer'], function () {
    var router = layui.router, $ = layui.jquery, table = layui.table,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    var urls = {
        queryPageData: "/roleSupport/queryPageData",
        addDataPage: "/roleSupport/addDataPage",
    };
    queryPageData();

    var tableIns;

    function queryPageData() {
        tableIns = router.table({
            elem: "#dataList",
            url: urls.queryPageData,
            type: 'post',
            where: {"roleName": $("#roleName").val()},
            cols: [[
                {
                    title: '角色ID',
                    templet: function (d) {
                        return "<a class='layui-btn layui-btn-xs layui-btn-primary' lay-event=\"detail\">" + d.roleId + "</a>";
                    }
                },
                {
                    field: 'roleName',
                    title: '角色名称',
                },
                {
                    field: 'roleName',
                    title: '操作',
                    templet: function () {
                        return "<a class=\"layui-btn layui-btn-xs layui-btn-danger\" lay-event=\"del\">删除</a>";
                    }
                }
            ]]
        });
    }

    $("#btnQuery").on("click", function () {
        queryPageData();
    });
    //列表操作
    table.on('tool(dataList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;
        if (layEvent == 'del') {
            layer.confirm('确定删除该角色吗？', {icon: 3, title: '提示信息'}, function (index) {
                layer.close(index)
                tableIns.reload();
            });
        } else if (layEvent == 'detail') {
            layer.msg("想看详情不容易");
        }
    });

    $("#btnAddPage").click(function () {
        var index = layui.layer.open({
            title: "添加角色",
            type: 2,
            content: urls.addDataPage,
            success: function () {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回角色管理', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        });
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(index);
        });
    });
});