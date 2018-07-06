package org.iartisan.admin.template.controller.activiti;


import org.iartisan.admin.template.service.activiti.ModelManagement;
import org.iartisan.runtime.web.contants.ReqContants;
import org.iartisan.runtime.web.controller.BaseController;
import org.iartisan.runtime.web.controller.ISupportPageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;


/**
 * <p>
 *
 * @author King
 * @since 2018/6/28
 */
@Controller
@RequestMapping("activiti/model")
public class ActivitiModelPageController extends BaseController implements ISupportPageController {

    @Autowired
    private ModelManagement modelManagement;

    private static final String VIEW_PREFIX = "activiti/model/";


    @GetMapping(ReqContants.REQ_INDEX)
    public String index() {
        return VIEW_PREFIX + "model_index";
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
        String id = modelManagement.design("new", "new", "new");
        return "redirect:/activitiView/modeler.html?modelId=" + id;
    }
}
