<form class="layui-form" id="formAdd">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-inline">
            <input type="text" name="userName" id="userName" class="layui-input" placeholder="请输入用户名称"
                   lay-verify="required">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
            <input type="checkbox" name="userStatus" lay-skin="switch" lay-text="正常|停用" checked value="E">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">所有角色</label>
        <div class="layui-input-block">
        <#if data ?? >
            <#list data as val >
                <input type="checkbox" name="roles" id="roles" title="${val.roleName}" lay-skin="primary" value="${val.roleId!''}"/>
            </#list>
        </#if>
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 160px;">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="btnAdd" type="button">
                添加
            </button>
            <button type="button" class="layui-btn layui-btn-primary" id="btnClose">关闭</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="/assets/iartisan/js/user/user_add.js"></script>
