layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['jquery', 'laydate'], function () {
    var laydate = layui.laydate;
    laydate.render({elem: '#beginTime'});
    laydate.render({elem: '#endTime'});

});