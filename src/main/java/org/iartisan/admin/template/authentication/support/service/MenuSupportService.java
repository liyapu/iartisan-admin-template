package org.iartisan.admin.template.authentication.support.service;

import org.iartisan.admin.template.authentication.support.PermissionType;
import org.iartisan.admin.template.authentication.support.dbm.mapper.SystemMenuMapper;
import org.iartisan.admin.template.authentication.support.dbm.mapper.SystemRolePermissionMapper;
import org.iartisan.admin.template.authentication.support.dbm.model.SystemMenuDO;
import org.iartisan.admin.template.authentication.support.dbm.model.SystemRolePermissionDO;
import org.iartisan.admin.template.authentication.support.service.entity.MenuEntity;
import org.iartisan.runtime.bean.Page;
import org.iartisan.runtime.bean.PageWrapper;
import org.iartisan.runtime.jdbc.PageHelper;
import org.iartisan.runtime.utils.CollectionUtil;
import org.iartisan.runtime.utils.UUIDUtil;
import org.iartisan.runtime.web.authentication.MenuTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author King
 * @since 2018/2/26
 */
@Service
public class MenuSupportService {

    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Autowired
    private RoleSupportService roleSupportService;

    @Autowired
    private SystemRolePermissionMapper systemRolePermissionMapper;

    public PageWrapper<MenuTree> getMenuPageData(Page page, String menuName) {
        SystemMenuDO systemMenuDO = new SystemMenuDO();
        systemMenuDO.setMenuName(menuName);
        PageWrapper<SystemMenuDO> dbResult = PageHelper.getPageData(systemMenuMapper, page, systemMenuDO);
        PageWrapper<MenuTree> result = new PageWrapper<>(dbResult.getPage());
        List<MenuTree> pageList = new ArrayList<>();
        for (SystemMenuDO menuDO : dbResult.getDataList()) {
            MenuTree tree = new MenuTree();
            tree.setId(menuDO.getMenuId());
            tree.setTitle(menuDO.getMenuName());
            tree.setHref(menuDO.getMenuUrl());
            tree.setIcon(menuDO.getMenuIcon());
            pageList.add(tree);
        }
        result.setDataList(pageList);
        return result;
    }

    public List<MenuTree> getFirstMenus() {
        List<SystemMenuDO> firstMenuList = systemMenuMapper.selectFirstMenus(new SystemMenuDO());
        List<MenuTree> result = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(firstMenuList)) {
            for (SystemMenuDO systemMenuDO : firstMenuList) {
                MenuTree tree = new MenuTree();
                tree.setId(systemMenuDO.getMenuId());
                tree.setTitle(systemMenuDO.getMenuName());
                result.add(tree);
            }
        }
        return result;
    }

    public void addMenu(MenuEntity menuEntity) {
        SystemMenuDO dbInsert = new SystemMenuDO();
        dbInsert.setMenuId(UUIDUtil.shortId());
        dbInsert.setMenuName(menuEntity.getMenuName());
        dbInsert.setParentMenuId(menuEntity.getParentMenuId());
        dbInsert.setMenuUrl(menuEntity.getMenuUrl());
        dbInsert.setMenuIcon(menuEntity.getMenuIcon());
        dbInsert.setMenuPermission(menuEntity.getMenuPermission());
        systemMenuMapper.insert(dbInsert);
    }

    public void deleteData(String menuId) {
        systemMenuMapper.deleteById(menuId);
    }

    public MenuEntity getMenuById(String menuId) {
        SystemMenuDO dbResult = systemMenuMapper.selectById(menuId);
        MenuEntity result = new MenuEntity();
        result.setParentMenuId(dbResult.getParentMenuId());
        result.setMenuUrl(dbResult.getMenuUrl());
        result.setMenuName(dbResult.getMenuName());
        result.setMenuIcon(dbResult.getMenuIcon());
        result.setMenuPermission(dbResult.getMenuPermission());
        result.setParentMenuId(dbResult.getParentMenuId());
        result.setMenuId(dbResult.getMenuId());
        return result;
    }

    public void modifyData(MenuEntity menuEntity) {
        SystemMenuDO dbModify = new SystemMenuDO();
        dbModify.setMenuId(menuEntity.getMenuId());
        dbModify.setMenuName(menuEntity.getMenuName());
        dbModify.setMenuPermission(menuEntity.getMenuPermission());
        dbModify.setMenuUrl(menuEntity.getMenuUrl());
        dbModify.setMenuIcon(menuEntity.getMenuIcon());
        systemMenuMapper.updateById(dbModify);
    }

    //获取菜单列表
    public List<MenuTree> getMenus(String userId) {
        List<String> roles = roleSupportService.getRoleIdsByUserId(userId);
        if (null == roles) {
            return null;
        }
        SystemRolePermissionDO dbQuery = new SystemRolePermissionDO();
        dbQuery.setRoleIds(roles);
        dbQuery.setPermissionType(PermissionType.m.name());
        List<String> menuIds = systemRolePermissionMapper.selectPermissions(dbQuery);
        if (null == menuIds) {
            return null;
        }
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
            firstTree.setPermission(firstMenu.getMenuPermission());
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
                    secondTree.setPermission(secondMenu.getMenuPermission());
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
