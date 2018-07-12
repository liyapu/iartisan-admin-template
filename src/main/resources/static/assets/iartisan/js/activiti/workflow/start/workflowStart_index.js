layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['jquery', 'layer', 'router', 'util'], function () {
    var $ = layui.jquery, layer = layui.layer, router = layui.router, util = layui.util;
    var urls = {
        addDataPage: "/activiti/workflow/start/addDataPage",
        queryPageData: "/activiti/workflow/start/owner/queryPageData",
    };

    $("#btnStart").on('click', function () {
        layui.layer.open({
            type: 2,
            title: '发起请假',
            skin: 'layui-layer-molv',
            area: ['650px', '650px'],
            content: urls.addDataPage,
            btn: ['修改', '关闭'],
            btnAlign: 'c',
            yes: function (index, layero) {
                layero.find('iframe').contents().find("#formAdd").find("#btnAdd").click();
            },
            btn2: function (index) {
                layer.close(index);
            }
        });
    });
    //加载自己的流程列表
    queryPageData();

    var tableIns;

    function queryPageData() {
        tableIns = router.table({
            elem: "#dataList",
            url: urls.queryPageData,
            type: 'post',
            cols: [[
                {
                    title: '任务名称',
                    field: 'taskNodeName',
                    align: 'center'
                },
                {
                    title: '部署时间',
                    templet: function (d) {
                        return util.toDateString(d.startTime);
                    },
                    align: 'center'
                },
                {
                    title: '操作',
                    templet: function () {
                        var html = "<a class='layui-btn layui-btn-danger layui-btn-xs' lay-event='del'>删除</a>";
                        return html;
                    }
                }
            ]]
        });
    }
    function loadProcessDefineImg() {
        var processInstanceId = $("#processInstanceId").val();
        var imageUrl = page.ajaxURL.getProcessDefineImg + "?processInstanceId=" + processInstanceId;
        $('#workflowTraceDiv img').attr('src', imageUrl);
        //渲染流程节点
        $.getJSON(page.ajaxURL.getProcessTrace + "?processInstanceId=" + processInstanceId, function (data) {
            var infos = data.dataList;
            var positionHtml = "";
            // 生成图片
            $.each(infos, function (i, v) {
                var vars = v.vars;
                var $positionDiv = $('<div/>', {
                    'class': 'activity-attr'
                }).css({
                    position: 'absolute',
                    left: (v.x ),
                    top: (v.y ),
                    width: (v.width ),
                    height: (v.height ),
                    backgroundColor: 'black',
                    opacity: 0,
                });
                // 节点边框
                var $border = $('<div/>', {
                    'class': 'activity-attr-border'
                }).css({
                    position: 'absolute',
                    left: (v.x ),
                    top: (v.y ),
                    width: (v.width ),
                    height: (v.height ),
                }).attr("var", JSON.stringify(vars));
                if (v.currentActiviti) {
                    $border.addClass('ui-corner-all-12').css({
                        border: '3px solid red'
                    });
                }
                positionHtml += $positionDiv.outerHTML() + $border.outerHTML();

            });
            // alert(positionHtml);
            $('#processImageBorder').html(positionHtml);
            // 设置每个节点的data
            /*$('#workflowTraceDiv .activity-attr').each(function(i, v) {
             $(this).data('vars', varsArray[i]);
             });*/
            /*$(".activity-attr-border").mouseover(function () {
                var top = $(this).offset().top + $(this).height() + 10;
                var data = $(this).attr("var");
                var line1 = '<div class="row clearfix"><label class="movelabel">' + data + '</div>';
                var htmlcontent = "<div class='moveonStyle' style='position: absolute;top:" + top + "px; left:" + $(this).offset().left + "px;'>" + line1 + "</div>";
                $('body').append(htmlcontent);
            })
            $(".activity-attr-border").mouseleave(function () {
                $(".moveonStyle").remove()
            })*/
        });
    }

,
});