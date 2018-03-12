CREATE TABLE `system_menu` (
  `MENU_ID` varchar(50) NOT NULL COMMENT 'MENU_ID',
  `MENU_NAME` varchar(100) NOT NULL COMMENT '菜单名称',
  `MENU_PERMISSION` varchar(50) NOT NULL COMMENT '菜单权限编码 menu:一级菜单:二级菜单',
  `MENU_URL` varchar(100) DEFAULT NULL COMMENT '菜单跳转链接',
  `MENU_ICON` varchar(20) DEFAULT NULL COMMENT '菜单icon',
  `PARENT_MENU_ID` varchar(50) DEFAULT NULL COMMENT '上一级菜单id',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

INSERT INTO system_menu (MENU_ID, MENU_NAME, MENU_PERMISSION, MENU_URL, MENU_ICON, PARENT_MENU_ID, CREATE_TIME, UPDATE_TIME) VALUES ('1', '权限管理', 'auth:manage', null, '&#xe620;', '', null, null);
INSERT INTO system_menu (MENU_ID, MENU_NAME, MENU_PERMISSION, MENU_URL, MENU_ICON, PARENT_MENU_ID, CREATE_TIME, UPDATE_TIME) VALUES ('2', '菜单管理', 'auth:manage:menu', '/menuSupport/index', '&#xe60a;', '1', null, null);
INSERT INTO system_menu (MENU_ID, MENU_NAME, MENU_PERMISSION, MENU_URL, MENU_ICON, PARENT_MENU_ID, CREATE_TIME, UPDATE_TIME) VALUES ('3', '用户管理', 'auth:manage:user', '/userSupport/index', '&#xe612;', '1', null, null);
INSERT INTO system_menu (MENU_ID, MENU_NAME, MENU_PERMISSION, MENU_URL, MENU_ICON, PARENT_MENU_ID, CREATE_TIME, UPDATE_TIME) VALUES ('4', '角色管理', 'auth:manage:role', '/roleSupport/index', '&#xe6af;', '1', null, null);