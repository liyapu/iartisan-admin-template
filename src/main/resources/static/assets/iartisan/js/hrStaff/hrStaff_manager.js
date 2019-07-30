layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['router', 'table', 'util', 'iartisntips'], function () {
    let router = layui.router, table = layui.table,
        util = layui.util, tips = layui.iartisntips;

    let urls = {
        addDataPage: "/hrStaff/addDataPage",
        queryPageData:"/hrStaff/queryPageData"
    };
    queryPageData();

    let area = ['50%', '80%'];

    function queryPageData() {
        router.table({
            elem: "#dataList",
            url: urls.queryPageData,
            type: 'post',
            where: {"userName": $("#userName").val()},
            even: true,
            cols: [[
                {
                    field: 'userName',
                    title: '用户名',
                },
                {
                    field: 'roles',
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
                        html += "<a class='layui-btn layui-btn-xs layui-btn-danger' lay-event='del'>删除</a>";
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
                area: area,
                content: urls.modifyDataPage + "?userId=" + data.userId,
                btn: ['修改', '关闭'],
                yes: function (index, layero) {
                    layero.find('iframe').contents().find("#formModify").find("#btnModify").click();
                },
                btn2: function (index) {
                    layer.close(index);
                }
            });
        } else if (layEvent == 'del') {
            layer.confirm('确定删除该员工吗？', {icon: 3, title: '提示信息'}, function (index) {
                layer.close(index);
                /*$.post(urls.deleteData, {userId: data.userId}, function (res) {
                    if (res.code == '000000') {
                        tableIns.reload();
                    }
                });*/
                router.post({
                    url: urls.deleteData, data: {userId: data.userId}, success: function () {
                        tableIns.reload();
                    }
                })
            });
        }
    });
    $("#btnAddPage").click(function () {
        layui.layer.open({
            type: 2,
            title: '添加员工',
            area: area,
            content: urls.addDataPage,
            skin: 'layui-layer-molv',
            btn: ['提交', '关闭'],
            btnAlign: 'c',
            yes: function (index, layero) {
                layero.find('iframe').contents().find("#formAdd").find("#btnAdd").click();
            },
            btn2: function (index) {
                layer.close(index);
            }
        });
    });
    tips.show();
});