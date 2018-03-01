var ztree = "";
layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['jquery', 'router'], function () {

    var $ = layui.jquery, form = layui.form, router = layui.router,
        layer = parent.layer === undefined ? layui.layer : top.layer;


    var urls = {
        getResourceData: "/roleSupport/getResourceData",
        addData: "/roleSupport/addData"
    };

    var xtree;

    router.get({
        url: urls.getResourceData,
        success: function (res) {
            //创建tree
            xtree = new layuiXtree({
                elem: 'auths',             //放xtree的容器（必填，只能为id，注意不带#号）
                form: form,                //layui form对象 （必填）
                data: res.dataList,        //数据，结构请参照下面 （必填）
                isopen: true,              //初次加载时全部展开，默认true （选填）
                color: "#000",             //图标颜色 （选填）
                icon: {                    //图标样式 （选填）
                    open: "",            //节点打开的图标（使用layui的图标，这里只填入图标代号即可）
                    close: "&#xe654;",     //节点关闭的图标
                    end: ""                //末尾节点的图标
                }
            });
        }
    });

    form.on("submit(formData)", function (data) {
        var oCks = xtree.GetChecked();
        var auths = new Array();
        for (var i = 0; i < oCks.length; i++) {
            var partent = xtree.GetParent(oCks[i].value);
            auths.push(oCks[i].value);
        }
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        router.post({
            url: urls.addData,
            data: {"roleName": data.field.roleName, "permissions": auths.join(",")},
            success: function () {
                top.layer.close(index);
                layer.closeAll("iframe");
                parent.location.reload();
            }
        });

        /* var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});*/
        /* setTimeout(function(){
             top.layer.close(index);
             top.layer.msg("用户添加成功！");
             layer.closeAll("iframe");
             //刷新父页面
             parent.location.reload();
         },2000);*/

        return false;
    })
});