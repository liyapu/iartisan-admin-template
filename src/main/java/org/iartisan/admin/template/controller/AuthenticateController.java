package org.iartisan.admin.template.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.iartisan.admin.template.authentication.AuthenticationService;
import org.iartisan.admin.template.authentication.MenuTree;
import org.iartisan.admin.template.authentication.RealmBean;
import org.iartisan.admin.template.contants.ReqContants;
import org.iartisan.admin.template.contants.WebConstants;
import org.iartisan.runtime.exception.NoRecordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.border.EtchedBorder;
import java.util.ArrayList;
import java.util.List;

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


    @PostMapping(ReqContants.REQ_AUTHENTICATE)
    public String authenticate(RealmBean authenticator) {
        //判断用户名和密码是否正确
        if (StringUtils.isEmpty(authenticator.getUserName()) || StringUtils.isEmpty(authenticator.getUserPwd())) {
            return authenticateErrorPage;
        }
        Subject subject = SecurityUtils.getSubject();
        //sha256加密
        UsernamePasswordToken token = new UsernamePasswordToken(authenticator.getUserName(), authenticator.getUserPwd());
        subject.login(token);
        return "redirect:" + authenticateSuccessPage;
    }

    @ResponseBody
    @GetMapping("getMenus")
    public List<MenuTree> authenticate(HttpServletRequest request) {
        //response.addHeader("Access-Control-Allow-Origin", "*");
        /*  MenuTree tree = new MenuTree();
        tree.setTitle("在线客服");
        tree.setIcon("&#xe63a;");
        List<MenuTree> firsts = new ArrayList<>();
        firsts.add(tree);*/
        String token = request.getHeader("token");
        //authenticatorService.getNavs(token);
        //return firsts;
        return null;
    }

    @GetMapping(ReqContants.REQ_LOGOUT)
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:login.html";
    }
}
