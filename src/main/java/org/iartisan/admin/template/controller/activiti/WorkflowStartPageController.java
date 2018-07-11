package org.iartisan.admin.template.controller.activiti;

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
@RequestMapping("activiti/workflowStart")
public class WorkflowStartPageController extends BaseController implements ISupportPageController {

    private static final String VIEW_PREFIX = "activiti/workflowStart/";


    @GetMapping(ReqContants.REQ_INDEX)
    public String index() {
        return VIEW_PREFIX + "workflowStart_index";
    }

    @GetMapping(ReqContants.REQ_ADD_DATA_PAGE)
    public String addDataPage(Model model) {
        return VIEW_PREFIX + "workflowStart_add";
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
