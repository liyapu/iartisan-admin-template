layui.define(['layer', 'form', 'element', 'upload', 'util', 'table'], function (exports) {

    var $ = layui.jquery, layer = layui.layer, table = layui.table;

    var success_code = "000000", err_msg = "请求异常，请重试";

    var router = {
        get: function (options) {
            options = options || {};
            return $.ajax({
                type: options.type || 'get',
                dataType: options.dataType || 'json',
                data: options.data || {},
                url: options.url,
                success: function (res) {
                    if (res.code === success_code) {
                        options.success && options.success(res);
                    } else {
                        layer.msg(res.message || res.code, {shift: 6});
                        options.error && options.error();
                    }
                },
                error: function (e) {
                    layer.msg(err_msg, {shift: 6});
                    options.error && options.error(e);
                }
            });
        },
        post: function (options) {
            options = options || {};
            return $.ajax({
                type: options.type || 'post',
                dataType: options.dataType || 'json',
                data: options.data || {},
                url: options.url,
                success: function (res) {
                    if (res.code === success_code) {
                        options.success && options.success(res);
                    } else {
                        layer.msg(res.message || res.code, {shift: 6});
                        options.error && options.error();
                    }
                },
                error: function (e) {
                    layer.msg(err_msg, {shift: 6});
                    options.error && options.error(e);
                }
            });
        },
        table: function (options) {
            table.render({
                elem: options.elem,
                url: options.url,
                method: options.type || 'get',
                cols: options.cols,
                page: true,
                skin: options.skin || 'line',
                limits: options.limits || [10],
                response: {
                    statusCode: "000000",
                    dataName: "dataList",
                    countName: "totalRecords"
                },
                request: {
                    pageName: 'currPage', //页码的参数名称，默认：page,
                    limitName: 'pageSize' //每页数据量的参数名，默认：limit
                }
            });
        },
        location: function (href) {
            location.href = href;
        }
    };

    exports('router', router);
});