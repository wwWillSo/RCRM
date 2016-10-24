/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2016-10-23 16:15:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for complaint
-- ----------------------------
DROP TABLE IF EXISTS `complaint`;
CREATE TABLE `complaint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of complaint
-- ----------------------------
INSERT INTO `complaint` VALUES ('1', '3', 'adasdds');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `mobile` varchar(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `sex` enum('MAN','WOMAN') NOT NULL DEFAULT 'MAN',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', 'asd', 'asd', 'asd', '2016-10-22', 'MAN');
INSERT INTO `customer` VALUES ('3', 'suzhenwei', '13531897648', '244864220@qq.com', '2016-10-03', 'MAN');

-- ----------------------------
-- Table structure for dish_menu
-- ----------------------------
DROP TABLE IF EXISTS `dish_menu`;
CREATE TABLE `dish_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dish_id` int(11) NOT NULL,
  `dish_name` varchar(255) NOT NULL,
  `dish_type` enum('西餐','中餐') NOT NULL DEFAULT '中餐',
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dish_menu
-- ----------------------------
INSERT INTO `dish_menu` VALUES ('1', '101', '扒饭', '西餐', '100.00');
INSERT INTO `dish_menu` VALUES ('2', '102', '意面', '西餐', '100.00');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `order_list` varchar(255) DEFAULT NULL,
  `order_time` datetime DEFAULT NULL,
  `expenditure` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1', '3', '扒饭', '2016-10-23 14:48:07', '100.00');
INSERT INTO `order` VALUES ('2', '3', '意面、扒饭', '2016-10-24 14:48:31', '200.00');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('5', 'admin', 'KgNkOkOwNNoY53UeHACr6w==', 'admin');
INSERT INTO `user` VALUES ('6', '123213', 'blBcjlT3ribP0kq6S8msCQ==', 'user');
