package org.iartisan.admin.template.authentication;

import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <p>
 *
 * @author King
 * @since 2018/2/9
 */
@Service
public class AuthenticationServiceImpl extends AuthenticationService {
    @Override
    protected RealmBean getRealmBean(String userName, String userPwd) {
        return new RealmBean();
    }

    @Override
    protected Set<String> getPermissions(String userId) {
        return null;
    }
}
