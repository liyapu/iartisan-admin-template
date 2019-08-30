package org.iartisan.admin.template.controller.bpm.rest;


import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.iartisan.admin.template.service.activiti.DeploymentManagement;
import org.iartisan.runtime.bean.PageWrapper;
import org.iartisan.runtime.bean.Pagination;
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
 *
 * @author King
 * @since 2018/6/28
 */
@RestController
@RequestMapping("bpm/deployment")
public class BpmDeploymentRestController extends BaseController implements ISupportRestController<Deployment> {

    @Autowired
    private DeploymentManagement deploymentManagement;

    @PostMapping(ReqContants.REQ_QUERY_PAGE_DATA)
    public WebR queryPageData(Pagination page) {
        PageWrapper<ProcessDefinition> pageData = deploymentManagement.getProcessDefinitionPage(page);
        WebR webR = new WebR(pageData.getPage());
        webR.setData(pageData.getRows());
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
