package org.iartisan.admin.template.controller;

import org.iartisan.runtime.web.contants.ReqContants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <p>
 *
 * @author King
 * @since 2018/2/9
 */
@Controller
public class IndexController {

    @PostMapping(ReqContants.REQ_INDEX)
    public String index() {
        return "index";
    }

}
