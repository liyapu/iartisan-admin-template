package org.iartisan.admin.template.authentication.support.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.iartisan.admin.template.authentication.support.PermissionType;
import org.iartisan.admin.template.authentication.support.dbm.mapper.*;
import org.iartisan.admin.template.authentication.support.dbm.model.SystemMenuDO;
import org.iartisan.admin.template.authentication.support.dbm.model.SystemRolePermissionDO;
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
public class UserService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Autowired
    private SystemUserRoleMapper systemUserRoleMapper;

    @Autowired
    private SystemRolePermissionMapper systemRolePermissionMapper;

    @Autowired
    private SystemMenuMapper systemMenuMapper;

    public SystemUserDO login(String userName, String userPwd) {
        SystemUserDO dbQuery = new SystemUserDO();
        dbQuery.setUserName(userName);
        dbQuery.setUserPwd(userPwd.toLowerCase());
        return systemUserMapper.selectOne(dbQuery);
    }

    //获取角色列表
    private List<String> getRolesByUserId(String userId) {
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
        List<String> roles = getRolesByUserId(userId);
        if (null == roles) {
            return null;
        }
        SystemRolePermissionDO dbQuery = new SystemRolePermissionDO();
        dbQuery.setRoleIds(roles);
        List<String> menuIds = systemRolePermissionMapper.selectPermissions(dbQuery);
        //得到一级菜单
        List<SystemMenuDO> firstMenus = systemMenuMapper.selectFirstMenus(menuIds);
        //得到二级菜单
        if (firstMenus == null) {
            return null;
        }
        List<MenuTree> result = new ArrayList<>();
        for (SystemMenuDO firstMenu : firstMenus) {
            MenuTree firstTree = new MenuTree();
            firstTree.setTitle(firstMenu.getMenuName());
            firstTree.setIcon(firstMenu.getMenuIcon());
            SystemMenuDO secondQuery = new SystemMenuDO();
            secondQuery.setMenuIds(menuIds);
            secondQuery.setParentMenuId(firstMenu.getMenuId());
            List<SystemMenuDO> secondMenus = systemMenuMapper.selectSecondMenus(secondQuery);
            if (null != secondMenus) {
                //添加二级菜单
                List<MenuTree> children = new ArrayList<>();
                for (SystemMenuDO secondMenu : secondMenus) {
                    MenuTree secondTree = new MenuTree();
                    secondTree.setTitle(secondMenu.getMenuName());
                    secondTree.setIcon(secondMenu.getMenuIcon());
                    children.add(secondTree);
                }
                firstTree.setChildren(children);
            }
            result.add(firstTree);
        }
        //查找二级菜单
        return result;
    }


}
