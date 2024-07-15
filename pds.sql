/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.32 : Database - pds
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`pds` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `pds`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `address_id` int NOT NULL AUTO_INCREMENT COMMENT '收获地址id',
  `user_id` int NOT NULL COMMENT '用户id',
  `address_info` varchar(255) NOT NULL COMMENT '收货地址',
  `contacts` varchar(255) NOT NULL COMMENT '联系人',
  `phone` varchar(255) NOT NULL COMMENT '联系方式',
  `is_default` int NOT NULL DEFAULT '0' COMMENT '默认地址（1-是，0-不是）',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2025010 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `address` */

insert  into `address`(`address_id`,`user_id`,`address_info`,`contacts`,`phone`,`is_default`) values (1,2025001,'安徽省合肥市庐阳区XX小区','位先生1','13359074748',1),(2025008,1598545925,'我是地址','我是188','18888888888',1);

/*Table structure for table `administrators` */

DROP TABLE IF EXISTS `administrators`;

CREATE TABLE `administrators` (
  `administrators_id` int NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `nickname` varchar(255) NOT NULL COMMENT '昵称',
  `phone` varchar(255) NOT NULL COMMENT '手机号',
  `password` varchar(255) NOT NULL COMMENT '登录密码',
  PRIMARY KEY (`administrators_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `administrators` */

insert  into `administrators`(`administrators_id`,`nickname`,`phone`,`password`) values (1,'管理员','13359074748','123456');

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `cart_id` int NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `user_id` int NOT NULL COMMENT '用户id',
  `goods_id` int NOT NULL COMMENT '商品id',
  `goods_number` int NOT NULL COMMENT '商品数量',
  `goods_selected_status` int NOT NULL COMMENT '选中状态（1-选中，0-未选中）',
  `is_selected` int NOT NULL DEFAULT '1' COMMENT '是否可勾选（1-可以，0-不可以）',
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1762107528 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cart` */

insert  into `cart`(`cart_id`,`user_id`,`goods_id`,`goods_number`,`goods_selected_status`,`is_selected`) values (1762107527,2025001,3,1,1,1);

/*Table structure for table `distributor` */

DROP TABLE IF EXISTS `distributor`;

CREATE TABLE `distributor` (
  `distributor_id` int NOT NULL AUTO_INCREMENT COMMENT '分销商id',
  `nickname` varchar(255) NOT NULL COMMENT '昵称',
  `phone` varchar(255) NOT NULL COMMENT '手机号',
  `password` varchar(255) NOT NULL COMMENT '登录密码',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态（1-正常，2-冻结）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `commission_rate` int NOT NULL COMMENT '佣金比例',
  `already_withdrawn` decimal(10,2) NOT NULL COMMENT '已提现佣金',
  PRIMARY KEY (`distributor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2025004 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `distributor` */

insert  into `distributor`(`distributor_id`,`nickname`,`phone`,`password`,`status`,`create_time`,`commission_rate`,`already_withdrawn`) values (2025001,'分销商1','13359074748','666666',1,'2024-07-12 09:16:42',10,'0.00'),(2025002,'分销商2','18888888888','666666',1,'2024-07-13 14:28:37',10,'0.00');

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `goods_id` int NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `goods_classification_id` int NOT NULL COMMENT '商品分类id',
  `goods_name` varchar(255) NOT NULL COMMENT '商品名称',
  `goods_price` decimal(10,2) NOT NULL COMMENT '商品价格',
  `goods_old_price` decimal(10,2) NOT NULL COMMENT '商品市场价',
  `goods_company` varchar(255) NOT NULL COMMENT '商品单位',
  `goods_total_inventory` int NOT NULL COMMENT '商品总库存',
  `goods_img` varchar(255) DEFAULT NULL COMMENT '商品图片地址',
  `goods_video` varchar(255) DEFAULT NULL COMMENT '商品视频地址',
  `goods_status` int NOT NULL DEFAULT '1' COMMENT '商品状态（1-正常，2-下架）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_delete` int NOT NULL DEFAULT '1' COMMENT '是否删除（1-未删除，2-已删除）',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `goods` */

insert  into `goods`(`goods_id`,`goods_classification_id`,`goods_name`,`goods_price`,`goods_old_price`,`goods_company`,`goods_total_inventory`,`goods_img`,`goods_video`,`goods_status`,`create_time`,`is_delete`) values (1,4,'星际导弹（每盒四根）','15.00','25.00','4根/盒',36,'http://ywesc-img.webwss.cn/yh/1.png','http://ywesc-img.webwss.cn/yh/1.mp4',1,'2024-07-02 16:42:38',1),(2,5,'蝌蚪王','10.00','15.00','盒',120,'http://ywesc-img.webwss.cn/yh/2.jpg','http://ywesc-img.webwss.cn/yh/2.mp4',1,'2024-07-02 16:43:34',1),(3,5,'小金鱼','5.00','8.00','盒',48,'http://ywesc-img.webwss.cn/yh/3.png','http://ywesc-http://ywesc-img.webwss.cn/yh/3.mp4',1,'2024-07-02 16:44:44',1),(4,5,'小霸王砂炮（摔炮，内含12小盒）','36.00','45.00','12盒/箱',24,'http://ywesc-img.webwss.cn/yh/4.png','http://ywesc-img.webwss.cn/yh/4.mp4',1,'2024-07-02 16:46:23',1),(5,4,'888型迷彩加特林','55.00','90.00','根',75,'http://ywesc-img.webwss.cn/yh/5.jpg','http://ywesc-img.webwss.cn/yh/5.mp4',1,'2024-07-02 16:47:15',1),(6,4,'小豌豆自动旋转加特林（礼盒装|每盒4根）','188.00','240.00','4根/盒',7,'http://ywesc-img.webwss.cn/yh/6.png','http://ywesc-img.webwss.cn/yh/6.mp4',1,'2024-07-02 16:47:45',1),(7,4,'1.5型彩色龙珠60型','26.00','35.00','6根/盒',90,'http://ywesc-img.webwss.cn/yh/8.jpg','http://ywesc-img.webwss.cn/yh/8.mp4',1,'2024-07-02 16:49:00',1),(8,4,'388发格林机枪（加特林）','40.00','50.00','1根/盒',28,'http://ywesc-img.webwss.cn/yh/9.jpg','http://ywesc-img.webwss.cn/yh/9.mp4',1,'2024-07-02 16:49:36',1),(9,4,'畅销加特林（1088型）','58.00','95.00','个',96,'http://ywesc-img.webwss.cn/yh/10.jpg','http://ywesc-img.webwss.cn/yh/10.mov',1,'2024-07-02 16:50:03',1),(10,4,'蓝色海洋加特林','88.00','105.00','个',80,'http://ywesc-img.webwss.cn/yh/11.png','http://ywesc-img.webwss.cn/yh/11.mp4',1,'2024-07-02 16:50:25',1),(11,1,'孔雀开屏（畅销款，效果贼好）','18.00','50.00','个',240,'http://ywesc-img.webwss.cn/yh/12.jpg','http://ywesc-img.webwss.cn/yh/12.mp4',1,'2024-07-02 16:50:50',1),(12,1,'一夜暴富（效果多变）','45.00','90.00','盒',24,'http://ywesc-img.webwss.cn/yh/13.jpg','http://ywesc-img.webwss.cn/yh/13.mp4',1,'2024-07-02 16:51:14',1),(13,3,'空中闪星（可飞天，效果好）','10.00','15.00','6个/盒',64,'http://ywesc-img.webwss.cn/yh/14.png','http://ywesc-img.webwss.cn/yh/14.mp4',1,'2024-07-02 16:51:37',1),(14,1,'梦境花园','25.00','40.00','4个/盒',36,'http://ywesc-img.webwss.cn/yh/15.png','http://ywesc-img.webwss.cn/yh/15.mp4',1,'2024-07-02 16:52:05',1),(15,3,'水母新秀','30.00','40.00','盒',40,'http://ywesc-img.webwss.cn/yh/16.jpg','http://ywesc-img.webwss.cn/yh/16.mp4',1,'2024-07-02 16:52:29',1),(16,4,'干将莫邪（礼盒装加特林，送礼首选）','168.00','220.00','2根/盒',4,'http://ywesc-img.webwss.cn/yh/17.png','http://ywesc-img.webwss.cn/yh/17.mp4',1,'2024-07-02 16:52:48',1),(17,1,'时空战车（效果变化多样）','45.00','110.00','个',24,'http://ywesc-img.webwss.cn/yh/18.jpg','http://ywesc-img.webwss.cn/yh/18.mp4',1,'2024-07-02 16:53:28',1),(18,1,'恭喜发财（特大号烟花，仅有4个）','99.00','205.00','1个/盒',4,'http://ywesc-img.webwss.cn/yh/19.png','http://ywesc-img.webwss.cn/yh/19.mp4',1,'2024-07-02 16:53:52',1),(19,4,'大号舞龙棒','25.00','48.00','根',72,'http://ywesc-img.webwss.cn/yh/20.png','http://ywesc-img.webwss.cn/yh/20.mp4',1,'2024-07-02 16:54:14',1),(20,1,'鸿运当头（外观喜庆，过年必备）','36.00','50.00','1个/盒',24,'http://ywesc-img.webwss.cn/yh/21.jpg','http://ywesc-img.webwss.cn/yh/21.mp4',1,'2024-07-02 16:54:40',1),(21,1,'明猩喷花（每盒5根）','20.00','55.00','5根/盒',28,'http://ywesc-img.webwss.cn/yh/22.jpg','http://ywesc-img.webwss.cn/yh/22.mp4',1,'2024-07-02 16:55:02',1),(22,1,'潮蛙喷花（炫彩多姿）','20.00','60.00','4个/盒',48,'http://ywesc-img.webwss.cn/yh/23.png','http://ywesc-img.webwss.cn/yh/23.mp4',1,'2024-07-02 16:55:40',1),(23,1,'36型迷你花（内部强推，效果贼好）','40.00','65.00','个',20,'http://ywesc-img.webwss.cn/yh/24.png','http://ywesc-img.webwss.cn/yh/24.mp4',1,'2024-07-02 16:56:03',1),(24,4,'蓝火狙击枪（加特林）','55.00','140.00','个',18,'http://ywesc-img.webwss.cn/yh/25.png','http://ywesc-img.webwss.cn/yh/25.mp4',1,'2024-07-02 16:56:29',1),(25,1,'童趣乐园（内含4个，性价比高）','22.00','40.00','4个/盒',72,'http://ywesc-img.webwss.cn/yh/27.jpg','http://ywesc-img.webwss.cn/yh/27.mp4',1,'2024-07-02 16:56:54',1),(26,1,'乐哈哈（性价比极高）','30.00','60.00','3个/盒',84,'http://ywesc-img.webwss.cn/yh/28.png','http://ywesc-img.webwss.cn/yh/28.mp4',1,'2024-07-02 16:57:21',1),(27,1,'三角斗士（三分钟）','50.00','85.00','个',48,'http://ywesc-img.webwss.cn/yh/29.png','http://ywesc-img.webwss.cn/yh/29.mp4',1,'2024-07-02 16:57:43',1),(28,1,'贵族天地（内含6个，极具性价比）','35.00','68.00','6个/盒',100,'http://ywesc-img.webwss.cn/yh/30.png','http://ywesc-img.webwss.cn/yh/30.mp4',1,'2024-07-02 16:58:04',1),(29,3,'水母王国','22.00','55.00','盒',20,'http://ywesc-img.webwss.cn/yh/31.png','http://ywesc-img.webwss.cn/yh/31.mp4',1,'2024-07-02 16:58:25',1),(30,3,'36发空中花雨','25.00','36.00','盒',36,'http://ywesc-img.webwss.cn/yh/32.jpg','http://ywesc-img.webwss.cn/yh/32.mp4',1,'2024-07-02 16:58:48',1),(31,4,'旋转木马（时空灯笼）','20.00','30.00','个',24,'http://ywesc-img.webwss.cn/yh/33.jpg','http://ywesc-img.webwss.cn/yh/33.mp4',1,'2024-07-02 16:59:39',1),(32,4,'奉旨成龙（可燃放2分钟，超喜庆，可甩龙鞭）','58.00','110.00','个',16,'http://ywesc-img.webwss.cn/yh/34.jpg','http://ywesc-img.webwss.cn/yh/34.mp4',1,'2024-07-02 17:00:05',1),(33,2,'赚转锦鲤（外观喜庆，附带旋转效果）','58.00','95.00','个',36,'http://ywesc-img.webwss.cn/yh/35.jpg','http://ywesc-img.webwss.cn/yh/35.mp4',1,'2024-07-02 17:01:29',1),(34,1,'珍珠遍地（盒装）','16.00','25.00','盒',120,'http://ywesc-img.webwss.cn/yh/36.jpg','http://ywesc-img.webwss.cn/yh/36.mp4',1,'2024-07-02 17:01:53',1),(35,1,'三色跳猫','8.00','12.00','10个/盒',100,'http://ywesc-img.webwss.cn/yh/37.png','http://ywesc-img.webwss.cn/yh/37.mp4',1,'2024-07-02 17:02:23',1),(36,4,'如意金箍棒（孩子专属）','18.00','40.00','个',36,'http://ywesc-img.webwss.cn/yh/38.jpg','http://ywesc-img.webwss.cn/yh/38.mp4',1,'2024-07-02 17:02:43',1),(37,4,'蓝骑士加特林(可燃放90秒，专属加特林，市场少见，先到先得)','78.00','128.00','个',16,'http://ywesc-img.webwss.cn/yh/39.jpg','http://ywesc-img.webwss.cn/yh/39.mp4',1,'2024-07-02 17:03:07',1),(38,3,'020星际火箭','22.00','30.00','盒',40,'http://ywesc-img.webwss.cn/yh/40.png','http://ywesc-img.webwss.cn/yh/40.mp4',1,'2024-07-02 17:03:29',1),(39,4,'金箍棒（伸缩）','30.00','58.00','个',48,'http://ywesc-img.webwss.cn/yh/41.png','http://ywesc-img.webwss.cn/yh/41.mp4',1,'2024-07-02 17:03:51',1),(40,1,'萌宠来袭','30.00','40.00','个',0,'http://ywesc-img.webwss.cn/yh/42.png','http://ywesc-img.webwss.cn/yh/42.mp4',1,'2024-07-02 17:04:16',1),(41,4,'15波焰星辰（每盒2根）','28.00','60.00','2根/盒',48,'http://ywesc-img.webwss.cn/yh/43.jpg','http://ywesc-img.webwss.cn/yh/43.mp4',1,'2024-07-02 17:04:39',1),(42,4,'30波魔幻星空（每盒4根）','28.00','68.00','4根/盒',96,'http://ywesc-img.webwss.cn/yh/44.jpg','http://ywesc-img.webwss.cn/yh/44.mp4',1,'2024-07-02 17:04:58',1),(43,4,'超级变变变（大号）','15.00','20.00','8根/盒',60,'http://ywesc-img.webwss.cn/yh/45.jpg','http://ywesc-img.webwss.cn/yh/45.mp4',1,'2024-07-02 17:05:18',1),(44,3,'超级水母','22.00','45.00','盒',100,'http://ywesc-img.webwss.cn/yh/46.jpg','http://ywesc-img.webwss.cn/yh/46.mp4',1,'2024-07-02 17:05:35',1),(45,4,'炫彩星空（100发）','68.00','115.00','盒',8,'http://ywesc-img.webwss.cn/yh/47.jpg','http://ywesc-img.webwss.cn/yh/47.mp4',1,'2024-07-02 17:06:00',1),(46,4,'神奇宝贝','22.00','45.00','个',48,'http://ywesc-img.webwss.cn/yh/49.jpg','http://ywesc-img.webwss.cn/yh/49.mp4',1,'2024-07-02 17:06:28',1),(47,4,'红旗火炬（大号火炬外观，先喷火，后喷花）','25.00','60.00','根',80,'http://ywesc-img.webwss.cn/yh/50.jpg','http://ywesc-img.webwss.cn/yh/50.mp4',1,'2024-07-02 17:06:48',1),(48,1,'财神到（迎财神专属）','45.00','80.00','个',60,'http://ywesc-img.webwss.cn/yh/51.jpg','http://ywesc-img.webwss.cn/yh/51.mp4',1,'2024-07-02 17:07:18',1),(49,1,'孔雀舞（大号孔雀，非常畅销，市场低价）','45.00','65.00','个',120,'http://ywesc-img.webwss.cn/yh/52.jpg','http://ywesc-img.webwss.cn/yh/52.mp4',1,'2024-07-02 17:07:38',1),(50,1,'梅兰竹菊','26.00','60.00','个',20,'http://ywesc-img.webwss.cn/yh/53.jpg','http://ywesc-img.webwss.cn/yh/53.mp4',1,'2024-07-02 17:08:00',1),(51,2,'炫彩转盘（自动旋转效果）','36.00','60.00','个',24,'http://ywesc-img.webwss.cn/yh/54.jpg','http://ywesc-img.webwss.cn/yh/54.mp4',1,'2024-07-02 17:08:21',1);

/*Table structure for table `goods_classification` */

DROP TABLE IF EXISTS `goods_classification`;

CREATE TABLE `goods_classification` (
  `goods_classification_id` int NOT NULL AUTO_INCREMENT COMMENT '商品分类ID',
  `goods_classification_name` varchar(255) NOT NULL COMMENT '商品分类名称',
  PRIMARY KEY (`goods_classification_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `goods_classification` */

insert  into `goods_classification`(`goods_classification_id`,`goods_classification_name`) values (1,'喷花类'),(2,'旋转类'),(3,'升空类'),(4,'手持类'),(5,'摔炮类');

/*Table structure for table `notice` */

DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '通知id',
  `notice_content` varchar(255) DEFAULT NULL COMMENT '通知内容',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `notice` */

insert  into `notice`(`notice_id`,`notice_content`) values (1,'满300减10，满500减30，满1000减100，满200以上即可专人送货上门、满100可顺路送货上门（仅限合肥市），若系统出现卡顿，刷新页面即可恢复正常');

/*Table structure for table `order_list` */

DROP TABLE IF EXISTS `order_list`;

CREATE TABLE `order_list` (
  `order_list_id` int NOT NULL AUTO_INCREMENT COMMENT '订单列表id',
  `orders_id` int NOT NULL COMMENT '订单id',
  `goods_id` int NOT NULL COMMENT '商品id',
  `goods_price` decimal(10,2) NOT NULL COMMENT '商品单价',
  `goods_number` int NOT NULL COMMENT '商品数量',
  `price_subtotal` decimal(10,2) NOT NULL COMMENT '商品小计',
  PRIMARY KEY (`order_list_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2025123 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `order_list` */

insert  into `order_list`(`order_list_id`,`orders_id`,`goods_id`,`goods_price`,`goods_number`,`price_subtotal`) values (2025101,2025048,4,'36.00',2,'72.00'),(2025102,2025048,1,'15.00',1,'15.00'),(2025103,2025049,3,'5.00',1,'5.00'),(2025104,2025049,2,'10.00',2,'20.00'),(2025105,2025050,20,'36.00',1,'36.00'),(2025106,2025050,24,'55.00',1,'55.00'),(2025107,2025051,14,'25.00',1,'25.00'),(2025108,2025052,18,'99.00',1,'99.00'),(2025109,2025053,11,'18.00',3,'54.00'),(2025110,2025054,7,'26.00',2,'52.00'),(2025111,2025055,17,'45.00',3,'135.00'),(2025112,2025056,8,'40.00',2,'80.00'),(2025113,2025057,1,'15.00',1,'15.00'),(2025114,2025057,3,'5.00',1,'5.00'),(2025115,2025057,6,'188.00',2,'376.00'),(2025116,2025057,9,'58.00',2,'116.00'),(2025117,2025057,5,'55.00',1,'55.00'),(2025118,2025057,10,'88.00',1,'88.00'),(2025119,2025057,15,'30.00',1,'30.00'),(2025120,2025058,4,'36.00',1,'36.00'),(2025121,2025058,6,'188.00',5,'940.00');

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `orders_id` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `user_id` int NOT NULL COMMENT '用户id',
  `address_id` int NOT NULL COMMENT '地址id',
  `order_total_price` decimal(10,2) NOT NULL COMMENT '订单总额',
  `order_discount_price` decimal(10,2) NOT NULL COMMENT '优惠金额',
  `order_actual_payment` decimal(10,2) NOT NULL COMMENT '实付金额',
  `order_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `order_remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `order_status` int DEFAULT '1' COMMENT '订单状态（1-已下单，2-分拣中，3-配送中，4-已完成）',
  `pay_type` int DEFAULT NULL COMMENT '支付类型（1-微信，2-支付宝，3-现金）',
  `completion_time` timestamp NULL DEFAULT NULL COMMENT '交易完成时间',
  `distributor_id` int NOT NULL COMMENT '分销商id',
  PRIMARY KEY (`orders_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2025060 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `orders` */

insert  into `orders`(`orders_id`,`user_id`,`address_id`,`order_total_price`,`order_discount_price`,`order_actual_payment`,`order_create_time`,`order_remarks`,`order_status`,`pay_type`,`completion_time`,`distributor_id`) values (2025048,2025001,1,'87.00','0.00','87.00','2024-07-12 10:24:37','',4,2,'2024-07-15 11:38:20',2025001),(2025049,2025001,1,'25.00','0.00','25.00','2024-07-11 10:27:11','',2,NULL,NULL,2025001),(2025050,2025001,1,'91.00','0.00','91.00','2024-07-11 10:27:57','',1,NULL,NULL,2025001),(2025051,2025001,1,'25.00','0.00','25.00','2024-07-10 10:28:44','',1,NULL,NULL,2025001),(2025052,2025001,1,'99.00','0.00','99.00','2024-07-09 10:28:52','',1,NULL,NULL,2025001),(2025053,2025001,1,'54.00','0.00','54.00','2024-07-08 10:29:01','',1,NULL,NULL,2025001),(2025054,2025001,1,'52.00','0.00','52.00','2024-07-07 10:29:07','',1,NULL,NULL,2025001),(2025055,2025001,1,'135.00','0.00','135.00','2024-07-06 10:29:18','',1,NULL,NULL,2025001),(2025056,2025001,1,'80.00','0.00','80.00','2024-07-12 14:28:39','',2,NULL,NULL,2025001),(2025057,2025001,1,'685.00','30.00','655.00','2024-07-12 14:39:20','',4,2,'2024-07-15 11:36:00',2025001),(2025058,1598545925,1,'976.00','30.00','946.00','2024-07-12 19:56:04','',4,3,'2024-07-15 11:37:42',2025002);

/*Table structure for table `reduction` */

DROP TABLE IF EXISTS `reduction`;

CREATE TABLE `reduction` (
  `full_reduction_id` int NOT NULL AUTO_INCREMENT COMMENT '满减id',
  `full_condition_price` decimal(10,2) NOT NULL COMMENT '满减条件金额',
  `full_reduction_amount` decimal(10,2) NOT NULL COMMENT '满减金额',
  PRIMARY KEY (`full_reduction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `reduction` */

insert  into `reduction`(`full_reduction_id`,`full_condition_price`,`full_reduction_amount`) values (1,'300.00','10.00'),(2,'500.00','30.00'),(3,'1000.00','100.00');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓氏',
  `phone` varchar(255) NOT NULL COMMENT '手机号',
  `user_status` int NOT NULL DEFAULT '1' COMMENT '用户状态（1-正常，2-冻结）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1598545927 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`user_id`,`username`,`phone`,`user_status`,`create_time`) values (2025001,'位','13359074748',1,'2024-07-04 13:41:37'),(1598545925,'位','18888888888',1,'2024-07-10 14:55:02'),(1598545926,'位','17777777777',1,'2024-07-11 16:39:41');

/*Table structure for table `website` */

DROP TABLE IF EXISTS `website`;

CREATE TABLE `website` (
  `website_id` int NOT NULL AUTO_INCREMENT COMMENT '网站id',
  `website_path` varchar(255) DEFAULT NULL COMMENT ' 网站地址',
  PRIMARY KEY (`website_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `website` */

insert  into `website`(`website_id`,`website_path`) values (1,'http://192.168.16.96:5173');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
