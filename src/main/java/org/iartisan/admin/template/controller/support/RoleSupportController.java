package org.iartisan.admin.template.controller.support;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author King
 * @since 2018/2/28
 */
@Controller
@RequestMapping("roleSupport")
public class RoleSupportController extends BaseController {

    private static final String VIEW_PREFIX = "role/";

    @Autowired
    private RoleSupportService roleSupportService;

    @Autowired
    private ResourceSupportService resourceSupportService;


    @GetMapping(ReqContants.REQ_INDEX)
    public String index() {
        return VIEW_PREFIX + "role_index";
    }

    @ResponseBody
    @PostMapping(ReqContants.REQ_QUERY_PAGE_DATA)
    public WebR queryPageData(Page page, String roleName) {
        PageWrapper<RoleEntity> pageData = roleSupportService.getAuthPageData(page, roleName);
        WebR webR = new WebR(pageData.getPage());
        webR.setDataList(pageData.getDataList());
        return webR;
    }

    @GetMapping(ReqContants.REQ_ADD_DATA_PAGE)
    public String addDataPage() {
        return VIEW_PREFIX + "role_add";
    }

    @ResponseBody
    @PostMapping(ReqContants.REQ_ADD_DATA)
    public WebR addData(RoleEntity roleEntity) {
        roleSupportService.addRole(roleEntity);
        WebR r = new WebR();
        return r;
    }

    @ResponseBody
    @GetMapping("getResourceData")
    public WebR getResourceData() {
        List<ResourceEntity> dataList = resourceSupportService.getResourceList();
        WebR r = new WebR();
        r.setDataList(dataList);
        return r;
    }

    @ResponseBody
    @GetMapping("getResourceListByRoleId")
    public WebR getResourceListByRoleId(String roleId) {
        List<ResourceEntity> dataList = resourceSupportService.getResourceListByRoleId(roleId);
        WebR r = new WebR();
        r.setDataList(dataList);
        return r;
    }

    @GetMapping(ReqContants.REQ_QUERY_DETAIL_DATA)
    public String queryDetailData(String roleId, Model model) {
        RoleEntity authEntity = roleSupportService.getAuthDetail(roleId);
        model.addAttribute(_data, authEntity);
        return VIEW_PREFIX + "role_detail";
    }
}
