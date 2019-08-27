package org.iartisan.admin.template.controller.activiti.rest;


import afu.org.checkerframework.checker.oigj.qual.O;
import org.activiti.engine.repository.Model;
import org.iartisan.admin.template.service.activiti.ModelManagement;
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
@RequestMapping("activiti/model")
public class ActivitiModelRestController extends BaseController implements ISupportRestController<org.activiti.engine.repository.Model> {

    @Autowired
    private ModelManagement modelManagement;

    @PostMapping(ReqContants.REQ_QUERY_PAGE_DATA)
    public WebR queryPageData(Pagination page) {
        try {
            PageWrapper<Model> pageData = modelManagement.queryModelPage(page);
            WebR webR = new WebR();
            webR.setData(pageData.getRows());
            return webR;
        } catch (Exception e) {
            logger.error("", e);
            WebR r = new WebR();
            r.isError(e.getMessage());
            return r;
        }
    }

    @PostMapping("deploy")
    public WebR deploy(String modelId) {
        WebR r = new WebR();
        try {
            modelManagement.deploy(modelId);
        } catch (Exception e) {
            r.isError(e.getMessage());
        }
        return r;
    }

    @Override
    @PostMapping(ReqContants.REQ_DELETE_DATA)
    public WebR deleteData(String modelId) {
        WebR r = new WebR();
        modelManagement.deleteData(modelId);
        return r;
    }

    @Override
    public WebR modifyData(org.activiti.engine.repository.Model model) {
        return null;
    }

    @Override
    public WebR addData(org.activiti.engine.repository.Model model) {
        return null;
    }
}
