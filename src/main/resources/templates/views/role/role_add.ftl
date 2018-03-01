<form class="layui-form layui-row layui-col-space10">
    <div class="layui-col-md9 layui-col-xs12">
        <div class="layui-row layui-col-space12">
            <div class="layui-col-md12 layui-col-xs12">
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">角色名称</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input newsName" placeholder="请输入角色名称" lay-verify="required">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入备注" class="layui-textarea abstract" lay-verify="required"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">授权</label>
                    <div class="layui-input-block">
                        <div id="auths" style="border:1px solid block;"></div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="formData" id="btnAdd" type="button">添加</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript" src="/assets/iartisan/plugins/xtree/layui-xtree.js"></script>
<script type="text/javascript" src="/assets/iartisan/js/role/role_add.js"></script>