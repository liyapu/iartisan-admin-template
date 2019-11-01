DROP database IF EXISTS `iartisan_admin_template`;
CREATE DATABASE `iartisan_admin_template` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';

DROP TABLE IF EXISTS `biz_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `biz_dept` (
  `DEPT_ID` varchar(48) NOT NULL COMMENT '主键',
  `DEPT_NAME` varchar(50) NOT NULL COMMENT '部门名',
  `DEPT_LEADER` varchar(32) DEFAULT NULL COMMENT '部门领导',
  `DEPT_PATH` varchar(50) DEFAULT NULL COMMENT '部门树',
  `DEPT_PARENT` varchar(50) DEFAULT NULL COMMENT '上一级部门',
  `STATUS` varchar(1) NOT NULL DEFAULT 'E' COMMENT '数据状态',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`DEPT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biz_dept`
--

LOCK TABLES `biz_dept` WRITE;
/*!40000 ALTER TABLE `biz_dept` DISABLE KEYS */;
INSERT INTO `biz_dept` VALUES ('1','iartisan',NULL,'1',NULL,'E',NULL,NULL),('201911013267','产品销售',NULL,'1-201911015536-201911013267','201911015536','E','2019-11-01 19:45:15','2019-11-01 19:45:15'),('201911014671','开发组',NULL,'1-201911017968-201911014671','201911017968','E','2019-11-01 19:44:38','2019-11-01 19:44:38'),('201911015433','测试组',NULL,'1-201911017968-201911015433','201911017968','E','2019-11-01 19:44:45','2019-11-01 19:44:45'),('201911015536','销售部',NULL,'1-201911015536','1','E','2019-11-01 19:45:00','2019-11-01 19:45:00'),('201911016909','创意销售',NULL,'1-201911015536-201911016909','201911015536','E','2019-11-01 19:45:24','2019-11-01 19:45:24'),('201911017968','研发部',NULL,'1-201911017968','1','E','2019-11-01 19:44:25','2019-11-01 19:44:25');
/*!40000 ALTER TABLE `biz_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `biz_staff`
--

DROP TABLE IF EXISTS `biz_staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `biz_staff` (
  `STAFF_ID` varchar(48) NOT NULL COMMENT '主键',
  `STAFF_NAME` varchar(50) NOT NULL COMMENT '员工姓名',
  `STAFF_STATUS` varchar(1) DEFAULT NULL COMMENT 'Y:在职 N离职',
  `STAFF_DEPT` varchar(48) DEFAULT NULL COMMENT '员工所在部门',
  `STATUS` varchar(1) NOT NULL DEFAULT 'E' COMMENT '数据状态',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`STAFF_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biz_staff`
--

LOCK TABLES `biz_staff` WRITE;
/*!40000 ALTER TABLE `biz_staff` DISABLE KEYS */;
INSERT INTO `biz_staff` VALUES ('20191101156021652554827236780225','小李开','Y','201911014671','E','2019-11-01 19:46:23',NULL),('20191101781387025436866870464105','小张开','Y','201911014671','E','2019-11-01 19:46:16',NULL);
/*!40000 ALTER TABLE `biz_staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_log`
--

DROP TABLE IF EXISTS `system_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USER_ID` varchar(48) NOT NULL COMMENT '用户ID',
  `USER_NAME` varchar(50) NOT NULL COMMENT '用户名',
  `METHOD` varchar(150) DEFAULT NULL COMMENT '操作方法',
  `METHOD_DESC` varchar(50) DEFAULT NULL COMMENT '操作方法说明',
  `IP` varchar(17) DEFAULT NULL COMMENT '操作IP',
  `START_TIME` datetime DEFAULT NULL COMMENT '发起时间',
  `END_TIME` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_log`
--

LOCK TABLES `system_log` WRITE;
/*!40000 ALTER TABLE `system_log` DISABLE KEYS */;
INSERT INTO `system_log` VALUES (19,'1','admin','org.iartisan.admin.template.controller.support.page.UserSupportController.index','用户管理初始页面','192.168.3.46','2019-11-01 19:38:27',NULL),(20,'1','admin','org.iartisan.admin.template.controller.support.page.RoleSupportController.index','角色管理初始页面','192.168.3.46','2019-11-01 19:38:28',NULL),(21,'1','admin','org.iartisan.admin.template.controller.support.page.MenuSupportController.index','菜单管理初始页面','192.168.3.46','2019-11-01 19:38:29',NULL),(22,'1','admin','org.iartisan.admin.template.controller.support.page.RoleSupportController.index','角色管理初始页面','192.168.3.46','2019-11-01 19:41:57',NULL),(23,'1','admin','org.iartisan.admin.template.controller.support.page.MenuSupportController.index','菜单管理初始页面','192.168.3.46','2019-11-01 19:42:16',NULL),(24,'1','admin','org.iartisan.admin.template.controller.support.page.RoleSupportController.index','角色管理初始页面','192.168.3.46','2019-11-01 19:42:21',NULL),(25,'1','admin','org.iartisan.admin.template.controller.support.page.UserSupportController.index','用户管理初始页面','192.168.3.46','2019-11-01 19:42:26',NULL),(26,'1','admin','org.iartisan.admin.template.controller.support.page.UserSupportController.index','用户管理初始页面','192.168.3.46','2019-11-01 19:47:01',NULL),(27,'1','admin','org.iartisan.admin.template.controller.support.page.RoleSupportController.index','角色管理初始页面','192.168.3.46','2019-11-01 19:47:04',NULL),(28,'1','admin','org.iartisan.admin.template.controller.support.page.RoleSupportController.index','角色管理初始页面','192.168.3.46','2019-11-01 19:47:11',NULL),(29,'1','admin','org.iartisan.admin.template.controller.support.page.MenuSupportController.index','菜单管理初始页面','192.168.3.46','2019-11-01 20:02:51',NULL),(30,'1','admin','org.iartisan.admin.template.controller.support.page.UserSupportController.index','用户管理初始页面','192.168.3.46','2019-11-01 20:02:52',NULL),(31,'1','admin','org.iartisan.admin.template.controller.support.page.RoleSupportController.index','角色管理初始页面','192.168.3.46','2019-11-01 20:02:53',NULL);
/*!40000 ALTER TABLE `system_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_menu`
--

DROP TABLE IF EXISTS `system_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_menu` (
  `MENU_ID` varchar(50) NOT NULL COMMENT 'MENU_ID',
  `MENU_NAME` varchar(100) NOT NULL COMMENT '菜单名称',
  `MENU_PERMISSION` varchar(50) NOT NULL COMMENT '菜单权限编码 menu:一级菜单:二级菜单',
  `MENU_URL` varchar(100) DEFAULT NULL COMMENT '菜单跳转链接',
  `MENU_ICON` varchar(40) DEFAULT NULL COMMENT '菜单icon',
  `PARENT_MENU_ID` varchar(50) DEFAULT NULL COMMENT '上一级菜单id',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_menu`
--

LOCK TABLES `system_menu` WRITE;
/*!40000 ALTER TABLE `system_menu` DISABLE KEYS */;
INSERT INTO `system_menu` VALUES ('1','权限管理','auth:manage','','layui-icon-set-fill','',NULL,NULL),('2','菜单管理','auth:manage:menu:index','/menuSupport/index','layui-icon-list','1',NULL,NULL),('201806285972','流程部署','activiti:deployment','/bpm/deployment/index','layui-icon-template-1','201806287855',NULL,NULL),('201806287855','模拟流程','activiti','','layui-icon-app','',NULL,NULL),('201907126892','类人事管理','hr:management','','layui-icon-user','',NULL,NULL),('201907176106','部门管理','dept','/hrDept/index','layui-icon-home','201907126892',NULL,NULL),('201907266729','员工管理','hr:Staff','/hrStaff/index','layui-icon-group','201907126892',NULL,NULL),('201908301154','新建事项','new:todo','bpm/demo/newItem/index','layui-icon-list','201806287855',NULL,NULL),('201908301765','待办任务','todo','bpm/demo/todoList','layui-icon-face-surprised','201806287855',NULL,NULL),('3','用户管理','auth:manage:user:index','/userSupport/index','layui-icon-friends','1',NULL,NULL),('4','角色管理','auth:manage:role:index','/roleSupport/index','layui-icon-face-smile','1',NULL,NULL),('5','日志管理','auth:log',NULL,'layui-icon-engine',NULL,NULL,NULL),('6','操作日志','auth:log:operation:index','/log/index','layui-icon-survey','5',NULL,NULL),('7','Druid监控','auth:log:druid','/druid/sql.html','layui-icon-chart','5',NULL,NULL),('8','日志图表','auth:log:charts:index','/logCharts/index','layui-icon-chart-screen','5',NULL,NULL);
/*!40000 ALTER TABLE `system_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_message`
--

DROP TABLE IF EXISTS `system_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_message` (
  `uuid` varchar(100) NOT NULL COMMENT '主键',
  `receiver_id` varchar(45) DEFAULT NULL COMMENT '接收者id',
  `receiver_name` varchar(45) DEFAULT NULL COMMENT '接收者name',
  `sender_id` varchar(45) DEFAULT NULL COMMENT '发送者id',
  `sender_name` varchar(45) DEFAULT NULL COMMENT '发送者name',
  `msg_type_id` varchar(45) DEFAULT NULL COMMENT '消息类型',
  `msg_type_desc` varchar(45) DEFAULT NULL COMMENT '消息类型说明',
  `msg_title` varchar(45) DEFAULT NULL COMMENT '消息标题',
  `msg_content` varchar(100) DEFAULT NULL COMMENT '消息内容',
  `msg_param` varchar(45) DEFAULT NULL COMMENT '消息参数',
  `is_read` varchar(45) DEFAULT NULL COMMENT '是否已读',
  `status` varchar(45) DEFAULT NULL COMMENT '数据状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_message`
--

LOCK TABLES `system_message` WRITE;
/*!40000 ALTER TABLE `system_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_resource`
--

DROP TABLE IF EXISTS `system_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_resource` (
  `RESOURCE_ID` varchar(48) NOT NULL COMMENT '主键',
  `RESOURCE_NAME` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `RESOURCE_PERMISSION` varchar(50) NOT NULL COMMENT '资源权限编码 resource:菜单id:资源id',
  `MENU_ID` varchar(50) NOT NULL COMMENT '对应的MENU_ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`RESOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_resource`
--

LOCK TABLES `system_resource` WRITE;
/*!40000 ALTER TABLE `system_resource` DISABLE KEYS */;
INSERT INTO `system_resource` VALUES ('1','添加页面','auth:manage:role:addDataPage','4',NULL,NULL),('10','添加页面','auth:manage:user:addDataPage','3',NULL,NULL),('11','添加','auth:manage:user:addData','3',NULL,NULL),('12','修改页面','auth:manage:user:modifyDataPage','3',NULL,NULL),('13','修改','auth:manage:user:modifyData','3',NULL,NULL),('14','删除','auth:manage:user:deleteData','3',NULL,NULL),('15','状态切换','auth:manage:user:changeStatus','3',NULL,NULL),('2','添加','auth:manage:role:addData','4',NULL,NULL),('3','详情页面','auth:manage:role:queryDetailPage','4',NULL,NULL),('4','删除','auth:manage:role:deleteData','4',NULL,NULL),('5','添加页面','auth:manage:menu:addDataPage','2',NULL,NULL),('6','添加','auth:manage:menu:addData','2',NULL,NULL),('7','修改页面','auth:manage:menu:modifyDataPage','2',NULL,NULL),('8','修改','auth:manage:menu:modifyData','2',NULL,NULL),('9','删除','auth:manage:menu:deleteData','2',NULL,NULL);
/*!40000 ALTER TABLE `system_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_role`
--

DROP TABLE IF EXISTS `system_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_role` (
  `ROLE_ID` varchar(48) NOT NULL COMMENT '主键id',
  `ROLE_NAME` varchar(50) NOT NULL COMMENT '角色名称',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_role`
--

LOCK TABLES `system_role` WRITE;
/*!40000 ALTER TABLE `system_role` DISABLE KEYS */;
INSERT INTO `system_role` VALUES ('1','admin','2019-11-01 19:47:11',NULL);
/*!40000 ALTER TABLE `system_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_role_permission`
--

DROP TABLE IF EXISTS `system_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_role_permission` (
  `ROLE_ID` varchar(50) NOT NULL COMMENT '角色ID',
  `PERMISSION_ID` varchar(50) NOT NULL COMMENT '资源id',
  `PERMISSION_TYPE` varchar(1) NOT NULL COMMENT 'm:菜单 r:resource',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_role_permission`
--

LOCK TABLES `system_role_permission` WRITE;
/*!40000 ALTER TABLE `system_role_permission` DISABLE KEYS */;
INSERT INTO `system_role_permission` VALUES ('1','1','m','2019-11-01 19:47:11'),('1','2','m','2019-11-01 19:47:11'),('1','5','r','2019-11-01 19:47:11'),('1','6','r','2019-11-01 19:47:11'),('1','7','r','2019-11-01 19:47:11'),('1','8','r','2019-11-01 19:47:11'),('1','9','r','2019-11-01 19:47:11'),('1','3','m','2019-11-01 19:47:11'),('1','10','r','2019-11-01 19:47:11'),('1','11','r','2019-11-01 19:47:11'),('1','12','r','2019-11-01 19:47:11'),('1','13','r','2019-11-01 19:47:11'),('1','14','r','2019-11-01 19:47:11'),('1','15','r','2019-11-01 19:47:11'),('1','4','m','2019-11-01 19:47:11'),('1','1','r','2019-11-01 19:47:11'),('1','2','r','2019-11-01 19:47:11'),('1','3','r','2019-11-01 19:47:11'),('1','4','r','2019-11-01 19:47:11'),('1','201806287855','m','2019-11-01 19:47:11'),('1','201806285972','m','2019-11-01 19:47:11'),('1','201908301154','m','2019-11-01 19:47:11'),('1','201908301765','m','2019-11-01 19:47:11'),('1','201907126892','m','2019-11-01 19:47:11'),('1','201907176106','m','2019-11-01 19:47:11'),('1','201907266729','m','2019-11-01 19:47:11'),('1','5','m','2019-11-01 19:47:11'),('1','6','m','2019-11-01 19:47:11'),('1','7','m','2019-11-01 19:47:11'),('1','8','m','2019-11-01 19:47:11');
/*!40000 ALTER TABLE `system_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_user`
--

DROP TABLE IF EXISTS `system_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_user` (
  `USER_ID` varchar(48) NOT NULL COMMENT '主键',
  `USER_NAME` varchar(50) NOT NULL COMMENT '用户名',
  `USER_PWD` varchar(50) NOT NULL COMMENT '密码',
  `STATUS` varchar(1) NOT NULL DEFAULT '0' COMMENT '员工状态',
  `STAFF_ID` varchar(50) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_user`
--

LOCK TABLES `system_user` WRITE;
/*!40000 ALTER TABLE `system_user` DISABLE KEYS */;
INSERT INTO `system_user` VALUES ('1','admin','21232f297a57a5a743894a0e4a801fc3','E',NULL,'2019-11-01 19:30:54',NULL);
/*!40000 ALTER TABLE `system_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_user_role`
--

DROP TABLE IF EXISTS `system_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_user_role` (
  `USER_ID` varchar(50) NOT NULL COMMENT '用户ID',
  `ROLE_ID` varchar(50) NOT NULL COMMENT '角色ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `system_user_role`
--
LOCK TABLES `system_user_role` WRITE;
/*!40000 ALTER TABLE `system_user_role` DISABLE KEYS */;
INSERT INTO `system_user_role` VALUES ('1','1',NULL,NULL);
/*!40000 ALTER TABLE `system_user_role` ENABLE KEYS */;
UNLOCK TABLES;
