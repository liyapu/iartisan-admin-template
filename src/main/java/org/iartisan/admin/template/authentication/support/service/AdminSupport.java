package org.iartisan.admin.template.authentication.support.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.iartisan.admin.template.authentication.support.dbm.mapper.SystemResourceMapper;
import org.iartisan.admin.template.authentication.support.dbm.mapper.SystemUserMapper;
import org.iartisan.admin.template.authentication.support.dbm.mapper.SystemUserRoleMapper;
import org.iartisan.admin.template.authentication.support.dbm.model.SystemUserDO;
import org.iartisan.admin.template.authentication.support.dbm.model.SystemUserRoleDO;
import org.iartisan.runtime.web.authentication.MenuTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private SystemUserRoleMapper systemUserRoleMapper;

    @Autowired
    private SystemResourceMapper systemResourceMapper;

    public SystemUserDO login(String userName, String userPwd) {
        SystemUserDO dbQuery = new SystemUserDO();
        dbQuery.setUserName(userName);
        dbQuery.setUserPwd(userPwd.toLowerCase());
        return systemUserMapper.selectOne(dbQuery);
    }

    //获取角色列表
    public List<String> getRolesByUserId(String userId) {
        SystemUserRoleDO systemUserRoleDO = new SystemUserRoleDO();
        systemUserRoleDO.setUserId(userId);
        Wrapper<SystemUserRoleDO> query = new EntityWrapper<>(systemUserRoleDO);
        List<SystemUserRoleDO> dbResult = systemUserRoleMapper.selectList(query);
        if (null == dbResult) {
            return null;
        }
        List<String> result = new ArrayList<>();
        for (SystemUserRoleDO userRoleDO : dbResult) {
            result.add(userRoleDO.getRoleId());
        }
        return result;
    }

    //获取菜单列表
    public List<MenuTree> getMenus(String userId) {
        //查询role 所有的menuId
        //systemResourceMapper.selectList()

        //查找一级菜单

        //查找二级菜单

        return null;
    }


}
