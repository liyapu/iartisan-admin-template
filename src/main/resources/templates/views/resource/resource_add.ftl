<div class="layui-form" style="margin-top:5px;margin-right: 20px;">
    <form id="formAdd">
        <div class="layui-form-item">
            <label class="layui-form-label">权限名称</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" placeholder="请输入菜单名称" lay-verify="required"
                       name="menuName">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">权限代码</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" placeholder="请输入菜单名称" lay-verify="required"
                       name="menuPermission">
            </div>
        </div>
        <input type="hidden" lay-submit="" lay-filter="addData" id="btnAdd"/>
    </form>
</div>