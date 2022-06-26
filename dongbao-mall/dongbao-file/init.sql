create database mca_dongbao;

CREATE USER dongbao IDENTIFIED by 'dongbao';
GRANT ALL on mca_dongbao.* to 'dongbao'@'%';

CREATE TABLE `ums_member` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `username` varchar(64) DEFAULT NULL,
                              `password` varchar(64) DEFAULT NULL,
                              `icon` varchar(500) DEFAULT NULL COMMENT '头像',
                              `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
                              `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
                              `note` varchar(500) DEFAULT NULL COMMENT '备注信息',
                              `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
                              `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
                              `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
                              `status` int DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用',
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `un_name` (`username`) USING BTREE COMMENT '用户名唯一'
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb3 COMMENT='后台用户表';