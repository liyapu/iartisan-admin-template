package org.iartisan.admin.template.authentication;

import org.iartisan.admin.template.authentication.support.dbm.model.SystemUserDO;
import org.iartisan.admin.template.authentication.support.service.AdminSupport;
import org.iartisan.runtime.web.authentication.AuthenticationService;
import org.iartisan.runtime.web.authentication.MenuTree;
import org.iartisan.runtime.web.authentication.RealmBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private AdminSupport adminSupport;

    @Override
    protected RealmBean getRealmBean(String userName, String userPwd) {
        //判断用户名密码是否正确
        SystemUserDO userDO = adminSupport.login(userName, userPwd);
        if (null == userDO) {
            return null;
        }
        //加载用户信息
        RealmBean bean = new RealmBean();
        bean.setUserName(userDO.getUserName());
        bean.setUserId(userDO.getUserId());
        //加载用户菜单列表
        List<MenuTree> menuTreeList = new ArrayList<>();
        MenuTree tree = new MenuTree();
        tree.setTitle("test");
        menuTreeList.add(tree);
        bean.setMenuTrees(menuTreeList);
        return bean;
    }

    @Override
    protected Set<String> getPermissions(String userId) {
        return null;
    }
}
