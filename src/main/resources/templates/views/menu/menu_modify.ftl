<form class="layui-form" id="formAdd">
    <div class="layui-form-item">
        <label class="layui-form-label">父级名称</label>
        <div class="layui-input-inline">
            <select name="parentMenuId">
                <option value="">/</option>
                <#if data ?? >
                    <#list data as val>
                        <option value="${val.id}">${val.title}</option>
                    </#list>
                </#if>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">菜单名称</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input newsName" placeholder="请输入菜单名称" lay-verify="required"
                   name="menuName" value="${data.menuName!''}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">权限代码</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input newsName" placeholder="请输入菜单名称" lay-verify="required"
                   name="menuPermission" value="${data.menuPermission!''}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">链接</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input newsName" placeholder="请输入菜单名称" lay-verify="required"
                   name="menuUrl" value="${data.menuUrl!''}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">图标</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input newsName" placeholder="请输入菜单名称" lay-verify="required"
                   name="menuIcon" >
        </div>
        <div class="layui-form-mid"> <i class="layui-icon"> ${data.menuIcon!''}</i></div>
    </div>
    <input type="hidden" lay-submit="" lay-filter="addData" id="btnAdd"/>
</form>
<script type="text/javascript" src="/assets/iartisan/js/menu/menu_add.js"></script>