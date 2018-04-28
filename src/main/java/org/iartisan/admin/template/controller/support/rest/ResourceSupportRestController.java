package org.iartisan.admin.template.controller.support.rest;

import org.iartisan.admin.template.authentication.ResourceSupportService;
import org.iartisan.admin.template.authentication.service.entity.ResourceEntity;
import org.iartisan.runtime.bean.Page;
import org.iartisan.runtime.bean.PageWrapper;
import org.iartisan.runtime.env.EnvContextConfig;
import org.iartisan.runtime.web.WebR;
import org.iartisan.runtime.web.contants.ReqContants;
import org.iartisan.runtime.web.controller.BaseController;
import org.iartisan.runtime.web.controller.ISupportRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 菜单权限管理
 *
 * @author King
 * @since 2018/3/30
 */
@RestController
@RequestMapping("resourceSupport")
public class ResourceSupportRestController extends BaseController implements ISupportRestController<ResourceEntity> {

    @Autowired
    private ResourceSupportService resourceSupportService;

    @PostMapping(ReqContants.REQ_DELETE_DATA)
    public WebR deleteData(String resourceId) {
        WebR r = new WebR();
        resourceSupportService.deleteResourceById(resourceId);
        return r;
    }

    @Override
    public WebR modifyData(ResourceEntity resourceEntity) {
        return null;
    }

    @PostMapping(ReqContants.REQ_ADD_DATA)
    public WebR addData(ResourceEntity resourceEntity) {
        WebR r = new WebR();
        resourceSupportService.addResourceData(resourceEntity);
        return r;
    }

    /**
     * 查询resource
     *
     * @param page
     * @param menuId
     * @return
     */
    @PostMapping(ReqContants.REQ_QUERY_PAGE_DATA)
    public WebR queryPageData(Page page, String menuId) {
        PageWrapper<ResourceEntity> pageData = resourceSupportService.getResourcePageData(page, menuId);
        WebR webR = new WebR(pageData.getPage());
        webR.setData(pageData.getDataList());
        return webR;
    }
}
