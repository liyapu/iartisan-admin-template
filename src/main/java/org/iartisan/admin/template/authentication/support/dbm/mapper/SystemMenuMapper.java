package org.iartisan.admin.template.authentication.support.dbm.mapper;


import org.iartisan.admin.template.authentication.support.dbm.model.SystemMenuDO;
import org.iartisan.runtime.jdbc.MybatisBaseMapper;

import java.util.List;

/**
 * system_menu 表操作接口
 *
 * @author King
 */
public interface SystemMenuMapper extends MybatisBaseMapper<SystemMenuDO> {

    List<SystemMenuDO> selectFirstMenus(List<String> t);

    List<SystemMenuDO> selectSecondMenus(SystemMenuDO t);

}