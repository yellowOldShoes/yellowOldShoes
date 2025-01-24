

#----------------------------------------------------------------------------------------------------

CREATE TABLE `user` (
                        `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                        `account` VARCHAR(30) NOT NULL COMMENT '账号',
                        `name` VARCHAR(50) DEFAULT NULL COMMENT '姓名',
                        `department_id` BIGINT(20) DEFAULT NULL COMMENT '用户所属部门ID',
                        `position` VARCHAR(50) DEFAULT NULL COMMENT '用户职位',
                        `region_id` BIGINT(20) DEFAULT NULL COMMENT '用户所属地区ID',
                        `email` VARCHAR(255) DEFAULT NULL COMMENT '邮箱',
                        `mobile` VARCHAR(20) DEFAULT '' COMMENT '手机',
                        `status` TINYINT(1) DEFAULT 0 COMMENT '启用状态 1启用 0禁用',
                        `avatar` VARCHAR(255) DEFAULT '' COMMENT '头像',
                        `work_describe` VARCHAR(255) DEFAULT '' COMMENT '工作描述',
                        `password_hash` VARCHAR(255) NOT NULL COMMENT '密码哈希值',
                        `last_login_time` DATETIME DEFAULT NULL COMMENT '最后登录时间',
                        `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `UN_ACCOUNT` (`account`) USING BTREE,
                        KEY `IDX_EMAIL` (`email`),
                        KEY `IDX_MOBILE` (`mobile`),
                        FOREIGN KEY (`department_id`) REFERENCES `department`(`id`),
                        FOREIGN KEY (`region_id`) REFERENCES `region`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE `resource` (
                            `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '资源ID',
                            `name` VARCHAR(50) NOT NULL COMMENT '资源名称',
                            `type` VARCHAR(20) NOT NULL COMMENT '资源类型，如按钮、接口等',
                            `description` VARCHAR(255) DEFAULT NULL COMMENT '资源描述',
                            `path` VARCHAR(255) DEFAULT NULL COMMENT '资源路径（如果是接口）',
                            `method` VARCHAR(10) DEFAULT NULL COMMENT '请求方法（GET, POST, PUT, DELETE）',
                            `department_id` BIGINT(20) DEFAULT NULL COMMENT '资源所属部门ID',
                            `sensitivity` VARCHAR(20) DEFAULT 'low' COMMENT '资源敏感级别',
                            `is_enable` TINYINT(1) DEFAULT 1 COMMENT '是否启用',
                            `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`) USING BTREE,
                            KEY `IDX_TYPE` (`type`),
                            KEY `IDX_DEPARTMENT_ID` (`department_id`),
                            FOREIGN KEY (`department_id`) REFERENCES `department`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源表';

CREATE TABLE `menu` (
                        `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
                        `name` VARCHAR(50) NOT NULL COMMENT '菜单名称',
                        `description` VARCHAR(255) DEFAULT NULL COMMENT '菜单描述',
                        `path` VARCHAR(255) DEFAULT NULL COMMENT '菜单路径',
                        `parent_id` BIGINT(20) DEFAULT 0 COMMENT '父级菜单ID',
                        `sort_value` INT(11) DEFAULT 1 COMMENT '排序值',
                        `icon` VARCHAR(255) DEFAULT NULL COMMENT '菜单图标',
                        `is_enable` TINYINT(1) DEFAULT 1 COMMENT '是否启用',
                        `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        PRIMARY KEY (`id`) USING BTREE,
                        KEY `IDX_PARENT_ID` (`parent_id`),
                        FOREIGN KEY (`parent_id`) REFERENCES `menu`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';


CREATE TABLE `permission` (
                              `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
                              `name` VARCHAR(100) NOT NULL COMMENT '权限名称',
                              `code` VARCHAR(50) NOT NULL COMMENT '权限代码，唯一标识',
                              `description` VARCHAR(255) DEFAULT NULL COMMENT '权限描述',
                              `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `UN_CODE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

CREATE TABLE `policy` (
                          `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '策略ID',
                          `name` VARCHAR(100) NOT NULL COMMENT '策略名称',
                          `subject_condition` TEXT DEFAULT NULL COMMENT '主体条件（如用户属性）',
                          `resource_condition` TEXT DEFAULT NULL COMMENT '资源条件（如资源属性）',
                          `action` VARCHAR(50) NOT NULL COMMENT '允许的操作（如 read, write, delete）',
                          `effect` VARCHAR(10) DEFAULT 'allow' COMMENT '策略效果（允许或拒绝）',
                          `environment_condition` TEXT DEFAULT NULL COMMENT '环境条件（如时间、IP等）',
                          `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='策略规则表';

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Casbin rbac 策略表';

CREATE TABLE `attribute_mapping` (
                                     `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '属性ID',
                                     `attribute_key` VARCHAR(100) NOT NULL COMMENT '属性名称',
                                     `attribute_type` VARCHAR(20) NOT NULL COMMENT '属性类型（user, resource, environment）',
                                     `description` VARCHAR(255) DEFAULT NULL COMMENT '属性描述',
                                     PRIMARY KEY (`id`),
                                     UNIQUE KEY `UN_ATTRIBUTE_KEY` (`attribute_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='属性映射表';


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


CREATE TABLE `user_role` (
                             `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '用户角色关系ID',
                             `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
                             `role_id` BIGINT(20) NOT NULL COMMENT '角色ID',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `UN_USER_ROLE` (`user_id`, `role_id`),
                             FOREIGN KEY (`user_id`) REFERENCES `user`(`id`),
                             FOREIGN KEY (`role_id`) REFERENCES `role`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

CREATE TABLE `department` (
                              `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
                              `name` VARCHAR(100) NOT NULL COMMENT '部门名称',
                              `description` VARCHAR(255) DEFAULT NULL COMMENT '部门描述',
                              `parent_id` BIGINT(20) DEFAULT NULL COMMENT '父部门ID',
                              `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              PRIMARY KEY (`id`),
                              KEY `IDX_PARENT_ID` (`parent_id`),
                              FOREIGN KEY (`parent_id`) REFERENCES `department`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';


CREATE TABLE `region` (
                          `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '地区ID',
                          `name` VARCHAR(100) NOT NULL COMMENT '地区名称',
                          `parent_id` BIGINT(20) DEFAULT NULL COMMENT '父地区ID',
                          `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          PRIMARY KEY (`id`),
                          KEY `IDX_PARENT_ID` (`parent_id`),
                          FOREIGN KEY (`parent_id`) REFERENCES `region`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='地区表';

CREATE TABLE `role_permission` (
                                   `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                                   `role_id` BIGINT(20) NOT NULL,
                                   `permission_id` BIGINT(20) NOT NULL,
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `UN_ROLE_PERMISSION` (`role_id`, `permission_id`),
                                   FOREIGN KEY (`role_id`) REFERENCES `role`(`id`),
                                   FOREIGN KEY (`permission_id`) REFERENCES `permission`(`id`)
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