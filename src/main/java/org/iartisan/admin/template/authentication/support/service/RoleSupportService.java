package org.iartisan.admin.template.authentication.support.service;

import org.iartisan.admin.template.authentication.support.dbm.mapper.SystemRoleMapper;
import org.iartisan.admin.template.authentication.support.dbm.mapper.SystemRolePermissionMapper;
import org.iartisan.admin.template.authentication.support.dbm.model.SystemRoleDO;
import org.iartisan.admin.template.authentication.support.dbm.model.SystemRolePermissionDO;
import org.iartisan.admin.template.authentication.support.dbm.model.SystemUserDO;
import org.iartisan.admin.template.authentication.support.service.entity.AuthEntity;
import org.iartisan.admin.template.authentication.support.service.entity.RoleEntity;
import org.iartisan.admin.template.authentication.support.service.entity.UserEntity;
import org.iartisan.runtime.bean.Page;
import org.iartisan.runtime.bean.PageWrapper;
import org.iartisan.runtime.jdbc.PageHelper;
import org.iartisan.runtime.utils.StringUtils;
import org.iartisan.runtime.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private SystemRolePermissionMapper systemRolePermissionMapper;


    public AuthEntity getAuthDetail(String roleId) {
        SystemRoleDO dbResult = systemRoleMapper.selectById(roleId);
        AuthEntity result = new AuthEntity();
        if (null != dbResult) {
            result.setRoleId(dbResult.getRoleId());
            result.setRoleName(dbResult.getRoleName());
        }
        return result;
    }

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

    @Transactional
    public void addRole(RoleEntity roleEntity) {
        SystemRoleDO dbRoleInsert = new SystemRoleDO();
        dbRoleInsert.setRoleName(roleEntity.getRoleName());
        String roleId = UUIDUtil.shortId();
        dbRoleInsert.setRoleId(roleId);
        dbRoleInsert.setCreateTime(new Date());
        systemRoleMapper.insert(dbRoleInsert);
        //添加permission
        String permissionStr = roleEntity.getPermissions();
        if (StringUtils.isNotEmpty(permissionStr)) {
            String[] permissions = permissionStr.split(",");
            for (String permission : permissions) {
                SystemRolePermissionDO dbPermission = new SystemRolePermissionDO();
                dbPermission.setRoleId(roleId);
                String[] types = permission.split("\\|");
                dbPermission.setPermissionId(types[0]);
                if (types.length < 2) {
                    dbPermission.setPermissionType("m");
                } else {
                    dbPermission.setPermissionType(types[1]);
                }
                dbPermission.setCreateTime(new Date());
                systemRolePermissionMapper.insert(dbPermission);
            }
        }
    }
}
