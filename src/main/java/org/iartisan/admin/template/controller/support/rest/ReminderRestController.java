package org.iartisan.admin.template.controller.support.rest;

import org.iartisan.admin.template.service.query.SystemMsgQueryService;
import org.iartisan.runtime.web.WebR;
import org.iartisan.runtime.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务提醒controller
 *
 * @author King  2019-9-29
 */
@RestController
@RequestMapping("reminder")
public class ReminderRestController extends BaseController {

    @Autowired
    private SystemMsgQueryService systemMsgQueryService;

    @GetMapping("getUnreadCount")
    public WebR getUnreadCount() {
        WebR webR = new WebR();
        int unreadCount = systemMsgQueryService.getUnreadCount(getCustId());
        webR.setData(unreadCount);
        return webR;
    }

}
