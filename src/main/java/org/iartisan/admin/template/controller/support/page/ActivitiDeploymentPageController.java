package org.iartisan.admin.template.controller.support.page;

import org.iartisan.runtime.web.contants.ReqContants;
import org.iartisan.runtime.web.controller.BaseController;
import org.iartisan.runtime.web.controller.ISupportPageController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
