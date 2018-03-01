package org.iartisan.admin.template.authentication.support.service;

import org.iartisan.admin.template.authentication.support.dbm.mapper.SystemMenuMapper;
import org.iartisan.admin.template.authentication.support.dbm.model.SystemMenuDO;
import org.iartisan.admin.template.authentication.support.service.entity.ResourceEntity;
import org.iartisan.runtime.utils.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
                    }
                }
                firstEntity.setData(secondData);
                result.add(firstEntity);
            }
        }
        return result;
    }
}
