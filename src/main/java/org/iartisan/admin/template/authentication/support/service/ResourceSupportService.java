package org.iartisan.admin.template.authentication.support.service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.iartisan.admin.template.authentication.support.PermissionType;
import org.iartisan.admin.template.authentication.support.dbm.mapper.SystemMenuMapper;
import org.iartisan.admin.template.authentication.support.dbm.mapper.SystemResourceMapper;
import org.iartisan.admin.template.authentication.support.dbm.mapper.SystemRolePermissionMapper;
import org.iartisan.admin.template.authentication.support.dbm.model.SystemMenuDO;
import org.iartisan.admin.template.authentication.support.dbm.model.SystemResourceDO;
import org.iartisan.admin.template.authentication.support.dbm.model.SystemRolePermissionDO;
import org.iartisan.admin.template.authentication.support.service.entity.ZTreeEntity;
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


    public static final String split = "|";

    public List<ZTreeEntity> getResourceTree() {
        List<ZTreeEntity> result = new ArrayList<>();
        //加载菜单列表
        List<SystemMenuDO> menuDOList = systemMenuMapper.selectList(Condition.EMPTY);
        if (CollectionUtil.isNotEmpty(menuDOList)) {
            for (SystemMenuDO systemMenuDO : menuDOList) {
                ZTreeEntity entity = new ZTreeEntity();
                entity.setId(systemMenuDO.getMenuId() + split + "m");
                entity.setpId(systemMenuDO.getParentMenuId() + split + "m");
                entity.setName(systemMenuDO.getMenuName());
                result.add(entity);
                //查询resource
                List<SystemResourceDO> resourceDOS = getResourceByMenuId(systemMenuDO.getMenuId());
                if (CollectionUtil.isNotEmpty(resourceDOS)) {
                    for (SystemResourceDO resourceDO : resourceDOS) {
                        ZTreeEntity resourceEntity = new ZTreeEntity();
                        resourceEntity.setId(resourceDO.getResourceId() + split + "r");
                        resourceEntity.setpId(systemMenuDO.getMenuId() + split + "m");
                        resourceEntity.setName(resourceDO.getResourceName());
                        result.add(resourceEntity);
                    }
                }
            }
        }
        return result;
    }


    private List<SystemResourceDO> getResourceByMenuId(String menuId) {
        SystemResourceDO resourceDO = new SystemResourceDO();
        resourceDO.setMenuId(menuId);
        List<SystemResourceDO> dbResult = systemResourceMapper.selectList(new EntityWrapper<>(resourceDO));
        return dbResult;
    }

    public List<ZTreeEntity> getResourceListByRoleId(String roleId, boolean chkDisabled) {
        List<ZTreeEntity> dbResult = getResourceTree();
        if (CollectionUtil.isNotEmpty(dbResult)) {
            SystemRolePermissionDO dbQuery = new SystemRolePermissionDO();
            dbQuery.setRoleId(roleId);
            List<SystemRolePermissionDO> permissionDOS = systemRolePermissionMapper.selectList(dbQuery);
            if (CollectionUtil.isNotEmpty(permissionDOS)) {
                for (ZTreeEntity entity : dbResult) {
                    //
                    for (SystemRolePermissionDO permissionDO : permissionDOS) {
                        if (entity.getId().equals(permissionDO.getPermissionId() + split + permissionDO.getPermissionType())) {
                            entity.setChecked(true);
                            // permissionDOS.remove(permissionDO);
                        }
                    }
                    entity.setChkDisabled(chkDisabled);
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
