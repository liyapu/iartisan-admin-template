layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['jquery', 'router', 'table', 'layer'], function () {
    var router = layui.router, $ = layui.jquery, table = layui.table,
        layer = layui.layer;

    var urls = {
        queryPageData: "/menuSupport/queryPageData",
        addDataPage: "/menuSupport/addDataPage",
        deleteData: "/menuSupport/deleteData",
        modifyDataPage: "/menuSupport/modifyDataPage",
        addResourceIndex: "/resourceSupport/index"
    };
    queryPageData();

    var tableIns;

    function queryPageData() {
        tableIns = router.table({
            elem: "#dataList",
            url: urls.queryPageData,
            type: 'post',
            where: {"menuName": $("#menuName").val()},
            cols: [[
                {
                    field: 'title',
                    title: '菜单名称',
                },
                {
                    field: 'href',
                    title: '跳转链接',
                },
                {
                    title: '图标',
                    templet: function (d) {
                        return "<i class=\"layui-icon\">" + d.icon + "</i>";
                    }
                },
                {
                    title: '操作 <i style="color: #FF5722;" class="layui-icon alone-tips" lay-tips="<span style=\'color:red;\'>子权限管理</span></br>主要是针对菜单下按钮级别的操作进行权限控制</br>该功能主要适用于二级菜单</br>"></i>',
                    fixed: "right",
                    align: "center",
                    templet: function (d) {
                        var html = "<a class='layui-btn layui-btn-xs' lay-event='edit'>编辑</a>";
                        /* html += "<a class='layui-btn layui-btn-xs layui-btn-danger' lay-event='del'>删除</a>";*/
                        html += "<a class='layui-btn layui-btn-xs layui-btn-normal' lay-event='addMenuSource'>子权限管理</a>";
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
        if (layEvent == 'addMenuSource') {
            layui.layer.open({
                type: 2,
                maxmin: true,
                anim: 1,
                title: '添加子菜单 <span style="margin-left: 5px;font-size: 10px">[' + data.title + "]</span>",
                skin: 'layui-layer-molv',
                area: ['800px', '600px'],
                content: urls.addResourceIndex + "?menuId=" + data.id,
                btn: ['关闭'],
                btnAlign: 'c',
                btn1: function (index) {
                    layer.close(index);
                }
            });
        }
        if (layEvent == 'edit') {
            layui.layer.open({
                type: 2,
                title: '菜单信息修改',
                skin: 'layui-layer-molv',
                area: ['500px', '500px'],
                content: urls.modifyDataPage + "?menuId=" + data.id,
                btn: ['修改', '关闭'],
                btnAlign: 'c',
                yes: function (index, layero) {
                    layero.find('iframe').contents().find("#formModify").find("#btnModify").click();
                },
                btn2: function (index) {
                    layer.close(index);
                }
            });
        }
    });

    $("#btnAddPage").click(function () {
        var index = layui.layer.open({
            title: "添加菜单",
            type: 2,
            content: urls.addDataPage,
            area: ['600px', '500px'],
            maxmin: true,
            btn: ['添加', '关闭'],
            btnAlign: 'c',
            skin: 'layui-layer-molv',
            yes: function (index, layero) {
                layero.find('iframe').contents().find("#formAdd").find("#btnAdd").click();
            },
            btn2: function (index) {
                layui.layer.close(index);
            }
        });
    });

    $('*[lay-tips]').on('mouseenter', function () {
        var content = $(this).attr('lay-tips');
        this.index = layer.tips('<div style="padding: 10px; font-size: 14px; color: #eee;">' + content + '</div>', this, {
            time: -1, maxWidth: 280, tips: [4, '#3A3D49']
        });
    }).on('mouseleave', function () {
        layer.close(this.index);
    });
});