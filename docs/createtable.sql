use meifenqi;
set names utf8;

#sys_user表
CREATE TABLE `sys_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '帐户ID',
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT 'username,maybe empty',
  `realname` varchar(32) NOT NULL DEFAULT '' COMMENT 'realname,maybe empty',
  `mobile` varchar(20) NOT NULL DEFAULT '' COMMENT 'mobile',
  `role_id` bigint(20) unsigned NOT NULL default 0 comment '角色ID',
  `status` int(4) default 1 comment '状态',
  `created` datetime NOT NULL COMMENT 'sysuser_create_time',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY uniq_uname(`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '系统用户表';

insert into sys_user values (1, "yongshan.xing", "邢永山", "18612258336", "1", "1","2015-10-14 11:54:38", "2015-10-14 14:29:16");
insert into sys_user values (2, "hui.zhang", "张辉", "15910812061", "1", "1","2015-10-14 11:54:38", "2015-10-14 14:29:16");

#sys_passports表记录登录票据信息
CREATE TABLE `sys_passport` (
  `uid` bigint(20) NOT NULL COMMENT 'user id',
  `ticket` varchar(32) NOT NULL DEFAULT '' COMMENT 'login ticket',
  `created_at` datetime NOT NULL COMMENT 'ticket created time',
  `expired_at` datetime NOT NULL COMMENT 'ticket expired time',
  `actived_at` datetime DEFAULT NULL COMMENT 'ticket last actived time',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT 'md5(plainpasswd+salt)',
  PRIMARY KEY (`uid`),
  KEY `uid_ticket_expire` (`uid`,`ticket`,`expired_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into sys_passport values (1, "b7cf1f249a3e9bc7de63f448d902f552", "2015-09-23 01:14:04", "2015-10-29 08:23:44", "2015-09-23 00:00:00", "14bc9cd1705c7d7fc8985a88d6b36bc9");
insert into sys_passport values (2, "b7cf1f249a3e9bc7de63f448d902f552", "2015-09-23 01:14:04", "2015-10-29 08:23:44", "2015-09-23 00:00:00", "14bc9cd1705c7d7fc8985a88d6b36bc9");

#op_logs操作记录表
CREATE TABLE `op_logs` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user` varchar(32) NOT NULL DEFAULT '' COMMENT 'username',
  `type` varchar(100) NOT NULL DEFAULT '' COMMENT 'type',
  `description` varchar(1000) NOT NULL COMMENT '操作描述',
  `ip` varchar(32) DEFAULT NULL COMMENT '操作ip',
  `created_at` datetime NOT NULL COMMENT 'created time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#用户扩展表
CREATE TABLE `users_extend` (
  `uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '帐户ID',
  `invite_code` varchar(50) NOT NULL DEFAULT '' COMMENT '邀请码',
  `is_bind` tinyint(1) NOT NULL DEFAULT '0'COMMENT '是否绑定邀请码',
  `open_id` varchar(300) NOT NULL DEFAULT '',
  `remark` varchar(300) NOT NULL DEFAULT '',
  `created_at` datetime NOT NULL COMMENT '用户额度创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  UNIQUE KEY `uniq_uid` (`uid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='额度表';

#sys_acl操作记录表
CREATE TABLE `sys_acl` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT 'parent id',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT 'username',
  `url` varchar(300) NOT NULL DEFAULT '' COMMENT 'url',
  `type` int(4) NOT NULL COMMENT '类型1为menu2为其它url等',
  `index` int(10) NOT NULL COMMENT '操作描述',
  `created_at` datetime NOT NULL COMMENT 'created time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into sys_acl values(1, 0, "首页", "/", 1, 1, "2015-10-12 18:50:00");
insert into sys_acl values(2, 0, "商品模块", "/sell/", 1, 2, "2015-10-12 18:50:00");
insert into sys_acl values(3, 2, "商品列表", "/sell/items/", 1, 3, "2015-10-12 18:50:00");
insert into sys_acl values(4, 0, "医院模块", "/hospital/", 1, 4, "2015-10-12 18:50:00");
insert into sys_acl values(5, 4, "医院列表", "/hospital/list/", 1, 5, "2015-10-12 18:50:00");
insert into sys_acl values(6, 0, "订单模块", "/order/", 1, 6, "2015-10-12 18:50:00");
insert into sys_acl values(7, 6, "订单管理", "/order/list/", 1, 7, "2015-10-12 18:50:00");
insert into sys_acl values(8, 6, "院方诊单", "/order/consult/", 1, 7, "2015-10-12 18:50:00");
insert into sys_acl values(17, 6, "院方订单详情", "/order/view/", 2, 7, "2015-10-12 18:50:00");
insert into sys_acl values(9, 6, "订单编辑", "/order/edit/", 2, 8, "2015-10-12 18:50:00");
insert into sys_acl values(10, 0, "系统用户", "/sysuser/", 1, 9, "2015-10-12 18:50:00");
insert into sys_acl values(11, 10, "用户列表", "/sysuser/list/", 1, 10, "2015-10-12 18:50:00");
insert into sys_acl values(12, 10, "添加用户", "/sysuser/add/", 1, 11, "2015-10-12 18:50:00");
insert into sys_acl values(13, 10, "删除用户", "/sysuser/delete/", 2, 12, "2015-10-12 18:50:00");
insert into sys_acl values(14, 0, "系统模块", "/system/", 1, 21, "2015-10-12 18:50:00");
insert into sys_acl values(15, 14, "系统配置", "/system/config/", 1, 22, "2015-10-12 18:50:00");
insert into sys_acl values(16, 14, "业务配置", "/system/business/", 1, 23, "2015-10-12 18:50:00");
insert into sys_acl values(18, 0, "微信模块", "/wechat/", 1, 13, "2015-10-12 18:50:00");
insert into sys_acl values(19, 18, "消息列表", "/wechat/message/", 1, 14, "2015-10-12 18:50:00");
insert into sys_acl values(20, 18, "微信配置", "/wechat/config/", 1, 15, "2015-10-12 18:50:00");
insert into sys_acl values(21, 18, "消息查看", "/wechat/view/", 1, 16, "2015-10-12 18:50:00");

insert into sys_acl values(22, 10, "角色列表", "/sysuser/role/", 1, 30, "2015-10-12 18:50:00");
insert into sys_acl values(23, 10, "权限管理", "/sysuser/permission/", 1, 31, "2015-10-12 18:50:00");
insert into sys_acl values(24, 2, "添加商品", "/sell/items/add/", 2, 31, "2015-10-12 18:50:00");

#sys_role表
CREATE TABLE `sys_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `rolename` varchar(100) NOT NULL DEFAULT '' COMMENT 'rolename',
  `roledesc` varchar(300) NOT NULL DEFAULT '' COMMENT 'roledesc',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into sys_role values (0, "公共", "开放给所有后台人员的权限");
insert into sys_role values (1, "管理员", "系统管理员");
insert into sys_role values (2, "运营", "运营");
insert into sys_role values (3, "风控", "风控");
insert into sys_role values (4, "财务", "财务");
insert into sys_role values (5, "面签师", "风控面签师");
insert into sys_role values (6, "医院财务", "医院财务");
insert into sys_role values (7, "医院分诊", "医院分诊");

#sys_permission权限表
CREATE TABLE `sys_permission` (
  `acl` bigint(20) unsigned NOT NULL COMMENT 'acl',
  `role` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT 'role',
  UNIQUE KEY `uiq_acl_role` (`acl`,`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into sys_permission values (1, 0);
insert into sys_permission values (2, 0);
insert into sys_permission values (3, 0);
insert into sys_permission values (4, 0);
insert into sys_permission values (5, 0);
insert into sys_permission values (6, 1);
insert into sys_permission values (6, 6);
insert into sys_permission values (6, 7);
insert into sys_permission values (7, 0);
insert into sys_permission values (8, 1);
insert into sys_permission values (8, 6);
insert into sys_permission values (8, 7);
insert into sys_permission values (9, 0);
insert into sys_permission values (10, 0);
insert into sys_permission values (11, 0);
insert into sys_permission values (12, 0);
insert into sys_permission values (13, 0);
insert into sys_permission values (14, 1);
insert into sys_permission values (15, 1);
insert into sys_permission values (16, 1);
insert into sys_permission values (17, 0);
insert into sys_permission values (18, 0);
insert into sys_permission values (19, 0);
insert into sys_permission values (20, 0);
insert into sys_permission values (21, 0);

insert into sys_permission values (28, 1);
insert into sys_permission values (29, 1);
insert into sys_permission values (30, 1);

#system_config系统配置表
CREATE TABLE `system_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `key` varchar(100) NOT NULL COMMENT 'parent id',
  `value` varchar(300) NOT NULL DEFAULT '' COMMENT 'username',
  `desc` varchar(300) NOT NULL DEFAULT '' COMMENT 'url',
  `index` int(10) NOT NULL COMMENT '操作描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_key`(`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '系统配置表';


CREATE TABLE `product_img` (
`id`  bigint(100) UNSIGNED NOT NULL AUTO_INCREMENT ,
`pid`  bigint(100) NOT NULL ,
`img`  varchar(100) NOT NULL DEFAULT '' COMMENT '图片' ,
`desc`  varchar(100) NOT NULL DEFAULT '' COMMENT '详情' ,
`index`  tinyint(4) NOT NULL DEFAULT 0 ,
`flag`  tinyint(4) NULL ,
PRIMARY KEY (`id`),
UNIQUE INDEX `uniq_pidimg` (`pid`, `img`) USING BTREE 
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '产品图片表';

ALTER TABLE `product_detail`
ADD COLUMN `cure_means`  varchar(1000) NOT NULL DEFAULT '' AFTER `body`;

ALTER TABLE `product_detail`
ADD COLUMN `cure_dur`  varchar(1000) NOT NULL DEFAULT '' AFTER `cure_means`;

ALTER TABLE `product_detail`
ADD COLUMN `cure_hospital`  varchar(1000) NOT NULL DEFAULT '' AFTER `cure_dur`;

ALTER TABLE `product_detail`
ADD COLUMN `recover_dur`  varchar(1000) NOT NULL DEFAULT '' AFTER `cure_hospital`;

ALTER TABLE `product_detail`
ADD COLUMN `merit`  varchar(1000) NOT NULL DEFAULT '' AFTER `recover_dur`;

ALTER TABLE `product_detail`
ADD COLUMN `cure_method`  varchar(1000) NOT NULL DEFAULT '' AFTER `merit`;

ALTER TABLE `product_detail`
ADD COLUMN `taboo_crowd`  varchar(1000) NOT NULL DEFAULT '' AFTER `cure_method`;

ALTER TABLE `product_detail`
ADD COLUMN `cure_num`  varchar(1000) NOT NULL DEFAULT '' AFTER `taboo_crowd`;

ALTER TABLE `product_detail`
ADD COLUMN `anes_method`  varchar(1000) NOT NULL DEFAULT '' AFTER `cure_num`;

ALTER TABLE `product_detail`
ADD COLUMN `doctor_level`  varchar(1000) NOT NULL DEFAULT '' AFTER `anes_method`;

ALTER TABLE `product_detail`
ADD COLUMN `cure_cycle`  varchar(1000) NOT NULL DEFAULT '' AFTER `doctor_level`;


ALTER TABLE `product`
ADD COLUMN `type2`  varchar(1000) NOT NULL DEFAULT '' AFTER `type`;

ALTER TABLE `product_detail`
ADD COLUMN `warning`  varchar(1000) NOT NULL DEFAULT '' AFTER `type2`;

ALTER TABLE `product_detail`
ADD COLUMN `crowd`  varchar(1000) NOT NULL DEFAULT '' AFTER `warning`;

ALTER TABLE `product`
ADD COLUMN `is_fq`  tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否分期' AFTER `type2`;



CREATE TABLE `home_classify` (
`id`  int UNSIGNED NOT NULL AUTO_INCREMENT ,
`name`  varchar(100) NOT NULL DEFAULT '' COMMENT '名称' ,
`url`  varchar(100) NOT NULL DEFAULT '' COMMENT 'url' ,
`index`  int(4) NOT NULL DEFAULT 0 COMMENT '排序' ,
PRIMARY KEY (`id`)
);

