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
    'status'      tinyint default 0 not null comment '角色状态',
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
    `level`           tinyint(2)                            not null comment '层级',
    `url`             varchar(250) comment '路径',
    `code`            varchar(250) comment '权限代码',
    `permission_name` varchar(20)                        not null comment '资源名称',
    `sort_num`        tinyint(3) default 0 comment '排序字段 默认0',
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

#验证码表
create table if not exists `sys_code`
(
    `id`          bigint(20) unsigned not null auto_increment comment '主键',
    `code`       varchar(4)         not null comment '验证码',
    `used`        tinyint(1) default 0 comment '使用状态 0未使用1已使用',
    `email`       varchar(30) comment '邮箱',
    `create_time` datetime comment '创建时间',
    `update_time` datetime comment '修改时间',
    primary key (`id`)
) engine = InnoDB
  default charset = utf8 comment '验证码表';

# 栏目表
create table if not exists `column_menu`
(
    `id`    bigint(20) unsigned not null auto_increment comment '主键',
    `column_name` varchar(20) not null comment '栏目名',
    primary key (`id`)
) engine = InnoDB
  default charset =utf8 comment '栏目表';

# 文章表
create table if not exists `article`
(
    `id`        bigint(20) unsigned not null auto_increment comment '主键',
    `title`     varchar(20) not null comment '标题',
    `author`    varchar(20) not null comment '作者',
    `create_time`   datetime comment '发布时间',
    `update_time`   datetime comment '修改时间',
    `article`   longtext not null comment '文章内容',
    `hits`  int comment '点击数',
    `post_num` int comment '评论数',
    `sort_id` bigint(20) unsigned        not null comment '所属栏目id',
    primary key (`id`),
    foreign key (sort_id) references column_menu (id)
) engine = InnoDB
  default charset = utf8 comment '文章表';

# 评论表
create table if not exists `comment_form`
(
    `id`    bigint(20) unsigned not null  auto_increment comment '主键',
    `username`  varchar(20) not null comment '评论人',
    `create_time` datetime comment '评论时间',
    `update_time` datetime comment '最后编辑时间',
    `article_id` bigint(20) unsigned not null comment '文章id',
    primary key (`id`),
    foreign key (article_id) references article (id)
)engine =InnoDB
default charset =utf8 comment '评论表'