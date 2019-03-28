/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : lucky_share

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-03-28 17:24:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_article`
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(64) DEFAULT NULL,
  `intro` varchar(1024) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `download_url` varchar(256) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_article
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_exchange`
-- ----------------------------
DROP TABLE IF EXISTS `tb_exchange`;
CREATE TABLE `tb_exchange` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `aid` int(11) NOT NULL,
  `volume` int(11) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_exchange
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_notice`
-- ----------------------------
DROP TABLE IF EXISTS `tb_notice`;
CREATE TABLE `tb_notice` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `title` varchar(256) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_notice
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_sign`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sign`;
CREATE TABLE `tb_sign` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `volume` int(11) NOT NULL,
  `sign_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_sign
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `open_id` varchar(50) NOT NULL COMMENT 'open id',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `avatar_url` varchar(512) DEFAULT NULL COMMENT '头像',
  `gender` int(11) DEFAULT NULL COMMENT '性别',
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_openid` (`open_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('0000000001', 'db0584ef675d41328098cf04d0585a65', 'Kevin.Chen', 'https://www.baidu.com/img/bd_logo1.png', '1', '2019-03-26 10:26:19');
