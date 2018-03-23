CREATE TABLE `system_resource` (
  `RESOURCE_ID` varchar(48) NOT NULL COMMENT '主键',
  `RESOURCE_NAME` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `RESOURCE_PERMISSION` varchar(50) NOT NULL COMMENT '资源权限编码 resource:菜单id:资源id',
  `MENU_ID` varchar(50) NOT NULL COMMENT '对应的MENU_ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`RESOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源权限表';