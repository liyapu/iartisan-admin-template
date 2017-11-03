package org.iartisan.admin.template.controller;


import org.iartisan.admin.template.authentication.AuthenticationService;
import org.iartisan.admin.template.authentication.RealmBean;
import org.iartisan.admin.template.contants.ReqContants;
import org.iartisan.admin.template.contants.WebConstants;
import org.iartisan.runtime.exception.NoRecordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 权限认证请求
 *
 * @author King
 * @since 2017/4/13
 */
@Controller
public class AuthenticateController {

    @Autowired(required = false)
    private AuthenticationService authenticatorService;

    @Value("${iartisan.admin.authenticate.success:/home}")
    private String authenticateSuccessPage;

    @Value("${iartisan.admin.authenticate.error:login}")
    private String authenticateErrorPage;



    @RequestMapping(ReqContants.REQ_AUTHENTICATE)
    public String authenticate(RealmBean authenticator, HttpServletRequest request, Model model) {
        //判断用户名和密码是否正确
        if (StringUtils.isEmpty(authenticator.getUserName()) || StringUtils.isEmpty(authenticator.getUserPwd())) {
            return authenticateErrorPage;
        }
        try {
            RealmBean result = authenticatorService.doAuthentication(authenticator.getUserName(), authenticator.getUserPwd());
            request.getSession().setAttribute(WebConstants._USER, result);
        } catch (NoRecordException e) {
            model.addAttribute(WebConstants._ERR_MSG, "用户名或密码不存在");
            return authenticateErrorPage;
        }
        return "redirect:" + authenticateSuccessPage;
    }
}
