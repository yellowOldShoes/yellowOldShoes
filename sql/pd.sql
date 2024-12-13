# user_organization----------------组织表
# user_occupation------------岗位表
# user---------------用户表
# role---------------角色表
# resource-----------资源表
# menu---------------菜单表
# user_role----------用户角色关系表
# role_authority-----角色权限关系表
# role_org-----------角色组织关系表

-- ----------------------------
-- Table structure for `user_occupation`
-- ----------------------------
DROP TABLE IF EXISTS `user_occupation`;
CREATE TABLE `user_occupation` (
   `id` bigint(20) NOT NULL COMMENT 'ID',
   `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
   `org_id` bigint(20) DEFAULT '0' COMMENT '组织ID\n#c_core_org',
   `status` bit(1) DEFAULT b'1' COMMENT '状态',
   `describe_` varchar(255) DEFAULT '' COMMENT '描述',
   `create_time` datetime DEFAULT NULL,
   `create_user` bigint(20) DEFAULT NULL,
   `update_time` datetime DEFAULT NULL,
   `update_user` bigint(20) DEFAULT NULL,
   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='岗位';


DROP TABLE IF EXISTS `user_organization`;
CREATE TABLE `user_organization` (
   `id` bigint(20) NOT NULL COMMENT 'ID',
   `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
   `abbreviation` varchar(255) DEFAULT '' COMMENT '简称',
   `parent_id` bigint(20) DEFAULT '0' COMMENT '父ID',
   `tree_path` varchar(255) DEFAULT ',' COMMENT '树结构',
   `sort_value` int(11) DEFAULT '1' COMMENT '排序',
   `status` bit(1) DEFAULT b'1' COMMENT '状态',
   `describe_` varchar(255) DEFAULT '' COMMENT '描述',
   `create_time` datetime DEFAULT NULL,
   `create_user` bigint(20) DEFAULT NULL,
   `update_time` datetime DEFAULT NULL,
   `update_user` bigint(20) DEFAULT NULL,
   PRIMARY KEY (`id`) USING BTREE,
   FULLTEXT KEY `FU_PATH` (`tree_path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='组织';


DROP TABLE IF EXISTS `user_common_opt_log`;
CREATE TABLE `user_common_opt_log` (
     `id` bigint(20) NOT NULL COMMENT '主键',
     `request_ip` varchar(50) DEFAULT '' COMMENT '操作IP',
     `type` varchar(5) DEFAULT 'OPT' COMMENT '日志类型\n#LogType{OPT:操作类型;EX:异常类型}',
     `user_name` varchar(50) DEFAULT '' COMMENT '操作人',
     `description` varchar(255) DEFAULT '' COMMENT '操作描述',
     `class_path` varchar(255) DEFAULT '' COMMENT '类路径',
     `action_method` varchar(50) DEFAULT '' COMMENT '请求方法',
     `request_uri` varchar(50) DEFAULT '' COMMENT '请求地址',
     `http_method` varchar(10) DEFAULT 'GET' COMMENT '请求类型\n#HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}',
     `params` longtext COMMENT '请求参数',
     `result` longtext COMMENT '返回值',
     `ex_desc` longtext COMMENT '异常详情信息',
     `ex_detail` longtext COMMENT '异常描述',
     `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
     `finish_time` timestamp NULL DEFAULT NULL COMMENT '完成时间',
     `consuming_time` bigint(20) DEFAULT '0' COMMENT '消耗时间',
     `ua` varchar(500) DEFAULT '' COMMENT '浏览器',
     `create_time` datetime DEFAULT NULL,
     `create_user` bigint(20) DEFAULT NULL,
     PRIMARY KEY (`id`) USING BTREE,
     KEY `index_type` (`type`) USING BTREE COMMENT '日志类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统日志';


DROP TABLE IF EXISTS `user_common_login_log`;
CREATE TABLE `user_common_login_log` (
   `id` bigint(20) NOT NULL COMMENT '主键',
   `request_ip` varchar(50) DEFAULT '' COMMENT '操作IP',
   `user_id` bigint(20) DEFAULT NULL COMMENT '登录人ID',
   `user_name` varchar(50) DEFAULT NULL COMMENT '登录人姓名',
   `account` varchar(30) DEFAULT '' COMMENT '登录人账号',
   `description` varchar(255) DEFAULT '' COMMENT '登录描述',
   `login_date` date DEFAULT NULL COMMENT '登录时间',
   `ua` varchar(500) DEFAULT '0' COMMENT '浏览器请求头',
   `browser` varchar(100) DEFAULT NULL COMMENT '浏览器名称',
   `browser_version` varchar(255) DEFAULT NULL COMMENT '浏览器版本',
   `operating_system` varchar(100) DEFAULT NULL COMMENT '操作系统',
   `location` varchar(50) DEFAULT '' COMMENT '登录地点',
   `create_time` datetime DEFAULT NULL,
   `create_user` bigint(20) DEFAULT NULL,
   PRIMARY KEY (`id`) USING BTREE,
   KEY `IDX_BROWSER` (`browser`) USING BTREE,
   KEY `IDX_OPERATING` (`operating_system`) USING BTREE,
   KEY `IDX_LOGIN_DATE` (`login_date`,`account`) USING BTREE,
   KEY `IDX_ACCOUNT_IP` (`account`,`request_ip`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='登录日志';


DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
     `id` bigint(20) NOT NULL,
     `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色ID\n#c_auth_role',
     `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID\n#c_core_accou',
     `create_user` bigint(20) DEFAULT NULL COMMENT '创建人ID',
     `create_time` datetime DEFAULT NULL COMMENT '创建时间',
     PRIMARY KEY (`id`) USING BTREE,
     KEY `IDX_KEY` (`role_id`,`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='角色分配\r\n账号角色绑定';

/**
  颁发口令的人的口令为准
 */
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` bigint(20) NOT NULL COMMENT 'ID',
    `account` varchar(30) NOT NULL COMMENT '账号',
    `name` varchar(50) NOT NULL COMMENT '姓名',
    `org_id` bigint(20) DEFAULT NULL COMMENT '组织ID\n#c_core_org',
    `station_id` bigint(20) DEFAULT NULL COMMENT '岗位ID\n#c_core_station',
    `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
    `mobile` varchar(20) DEFAULT '' COMMENT '手机',
    `sex` varchar(1) DEFAULT 'N' COMMENT '性别\n#Sex{W:女;M:男;N:未知}',
    `status` bit(1) DEFAULT b'0' COMMENT '启用状态 1启用 0禁用',
    `avatar` varchar(255) DEFAULT '' COMMENT '头像',
    `work_describe` varchar(255) DEFAULT '' COMMENT '工作描述\r\n比如：  市长、管理员、局长等等   用于登陆展示',
    `password_error_last_time` datetime DEFAULT NULL COMMENT '最后一次输错密码时间',
    `password_error_num` int(11) DEFAULT '0' COMMENT '密码错误次数',
    `pass_code` varchar(50) default '' comment '通行口令',
    `password_expire_time` datetime DEFAULT NULL COMMENT '密码过期时间',
    `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
    `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
    `create_user` bigint(20) DEFAULT '0' COMMENT '创建人id',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_user` bigint(20) DEFAULT '0' COMMENT '更新人id',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `UN_ACCOUNT` (`account`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='用户';


DROP TABLE IF EXISTS `role_org`;
CREATE TABLE `role_org` (
    `id` bigint(20) NOT NULL COMMENT 'ID',
    `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID\n#c_auth_role',
    `org_id` bigint(20) DEFAULT NULL COMMENT '部门ID\n#c_core_org',
    `create_time` datetime DEFAULT NULL,
    `create_user` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='角色组织关系';

DROP TABLE IF EXISTS `role_authority`;
CREATE TABLE `role_authority` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `authority_id` bigint(20) NOT NULL COMMENT '权限id\n#c_auth_resource\n#c_auth_menu',
  `authority_type` varchar(10) NOT NULL DEFAULT 'MENU' COMMENT '权限类型\n#AuthorizeType{MENU:菜单;RESOURCE:资源;}',
  `role_id` bigint(20) NOT NULL COMMENT '角色id\n#c_auth_role',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `IDX_KEY` (`role_id`,`authority_type`,`authority_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='角色的资源';

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
    `id` bigint(20) NOT NULL,
    `name` varchar(30) NOT NULL DEFAULT '' COMMENT '角色名称',
    `code` varchar(20) DEFAULT '' COMMENT '角色编码',
    `describe_` varchar(100) DEFAULT '' COMMENT '功能描述',
    `status` bit(1) DEFAULT b'1' COMMENT '状态',
    `readonly` bit(1) DEFAULT b'0' COMMENT '是否内置角色',
    `create_user` bigint(20) DEFAULT '0' COMMENT '创建人id',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_user` bigint(20) DEFAULT '0' COMMENT '更新人id',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `UN_CODE` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='角色';

DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
    `id` bigint(20) NOT NULL COMMENT 'ID',
    `code` varchar(150) DEFAULT '' COMMENT '资源编码\n规则：\n链接：\n数据列：\n按钮：',
    `name` varchar(150) NOT NULL DEFAULT '' COMMENT '接口名称',
    `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID\n#c_auth_menu',
    `method` varchar(10) DEFAULT NULL,
    `url` varchar(255) DEFAULT NULL,
    `describe_` varchar(255) DEFAULT '' COMMENT '接口描述',
    `create_user` bigint(20) DEFAULT NULL COMMENT '创建人id',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_user` bigint(20) DEFAULT NULL COMMENT '更新人id',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `UN_CODE` (`code`) USING BTREE COMMENT '编码唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='资源';

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `name` varchar(20) NOT NULL DEFAULT '' COMMENT '菜单名称',
    `describe_` varchar(200) DEFAULT '' COMMENT '功能描述',
    `is_public` bit(1) DEFAULT b'0' COMMENT '是否公开菜单\r\n就是无需分配就可以访问的。所有人可见',
    `path` varchar(255) DEFAULT '' COMMENT '对应路由path',
    `component` varchar(255) DEFAULT NULL COMMENT '对应路由组件component',
    `is_enable` bit(1) DEFAULT b'1' COMMENT '状态',
    `sort_value` int(11) DEFAULT '1' COMMENT '排序',
    `icon` varchar(255) DEFAULT '' COMMENT '菜单图标',
    `group_` varchar(20) DEFAULT '' COMMENT '菜单分组',
    `parent_id` bigint(20) DEFAULT '0' COMMENT '父级菜单id',
    `create_user` bigint(20) DEFAULT NULL COMMENT '创建人id',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_user` bigint(20) DEFAULT NULL COMMENT '更新人id',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `INX_STATUS` (`is_enable`,`is_public`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='菜单';
