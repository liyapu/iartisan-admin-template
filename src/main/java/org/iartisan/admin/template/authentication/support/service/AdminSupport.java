package org.iartisan.admin.template.authentication.support.service;

import org.iartisan.admin.template.authentication.support.dbm.mapper.SystemUserMapper;
import org.iartisan.admin.template.authentication.support.dbm.model.SystemUserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理员管理
 * crud
 *
 * @author King
 * @since 2018/2/22
 */
@Service
public class AdminSupport {

    @Autowired
    private SystemUserMapper systemUserMapper;

    public SystemUserDO login(String userName, String userPwd) {
        SystemUserDO dbQuery = new SystemUserDO();
        dbQuery.setUserName(userName);
        dbQuery.setUserPwd(userPwd.toLowerCase());
        return systemUserMapper.selectOne(dbQuery);
    }

}
