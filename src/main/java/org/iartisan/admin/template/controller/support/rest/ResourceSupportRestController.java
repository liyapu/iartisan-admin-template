package org.iartisan.admin.template.controller.support.rest;

import org.iartisan.admin.template.authentication.support.service.entity.ResourceEntity;
import org.iartisan.admin.template.authentication.support.service.entity.UserEntity;
import org.iartisan.runtime.bean.Page;
import org.iartisan.runtime.bean.PageWrapper;
import org.iartisan.runtime.web.WebR;
import org.iartisan.runtime.web.contants.ReqContants;
import org.iartisan.runtime.web.controller.BaseController;
import org.iartisan.runtime.web.controller.ISupportPageController;
import org.iartisan.runtime.web.controller.ISupportRestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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


    @Override
    public WebR deleteData(String keyId) {
        return null;
    }

    @Override
    public WebR modifyData(ResourceEntity resourceEntity) {
        return null;
    }

    @Override
    public WebR addData(ResourceEntity resourceEntity) {
        return null;
    }

    @PostMapping(ReqContants.REQ_QUERY_PAGE_DATA)
    public WebR queryPageData(Page page, String menuId) {
        return null;
    }
}
