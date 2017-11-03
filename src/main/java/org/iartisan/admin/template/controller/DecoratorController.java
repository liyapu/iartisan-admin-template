package org.iartisan.admin.template.controller;

import org.iartisan.admin.template.contants.ReqContants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 装饰页面
 *
 * @author King
 * @since 2017/11/2
 */
@Controller
public class DecoratorController {

    @Value("${iartisan.admin.authenticate.login:login}")
    private String loginPage;

    @Value("${iartisan.admin.authenticate.decorator:decorator}")
    private String decoratorPage;

    @RequestMapping(ReqContants.REQ_LOGOUT)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return loginPage;
    }

    @RequestMapping(ReqContants.REQ_DECORATOR)
    public String decorator() {
        return decoratorPage;
    }

    @GetMapping(ReqContants.REQ_LOGIN)
    public String login() {
        return loginPage;
    }

    @GetMapping("/")
    public String index() {
        return loginPage;
    }

}
