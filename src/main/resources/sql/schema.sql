# 用户表
create table if not exists `user`
(
    `id`          bigint(20) unsigned not null auto_increment comment '主键',
    `username`   varchar(20)         not null comment '用户名',
    `phone`       varchar(12)         not null comment '电话',
    `password`    varchar(64)         not null comment '密码',
    `status`      tinyint(1) default 1 comment '账号状态 0封号1正常',
    `gender`      tinyint(1) default 2 comment '性别 0女 1男 2 保密',
    `email`       varchar(30) comment '邮箱',
    `id_number`   varchar(18) comment '身份证',
    `remark`      varchar(128) comment '备注',
    `create_time` datetime comment '创建时间',
    `update_time` datetime comment '修改时间',
    primary key (`id`)
) engine = InnoDB
  default charset = utf8 comment '用户表';
# token表
create table if not exists `token`
(
    `id`      bigint(20) unsigned not null auto_increment comment '主键',
    `phone`   varchar(12)         not null comment '电话',
    `token`   varchar(200)        not null comment 'token',
    primary key (id)
) engine = InnoDB
  default charset = utf8 comment 'token表';

# 角色表
CREATE TABLE IF NOT EXISTS `role`
(
    `id`          bigint(20) UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '主键',
    `role_name`   VARCHAR(20)                        NOT NULL COMMENT '角色名',
    `explain`     VARCHAR(128) COMMENT '备注',
    `create_time` DATETIME COMMENT '创建时间',
    `update_time` DATETIME COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT '角色表';
#用户角色表
create table if not exists `user_role`
(
    `id`          bigint(20) unsigned auto_increment not null comment '主键',
    `user_id`     bigint(20) unsigned                not null comment '用户主键',
    `role_id`     bigint(20) unsigned                not null comment '角色主键',
    `create_time` datetime comment '创建时间',
    `update_time` datetime comment '修改时间',
    primary key (`id`),
    foreign key (user_id) references user (id)
) ENGINE = InnoDB
  default charset = utf8 comment '用户角色表';
#资源表
create table if not exists `permission`
(
    `id`              bigint(20) unsigned auto_increment not null comment '主键',
    `type`            tinyint(2)                         not null comment '权限类型 1菜单 2功能',
    `icon`            varchar(250) comment '图标',
    `level`           int(11)                            not null comment '层级',
    `url`             varchar(250) comment '路径',
    `code`            varchar(250) comment '权限代码',
    `permission_name` varchar(20)                        not null comment '资源名称',
    `sort_num`        int(11) default 0 comment '排序字段 默认0',
    `parent_id`       bigint(20) comment '父菜单id',
    `remark`          varchar(128) comment '备注',
    `create_time`     datetime comment '创建时间',
    `update_time`     datetime comment '修改时间',
    primary key (id)
) engine = InnoDB
  default charset = utf8 comment '资源表';

#角色权限表
create table if not exists `role_permission`
(
    `id`            bigint(20) unsigned auto_increment not null comment '主键',
    `role_id`       bigint(20) unsigned                not null comment '用户主键',
    `permission_id` bigint(20) unsigned                not null comment '权限主键',
    `create_time`   datetime comment '创建时间',
    `update_time`   datetime comment '修改时间',
    primary key (id)
) engine = InnoDB
  default
      charset = utf8 comment '角色权限表';
