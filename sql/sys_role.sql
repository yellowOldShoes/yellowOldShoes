-- 用户表
CREATE TABLE users (
   id INT AUTO_INCREMENT PRIMARY KEY,
   username VARCHAR(50) NOT NULL UNIQUE,
   password VARCHAR(255) NOT NULL,
   email VARCHAR(100) NOT NULL UNIQUE,
   is_verified BOOLEAN DEFAULT FALSE,  -- 新增字段：标记邮箱是否验证
   status INT DEFAULT 1,               -- 新增字段：用户状态（1 - 启用，0 - 禁用）
   created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
   updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   password_updated_at DATETIME DEFAULT NULL, -- 新增字段：密码最后更新时间
);


-- 角色表
CREATE TABLE roles (
   id INT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(50) NOT NULL UNIQUE,
   parent_role_id INT,
   description VARCHAR(255),            -- 修改字段类型，避免过长的描述
   status INT DEFAULT 1,                -- 新增字段：角色状态（1 - 启用，0 - 禁用）
   created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
   updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   FOREIGN KEY (parent_role_id) REFERENCES roles(id)
);


-- permissions 表
CREATE TABLE permissions (
     id INT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(50) NOT NULL UNIQUE,    -- 权限名称
     description VARCHAR(255),            -- 权限描述
     type VARCHAR(50),                    -- 权限类型（页面、操作等）
     resource VARCHAR(255),               -- 资源名称，如菜单名、按钮等
     action VARCHAR(50),                  -- 操作类型（如：view, create, update, delete）
     status INT DEFAULT 1,                -- 权限状态（启用或禁用）
     created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
     updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


-- role_permissions 表
CREATE TABLE role_permissions (
  role_id INT NOT NULL,
  permission_id INT NOT NULL,
  PRIMARY KEY (role_id, permission_id),
  FOREIGN KEY (role_id) REFERENCES roles(id),
  FOREIGN KEY (permission_id) REFERENCES permissions(id)
);

-- user_roles 表
CREATE TABLE user_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- menus 表
CREATE TABLE menus (
   id INT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(100) NOT NULL,
   path VARCHAR(255) UNIQUE,            -- 菜单路径（路由）
   type VARCHAR(20) NOT NULL CHECK (type IN ('menu', 'button')), -- 菜单类型（菜单或按钮）
   parent_id INT,                       -- 父级菜单ID
   permission VARCHAR(50),              -- 该菜单对应的权限标识
   status INT DEFAULT 1,                -- 菜单状态（启用或禁用）
   `order` INT DEFAULT 0,             -- 新增字段：菜单排序
   created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
   updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   FOREIGN KEY (parent_id) REFERENCES menus(id)
);



-- role_menu 表
CREATE TABLE role_menu (
   role_id INT NOT NULL,
   menu_id INT NOT NULL,
   PRIMARY KEY (role_id, menu_id),
   FOREIGN KEY (role_id) REFERENCES roles(id),
   FOREIGN KEY (menu_id) REFERENCES menus(id)
);
