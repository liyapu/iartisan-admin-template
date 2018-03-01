package org.iartisan.admin.template.authentication.support.service;

import org.iartisan.admin.template.authentication.support.dbm.mapper.SystemRoleMapper;
import org.iartisan.admin.template.authentication.support.dbm.model.SystemRoleDO;
import org.iartisan.admin.template.authentication.support.dbm.model.SystemUserDO;
import org.iartisan.admin.template.authentication.support.service.entity.AuthEntity;
import org.iartisan.admin.template.authentication.support.service.entity.UserEntity;
import org.iartisan.runtime.bean.Page;
import org.iartisan.runtime.bean.PageWrapper;
import org.iartisan.runtime.jdbc.PageHelper;
import org.iartisan.runtime.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * auth service
 *
 * @author King
 * @since 2018/2/28
 */
@Service
public class RoleSupportService {

    @Autowired
    private SystemRoleMapper systemRoleMapper;


    public PageWrapper<AuthEntity> getAuthPageData(Page page, String roleName) {
        SystemRoleDO roleDO = new SystemRoleDO();
        if (StringUtils.isNotEmpty(roleName)) {
            roleDO.setRoleName(roleName);
        }
        PageWrapper<SystemRoleDO> dbResult = PageHelper.getPageData(systemRoleMapper, page, roleDO);
        PageWrapper<AuthEntity> result = new PageWrapper<>(dbResult.getPage());
        List<AuthEntity> pageList = new ArrayList<>();
        for (SystemRoleDO o : dbResult.getDataList()) {
            AuthEntity bean = new AuthEntity();
            bean.setRoleId(o.getRoleId());
            bean.setRoleName(o.getRoleName());
            pageList.add(bean);
        }
        result.setDataList(pageList);
        return result;
    }


}
