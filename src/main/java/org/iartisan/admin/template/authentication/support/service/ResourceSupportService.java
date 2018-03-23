package org.iartisan.admin.template.authentication.support.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.iartisan.admin.template.authentication.support.PermissionType;
import org.iartisan.admin.template.authentication.support.dbm.mapper.SystemMenuMapper;
import org.iartisan.admin.template.authentication.support.dbm.mapper.SystemResourceMapper;
import org.iartisan.admin.template.authentication.support.dbm.mapper.SystemRolePermissionMapper;
import org.iartisan.admin.template.authentication.support.dbm.model.SystemMenuDO;
import org.iartisan.admin.template.authentication.support.dbm.model.SystemResourceDO;
import org.iartisan.admin.template.authentication.support.dbm.model.SystemRolePermissionDO;
import org.iartisan.admin.template.authentication.support.service.entity.ResourceEntity;
import org.iartisan.runtime.utils.CollectionUtil;
import org.iartisan.runtime.web.authentication.MenuTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * resource 服务
 *
 * @author King
 * @since 2018/3/1
 */
@Service
public class ResourceSupportService {

    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Autowired
    private SystemRolePermissionMapper systemRolePermissionMapper;

    @Autowired
    private RoleSupportService roleSupportService;

    @Autowired
    private MenuSupportService menuSupportService;


    public List<ResourceEntity> getResourceList() {
        List<ResourceEntity> result = new ArrayList<>();
        //加载菜单列表
        List<SystemMenuDO> firstMenuList = systemMenuMapper.selectFirstMenus(new SystemMenuDO());
        if (CollectionUtil.isNotEmpty(firstMenuList)) {
            for (SystemMenuDO o : firstMenuList) {
                ResourceEntity firstEntity = new ResourceEntity();
                firstEntity.setTitle(o.getMenuName());
                firstEntity.setValue(o.getMenuId());
                //查询二级菜单
                SystemMenuDO dbQuery = new SystemMenuDO();
                dbQuery.setParentMenuId(o.getMenuId());
                List<SystemMenuDO> secondMenuList = systemMenuMapper.selectSecondMenus(dbQuery);
                List<ResourceEntity> secondData = new ArrayList<>();
                if (CollectionUtil.isNotEmpty(secondMenuList)) {
                    for (SystemMenuDO second : secondMenuList) {
                        ResourceEntity secondEntity = new ResourceEntity();
                        secondEntity.setTitle(second.getMenuName());
                        secondEntity.setValue(second.getMenuId());
                        secondEntity.setData(new ArrayList<>());
                        secondData.add(secondEntity);
                        //添加resource的内容
                    }
                }
                firstEntity.setData(secondData);
                result.add(firstEntity);
            }
        }
        return result;
    }

    public List<ResourceEntity> getResourceListByRoleId(String roleId) {
        List<ResourceEntity> dbResult = getResourceList();
        if (CollectionUtil.isNotEmpty(dbResult)) {
            SystemRolePermissionDO dbQuery = new SystemRolePermissionDO();
            List<String> roles = new ArrayList<>();
            roles.add(roleId);
            dbQuery.setRoleIds(roles);
            List<String> permissionIds = systemRolePermissionMapper.selectPermissions(dbQuery);
            for (ResourceEntity resourceEntity : dbResult) {
                if (CollectionUtil.isNotEmpty(resourceEntity.getData())) {
                    List<ResourceEntity> data = resourceEntity.getData();
                    for (ResourceEntity datum : data) {
                        if (permissionIds.contains(datum.getValue())) {
                            datum.setChecked(true);
                        }
                    }
                } else {
                    if (permissionIds.contains(resourceEntity.getValue())) {
                        resourceEntity.setChecked(true);
                    }
                }
            }
        }
        return dbResult;
    }

    @Autowired
    private SystemResourceMapper systemResourceMapper;

    public Set<String> getPermissionsByUserId(String userId) {
        Set<String> result = new HashSet<>();
        //通过userId 获取用户roleId
        List<MenuTree> menuTreeList = menuSupportService.getMenus(userId);
        if (CollectionUtil.isNotEmpty(menuTreeList)) {
            for (MenuTree tree : menuTreeList) {
                List<MenuTree> secondTrees = tree.getChildren();
                if (CollectionUtil.isNotEmpty(secondTrees)) {
                    for (MenuTree secondTree : secondTrees) {
                        result.add(secondTree.getPermission());
                    }
                }
            }
        }
        List<String> roleIds = roleSupportService.getRoleIdsByUserId(userId);
        //查询resource
        SystemRolePermissionDO dbRQuery = new SystemRolePermissionDO();
        dbRQuery.setRoleIds(roleIds);
        dbRQuery.setPermissionType(PermissionType.r.name());
        List<String> dbResult = systemRolePermissionMapper.selectPermissions(dbRQuery);
        if (CollectionUtil.isNotEmpty(dbResult)) {
            for (String s : dbResult) {
                SystemResourceDO dbResourceQuery = new SystemResourceDO();
                dbResourceQuery.setResourceId(s);
                Wrapper<SystemResourceDO> resourceDOWrapper = new EntityWrapper<>(dbResourceQuery);
                List<SystemResourceDO> dbResourceResult = systemResourceMapper.selectList(resourceDOWrapper);
                if (CollectionUtil.isNotEmpty(dbResourceResult)) {
                    for (SystemResourceDO systemResourceDO : dbResourceResult) {
                        result.add(systemResourceDO.getResourcePermission());
                    }
                }
            }
        }
        return result;
    }
}
