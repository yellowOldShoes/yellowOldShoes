CREATE TABLE `menu` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                        `name` varchar(20) NOT NULL DEFAULT '' COMMENT '菜单名称',
                        `describe_` varchar(200) DEFAULT '' COMMENT '功能描述',
                        `is_public` TINYINT(1) DEFAULT 0 COMMENT '是否公开菜单\r\n就是无需分配就可以访问的。所有人可见',
                        `path` varchar(255) DEFAULT '' COMMENT '对应路由path',
                        `component` varchar(255) DEFAULT NULL COMMENT '对应路由组件component',
                        `is_enable` TINYINT(1) DEFAULT 1 COMMENT '状态',
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
) ENGINE=InnoDB AUTO_INCREMENT=680085395794296902 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='菜单';

CREATE TABLE `user` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                        `account` varchar(30) NOT NULL COMMENT '账号',
                        `name` varchar(50) DEFAULT NULL COMMENT '姓名',
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
                        `password_expire_time` datetime DEFAULT NULL COMMENT '密码过期时间',
                        `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
                        `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
                        `create_user` bigint(20) DEFAULT '0' COMMENT '创建人id',
                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                        `update_user` bigint(20) DEFAULT '0' COMMENT '更新人id',
                        `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                        PRIMARY KEY (`id`) USING BTREE,
                        UNIQUE KEY `UN_ACCOUNT` (`account`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=683356335357559138 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='用户';

#角色表
CREATE TABLE `role` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                        `name` varchar(100) NOT NULL COMMENT '角色名称',
                        `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
                        `parent_role_id` bigint(20) DEFAULT NULL COMMENT '父角色ID，支持角色继承',
                        `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        PRIMARY KEY (`id`),
                        FOREIGN KEY (`parent_role_id`) REFERENCES `role` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#用户角色表
CREATE TABLE `role_user` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                             `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                             `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                             `valid_from` datetime DEFAULT NULL COMMENT '角色分配给用户的开始时间',
                             `valid_to` datetime DEFAULT NULL COMMENT '角色分配给用户的结束时间',
                             `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '分配时间',
                             PRIMARY KEY (`id`),
                             FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
                             FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



CREATE TABLE `resource` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '资源ID',
                            `name` varchar(50) NOT NULL COMMENT '资源名称',
                            `type` varchar(20) NOT NULL COMMENT '资源类型，如按钮、接口等',
                            `description` varchar(255) DEFAULT NULL COMMENT '资源描述',
                            `path` varchar(255) DEFAULT NULL COMMENT '资源路径（如果是接口）',
                            `method` varchar(10) DEFAULT NULL,  -- 请求方法 GET, POST, PUT, DELETE
                            `menu_id` bigint(20) DEFAULT NULL COMMENT '关联菜单ID（如果是菜单项）',
                            `is_enable` bit(1) DEFAULT b'1' COMMENT '是否启用',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源表';

CREATE TABLE `role_permission` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色与权限关联ID',
                                   `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                   `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
                                   `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   PRIMARY KEY (`id`),
                                   FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE,
                                   FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';


CREATE TABLE `permission_resource` (
                                       `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限与资源关联ID',
                                       `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
                                       `resource_id` bigint(20) NOT NULL COMMENT '资源ID',
                                       `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       PRIMARY KEY (`id`),
                                       FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE CASCADE,
                                       FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限与资源关联表';

CREATE TABLE `permission_menu` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限与菜单关联ID',
                                   `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
                                   `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
                                   `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   PRIMARY KEY (`id`),
                                   FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE CASCADE,
                                   FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限与菜单关联表';


CREATE TABLE `permission` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
                              `name` varchar(100) NOT NULL COMMENT '权限名称',
                              `code` varchar(50) NOT NULL COMMENT '权限代码，唯一标识',
                              `description` varchar(255) DEFAULT NULL COMMENT '权限描述',
                              `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `UN_CODE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `casbin_rule` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                               `ptype` varchar(100) NOT NULL COMMENT '策略类型（如 p 或 g）',
                               `v0` varchar(100) DEFAULT NULL COMMENT '主体（用户/角色）或用户-角色映射',
                               `v1` varchar(100) DEFAULT NULL COMMENT '资源（如 URL、菜单标识）',
                               `v2` varchar(100) DEFAULT NULL COMMENT '操作（read、write、delete）',
                               `v3` varchar(100) DEFAULT NULL COMMENT '可扩展字段（比如 domain）',
                               `v4` varchar(100) DEFAULT NULL COMMENT '可扩展字段',
                               `v5` varchar(100) DEFAULT NULL COMMENT '可扩展字段',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Casbin 策略表';



