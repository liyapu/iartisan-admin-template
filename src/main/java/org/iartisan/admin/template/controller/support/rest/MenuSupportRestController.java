package org.iartisan.admin.template.controller.support.rest;

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
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * menu support
 *
 * @author King
 * @since 2018/2/26
 */
@RestController
@RequestMapping("menuSupport")
public class MenuSupportRestController extends BaseController {

    private static final String VIEW_PREFIX = "menu/";

    @Autowired
    private MenuSupportService menuSupportService;


    @PostMapping(ReqContants.REQ_QUERY_PAGE_DATA)
    public WebR queryPageData(Page page, String menuName) {
        PageWrapper<MenuTree> pageData = menuSupportService.getMenuPageData(page, menuName);
        WebR webR = new WebR(pageData.getPage());
        webR.setDataList(pageData.getDataList());
        return webR;
    }

    @PostMapping(ReqContants.REQ_ADD_DATA)
    public WebR addData(MenuEntity menuEntity) {
        menuSupportService.addMenu(menuEntity);
        WebR webR = new WebR();
        return webR;
    }

    @RequiresPermissions("deletedata")
    @PostMapping(ReqContants.REQ_DELETE_DATA)
    public WebR deleteData(String menuId) {
        menuSupportService.deleteData(menuId);
        WebR webR = new WebR();
        return webR;
    }

    @PostMapping(ReqContants.REQ_MODIFY_DATA)
    public WebR modifyData(MenuEntity menuEntity) {
        menuSupportService.modifyData(menuEntity);
        WebR webR = new WebR();
        return webR;
    }
}
