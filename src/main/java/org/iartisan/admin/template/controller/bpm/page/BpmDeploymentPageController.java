package org.iartisan.admin.template.controller.bpm.page;


import org.iartisan.admin.template.service.activiti.DeploymentManagement;
import org.iartisan.runtime.web.WebR;
import org.iartisan.runtime.web.contants.ReqContants;
import org.iartisan.runtime.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * <p>
 *
 * @author King
 * @since 2018/6/28
 */
@Controller
@RequestMapping("bpm/deployment")
public class BpmDeploymentPageController extends BaseController {

    private static final String VIEW_PREFIX = "bpm/deployment/";

    @Autowired
    private DeploymentManagement deploymentManagement;

    @GetMapping(ReqContants.REQ_INDEX)
    public String index() {
        return VIEW_PREFIX + "deployment_index";
    }

    @ResponseBody
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

    @ResponseBody
    @PostMapping(ReqContants.REQ_DELETE_DATA)
    public WebR delete(String deploymentId) {
        WebR webR = new WebR();
        webR.isSuccess("成功");
        try {
            deploymentManagement.delete(deploymentId);
        } catch (Exception e) {
            webR.isError(e.getMessage());
        }
        return webR;
    }

    @GetMapping("toDesign")
    public String toDesign() {
        return "redirect:/modler/index.html#/processes";
    }
}
