<form class="layui-form">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" placeholder="请输入角色名称" lay-verify="required">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
            <input type="checkbox" name="switch" lay-skin="switch" lay-text="正常|停用" checked>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">所有角色</label>
        <div class="layui-input-block">
        <#if data ?? >
            <#list (data.roleEntities) as val >
                <input type="checkbox" name="role" title="${val.roleName}" lay-skin="primary" value="${val.roldId!''}"
                       <#if (val.own)>checked</#if>
                >
            </#list>
        </#if>
        </div>
    </div>
    <input type="hidden" name="userId" value="${data.userId!''}">
</form>
<script type="text/javascript" src="/assets/iartisan/js/user/user_modify.js"></script>
<#--
<table id="dataList" lay-filter="dataList"></table>
<script type="text/javascript" src="/assets/iartisan/js/user/user_index.js"></script>-->
