/*
Navicat MySQL Data Transfer

Source Server         : 本地mysql
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : taco_cloud

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2021-04-09 17:47:51
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
INSERT INTO `order_tacos` VALUES ('061d69bd-8db9-49f9-a9e2-edf9a16a1113', '491a6468-4f38-4281-82cf-a18846263d7b');
INSERT INTO `order_tacos` VALUES ('441d032f-7e02-4196-a3d2-ae16b8016e6c', '491a6468-4f38-4281-82cf-a18846263d7b');
INSERT INTO `order_tacos` VALUES ('441d032f-7e02-4196-a3d2-ae16b8016e6c', '91eb515e-c28a-470d-adfb-1b0bb59e9aff');

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
INSERT INTO `taco` VALUES ('491a6468-4f38-4281-82cf-a18846263d7b', 'mashirro', '2021-04-09 17:34:02');
INSERT INTO `taco` VALUES ('91eb515e-c28a-470d-adfb-1b0bb59e9aff', 'mashirro007', '2021-04-09 17:36:48');
INSERT INTO `taco` VALUES ('c3aec134-f157-4c24-8692-b22bb4cca093', 'mashirro', '2021-04-09 15:53:05');

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
INSERT INTO `tacoorder` VALUES ('061d69bd-8db9-49f9-a9e2-edf9a16a1113', '陈先生', '陈加敦', '成都', '', '400213', '', '', '', '2021-04-09 17:34:35');
INSERT INTO `tacoorder` VALUES ('441d032f-7e02-4196-a3d2-ae16b8016e6c', '陈先生2号', '陈加敦', '成都', '', '400213', '', '', '', '2021-04-09 17:37:14');
INSERT INTO `tacoorder` VALUES ('8afcbfd7-7719-45b4-8fa4-1c5bd5686e20', '陈先生', '陈加敦', '成都', '', '400213', '', '', '', '2021-04-09 16:09:19');

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
INSERT INTO `taco_ingredients` VALUES ('c3aec134-f157-4c24-8692-b22bb4cca093', 'COTO');
INSERT INTO `taco_ingredients` VALUES ('491a6468-4f38-4281-82cf-a18846263d7b', 'COTO');
INSERT INTO `taco_ingredients` VALUES ('91eb515e-c28a-470d-adfb-1b0bb59e9aff', 'CARN');
