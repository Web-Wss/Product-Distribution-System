/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : pds

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 02/07/2024 14:49:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `address_id` int NOT NULL AUTO_INCREMENT COMMENT '收获地址id',
  `user_id` int NOT NULL COMMENT '用户id',
  `address_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货地址',
  `contacts` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系人',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系方式',
  PRIMARY KEY (`address_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------

-- ----------------------------
-- Table structure for administrators
-- ----------------------------
DROP TABLE IF EXISTS `administrators`;
CREATE TABLE `administrators`  (
  `administrators_id` int NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录密码',
  PRIMARY KEY (`administrators_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of administrators
-- ----------------------------

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `cart_id` int NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `user_id` int NOT NULL COMMENT '用户id',
  `goods_id` int NOT NULL COMMENT '商品id',
  `goods_number` int NOT NULL COMMENT '商品数量',
  `goods_selected_status` int NOT NULL COMMENT '选中状态（1-选中，2-未选中）',
  PRIMARY KEY (`cart_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------

-- ----------------------------
-- Table structure for distributor
-- ----------------------------
DROP TABLE IF EXISTS `distributor`;
CREATE TABLE `distributor`  (
  `distributor_id` int NOT NULL AUTO_INCREMENT COMMENT '分销商id',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录密码',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态（1-正常，2-冻结）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `commission_rate` int NOT NULL COMMENT '佣金比例',
  PRIMARY KEY (`distributor_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of distributor
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` int NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `goods_classification_id` int NOT NULL COMMENT '商品分类id',
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `goods_price` decimal(10, 2) NOT NULL COMMENT '商品价格',
  `goods_old_price` decimal(10, 2) NOT NULL COMMENT '商品市场价',
  `goods_company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品单位',
  `goods_total_inventory` int NOT NULL COMMENT '商品总库存',
  `goods_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品图片地址',
  `goods_video` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品视频地址',
  `goods_status` int NOT NULL DEFAULT 1 COMMENT '商品状态（1-正常，2-下架）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT '是否删除（1-未删除，2-已删除）',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for goods_classification
-- ----------------------------
DROP TABLE IF EXISTS `goods_classification`;
CREATE TABLE `goods_classification`  (
  `goods_classification_id` int NOT NULL AUTO_INCREMENT COMMENT '商品分类ID',
  `goods_classification_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品分类名称',
  PRIMARY KEY (`goods_classification_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_classification
-- ----------------------------

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '通知id',
  `notice_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通知内容',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `other_id` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `user_id` int NOT NULL COMMENT '用户id',
  `address_id` int NOT NULL COMMENT '地址id',
  `order_total_price` decimal(10, 2) NOT NULL COMMENT '订单总额',
  `order_discount_price` decimal(10, 2) NOT NULL COMMENT '优惠金额',
  `order_actual_payment` decimal(10, 2) NOT NULL COMMENT '实付金额',
  `order_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `order_remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注信息',
  `order_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单状态（1-已下单，2-分拣中，3-配送中，4-已完成）',
  `pay_type` int NULL DEFAULT NULL COMMENT '支付类型（1-微信，2-支付宝）',
  `completion_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交易完成时间',
  `distributor_id` int NOT NULL COMMENT '分销商id',
  PRIMARY KEY (`other_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for order_list
-- ----------------------------
DROP TABLE IF EXISTS `order_list`;
CREATE TABLE `order_list`  (
  `order_list_id` int NOT NULL AUTO_INCREMENT COMMENT '订单列表id',
  `order_id` int NOT NULL COMMENT '订单id',
  `goods_id` int NOT NULL COMMENT '商品id',
  `goods_number` int NOT NULL COMMENT '商品数量',
  `price_subtotal` decimal(10, 2) NOT NULL COMMENT '商品小计',
  PRIMARY KEY (`order_list_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_list
-- ----------------------------

-- ----------------------------
-- Table structure for reduction
-- ----------------------------
DROP TABLE IF EXISTS `reduction`;
CREATE TABLE `reduction`  (
  `full_reduction_id` int NOT NULL AUTO_INCREMENT COMMENT '满减id',
  `full_condition_price` decimal(10, 2) NOT NULL COMMENT '满减条件金额',
  `full_reduction_amount` decimal(10, 2) NOT NULL COMMENT '满减金额',
  PRIMARY KEY (`full_reduction_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reduction
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录密码',
  `user_status` int NOT NULL COMMENT '用户状态（1-正常，2-冻结）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
