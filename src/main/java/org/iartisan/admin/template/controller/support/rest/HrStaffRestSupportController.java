package org.iartisan.admin.template.controller.support.rest;

import org.iartisan.admin.template.service.entity.StaffEntity;
import org.iartisan.admin.template.service.management.StaffManagementService;
import org.iartisan.admin.template.service.query.StaffQueryService;
import org.iartisan.runtime.bean.Page;
import org.iartisan.runtime.bean.PageWrapper;
import org.iartisan.runtime.web.WebR;
import org.iartisan.runtime.web.contants.ReqContants;
import org.iartisan.runtime.web.controller.BaseController;
import org.iartisan.runtime.web.controller.ISupportRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门管理页面
 *
 * @author King  2019-7-17
 */
@RestController
@RequestMapping("hrStaff")
public class HrStaffRestSupportController extends BaseController implements ISupportRestController<StaffEntity> {

    @Autowired
    private StaffQueryService staffQueryService;

    @Autowired
    private StaffManagementService staffManagementService;

    @Override
    public WebR deleteData(String keyId) {
        return null;
    }

    @Override
    public WebR modifyData(StaffEntity staffEntity) {
        return null;
    }

    @Override
    @PostMapping(ReqContants.REQ_ADD_DATA)
    public WebR addData(StaffEntity staffEntity) {
        WebR webR = new WebR();
        staffManagementService.saveData(staffEntity);
        return webR;
    }

    @PostMapping(ReqContants.REQ_QUERY_PAGE_DATA)
    public WebR getPageData(Page page, StaffEntity staffEntity) {
        PageWrapper<StaffEntity> pageDataByWrapper = staffQueryService.getPageData(page, staffEntity);
        WebR webR = new WebR(pageDataByWrapper.getPage());
        webR.setData(pageDataByWrapper.getData());
        return webR;
    }


}
