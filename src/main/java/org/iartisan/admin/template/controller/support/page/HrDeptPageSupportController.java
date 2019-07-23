package org.iartisan.admin.template.controller.support.page;

import org.iartisan.admin.template.service.entity.DeptEntity;
import org.iartisan.admin.template.service.query.DeptQueryService;
import org.iartisan.runtime.utils.StringUtils;
import org.iartisan.runtime.web.contants.ReqContants;
import org.iartisan.runtime.web.controller.BaseController;
import org.iartisan.runtime.web.controller.ISupportPageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 部门管理页面
 *
 * @author King  2019-7-17
 */
@Controller
@RequestMapping("hrDept")
public class HrDeptPageSupportController extends BaseController implements ISupportPageController {

    private static final String VIEW_PREFIX = "hrDept/";

    @Autowired
    private DeptQueryService deptQueryService;

    @Override
    @GetMapping(ReqContants.REQ_INDEX)
    public String index() {
        return VIEW_PREFIX + "hrDept_index";
    }

    @Override
    public String addDataPage(Model model) {
        return null;
    }

    @GetMapping(ReqContants.REQ_ADD_DATA_PAGE)
    public String addDataPage(String parentDeptId, Model model) {
        DeptEntity entity = deptQueryService.getDataById(parentDeptId);
        model.addAttribute("parentDept", entity);
        return VIEW_PREFIX + "hrDept_add";
    }

    @Override
    @GetMapping(ReqContants.REQ_MODIFY_DATA_PAGE)
    public String modifyDataPage(Model model, String deptId) {
        initData(model, deptId);
        return VIEW_PREFIX + "hrDept_modify";
    }

    private void initData(Model model, String deptId) {
        DeptEntity entity = deptQueryService.getDataById(deptId);
        model.addAttribute("dept", entity);
        DeptEntity parentEntity = null;
        if (StringUtils.isNotEmpty(entity.getDeptParent())) {
            parentEntity = deptQueryService.getDataById(entity.getDeptParent());
        }
        model.addAttribute("parentDept", parentEntity == null ? new DeptEntity() : parentEntity);
    }

    @Override
    @GetMapping(ReqContants.REQ_QUERY_DETAIL_PAGE)
    public String queryDetailPage(Model model, String deptId) {
        initData(model, deptId);
        return VIEW_PREFIX + "hrDept_detail";
    }
}
