var ztree = "";
layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['jquery', 'router'], function () {

    var form = layui.form, router = layui.router;

    var urls = {getResourceData: "/roleSupport/getResourceData"};

    var xtree;

    router.get({
        url: urls.getResourceData,
        success: function (res) {
            //创建tree
            xtree = new layuiXtree({
                elem: 'auths',//放xtree的容器（必填，只能为id，注意不带#号）
                form: form,        //layui form对象 （必填）
                data: res.dataList,        //数据，结构请参照下面 （必填）
                isopen: true,      //初次加载时全部展开，默认true （选填）
                color: "#000",          //图标颜色 （选填）
                icon: {                 //图标样式 （选填）
                    open: ""      //节点打开的图标（使用layui的图标，这里只填入图标代号即可）
                    , close: "&#xe654;"   //节点关闭的图标
                    , end: ""     //末尾节点的图标
                }
            });
        }
    });
    //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
    $(window).on("resize",function(){
        layui.layer.full(index);
    });

});