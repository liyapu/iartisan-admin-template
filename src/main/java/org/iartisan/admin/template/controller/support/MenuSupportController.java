package org.iartisan.admin.template.controller.support;

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

    //index
    @GetMapping(ReqContants.REQ_INDEX)
    public String index() {
        return VIEW_PREFIX + "menu_index";
    }

    @GetMapping(ReqContants.REQ_ADD_DATA_DIALOG)
    public String addDataDialog(Model model) {
        //查询一级菜单
        model.addAttribute(_data, menuSupportService.getFirstMenus());
        return VIEW_PREFIX + "menu_add";
    }

    @ResponseBody
    @PostMapping(ReqContants.REQ_QUERY_PAGE_DATA)
    public WebR queryPageData(Page page, String menuName) {
        PageWrapper<MenuTree> pageData = menuSupportService.getMenuPageData(page, menuName);
        WebR webR = new WebR(pageData.getPage());
        webR.setDataList(pageData.getDataList());
        return webR;
    }

    @ResponseBody
    @PostMapping(ReqContants.REQ_ADD_DATA)
    public WebR addData(MenuEntity menuEntity) {
        menuSupportService.addMenu(menuEntity);
        WebR webR = new WebR();
        return webR;
    }
}
