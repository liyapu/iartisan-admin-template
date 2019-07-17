layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['tree'], function () {
    let tree = layui.tree;
    let data = [{
        title: '陕西'
        , id: 3
        , children: [{
            title: '西安'
            , id: 3000
        }, {
            title: '延安'
            , id: 3001
        }]
    }];
    //常规用法
    tree.render({
        elem: '#test1' //默认是点击节点可进行收缩
        , data: data
    });

});