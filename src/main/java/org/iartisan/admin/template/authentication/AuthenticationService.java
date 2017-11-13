package org.iartisan.admin.template.authentication;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;
import java.util.Set;

/**
 * <p>
 * 认证类实现
 *
 * @author King
 * @since 2017/11/3
 */
//@Service
public class AuthenticationService extends AuthorizingRealm {

    //鉴权 判断用户拥有的权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //todo 权限列表
        Set<String> permsSet = null;
        info.setStringPermissions(permsSet);
        return info;
    }

    //认证  判断用户名和密码
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户名和密码
        String userName = (String) token.getPrincipal();
        String userPwd = new String((char[]) token.getCredentials());
        //todo 查询用户信息
        RealmBean realmBean = new RealmBean();
        //判断用户状态
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(realmBean, userPwd, getName());
        return info;
    }


}
