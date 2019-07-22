layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['jquery', 'tree', 'router'], function () {
    let $ = layui.jquery, tree = layui.tree, router = layui.router,
        urls = {
            getDataList: "/hrDept/queryListData",
            addDataPage: "/hrDept/addDataPage"
        };

    function initTree() {
        router.ajaxGet(urls.getDataList, {}, {}, function (res) {
            tree.render({
                elem: '#deptTree',
                data: $.parseJSON(res.data),
                operate: function (obj) {

                }
            });
        })
    }

    //添加按钮
    $("#btnAddPage").on('click', function () {
        router.ajaxGet(urls.addDataPage, {}, {}, function (res) {
            $("#rightPage").html(res);
        })
    });

    initTree();
});