/*
Navicat MySQL Data Transfer

Source Server         : locahost
Source Server Version : 50731
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2021-02-28 13:23:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `T_ORDER`;
CREATE TABLE `T_ORDER` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderno` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '订单编号',
  `userid` bigint(20) DEFAULT NULL COMMENT '用户id',
  `totalamount` decimal(12,2) DEFAULT NULL COMMENT '总金额',
  `payamount` decimal(12,2) DEFAULT NULL COMMENT '实际支付金额',
  `orderstatus` tinyint(1) DEFAULT NULL COMMENT '订单状态：0-未支付，1-已支付',
  `paytime` bigint(20) DEFAULT NULL COMMENT '支付时间',
  `createtime` bigint(20) DEFAULT NULL COMMENT '创建时间，时间戳',
  `updatetime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_ORDER_ORDERNO` (`orderno`),
  KEY `IDX_ORDER_USERID` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息表';

-- ----------------------------
-- Table structure for t_order item
-- ----------------------------
DROP TABLE IF EXISTS `T_ORDER ITEM`;
CREATE TABLE `T_ORDER ITEM` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderno` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '订单编号',
  `userid` bigint(20) DEFAULT NULL COMMENT '用户id',
  `productid` bigint(20) DEFAULT NULL COMMENT '商品id',
  `amount` decimal(12,2) DEFAULT NULL COMMENT '订单明细金额',
  `createtime` bigint(20) DEFAULT NULL COMMENT '创建时间，时间戳',
  `updatetime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `IDX_ORDER_ITME_ORDERNO` (`orderno`),
  KEY `IDX_ORDER_ITEM_USERID` (`userid`),
  KEY `IDX_ORDER_ITEM_PRODUCTID` (`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单明细表';

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `T_PRODUCT`;
CREATE TABLE `T_PRODUCT` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `productname` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '商品名称',
  `price` decimal(12,2) DEFAULT NULL COMMENT '商品价格',
  `productpic` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '商品图片',
  `createtime` bigint(20) DEFAULT NULL COMMENT '创建时间，时间戳',
  `updatetime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息表';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `T_USER`;
CREATE TABLE `T_USER` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '账号名称',
  `nickname` varchar(255) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '昵称',
  `password` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '密码',
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别：0-女，1-男',
  `phone` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '手机号',
  `createtime` bigint(20) DEFAULT NULL COMMENT '创建时间，时间戳',
  `updatetime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_USER_PHONE` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';
