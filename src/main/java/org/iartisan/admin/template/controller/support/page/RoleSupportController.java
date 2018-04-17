package org.iartisan.admin.template.controller.support.page;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.iartisan.admin.template.authentication.RoleSupportService;
import org.iartisan.admin.template.authentication.service.entity.RoleEntity;
import org.iartisan.runtime.web.contants.ReqContants;
import org.iartisan.runtime.web.controller.BaseController;
import org.iartisan.runtime.web.controller.ISupportPageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author King
 * @since 2018/2/28
 */
@Controller
@RequestMapping("roleSupport")
public class RoleSupportController extends BaseController implements ISupportPageController {

    private static final String VIEW_PREFIX = "role/";

    @Autowired
    private RoleSupportService roleSupportService;


    @RequiresPermissions("auth:manage:role:index")
    @GetMapping(ReqContants.REQ_INDEX)
    public String index() {
        return VIEW_PREFIX + "role_index";
    }

    @RequiresPermissions("auth:manage:role:addDataPage")
    @GetMapping(ReqContants.REQ_ADD_DATA_PAGE)
    public String addDataPage(Model model) {
        return VIEW_PREFIX + "role_add";
    }

    @RequiresPermissions("auth:manage:role:queryDetailPage")
    @GetMapping(ReqContants.REQ_QUERY_DETAIL_PAGE)
    public String queryDetailPage(Model model, String roleId) {
        RoleEntity authEntity = roleSupportService.getRoleDetail(roleId);
        model.addAttribute(_data, authEntity);
        return VIEW_PREFIX + "role_detail";
    }


    @Override
    public String modifyDataPage(Model model, String keyId) {
        return null;
    }
}
