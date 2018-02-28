package org.iartisan.admin.template.authentication.support.service;

import org.iartisan.admin.template.authentication.support.dbm.mapper.SystemMenuMapper;
import org.iartisan.admin.template.authentication.support.dbm.model.SystemMenuDO;
import org.iartisan.runtime.bean.Page;
import org.iartisan.runtime.bean.PageWrapper;
import org.iartisan.runtime.jdbc.PageHelper;
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
}
