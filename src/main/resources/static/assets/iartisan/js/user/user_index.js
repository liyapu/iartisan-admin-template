layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['router', 'table', 'util'], function () {
    var router = layui.router, table = layui.table,
        util = layui.util;

    var urls = {
        queryPageData: "/userSupport/queryPageData",
        modifyDataDialog: "/userSupport/modifyDataDialog",
        addDataDialog: "/userSupport/addDataDialog",
        changeStatus: "/userSupport/changeStatus",
        addData: "/userSupport/addData"
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
                        var html = "<a class='layui-btn layui-btn-xs' lay-event='edit'>编辑</a>";
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
    table.on('tool(_table)', function (obj) {
        var layEvent = obj.event, data = obj.data;
        if (layEvent == 'edit') {
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
        var checked = this.checked;
        var index = layui.layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.post(urls.changeStatus, {userId: obj.value, status: checked ? 'E' : 'D'}, function (res) {
            layui.layer.close(index);
            if (!res.code.eq('000000')) {
                //如果不成功则返回
                obj.elem.checked = checked;
            }
        }).always(function () {
            layui.layer.close(index);
        });
        ;
    });

    $("#btnAdd").click(function () {
        //Ajax获取
        /* $.get(urls.addDataDialog, {}, function (res) {*/
        layui.layer.open({
            type: 2,
            title: '添加用户',
            area: ['500px', '500px'],
            content: urls.addDataDialog,
            btn: ['提交', '关闭'],
            btnAlign: 'c',
            yes: function (index, layero) {
                //layer.alert(layero.find('iframe').contentDocument);
                router.post({
                    url: urls.addData,
                    data: layero.find('iframe').contents().find("#formAdd").serialize(),
                    success: function (res) {
                        layui.layer.close(index);
                        layer.alert(res.message);
                        tableIns.reload('dataList', {});
                    }
                });
            },
            btn2: function (index) {
                layer.close(index);
            }
        });
    });
    /*});*/

});