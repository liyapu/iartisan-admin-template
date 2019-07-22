layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['jquery', 'zTree', 'router', 'layer'], function () {
    let $ = layui.jquery, tree = layui.tree, router = layui.router,
        urls = {
            getDataList: "/hrDept/queryListData",
            addDataPage: "/hrDept/addDataPage"
        },
        layer = layui.layer;

    let currentDeptId;

    let setting = {
        check: {
            enable: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: function (event, srcEvent, tree) {
                currentDeptId = tree.id;
            }
        }
    };

    function initTree() {
        let loading = layer.load(1);
        router.get({
            url: urls.getDataList,
            success: function (res) {
                layer.close(loading);
                $.fn.zTree.init($("#deptTree"), setting, res.data);
                $.fn.zTree.getZTreeObj("deptTree").expandAll(true);
            }
        });
    }


    //添加按钮
    $("#btnAddPage").on('click', function () {
        let load = layer.load(1);
        router.ajaxGet(urls.addDataPage,
            {parentDeptId: currentDeptId},
            {},
            function (res) {
            layer.close(load);
            $("#rightPage").html(res);
        })
    });

    initTree();
});