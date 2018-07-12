package org.iartisan.admin.template.controller.activiti.page;


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
}
