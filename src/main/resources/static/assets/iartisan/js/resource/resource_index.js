layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['jquery', 'router', 'table', 'layer'], function () {
    var router = layui.router, $ = layui.jquery, table = layui.table,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        util = layui.util;

    var urls = {
        queryPageData: "/resourceSupport/queryPageData",
        addDataPage: "/resourceSupport/addDataPage"
    };
    queryPageData();

    var tableIns;

    function queryPageData() {
        tableIns = router.table({
            elem: "#dataList",
            url: urls.queryPageData,
            type: 'post',
            where: {"roleName": $("#roleName").val()},
            height: 250,
            cols: [[
                {
                    title: '角色名称',
                    templet: function (d) {
                        return "<a class='layui-table-link' lay-event=\"detail\" href='javascript:void(0);'>" + d.roleName + "</a>";
                    }
                },
                {
                    title: '创建时间',
                    templet: function (d) {
                        return util.toDateString(d.createTime);
                    }
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
                layer.close(index);
                router.post({
                    url: urls.deleteData, data: {roleId: data.roleId}, success: function () {
                        tableIns.reload();
                    }
                });
            });
        } else if (layEvent == 'detail') {
            var index = layui.layer.open({
                title: '角色详情',
                type: 2,
                content: urls.queryDetailPage + "?roleId=" + data.roleId,
                area: ['500px', '500px'],
                skin: 'layui-layer-molv',
                maxmin: true,
                btn: "关闭",
                btnAlign: 'c',
                close: function (index) {
                    layer.close(index);
                }
            });
            $(window).on("resize", function () {
                layui.layer.restore(index);
            });
        }
    });

    $("#btnAddPage").on("click", function () {
        var value = $(this).val();
        layui.layer.open({
            title: "添加菜单资源权限",
            type: 2,
            content: urls.addDataPage + "?menuId=" + value,
            area: ['400px', '250px'],
            btn: ['添加', '关闭'],
            btnAlign: 'c',
            anim: 1,
            yes: function (index, layero) {
                layero.find('iframe').contents().find("#formAdd").find("#btnAdd").click();
            },
            btn2: function (index) {
                layui.layer.close(index);
            }
        });
    });
});