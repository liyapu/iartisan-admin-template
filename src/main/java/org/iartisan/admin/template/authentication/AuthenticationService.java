package org.iartisan.admin.template.authentication;

import org.iartisan.runtime.exception.NoRecordException;

/**
 * <p>
 * 认证接口
 *
 * @author King
 * @since 2017/7/6
 */
public interface AuthenticationService {
    /**
     * 登录认证
     *
     * @param name
     * @param password
     * @return
     * @throws NoRecordException
     */
    RealmBean doAuthentication(String name, String password) throws NoRecordException;
}
