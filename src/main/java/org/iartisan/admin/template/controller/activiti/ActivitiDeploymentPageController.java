package org.iartisan.admin.template.controller.activiti;


import org.iartisan.admin.template.service.activiti.DeploymentManagement;
import org.iartisan.runtime.web.WebR;
import org.iartisan.runtime.web.contants.ReqContants;
import org.iartisan.runtime.web.controller.BaseController;
import org.iartisan.runtime.web.controller.ISupportPageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;


/**
 * <p>
 *
 * @author King
 * @since 2018/6/28
 */
@Controller
@RequestMapping("activiti/deployment")
public class ActivitiDeploymentPageController extends BaseController implements ISupportPageController {

    private static final String VIEW_PREFIX = "activiti/deployment/";

    @Autowired
    private DeploymentManagement deploymentManagement;

    @GetMapping(ReqContants.REQ_INDEX)
    public String index() {
        return VIEW_PREFIX + "deployment_index";
    }

    @Override
    public String addDataPage(Model model) {
        return null;
    }

    @Override
    public String modifyDataPage(Model model, String s) {
        return null;
    }

    @Override
    public String queryDetailPage(Model model, String s) {
        return null;
    }

    //在线设计页面
    @GetMapping("toDesign")
    public String toDesign() throws UnsupportedEncodingException {
        String id = deploymentManagement.design("new", "new", "new");
        return "redirect:/activitiView/modeler.html?modelId=" + id;
    }

    @PostMapping(value = "upload")
    public WebR uploadFile(HttpServletRequest request) throws IOException {
        WebR r = new WebR();
        try {
            StandardMultipartHttpServletRequest multipartRequest = (StandardMultipartHttpServletRequest) request;
            MultipartFile multipartFile = multipartRequest.getMultiFileMap().getFirst("file");

            deploymentManagement.deploy(multipartFile.getOriginalFilename(), multipartFile.getInputStream());
            return r;
        } catch (Exception ex) {
            logger.error("Exception", ex);
            r.isError(ex.getMessage());
            return r;
        }
    }
}
