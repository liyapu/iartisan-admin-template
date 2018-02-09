package org.iartisan.admin.template.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.iartisan.admin.template.authentication.RealmBean;
import org.iartisan.runtime.web.WebR;
import org.iartisan.runtime.web.contants.ReqContants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 权限认证请求
 *
 * @author King
 * @since 2017/4/13
 */
@Controller
public class AuthenticateController {

    @Value("${iartisan.admin.authenticate.success:index}")
    private String authenticateSuccessPage;

    @Value("${iartisan.admin.authenticate.error:login}")
    private String authenticateErrorPage;

    @ResponseBody
    @PostMapping(ReqContants.REQ_AUTHENTICATE)
    public WebR authenticate(RealmBean authenticator) {
        //判断用户名和密码是否正确
        /*if (StringUtils.isEmpty(authenticator.getUserName()) || StringUtils.isEmpty(authenticator.getUserPwd())) {
            return authenticateErrorPage;
        }*/
        authenticator = new RealmBean();
        authenticator.setUserName("trest");
        authenticator.setUserPwd("ss");
        Subject subject = SecurityUtils.getSubject();
        //sha256加密
        UsernamePasswordToken token = new UsernamePasswordToken(authenticator.getUserName(), authenticator.getUserPwd());
        subject.login(token);
        //return "redirect:" + authenticateSuccessPage;
        WebR r = new WebR();
        r.setMessage(authenticateSuccessPage);
        return r;
    }


}
