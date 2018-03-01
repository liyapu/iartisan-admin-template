package org.iartisan.admin.template.controller.support;

import org.iartisan.admin.template.authentication.support.service.ResourceSupportService;
import org.iartisan.admin.template.authentication.support.service.RoleSupportService;
import org.iartisan.admin.template.authentication.support.service.entity.AuthEntity;
import org.iartisan.admin.template.authentication.support.service.entity.ResourceEntity;
import org.iartisan.runtime.bean.Page;
import org.iartisan.runtime.bean.PageWrapper;
import org.iartisan.runtime.web.WebR;
import org.iartisan.runtime.web.contants.ReqContants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class RoleSupportController {

    private static final String VIEW_PREFIX = "role/";

    @Autowired
    private RoleSupportService authSupportService;

    @Autowired
    private ResourceSupportService resourceSupportService;


    @GetMapping(ReqContants.REQ_INDEX)
    public String index() {
        return VIEW_PREFIX + "role_index";
    }

    @ResponseBody
    @PostMapping(ReqContants.REQ_QUERY_PAGE_DATA)
    public WebR queryPageData(Page page, String roleName) {
        PageWrapper<AuthEntity> pageData = authSupportService.getAuthPageData(page, roleName);
        WebR webR = new WebR(pageData.getPage());
        webR.setDataList(pageData.getDataList());
        return webR;
    }

    @GetMapping(ReqContants.REQ_ADD_DATA_PAGE)
    public String addDataPage() {
        return VIEW_PREFIX + "role_add";
    }

    @PostMapping(ReqContants.REQ_ADD_DATA)
    public String addData() {
        return VIEW_PREFIX + "role_add";
    }

    @ResponseBody
    @GetMapping("getResourceData")
    public WebR getResourceData() {
        List<ResourceEntity> dataList = resourceSupportService.getResourceList();
        WebR r = new WebR();
        r.setDataList(dataList);
        return r;
    }

}
