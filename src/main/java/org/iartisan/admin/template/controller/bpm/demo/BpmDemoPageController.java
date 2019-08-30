package org.iartisan.admin.template.controller.bpm.demo;

import org.iartisan.runtime.web.contants.ReqContants;
import org.iartisan.runtime.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author King  2019-8-30
 */
@Controller
@RequestMapping("bpm/demo")
public class BpmDemoPageController extends BaseController {

    private static final String VIEW_PREFIX = "bpm/demo/new_item/";


    @GetMapping("newItem/index")
    public String newItemIndex() {
        return VIEW_PREFIX + "newItem_index";
    }

    @GetMapping("newItem/" + ReqContants.REQ_ADD_DATA_PAGE)
    public String addDataPage() {
        return VIEW_PREFIX + "newItem_add";
    }

}
