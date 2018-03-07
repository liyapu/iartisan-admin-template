layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['jquery', 'router', 'table', 'layer', 'util'], function () {
    var router = layui.router, $ = layui.jquery, table = layui.table,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        util = layui.util, form = layui.form;

    var urls = {
        queryPageData: "/userSupport/queryPageData",
        modifyDataDialog: "/userSupport/modifyDataDialog"
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
            var that = this;
            //多窗口模式，层叠置顶
            layui.layer.open({
                type: 2, //此处以iframe举例
                title: '用户权限修改',
                skin: 'layui-layer-molv',
                area: ['500px', '500px'],
                maxmin: true,
                content: urls.modifyDataDialog + "?userId=" + data.userId,
                btn: ['提交', '关闭'],//只是为了演示
                yes: function () {
                    //提交修改数据
                },
                btn2: function (index) {
                    layer.close(index);
                }

                /*,zIndex: layer.zIndex //重点1
                ,success: function(layero){
                    layer.setTop(layero); //重点2
                }*/
            });
        }
    });
    //监听性别操作
    form.on('switch(status)', function (obj) {

        return true;
        //layer.tips(this.value + '：' + obj.elem.checked, obj.othis);
    });

});