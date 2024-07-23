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

 Date: 23/07/2024 16:22:54
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
  `is_default` int NOT NULL DEFAULT 0 COMMENT '默认地址（1-是，0-不是）',
  PRIMARY KEY (`address_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2025014 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (2025014, 1598545929, '苏州市', '测试', '13359074748', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of administrators
-- ----------------------------
INSERT INTO `administrators` VALUES (1, '管理员', '13359074748', '123456');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `cart_id` int NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `user_id` int NOT NULL COMMENT '用户id',
  `goods_id` int NOT NULL COMMENT '商品id',
  `goods_number` int NOT NULL COMMENT '商品数量',
  `goods_selected_status` int NOT NULL COMMENT '选中状态（1-选中，0-未选中）',
  `is_selected` int NOT NULL DEFAULT 1 COMMENT '是否可勾选（1-可以，0-不可以）',
  PRIMARY KEY (`cart_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1762107599 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (1762107603, 1598545929, 1, 1, 1, 1);
INSERT INTO `cart` VALUES (1762107604, 1598545929, 3, 1, 1, 1);

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
  `already_withdrawn` decimal(10, 2) NOT NULL COMMENT '已提现佣金',
  PRIMARY KEY (`distributor_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2025004 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of distributor
-- ----------------------------
INSERT INTO `distributor` VALUES (2025006, '分销商1', '18888888888', '123456', 1, '2024-07-23 14:48:01', 10, 0.00);

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
  `goods_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品图片地址',
  `goods_video` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品视频地址',
  `goods_status` int NOT NULL DEFAULT 1 COMMENT '商品状态（1-正常，2-下架）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_delete` int NOT NULL DEFAULT 1 COMMENT '是否删除（1-未删除，2-已删除）',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, 4, '星际导弹（每盒四根）', 15.00, 25.00, '4根/盒', 'http://ywesc-img.webwss.cn/yh/1.png', 'http://ywesc-img.webwss.cn/yh/1.mp4', 1, '2024-07-02 16:42:38', 1);
INSERT INTO `goods` VALUES (2, 5, '蝌蚪王', 10.00, 15.00, '盒', 'http://ywesc-img.webwss.cn/yh/2.jpg', 'http://ywesc-img.webwss.cn/yh/2.mp4', 1, '2024-07-02 16:43:34', 1);
INSERT INTO `goods` VALUES (3, 5, '小金鱼', 5.00, 8.00, '盒', 'http://ywesc-img.webwss.cn/yh/3.png', 'http://ywesc-http://ywesc-img.webwss.cn/yh/3.mp4', 1, '2024-07-02 16:44:44', 1);
INSERT INTO `goods` VALUES (4, 5, '小霸王砂炮（摔炮，内含12小盒）', 36.00, 45.00, '12盒/箱', 'http://ywesc-img.webwss.cn/yh/4.png', 'http://ywesc-img.webwss.cn/yh/4.mp4', 1, '2024-07-02 16:46:23', 1);
INSERT INTO `goods` VALUES (5, 4, '888型迷彩加特林', 55.00, 90.00, '根', 'http://ywesc-img.webwss.cn/yh/5.jpg', 'http://ywesc-img.webwss.cn/yh/5.mp4', 1, '2024-07-02 16:47:15', 1);
INSERT INTO `goods` VALUES (6, 4, '小豌豆自动旋转加特林（礼盒装|每盒4根）', 188.00, 240.00, '4根/盒', 'http://ywesc-img.webwss.cn/yh/6.png', 'http://ywesc-img.webwss.cn/yh/6.mp4', 1, '2024-07-02 16:47:45', 1);
INSERT INTO `goods` VALUES (7, 4, '1.5型彩色龙珠60型', 26.00, 35.00, '6根/盒', 'http://ywesc-img.webwss.cn/yh/8.jpg', 'http://ywesc-img.webwss.cn/yh/8.mp4', 1, '2024-07-02 16:49:00', 1);
INSERT INTO `goods` VALUES (8, 4, '388发格林机枪（加特林）', 40.00, 50.00, '1根/盒', 'http://ywesc-img.webwss.cn/yh/9.jpg', 'http://ywesc-img.webwss.cn/yh/9.mp4', 1, '2024-07-02 16:49:36', 1);
INSERT INTO `goods` VALUES (9, 4, '畅销加特林（1088型）', 58.00, 95.00, '个', 'http://ywesc-img.webwss.cn/yh/10.jpg', 'http://ywesc-img.webwss.cn/yh/10.mov', 1, '2024-07-02 16:50:03', 1);
INSERT INTO `goods` VALUES (10, 4, '蓝色海洋加特林', 88.00, 105.00, '个', 'http://ywesc-img.webwss.cn/yh/11.png', 'http://ywesc-img.webwss.cn/yh/11.mp4', 1, '2024-07-02 16:50:25', 1);
INSERT INTO `goods` VALUES (11, 1, '孔雀开屏（畅销款，效果贼好）', 18.00, 50.00, '个', 'http://ywesc-img.webwss.cn/yh/12.jpg', 'http://ywesc-img.webwss.cn/yh/12.mp4', 1, '2024-07-02 16:50:50', 1);
INSERT INTO `goods` VALUES (12, 1, '一夜暴富（效果多变）', 45.00, 90.00, '盒', 'http://ywesc-img.webwss.cn/yh/13.jpg', 'http://ywesc-img.webwss.cn/yh/13.mp4', 1, '2024-07-02 16:51:14', 1);
INSERT INTO `goods` VALUES (13, 3, '空中闪星（可飞天，效果好）', 10.00, 15.00, '6个/盒', 'http://ywesc-img.webwss.cn/yh/14.png', 'http://ywesc-img.webwss.cn/yh/14.mp4', 1, '2024-07-02 16:51:37', 1);
INSERT INTO `goods` VALUES (14, 1, '梦境花园', 25.00, 40.00, '4个/盒', 'http://ywesc-img.webwss.cn/yh/15.png', 'http://ywesc-img.webwss.cn/yh/15.mp4', 1, '2024-07-02 16:52:05', 1);
INSERT INTO `goods` VALUES (15, 3, '水母新秀', 30.00, 40.00, '盒', 'http://ywesc-img.webwss.cn/yh/16.jpg', 'http://ywesc-img.webwss.cn/yh/16.mp4', 1, '2024-07-02 16:52:29', 1);
INSERT INTO `goods` VALUES (16, 4, '干将莫邪（礼盒装加特林，送礼首选）', 168.00, 220.00, '2根/盒', 'http://ywesc-img.webwss.cn/yh/17.png', 'http://ywesc-img.webwss.cn/yh/17.mp4', 1, '2024-07-02 16:52:48', 1);
INSERT INTO `goods` VALUES (17, 1, '时空战车（效果变化多样）', 45.00, 110.00, '个', 'http://ywesc-img.webwss.cn/yh/18.jpg', 'http://ywesc-img.webwss.cn/yh/18.mp4', 1, '2024-07-02 16:53:28', 1);
INSERT INTO `goods` VALUES (18, 1, '恭喜发财（特大号烟花，仅有4个）', 99.00, 205.00, '1个/盒', 'http://ywesc-img.webwss.cn/yh/19.png', 'http://ywesc-img.webwss.cn/yh/19.mp4', 1, '2024-07-02 16:53:52', 1);
INSERT INTO `goods` VALUES (19, 4, '大号舞龙棒', 25.00, 48.00, '根', 'http://ywesc-img.webwss.cn/yh/20.png', 'http://ywesc-img.webwss.cn/yh/20.mp4', 1, '2024-07-02 16:54:14', 1);
INSERT INTO `goods` VALUES (20, 1, '鸿运当头（外观喜庆，过年必备）', 36.00, 50.00, '1个/盒', 'http://ywesc-img.webwss.cn/yh/21.jpg', 'http://ywesc-img.webwss.cn/yh/21.mp4', 1, '2024-07-02 16:54:40', 1);
INSERT INTO `goods` VALUES (21, 1, '明猩喷花（每盒5根）', 20.00, 55.00, '5根/盒', 'http://ywesc-img.webwss.cn/yh/22.jpg', 'http://ywesc-img.webwss.cn/yh/22.mp4', 1, '2024-07-02 16:55:02', 1);
INSERT INTO `goods` VALUES (22, 1, '潮蛙喷花（炫彩多姿）', 20.00, 60.00, '4个/盒', 'http://ywesc-img.webwss.cn/yh/23.png', 'http://ywesc-img.webwss.cn/yh/23.mp4', 1, '2024-07-02 16:55:40', 1);
INSERT INTO `goods` VALUES (23, 1, '36型迷你花（内部强推，效果贼好）', 40.00, 65.00, '个', 'http://ywesc-img.webwss.cn/yh/24.png', 'http://ywesc-img.webwss.cn/yh/24.mp4', 1, '2024-07-02 16:56:03', 1);
INSERT INTO `goods` VALUES (24, 4, '蓝火狙击枪（加特林）', 55.00, 140.00, '个', 'http://ywesc-img.webwss.cn/yh/25.png', 'http://ywesc-img.webwss.cn/yh/25.mp4', 1, '2024-07-02 16:56:29', 1);
INSERT INTO `goods` VALUES (25, 1, '童趣乐园（内含4个，性价比高）', 22.00, 40.00, '4个/盒', 'http://ywesc-img.webwss.cn/yh/27.jpg', 'http://ywesc-img.webwss.cn/yh/27.mp4', 1, '2024-07-02 16:56:54', 1);
INSERT INTO `goods` VALUES (26, 1, '乐哈哈（性价比极高）', 30.00, 60.00, '3个/盒', 'http://ywesc-img.webwss.cn/yh/28.png', 'http://ywesc-img.webwss.cn/yh/28.mp4', 1, '2024-07-02 16:57:21', 1);
INSERT INTO `goods` VALUES (27, 1, '三角斗士（三分钟）', 50.00, 85.00, '个', 'http://ywesc-img.webwss.cn/yh/29.png', 'http://ywesc-img.webwss.cn/yh/29.mp4', 1, '2024-07-02 16:57:43', 1);
INSERT INTO `goods` VALUES (28, 1, '贵族天地（内含6个，极具性价比）', 35.00, 68.00, '6个/盒', 'http://ywesc-img.webwss.cn/yh/30.png', 'http://ywesc-img.webwss.cn/yh/30.mp4', 1, '2024-07-02 16:58:04', 1);
INSERT INTO `goods` VALUES (29, 3, '水母王国', 22.00, 55.00, '盒', 'http://ywesc-img.webwss.cn/yh/31.png', 'http://ywesc-img.webwss.cn/yh/31.mp4', 1, '2024-07-02 16:58:25', 1);
INSERT INTO `goods` VALUES (30, 3, '36发空中花雨', 25.00, 36.00, '盒', 'http://ywesc-img.webwss.cn/yh/32.jpg', 'http://ywesc-img.webwss.cn/yh/32.mp4', 1, '2024-07-02 16:58:48', 1);
INSERT INTO `goods` VALUES (31, 4, '旋转木马（时空灯笼）', 20.00, 30.00, '个', 'http://ywesc-img.webwss.cn/yh/33.jpg', 'http://ywesc-img.webwss.cn/yh/33.mp4', 1, '2024-07-02 16:59:39', 1);
INSERT INTO `goods` VALUES (32, 4, '奉旨成龙（可燃放2分钟，超喜庆，可甩龙鞭）', 58.00, 110.00, '个', 'http://ywesc-img.webwss.cn/yh/34.jpg', 'http://ywesc-img.webwss.cn/yh/34.mp4', 1, '2024-07-02 17:00:05', 1);
INSERT INTO `goods` VALUES (33, 2, '赚转锦鲤（外观喜庆，附带旋转效果）', 58.00, 95.00, '个', 'http://ywesc-img.webwss.cn/yh/35.jpg', 'http://ywesc-img.webwss.cn/yh/35.mp4', 1, '2024-07-02 17:01:29', 1);
INSERT INTO `goods` VALUES (34, 1, '珍珠遍地（盒装）', 16.00, 25.00, '盒', 'http://ywesc-img.webwss.cn/yh/36.jpg', 'http://ywesc-img.webwss.cn/yh/36.mp4', 1, '2024-07-02 17:01:53', 1);
INSERT INTO `goods` VALUES (35, 1, '三色跳猫', 8.00, 12.00, '10个/盒', 'http://ywesc-img.webwss.cn/yh/37.png', 'http://ywesc-img.webwss.cn/yh/37.mp4', 1, '2024-07-02 17:02:23', 1);
INSERT INTO `goods` VALUES (36, 4, '如意金箍棒（孩子专属）', 18.00, 40.00, '个', 'http://ywesc-img.webwss.cn/yh/38.jpg', 'http://ywesc-img.webwss.cn/yh/38.mp4', 1, '2024-07-02 17:02:43', 1);
INSERT INTO `goods` VALUES (37, 4, '蓝骑士加特林(可燃放90秒，专属加特林，市场少见，先到先得)', 78.00, 128.00, '个', 'http://ywesc-img.webwss.cn/yh/39.jpg', 'http://ywesc-img.webwss.cn/yh/39.mp4', 1, '2024-07-02 17:03:07', 1);
INSERT INTO `goods` VALUES (38, 3, '020星际火箭', 22.00, 30.00, '盒', 'http://ywesc-img.webwss.cn/yh/40.png', 'http://ywesc-img.webwss.cn/yh/40.mp4', 1, '2024-07-02 17:03:29', 1);
INSERT INTO `goods` VALUES (39, 4, '金箍棒（伸缩）', 30.00, 58.00, '个', 'http://ywesc-img.webwss.cn/yh/41.png', 'http://ywesc-img.webwss.cn/yh/41.mp4', 1, '2024-07-02 17:03:51', 1);
INSERT INTO `goods` VALUES (40, 1, '萌宠来袭', 30.00, 40.00, '个', 'http://ywesc-img.webwss.cn/yh/42.png', 'http://ywesc-img.webwss.cn/yh/42.mp4', 1, '2024-07-02 17:04:16', 1);
INSERT INTO `goods` VALUES (41, 4, '15波焰星辰（每盒2根）', 28.00, 60.00, '2根/盒', 'http://ywesc-img.webwss.cn/yh/43.jpg', 'http://ywesc-img.webwss.cn/yh/43.mp4', 1, '2024-07-02 17:04:39', 1);
INSERT INTO `goods` VALUES (42, 4, '30波魔幻星空（每盒4根）', 28.00, 68.00, '4根/盒', 'http://ywesc-img.webwss.cn/yh/44.jpg', 'http://ywesc-img.webwss.cn/yh/44.mp4', 1, '2024-07-02 17:04:58', 1);
INSERT INTO `goods` VALUES (43, 4, '超级变变变（大号）', 15.00, 20.00, '8根/盒', 'http://ywesc-img.webwss.cn/yh/45.jpg', 'http://ywesc-img.webwss.cn/yh/45.mp4', 1, '2024-07-02 17:05:18', 1);
INSERT INTO `goods` VALUES (44, 3, '超级水母', 22.00, 45.00, '盒', 'http://ywesc-img.webwss.cn/yh/46.jpg', 'http://ywesc-img.webwss.cn/yh/46.mp4', 1, '2024-07-02 17:05:35', 1);
INSERT INTO `goods` VALUES (45, 4, '炫彩星空（100发）', 68.00, 115.00, '盒', 'http://ywesc-img.webwss.cn/yh/47.jpg', 'http://ywesc-img.webwss.cn/yh/47.mp4', 1, '2024-07-02 17:06:00', 1);
INSERT INTO `goods` VALUES (46, 4, '神奇宝贝', 22.00, 45.00, '个', 'http://ywesc-img.webwss.cn/yh/49.jpg', 'http://ywesc-img.webwss.cn/yh/49.mp4', 1, '2024-07-02 17:06:28', 1);
INSERT INTO `goods` VALUES (47, 4, '红旗火炬（大号火炬外观，先喷火，后喷花）', 25.00, 60.00, '根', 'http://ywesc-img.webwss.cn/yh/50.jpg', 'http://ywesc-img.webwss.cn/yh/50.mp4', 1, '2024-07-02 17:06:48', 1);
INSERT INTO `goods` VALUES (48, 1, '财神到（迎财神专属）', 45.00, 80.00, '个', 'http://ywesc-img.webwss.cn/yh/51.jpg', 'http://ywesc-img.webwss.cn/yh/51.mp4', 1, '2024-07-02 17:07:18', 1);
INSERT INTO `goods` VALUES (49, 1, '孔雀舞（大号孔雀，非常畅销，市场低价）', 45.00, 65.00, '个', 'http://ywesc-img.webwss.cn/yh/52.jpg', 'http://ywesc-img.webwss.cn/yh/52.mp4', 1, '2024-07-02 17:07:38', 1);
INSERT INTO `goods` VALUES (50, 1, '梅兰竹菊', 26.00, 60.00, '个', 'http://ywesc-img.webwss.cn/yh/53.jpg', 'http://ywesc-img.webwss.cn/yh/53.mp4', 1, '2024-07-02 17:08:00', 1);
INSERT INTO `goods` VALUES (51, 2, '炫彩转盘（自动旋转效果）', 36.00, 60.00, '个', 'http://ywesc-img.webwss.cn/yh/54.jpg', 'http://ywesc-img.webwss.cn/yh/54.mp4', 1, '2024-07-02 17:08:21', 1);

-- ----------------------------
-- Table structure for goods_classification
-- ----------------------------
DROP TABLE IF EXISTS `goods_classification`;
CREATE TABLE `goods_classification`  (
  `goods_classification_id` int NOT NULL AUTO_INCREMENT COMMENT '商品分类ID',
  `goods_classification_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品分类名称',
  PRIMARY KEY (`goods_classification_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_classification
-- ----------------------------
INSERT INTO `goods_classification` VALUES (1, '喷花类');
INSERT INTO `goods_classification` VALUES (2, '旋转类');
INSERT INTO `goods_classification` VALUES (3, '升空类');
INSERT INTO `goods_classification` VALUES (4, '手持类');
INSERT INTO `goods_classification` VALUES (5, '摔炮类');

-- ----------------------------
-- Table structure for goods_inventory
-- ----------------------------
DROP TABLE IF EXISTS `goods_inventory`;
CREATE TABLE `goods_inventory`  (
  `goods_inventory_id` int NOT NULL COMMENT '商品库存id',
  `goods_id` int NOT NULL COMMENT '商品id',
  `goods_inventory_sum` int NOT NULL COMMENT '商品库存',
  PRIMARY KEY (`goods_inventory_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_inventory
-- ----------------------------
INSERT INTO `goods_inventory` VALUES (1, 1, 60);
INSERT INTO `goods_inventory` VALUES (2, 2, 115);
INSERT INTO `goods_inventory` VALUES (3, 3, 46);
INSERT INTO `goods_inventory` VALUES (4, 4, 20);
INSERT INTO `goods_inventory` VALUES (5, 5, 75);
INSERT INTO `goods_inventory` VALUES (6, 6, 6);
INSERT INTO `goods_inventory` VALUES (7, 7, 89);
INSERT INTO `goods_inventory` VALUES (8, 8, 28);
INSERT INTO `goods_inventory` VALUES (9, 9, 96);
INSERT INTO `goods_inventory` VALUES (10, 10, 80);
INSERT INTO `goods_inventory` VALUES (11, 11, 240);
INSERT INTO `goods_inventory` VALUES (12, 12, 24);
INSERT INTO `goods_inventory` VALUES (13, 13, 64);
INSERT INTO `goods_inventory` VALUES (14, 14, 36);
INSERT INTO `goods_inventory` VALUES (15, 15, 40);
INSERT INTO `goods_inventory` VALUES (16, 16, 4);
INSERT INTO `goods_inventory` VALUES (17, 17, 24);
INSERT INTO `goods_inventory` VALUES (18, 18, 4);
INSERT INTO `goods_inventory` VALUES (19, 19, 72);
INSERT INTO `goods_inventory` VALUES (20, 20, 24);
INSERT INTO `goods_inventory` VALUES (21, 21, 28);
INSERT INTO `goods_inventory` VALUES (22, 22, 48);
INSERT INTO `goods_inventory` VALUES (23, 23, 20);
INSERT INTO `goods_inventory` VALUES (24, 24, 18);
INSERT INTO `goods_inventory` VALUES (25, 25, 72);
INSERT INTO `goods_inventory` VALUES (26, 26, 84);
INSERT INTO `goods_inventory` VALUES (27, 27, 48);
INSERT INTO `goods_inventory` VALUES (28, 28, 100);
INSERT INTO `goods_inventory` VALUES (29, 29, 20);
INSERT INTO `goods_inventory` VALUES (30, 30, 36);
INSERT INTO `goods_inventory` VALUES (31, 31, 24);
INSERT INTO `goods_inventory` VALUES (32, 32, 16);
INSERT INTO `goods_inventory` VALUES (33, 33, 36);
INSERT INTO `goods_inventory` VALUES (34, 34, 120);
INSERT INTO `goods_inventory` VALUES (35, 35, 100);
INSERT INTO `goods_inventory` VALUES (36, 36, 36);
INSERT INTO `goods_inventory` VALUES (37, 37, 16);
INSERT INTO `goods_inventory` VALUES (38, 38, 40);
INSERT INTO `goods_inventory` VALUES (39, 39, 48);
INSERT INTO `goods_inventory` VALUES (40, 40, 0);
INSERT INTO `goods_inventory` VALUES (41, 41, 48);
INSERT INTO `goods_inventory` VALUES (42, 42, 96);
INSERT INTO `goods_inventory` VALUES (43, 43, 60);
INSERT INTO `goods_inventory` VALUES (44, 44, 100);
INSERT INTO `goods_inventory` VALUES (45, 45, 8);
INSERT INTO `goods_inventory` VALUES (46, 46, 48);
INSERT INTO `goods_inventory` VALUES (47, 47, 80);
INSERT INTO `goods_inventory` VALUES (48, 48, 60);
INSERT INTO `goods_inventory` VALUES (49, 49, 120);
INSERT INTO `goods_inventory` VALUES (50, 50, 20);
INSERT INTO `goods_inventory` VALUES (51, 51, 24);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '通知id',
  `notice_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通知内容',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '满300减10，满500减30，满1000减100，满200以上即可专人送货上门、满100可顺路送货上门（仅限合肥市），若系统出现卡顿，刷新页面即可恢复正常');

-- ----------------------------
-- Table structure for order_list
-- ----------------------------
DROP TABLE IF EXISTS `order_list`;
CREATE TABLE `order_list`  (
  `order_list_id` int NOT NULL AUTO_INCREMENT COMMENT '订单列表id',
  `orders_id` int NOT NULL COMMENT '订单id',
  `goods_id` int NOT NULL COMMENT '商品id',
  `goods_price` decimal(10, 2) NOT NULL COMMENT '商品单价',
  `goods_number` int NOT NULL COMMENT '商品数量',
  `price_subtotal` decimal(10, 2) NOT NULL COMMENT '商品小计',
  PRIMARY KEY (`order_list_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2025145 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_list
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `orders_id` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `user_id` int NOT NULL COMMENT '用户id',
  `address_id` int NOT NULL COMMENT '地址id',
  `order_total_price` decimal(10, 2) NOT NULL COMMENT '订单总额',
  `order_discount_price` decimal(10, 2) NOT NULL COMMENT '优惠金额',
  `order_actual_payment` decimal(10, 2) NOT NULL COMMENT '实付金额',
  `order_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `order_remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注信息',
  `order_status` int NULL DEFAULT 1 COMMENT '订单状态（1-已下单，2-分拣中，3-配送中，4-已完成）',
  `pay_type` int NULL DEFAULT NULL COMMENT '支付类型（1-微信，2-支付宝，3-现金）',
  `completion_time` timestamp NULL DEFAULT NULL COMMENT '交易完成时间',
  `distributor_id` int NOT NULL COMMENT '分销商id',
  PRIMARY KEY (`orders_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2025071 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reduction
-- ----------------------------
INSERT INTO `reduction` VALUES (1, 300.00, 10.00);
INSERT INTO `reduction` VALUES (2, 500.00, 30.00);
INSERT INTO `reduction` VALUES (3, 1000.00, 100.00);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓氏',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
  `user_status` int NOT NULL DEFAULT 1 COMMENT '用户状态（1-正常，2-冻结）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1598545928 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1598545928, '13359074748', '123456', 1, '2024-07-23 14:25:46');
INSERT INTO `user` VALUES (1598545929, '位', '13359074748', 1, '2024-07-23 14:36:21');

-- ----------------------------
-- Table structure for website
-- ----------------------------
DROP TABLE IF EXISTS `website`;
CREATE TABLE `website`  (
  `website_id` int NOT NULL AUTO_INCREMENT COMMENT '网站id',
  `website_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT ' 网站地址',
  PRIMARY KEY (`website_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of website
-- ----------------------------
INSERT INTO `website` VALUES (1, 'http://192.168.16.99:5173');

SET FOREIGN_KEY_CHECKS = 1;
