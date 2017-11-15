package org.iartisan.admin.template.authentication;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

/**
 * <p>
 * 认证接口服务
 *
 * @author King
 * @since 2017/11/3
 */
public abstract class AuthenticationService extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        RealmBean realmBean = (RealmBean) principals.getPrimaryPrincipal();
        //用户权限列表
        Set<String> permsSet = getPermissions(realmBean.getUserId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        String userPwd = new String((char[]) token.getCredentials());
        RealmBean realmBean = getRealmBean(userName, userPwd);
        if (realmBean == null) {
            throw new IncorrectCredentialsException("用户名或密码错误,请重新登录");
        }
        //判断用户状态
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(realmBean, userPwd, getName());
        return info;
    }

    /**
     * 用户登录
     *
     * @param userName
     * @param userPwd
     * @return
     */
   protected abstract RealmBean getRealmBean(String userName, String userPwd);

    /**
     * 获取用户权限列表
     *
     * @param userId
     * @return
     */
    protected abstract Set<String> getPermissions(String userId);
}
