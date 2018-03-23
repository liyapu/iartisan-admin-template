package org.iartisan.admin.template.controller.support.rest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.iartisan.admin.template.authentication.support.service.ResourceSupportService;
import org.iartisan.admin.template.authentication.support.service.RoleSupportService;
import org.iartisan.admin.template.authentication.support.service.entity.ResourceEntity;
import org.iartisan.admin.template.authentication.support.service.entity.RoleEntity;
import org.iartisan.runtime.bean.Page;
import org.iartisan.runtime.bean.PageWrapper;
import org.iartisan.runtime.web.WebR;
import org.iartisan.runtime.web.contants.ReqContants;
import org.iartisan.runtime.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author King
 * @since 2018/2/28
 */
@RestController
@RequestMapping("roleSupport")
public class RoleSupportRestController extends BaseController {


    @Autowired
    private RoleSupportService roleSupportService;

    @Autowired
    private ResourceSupportService resourceSupportService;


    @RequiresPermissions("deletedata")
    @PostMapping(ReqContants.REQ_DELETE_DATA)
    public WebR deleteData(String roleId) {
        roleSupportService.deleteRole(roleId);
        WebR r = new WebR();
        r.setMessage("删除角色成功");
        return r;
    }

    @GetMapping("getResourceData")
    public WebR getResourceData() {
        List<ResourceEntity> dataList = resourceSupportService.getResourceList();
        WebR r = new WebR();
        r.setDataList(dataList);
        return r;
    }

    @GetMapping("getResourceListByRoleId")
    public WebR getResourceListByRoleId(String roleId) {
        List<ResourceEntity> dataList = resourceSupportService.getResourceListByRoleId(roleId);
        WebR r = new WebR();
        r.setDataList(dataList);
        return r;
    }

    @PostMapping(ReqContants.REQ_QUERY_PAGE_DATA)
    public WebR queryPageData(Page page, String roleName) {
        PageWrapper<RoleEntity> pageData = roleSupportService.getAuthPageData(page, roleName);
        WebR webR = new WebR(pageData.getPage());
        webR.setDataList(pageData.getDataList());
        return webR;
    }

    @PostMapping(ReqContants.REQ_ADD_DATA)
    public WebR addData(RoleEntity roleEntity) {
        roleSupportService.addRole(roleEntity);
        WebR r = new WebR();
        return r;
    }

}