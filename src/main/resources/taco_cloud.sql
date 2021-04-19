/*
Navicat MySQL Data Transfer

Source Server         : 本地mysql
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : taco_cloud

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2021-04-16 11:31:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ingredient
-- ----------------------------
DROP TABLE IF EXISTS `ingredient`;
CREATE TABLE `ingredient` (
  `id` varchar(38) NOT NULL COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '配料名',
  `type` varchar(50) DEFAULT NULL COMMENT '配料类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保存配料信息';

-- ----------------------------
-- Records of ingredient
-- ----------------------------
INSERT INTO `ingredient` VALUES ('CARN', '墨西哥小肉', 'PROTEIN');
INSERT INTO `ingredient` VALUES ('CHED', '切达干酪', 'CHEESE');
INSERT INTO `ingredient` VALUES ('COTO', '玉米饼', 'WRAP');
INSERT INTO `ingredient` VALUES ('FLTO', '小麦饼', 'WRAP');
INSERT INTO `ingredient` VALUES ('GRBF', '碎牛肉', 'PROTEIN');
INSERT INTO `ingredient` VALUES ('JACK', '蒙德勒杰克奶酪', 'CHEESE');
INSERT INTO `ingredient` VALUES ('LETC', '生菜', 'VEGGIES');
INSERT INTO `ingredient` VALUES ('SLSA', '萨尔萨辣酱', 'SAUCE');
INSERT INTO `ingredient` VALUES ('SRCR', '酸奶油', 'SAUCE');
INSERT INTO `ingredient` VALUES ('TMTO', '番茄丁', 'VEGGIES');

-- ----------------------------
-- Table structure for order_tacos
-- ----------------------------
DROP TABLE IF EXISTS `order_tacos`;
CREATE TABLE `order_tacos` (
  `tacoOrderId` varchar(38) NOT NULL,
  `tacoId` varchar(38) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Order中的每行数据都对应一行或多行,将订单和与之相关的taco映射在一起';

-- ----------------------------
-- Records of order_tacos
-- ----------------------------

-- ----------------------------
-- Table structure for taco
-- ----------------------------
DROP TABLE IF EXISTS `taco`;
CREATE TABLE `taco` (
  `id` varchar(38) NOT NULL COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '卷饼名',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保存taco设计相关的信息';

-- ----------------------------
-- Records of taco
-- ----------------------------

-- ----------------------------
-- Table structure for tacoorder
-- ----------------------------
DROP TABLE IF EXISTS `tacoorder`;
CREATE TABLE `tacoorder` (
  `id` varchar(38) NOT NULL,
  `deliveryName` varchar(50) DEFAULT NULL,
  `deliveryStreet` varchar(255) DEFAULT NULL,
  `deliveryCity` varchar(50) DEFAULT NULL,
  `deliveryState` varchar(2) DEFAULT NULL,
  `deliveryZip` varchar(10) DEFAULT NULL,
  `ccNumber` varchar(16) DEFAULT NULL,
  `ccExpiration` varchar(5) DEFAULT NULL,
  `ccCVV` varchar(3) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保存必要的订单细节';

-- ----------------------------
-- Records of tacoorder
-- ----------------------------

-- ----------------------------
-- Table structure for taco_ingredients
-- ----------------------------
DROP TABLE IF EXISTS `taco_ingredients`;
CREATE TABLE `taco_ingredients` (
  `tacoId` varchar(38) NOT NULL COMMENT 'taco主键',
  `ingredientId` varchar(38) NOT NULL COMMENT '配料主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='taco中的每行数据都对应一行或多行,将taco和与之相关的配料映射在一起';

-- ----------------------------
-- Records of taco_ingredients
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(38) NOT NULL COMMENT '主键',
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `phoneNumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'mashirro', '$2a$10$ZHrVFer2MV.jp1osOpslP.n4p46XFbWBFRu9fdSkUIPl5mh0T60R.', '陈加敦', '武汉', null, null, '1862790275');
INSERT INTO `user` VALUES ('e84d1ece-0072-407d-9707-84fb6dabc065', 'mashirro007', '$2a$10$1M58P6geG6N1f4WM46LJtehGR.ts87YlGF2dF6QAKq/RLcJIaZYl2', '陈加敦', '成都', '', '400213', null);
