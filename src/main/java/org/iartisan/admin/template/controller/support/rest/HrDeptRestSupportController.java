package org.iartisan.admin.template.controller.support.rest;

import cn.hutool.json.JSONUtil;
import org.iartisan.admin.template.service.entity.DeptEntity;
import org.iartisan.admin.template.service.entity.DeptTreeEntity;
import org.iartisan.admin.template.service.query.DeptQueryService;
import org.iartisan.runtime.utils.JsonUtil;
import org.iartisan.runtime.web.WebR;
import org.iartisan.runtime.web.contants.ReqContants;
import org.iartisan.runtime.web.controller.BaseController;
import org.iartisan.runtime.web.controller.ISupportRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 部门管理页面
 *
 * @author King  2019-7-17
 */
@RestController
@RequestMapping("hrDept")
public class HrDeptRestSupportController extends BaseController implements ISupportRestController<DeptEntity> {

    @Autowired
    private DeptQueryService bizDeptQueryService;

    @Override
    public WebR deleteData(String keyId) {
        return null;
    }

    @Override
    public WebR modifyData(DeptEntity bizDeptEntity) {
        return null;
    }

    @Override
    public WebR addData(DeptEntity bizDeptEntity) {
        return null;
    }

    /**
     * 获取列表数据
     *
     * @return
     */
    @GetMapping(value = ReqContants.REQ_QUERY_LIST_DATA)
    public WebR getDataList() {
        WebR webR = new WebR();
        List<DeptTreeEntity> deptList = bizDeptQueryService.getDeptList();
        webR.setData(JSONUtil.toJsonStr(deptList));
        return webR;
    }
}
