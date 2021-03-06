<form class="layui-form" id="formAdd" autocomplete="off">
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
                   name="deptName" value="${dept.deptName}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">部门负责人</label>
        <div class="layui-input-inline">
            <select name="deptLeader" lay-search>
                <option value="">请选择部门负责人</option>
                <#if staffList ?? >
                    <#list staffList as val>
                        <option value="${val.staffId}" <#if (val.staffId)=(dept.deptLeader)> selected </#if> >
                         ${val.staffName}</option>
                    </#list>
                </#if>
            </select>
        </div>
    </div>
    <input type="hidden" name="deptId" value="${dept.deptId}" >
    <input type="hidden" lay-submit="" lay-filter="addData" id="btnAdd"/>
</form>
<script type="text/javascript" src="/assets/iartisan/js/hrDept/hrDept_modify.js?${staticVerison!'1'}"></script>