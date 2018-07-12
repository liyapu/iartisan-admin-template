package org.iartisan.admin.template.controller.activiti.page;

import org.iartisan.admin.template.service.activiti.DeploymentManagement;
import org.iartisan.admin.template.service.activiti.WorkflowManagement;
import org.iartisan.runtime.web.contants.ReqContants;
import org.iartisan.runtime.web.controller.BaseController;
import org.iartisan.runtime.web.controller.ISupportPageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * <p>
 *
 * @author King
 * @since 2018/6/28
 */
@Controller
@RequestMapping("activiti/workflow")
public class WorkflowManagementPageController extends BaseController implements ISupportPageController {

    private static final String VIEW_PREFIX = "activiti/workflow/";

    @Autowired
    private DeploymentManagement deploymentManagement;

    @Autowired
    private WorkflowManagement workflowManagement;


    @GetMapping("start" + ReqContants.REQ_INDEX)
    public String index() {
        return VIEW_PREFIX + "start/workflowStart_index";
    }

    @GetMapping("start/" + ReqContants.REQ_ADD_DATA_PAGE)
    public String addDataPage(Model model) {
        //查询所有流程列表
        model.addAttribute("deployments", deploymentManagement.getAllDeployments());
        return VIEW_PREFIX + "start/workflowStart_add";
    }

    @Override
    public String modifyDataPage(Model model, String s) {
        return null;
    }

    @Override
    public String queryDetailPage(Model model, String s) {
        return null;
    }

    @RequestMapping(value = "/getProcessDefineImg")
    public void loadProcessDefineImg(String processInstanceId, HttpServletResponse response)
            throws Exception {
        response.addHeader("Access-Control-Allow-Origin", "*");
        InputStream resourceAsStream = workflowManagement.getProcessDefinitionImg(processInstanceId);
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }
}
