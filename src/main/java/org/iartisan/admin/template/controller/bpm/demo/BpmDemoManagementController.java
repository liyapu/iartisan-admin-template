package org.iartisan.admin.template.controller.bpm.demo;

import org.iartisan.runtime.web.controller.BaseController;
import org.iartisan.runtime.web.controller.ISupportPageController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author King  2019-8-30
 */
@RestController
@RequestMapping("bpm/demo/")
public class BpmDemoManagementController extends BaseController implements ISupportPageController {

    private static final String VIEW_PREFIX = "bpm/demo/";

    @Override
    @GetMapping("index")
    public String index() {
        return VIEW_PREFIX + "newItem_index";
    }

    @Override
    public String addDataPage(Model model) {
        return null;
    }

    @Override
    public String modifyDataPage(Model model, String keyId) {
        return null;
    }

    @Override
    public String queryDetailPage(Model model, String keyId) {
        return null;
    }
}
