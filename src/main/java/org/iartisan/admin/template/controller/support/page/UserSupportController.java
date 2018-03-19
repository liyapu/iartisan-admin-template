package org.iartisan.admin.template.controller.support.page;

import org.iartisan.admin.template.authentication.support.service.RoleSupportService;
import org.iartisan.admin.template.authentication.support.service.UserSupportService;
import org.iartisan.admin.template.authentication.support.service.entity.UserEntity;
import org.iartisan.runtime.bean.Page;
import org.iartisan.runtime.bean.PageWrapper;
import org.iartisan.runtime.web.WebR;
import org.iartisan.runtime.web.authentication.MenuTree;
import org.iartisan.runtime.web.contants.ReqContants;
import org.iartisan.runtime.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * user support
 *
 * @author King
 * @since 2018/2/26
 */
@Controller
@RequestMapping("userSupport")
public class UserSupportController extends BaseController {

    private static final String VIEW_PREFIX = "user/";

    @Autowired
    private RoleSupportService roleSupportService;


    @GetMapping(ReqContants.REQ_INDEX)
    public String index() {
        return VIEW_PREFIX + "user_index";
    }


    @GetMapping(ReqContants.REQ_MODIFY_DATA_DIALOG)
    public String modifyDataDialog(String userId, Model model) {
        //查询用户所有角色
        model.addAttribute(_data, roleSupportService.getRoleByUserId(userId));
        return VIEW_PREFIX + "user_modify";
    }

    @GetMapping(ReqContants.REQ_ADD_DATA_DIALOG)
    public String addDataDialog(Model model) {
        //查询所有的角色列表
        model.addAttribute(_data, roleSupportService.getAllRoles());
        return VIEW_PREFIX + "user_add";
    }

}
