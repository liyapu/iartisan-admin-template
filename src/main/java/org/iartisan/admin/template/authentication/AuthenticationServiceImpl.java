package org.iartisan.admin.template.authentication;

import org.iartisan.admin.template.authentication.support.dbm.model.SystemUserDO;
import org.iartisan.admin.template.authentication.support.service.MenuSupportService;
import org.iartisan.admin.template.authentication.support.service.ResourceSupportService;
import org.iartisan.admin.template.authentication.support.service.UserSupportService;
import org.iartisan.runtime.web.authentication.AuthenticationService;
import org.iartisan.runtime.web.authentication.MenuTree;
import org.iartisan.runtime.web.authentication.RealmBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *
 * @author King
 * @since 2018/2/9
 */
@Service
public class AuthenticationServiceImpl extends AuthenticationService {

    @Autowired
    private UserSupportService userService;

    @Autowired
    private ResourceSupportService resourceSupportService;

    @Autowired
    private MenuSupportService menuSupportService;

    @Override
    protected RealmBean getRealmBean(String userName, String userPwd) {
        //判断用户名密码是否正确
        SystemUserDO userDO = userService.login(userName, userPwd);
        if (null == userDO) {
            return null;
        }
        //加载用户信息
        RealmBean bean = new RealmBean();
        bean.setUserName(userDO.getUserName());
        bean.setUserId(userDO.getUserId());
        //加载用户菜单列表
        List<MenuTree> trees = menuSupportService.getMenus(userDO.getUserId());
        bean.setMenuTrees(trees);
        return bean;
    }

    @Override
    protected Set<String> getPermissions(String userId) {
        //通过userId 获取可操作权限列表
        return resourceSupportService.getPermissionsByUserId(userId);
    }
}
