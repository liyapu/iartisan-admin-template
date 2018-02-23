package org.iartisan.admin.template.authentication.support.dbm.mapper;


import org.iartisan.admin.template.authentication.support.dbm.model.SystemRolePermissionDO;
import org.iartisan.runtime.jdbc.MybatisBaseMapper;

import java.util.List;

/**
 * system_role_permission 表操作接口
 *
 * @author King
 */
public interface SystemRolePermissionMapper extends MybatisBaseMapper<SystemRolePermissionDO> {

    List<String> selectPermissions(SystemRolePermissionDO t);

}