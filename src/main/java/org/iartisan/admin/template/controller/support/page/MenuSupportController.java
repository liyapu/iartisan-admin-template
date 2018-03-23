package org.iartisan.admin.template.controller.support.page;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.iartisan.admin.template.authentication.support.service.MenuSupportService;
import org.iartisan.admin.template.authentication.support.service.entity.MenuEntity;
import org.iartisan.runtime.bean.Page;
import org.iartisan.runtime.bean.PageWrapper;
import org.iartisan.runtime.web.WebR;
import org.iartisan.runtime.web.authentication.MenuTree;
import org.iartisan.runtime.web.contants.ReqContants;
import org.iartisan.runtime.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * menu support
 *
 * @author King
 * @since 2018/2/26
 */
@Controller
@RequestMapping("menuSupport")
public class MenuSupportController extends BaseController {

    private static final String VIEW_PREFIX = "menu/";

    @Autowired
    private MenuSupportService menuSupportService;


    //@RequiresPermissions("auth:manage:menu:index")
    @GetMapping(ReqContants.REQ_INDEX)
    public String index() {
        return VIEW_PREFIX + "menu_index";
    }

    //@RequiresPermissions("auth:manage:menu:addDataDialog")
    @GetMapping(ReqContants.REQ_ADD_DATA_DIALOG)
    public String addDataDialog(Model model) {
        //查询一级菜单
        model.addAttribute(_data, menuSupportService.getFirstMenus());
        return VIEW_PREFIX + "menu_add";
    }


    //@RequiresPermissions("auth:manage:menu:modifyDataDialog")
    @GetMapping(ReqContants.REQ_MODIFY_DATA_DIALOG)
    public String modifyDataDialog(Model model, String menuId) {
        //查询一级菜单
        model.addAttribute("firstMenus", menuSupportService.getFirstMenus());
        //查询一级菜单
        model.addAttribute(_data, menuSupportService.getMenuById(menuId));
        return VIEW_PREFIX + "menu_modify";
    }
}