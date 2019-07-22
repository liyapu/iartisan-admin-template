<form class="layui-form" id="formAdd">
    <div class="layui-form-item">
        <label class="layui-form-label">父级部门</label>
        <div class="layui-input-inline layui-form-mid layui-word-aux">
            ${parentDept.deptName}
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">部门名称</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" placeholder="请输入部门名称" lay-verify="required"
                   name="menuName">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" type="button">
                <i class="layui-icon">&#xe608;</i>添加
            </button>
        </div>
    </div>
</form>
<script type="text/javascript" src="/assets/iartisan/js/menu/menu_add.js?${staticVerison!'1'}"></script>