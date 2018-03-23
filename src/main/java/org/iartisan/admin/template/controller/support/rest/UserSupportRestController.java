package org.iartisan.admin.template.controller.support.rest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.iartisan.admin.template.authentication.support.service.RoleSupportService;
import org.iartisan.admin.template.authentication.support.service.UserSupportService;
import org.iartisan.admin.template.authentication.support.service.entity.UserEntity;
import org.iartisan.runtime.bean.Page;
import org.iartisan.runtime.bean.PageWrapper;
import org.iartisan.runtime.web.WebR;
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
@RestController
@RequestMapping("userSupport")
public class UserSupportRestController extends BaseController {

    private static final String VIEW_PREFIX = "user/";

    @Autowired
    private UserSupportService userSupportService;


    @PostMapping(ReqContants.REQ_QUERY_PAGE_DATA)
    public WebR queryPageData(Page page, String userName) {
        PageWrapper<UserEntity> pageData = userSupportService.getUserPageData(page, userName);
        WebR webR = new WebR(pageData.getPage());
        webR.setDataList(pageData.getDataList());
        return webR;
    }

    @PostMapping(ReqContants.REQ_ADD_DATA)
    public WebR addData(UserEntity entity) {
        userSupportService.addUser(entity);
        WebR r = new WebR();
        return r;
    }

    @PostMapping(ReqContants.REQ_MODIFY_DATA)
    public WebR modifyData(UserEntity entity) {
        userSupportService.modifyData(entity);
        WebR r = new WebR();
        return r;
    }

    @PostMapping("changeStatus")
    public WebR changeStatus(String userId, String status) {
        userSupportService.changeStatus(userId, status);
        WebR r = new WebR();
        return r;
    }

    @RequiresPermissions("deletedata")
    @PostMapping(ReqContants.REQ_DELETE_DATA)
    public WebR deleteData(String userId) {
        userSupportService.deleteByUserId(userId);
        WebR r = new WebR();
        return r;
    }
}