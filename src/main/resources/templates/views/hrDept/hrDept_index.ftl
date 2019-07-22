<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md3">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">
                            <i class="layui-icon">&#xe68e;</i> </i> 部门
                        </div>
                        <div class="layui-card-body">
                            <ul id="deptTree" name="deptTree"  class="ztree"></ul>
                        </div>
                        <div class="layui-card-header">
                            <button class="layui-btn layui-btn-normal" type="button" id="btnAddPage">
                                <i class="layui-icon">&#xe608;</i>添加下级部门
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="layui-col-md9">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body" style="height: 400px;" id="rightPage">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<link rel="stylesheet" href="/assets/iartisan/plugins/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/assets/iartisan/js/hrDept/hrDept_index.js?${staticVerison!'1'}"></script>
