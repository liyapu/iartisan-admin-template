package org.iartisan.admin.template.controller.activiti;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ActivitiException;
import org.apache.commons.lang3.StringUtils;
import org.iartisan.admin.template.service.activiti.DeploymentManagement;
import org.iartisan.runtime.web.contants.ReqContants;
import org.iartisan.runtime.web.controller.BaseController;
import org.iartisan.runtime.web.controller.ISupportPageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

import static org.activiti.editor.constants.ModelDataJsonConstants.MODEL_ID;
import static org.activiti.editor.constants.ModelDataJsonConstants.MODEL_NAME;

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
        String id = deploymentManagement.design("new","new","new");
        return "redirect:/activiti/modeler.html?modelId=" + id;
    }
}
