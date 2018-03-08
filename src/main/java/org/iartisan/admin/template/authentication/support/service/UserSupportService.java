package org.iartisan.admin.template.authentication.support.service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.iartisan.admin.template.authentication.support.dbm.mapper.*;
import org.iartisan.admin.template.authentication.support.dbm.model.*;
import org.iartisan.admin.template.authentication.support.service.entity.RoleEntity;
import org.iartisan.admin.template.authentication.support.service.entity.UserEntity;
import org.iartisan.admin.template.authentication.support.service.entity.UserRoleEntity;
import org.iartisan.runtime.bean.Page;
import org.iartisan.runtime.bean.PageWrapper;
import org.iartisan.runtime.constants.DataStatus;
import org.iartisan.runtime.jdbc.PageHelper;
import org.iartisan.runtime.utils.CollectionUtil;
import org.iartisan.runtime.utils.StringUtils;
import org.iartisan.runtime.utils.UUIDUtil;
import org.iartisan.runtime.web.authentication.MenuTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 管理员管理
 * crud
 *
 * @author King
 * @since 2018/2/22
 */
@Service
public class UserSupportService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Autowired
    private SystemRolePermissionMapper systemRolePermissionMapper;

    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Autowired
    private RoleSupportService roleSupportService;

    public SystemUserDO login(String userName, String userPwd) {
        SystemUserDO dbQuery = new SystemUserDO();
        dbQuery.setUserName(userName);
        dbQuery.setUserPwd(userPwd.toLowerCase());
        return systemUserMapper.selectOne(dbQuery);
    }


    //获取菜单列表
    public List<MenuTree> getMenus(String userId) {
        List<String> roles = roleSupportService.getRoleIdsByUserId(userId);
        if (null == roles) {
            return null;
        }
        SystemRolePermissionDO dbQuery = new SystemRolePermissionDO();
        dbQuery.setRoleIds(roles);
        List<String> menuIds = systemRolePermissionMapper.selectPermissions(dbQuery);
        SystemMenuDO systemMenuDO = new SystemMenuDO();
        systemMenuDO.setMenuIds(menuIds);
        List<SystemMenuDO> firstMenus = systemMenuMapper.selectFirstMenus(systemMenuDO);
        //得到二级菜单
        if (firstMenus == null) {
            return null;
        }
        List<MenuTree> result = new ArrayList<>();
        for (SystemMenuDO firstMenu : firstMenus) {
            MenuTree firstTree = new MenuTree();
            firstTree.setTitle(firstMenu.getMenuName());
            firstTree.setIcon(firstMenu.getMenuIcon());
            firstTree.setHref(firstMenu.getMenuUrl());
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
                    secondTree.setHref(secondMenu.getMenuUrl());
                    children.add(secondTree);
                }
                firstTree.setChildren(children);
            }
            result.add(firstTree);
        }
        //查找二级菜单
        return result;
    }


    public PageWrapper<UserEntity> getUserPageData(Page page, String userName) {
        SystemUserDO userDO = new SystemUserDO();
        if (StringUtils.isNotEmpty(userName)) {
            userDO.setUserName(userName);
        }
        PageWrapper<SystemUserDO> dbResult = PageHelper.getPageData(systemUserMapper, page, userDO);
        PageWrapper<UserEntity> result = new PageWrapper<>(dbResult.getPage());
        List<UserEntity> pageList = new ArrayList<>();
        for (SystemUserDO o : dbResult.getDataList()) {
            UserEntity bean = new UserEntity();
            bean.setUserName(o.getUserName());
            bean.setUserId(o.getUserId());
            bean.setUserStatus(o.getStatus());
            bean.setCreateDate(o.getCreateTime());
            pageList.add(bean);
        }
        result.setDataList(pageList);
        return result;
    }


    public void addUser(UserEntity userEntity) {
        SystemUserDO dbInsert = new SystemUserDO();
        dbInsert.setUserId(UUIDUtil.timeBaseId());
        dbInsert.setUserName(userEntity.getUserName());
        dbInsert.setStatus(userEntity.getUserStatus());
        dbInsert.setCreateTime(new Date());
        dbInsert.setUserPwd("123456");
        //设置默认密码
        //插入角色列表
        try {
            systemUserMapper.insert(dbInsert);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeStatus(String userId, String status) {
        //删除用户 非物理删除
        SystemUserDO dbModify = new SystemUserDO();
        dbModify.setUserId(userId);
        dbModify.setStatus(status);
        dbModify.setCreateTime(new Date());
        systemUserMapper.updateById(dbModify);
    }

}
