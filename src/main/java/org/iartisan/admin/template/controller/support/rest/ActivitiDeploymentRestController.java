package org.iartisan.admin.template.controller.support.rest;

import org.activiti.engine.repository.Deployment;
import org.iartisan.admin.template.service.activiti.DeploymentManagement;
import org.iartisan.admin.template.service.entity.DeploymentEntity;
import org.iartisan.admin.template.service.entity.LogEntity;
import org.iartisan.runtime.bean.Page;
import org.iartisan.runtime.bean.PageWrapper;
import org.iartisan.runtime.web.WebR;
import org.iartisan.runtime.web.contants.ReqContants;
import org.iartisan.runtime.web.controller.BaseController;
import org.iartisan.runtime.web.controller.ISupportPageController;
import org.iartisan.runtime.web.controller.ISupportRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * @author King
 * @since 2018/6/28
 */
@RestController
@RequestMapping("activiti/deployment")
public class ActivitiDeploymentRestController extends BaseController implements ISupportRestController<Deployment> {

    @Autowired
    private DeploymentManagement deploymentManagement;

    @PostMapping(ReqContants.REQ_QUERY_PAGE_DATA)
    public WebR queryPageData(Page page) {
        PageWrapper<DeploymentEntity> pageData = deploymentManagement.queryDeploymentsPage(page);
        WebR webR = new WebR(pageData.getPage());
        webR.setData(pageData.getData());
        return webR;
    }

    @Override
    public WebR deleteData(String s) {
        return null;
    }

    @Override
    public WebR modifyData(Deployment deployment) {
        return null;
    }

    @Override
    public WebR addData(Deployment deployment) {
        return null;
    }
}
