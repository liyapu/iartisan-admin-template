layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['jquery', 'router', 'table', 'layer', 'util'], function () {
    var router = layui.router, $ = layui.jquery, table = layui.table,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        util = layui.util, form = layui.form;

    var urls = {
        queryPageData: "/userSupport/queryPageData",
        modifyDataDialog: "/userSupport/modifyDataDialog",
        addDataDialog: "/userSupport/addDataDialog"
    };
    queryPageData();

    var tableIns;

    function queryPageData() {
        tableIns = router.table({
            elem: "#dataList",
            url: urls.queryPageData,
            type: 'post',
            where: {"userName": $("#userName").val()},
            cols: [[
                /*{
                    field: 'userId',
                    title: '用户ID',
                },*/
                {
                    field: 'userName',
                    title: '用户名',
                },
                {
                    field: 'userName',
                    title: '角色列表',
                },
                {
                    title: '状态',
                    templet: function (d) {
                        var checked = d.userStatus == 'E' ? 'checked' : null;
                        var status = "<input type='checkbox' value=" + d.userId + " lay-skin='switch' lay-text='正常|停用' lay-filter='status' " + checked + "> ";
                        return status;

                    }
                },
                {
                    title: '创建时间',
                    templet: function (d) {
                        return util.toDateString(d.createDate);

                    }
                },
                {
                    title: '操作',
                    fixed: "right",
                    align: "center",
                    templet: function () {
                        var html = "<a class=\"layui-btn layui-btn-xs\" lay-event=\"edit\">编辑</a>"
                        html += "<a class=\"layui-btn layui-btn-xs layui-btn-danger\" lay-event=\"del\">删除</a>";
                        return html;

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
            layer.confirm('确定删除该用户吗？', {icon: 3, title: '提示信息'}, function (index) {
                layer.close(index)
                tableIns.reload();
            });
        } else if (layEvent == 'edit') {
            layui.layer.open({
                type: 2,
                title: '用户信息修改',
                skin: 'layui-layer-molv',
                area: ['500px', '500px'],
                content: urls.modifyDataDialog + "?userId=" + data.userId,
                btn: ['提交', '关闭'],
                yes: function () {

                },
                btn2: function (index) {
                    layer.close(index);
                }
            });
        }
    });
    //监听性别操作
    form.on('switch(status)', function (obj) {
        return true;
    });

    $("#btnAdd").click(function () {
        layui.layer.open({
            type: 2,
            title: '添加用户',
            skin: 'layui-layer-molv',
            area: ['500px', '500px'],
            content: urls.addDataDialog,
            btn: ['提交', '关闭'],
            btnAlign: 'c',
            yes: function () {

            },
            btn2: function (index) {
                layer.close(index);
            }
        });
    });

});