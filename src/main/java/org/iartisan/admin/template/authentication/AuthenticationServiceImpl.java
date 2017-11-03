package org.iartisan.admin.template.authentication;

import org.iartisan.runtime.exception.NoRecordException;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 认证类实现
 *
 * @author King
 * @since 2017/11/3
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public RealmBean doAuthentication(String name, String password) throws NoRecordException {

        return new RealmBean();
    }
}
