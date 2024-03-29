/*
 Navicat Premium Data Transfer

 Source Server         : fishDB
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : localhost:3306
 Source Schema         : fishdb

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 29/03/2024 18:07:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attachment_information
-- ----------------------------
DROP TABLE IF EXISTS `attachment_information`;
CREATE TABLE `attachment_information`  (
                                           `attach_id` bigint NOT NULL AUTO_INCREMENT,
                                           `ori_table_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '源表名称',
                                           `ori_table_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '源表数据id',
                                           `create_by` bigint NULL DEFAULT NULL COMMENT '创建人',
                                           `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                           `update_by` bigint NULL DEFAULT NULL COMMENT '更新人',
                                           `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                           `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '是否删除（0未删除 1已删除）',
                                           `attach_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '附件路径',
                                           PRIMARY KEY (`attach_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attachment_information
-- ----------------------------
INSERT INTO `attachment_information` VALUES (1, 'cabinet_quotation', '43', 1, '2024-03-28 13:55:07', NULL, NULL, '0', 'http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-28/新建 文本文档 (2)_1711605306557.txt');
INSERT INTO `attachment_information` VALUES (2, 'cabinet_quotation', '43', 1, '2024-03-28 13:55:07', NULL, NULL, '0', 'http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-28/新建 文本文档_1711605306621.txt');
INSERT INTO `attachment_information` VALUES (3, 'cabinet_quotation', '44', 1, '2024-03-28 13:58:44', NULL, NULL, '0', 'http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-28/1_1711605524007.jpg');
INSERT INTO `attachment_information` VALUES (4, 'cabinet_quotation', '44', 1, '2024-03-28 13:58:44', NULL, NULL, '0', 'http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-28/1 - 副本_1711605524011.jpg');
INSERT INTO `attachment_information` VALUES (5, 'cabinet_quotation', '48', 1, '2024-03-28 17:33:23', NULL, NULL, '0', 'http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-28/fishdb_1711618403295.sql');
INSERT INTO `attachment_information` VALUES (6, 'cabinet_quotation', '49', 1, '2024-03-28 17:35:17', NULL, NULL, '0', 'http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-28/2bb7db4d-ae4e-4687-abab-2d84f0cd9c6e1_1711618517008.gif');
INSERT INTO `attachment_information` VALUES (7, 'cabinet_quotation', '49', 1, '2024-03-28 17:35:17', 1, '2024-03-29 16:38:53', '1', 'http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-28/hpditmu_20240328_1711618517017.zip');
INSERT INTO `attachment_information` VALUES (8, 'cabinet_quotation', '49', 1, '2024-03-28 17:35:17', NULL, NULL, '0', 'http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-28/新建 文本文档_1711618517026.txt');
INSERT INTO `attachment_information` VALUES (9, 'cabinet_quotation', '49', 1, '2024-03-28 17:35:17', NULL, NULL, '0', 'http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-28/三期问题反馈解决方案_质量安全部_1711618517088.pdf');
INSERT INTO `attachment_information` VALUES (10, 'cabinet_quotation', '49', 1, '2024-03-28 17:35:17', 1, '2024-03-29 16:36:39', '1', 'http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-28/三期问题反馈解决方案_数字化中心_1711618517118.docx');
INSERT INTO `attachment_information` VALUES (11, 'cabinet_quotation', '49', 1, '2024-03-29 16:39:27', 1, '2024-03-29 17:33:12', '1', 'http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-29/学习笔记_1711701566795.md');
INSERT INTO `attachment_information` VALUES (12, 'cabinet_quotation', '49', 1, '2024-03-29 17:26:05', 1, '2024-03-29 17:33:12', '1', 'http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-29/学习笔记_1711704364812.md');
INSERT INTO `attachment_information` VALUES (13, 'cabinet_quotation', '49', 1, '2024-03-29 17:30:04', 1, '2024-03-29 17:33:12', '1', 'http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-29/学习笔记_1711704604373.md');
INSERT INTO `attachment_information` VALUES (14, 'cabinet_quotation', '49', 1, '2024-03-29 17:30:13', 1, '2024-03-29 17:33:12', '1', 'http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-29/学习笔记_1711704612525.md');
INSERT INTO `attachment_information` VALUES (15, 'cabinet_quotation', '49', 1, '2024-03-29 17:31:13', 1, '2024-03-29 17:33:12', '1', 'http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-29/学习笔记_1711704673163.md');
INSERT INTO `attachment_information` VALUES (16, 'cabinet_quotation', '49', 1, '2024-03-29 17:33:23', 1, '2024-03-29 17:34:14', '1', 'http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-29/1 - 副本 - 副本_1711704802665.jpg');
INSERT INTO `attachment_information` VALUES (17, 'cabinet_quotation', '49', 1, '2024-03-29 17:33:27', 1, '2024-03-29 17:34:14', '1', 'http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-29/1 - 副本 - 副本_1711704807029.jpg');
INSERT INTO `attachment_information` VALUES (18, 'cabinet_quotation', '49', 1, '2024-03-29 17:35:27', 1, '2024-03-29 17:36:32', '1', 'http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-29/1_1711704927407.jpg');
INSERT INTO `attachment_information` VALUES (19, 'cabinet_quotation', '49', 1, '2024-03-29 17:36:06', 1, '2024-03-29 17:36:32', '1', 'http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-29/1_1711704966454.jpg');
INSERT INTO `attachment_information` VALUES (20, 'cabinet_quotation', '49', 1, '2024-03-29 17:36:54', 1, '2024-03-29 17:44:09', '1', 'http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-29/asec3_1711705014278.sql');
INSERT INTO `attachment_information` VALUES (21, 'cabinet_quotation', '49', 1, '2024-03-29 17:44:39', NULL, NULL, '0', 'http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-29/temp_1711705478837.sql');

-- ----------------------------
-- Table structure for cabinet_quotation
-- ----------------------------
DROP TABLE IF EXISTS `cabinet_quotation`;
CREATE TABLE `cabinet_quotation`  (
                                      `quotation_id` int NOT NULL AUTO_INCREMENT,
                                      `Customer_Name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户姓名',
                                      `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
                                      `telephone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
                                      `Product_Name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
                                      `color` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '颜色',
                                      `Cabinet_body` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '柜体',
                                      `Quotation_date` datetime NULL DEFAULT NULL COMMENT '报价单日期',
                                      `Cabinet_Total_price` decimal(18, 4) NULL DEFAULT NULL COMMENT '橱柜类价格',
                                      `Electrical_Total_price` decimal(18, 4) NULL DEFAULT NULL COMMENT '电器五金类价格',
                                      `all_Total_price_words` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '总价大写',
                                      `all_Total_price` decimal(18, 4) NULL DEFAULT NULL COMMENT '总价',
                                      `create_by` bigint NULL DEFAULT NULL COMMENT '创建人',
                                      `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                      `update_by` bigint NULL DEFAULT NULL COMMENT '更新人',
                                      `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                      `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '是否删除（0未删除 1已删除）',
                                      PRIMARY KEY (`quotation_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cabinet_quotation
-- ----------------------------
INSERT INTO `cabinet_quotation` VALUES (1, '小明', '地址', '123', NULL, NULL, NULL, '2024-03-03 00:00:00', 22.2100, 122.1000, NULL, 144.3100, 1, NULL, NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (2, '测试', '测试地址', '123456789', '测试产品', '测试颜色', '测试柜体', '2024-03-10 00:00:00', 2822.1100, 40605.0000, NULL, 43427.1100, 1, '2024-03-28 11:03:48', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (3, '测试', '测试地址', '123456789', '测试产品', '测试颜色', '测试柜体', '2024-03-10 00:00:00', 2822.1100, 40605.0000, NULL, 43427.1100, 1, '2024-03-28 11:05:44', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (4, '9', NULL, '9', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:10:26', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (5, '99', NULL, '99', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:13:08', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (6, '99', NULL, '99', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:13:14', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (7, '99', NULL, '99', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:13:21', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (8, '8', NULL, '8', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:13:51', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (9, '7', NULL, '7', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:15:20', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (10, '7', NULL, '7', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:15:34', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (11, '6', NULL, '6', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:16:22', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (12, '66', NULL, '66', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:17:45', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (13, '66', NULL, '66', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:18:06', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (14, '66', NULL, '66', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:21:13', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (15, '66', NULL, '66', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:22:52', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (16, '55', NULL, '55', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:24:41', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (17, '55', NULL, '55', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:24:49', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (18, '55', NULL, '55', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:25:20', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (19, '3333', NULL, '3333', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:32:34', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (20, '3333', NULL, '3333', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:32:52', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (21, '2222', NULL, '2222', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:34:36', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (22, '11', NULL, '111', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:36:45', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (23, '1', NULL, '1', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:41:44', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (24, '1', NULL, '1', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:42:04', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (25, '3', NULL, '3', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:43:22', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (26, '8', NULL, '8', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:47:52', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (27, '8', NULL, '8', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:48:19', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (28, '8', NULL, '8', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:49:13', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (29, '8', NULL, '8', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:50:05', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (30, '8', NULL, '8', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:50:48', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (31, '8', NULL, '8', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 11:51:09', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (32, '86', NULL, '86', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 12:01:51', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (33, '1', NULL, '1', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 13:31:42', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (34, '1', NULL, '1', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 13:33:15', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (35, '1', NULL, '1', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 13:33:58', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (36, '1', NULL, '1', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 13:35:43', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (37, '1', NULL, '1', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 13:36:23', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (38, '1', NULL, '1', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 13:36:54', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (39, '22', NULL, '22', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 13:39:28', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (40, '1', NULL, '1', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 13:48:20', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (41, '2', NULL, '2', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 13:50:38', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (42, '1', NULL, '1', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 13:51:12', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (43, '6666', NULL, '66666', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 13:55:04', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (44, '6666', NULL, '66666', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 13:58:40', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (45, 'pp', NULL, 'pp', NULL, NULL, NULL, NULL, 40.9300, 0.0000, NULL, 40.9300, 1, '2024-03-28 14:34:12', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (46, '66', '66', '6666', '66', '666', '666', '2024-03-28 14:50:39', 1.0000, 0.0000, NULL, 1.0000, 1, '2024-03-28 14:43:37', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (47, '11', '22', '66', '33', '55', '44', '2024-03-31 08:00:00', 1.0000, 0.0000, NULL, 1.0000, 1, '2024-03-28 14:54:34', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (48, '99', NULL, '99', NULL, NULL, NULL, NULL, 0.0000, 0.0000, NULL, 0.0000, 1, '2024-03-28 17:33:21', NULL, NULL, '0');
INSERT INTO `cabinet_quotation` VALUES (49, 'test1', '1', 'test1', '1', '1', '1', '2024-03-02 08:00:00', 1.0000, 9.0000, NULL, 10.0000, 1, '2024-03-28 08:00:00', 1, '2024-03-29 17:44:43', '0');

-- ----------------------------
-- Table structure for cabinet_quotation_detail
-- ----------------------------
DROP TABLE IF EXISTS `cabinet_quotation_detail`;
CREATE TABLE `cabinet_quotation_detail`  (
                                             `detail_id` int NOT NULL AUTO_INCREMENT,
                                             `quotation_id` int NULL DEFAULT NULL COMMENT '主表id',
                                             `project_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '项目名称',
                                             `Specification_model` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格型号',
                                             `Pricing_quantity` decimal(18, 4) NULL DEFAULT NULL COMMENT '计价数量',
                                             `unit_price` decimal(18, 4) NULL DEFAULT NULL COMMENT '单价',
                                             `Pricing_coefficient` decimal(18, 4) NULL DEFAULT NULL COMMENT '计价系数',
                                             `price_amount` decimal(18, 4) NULL DEFAULT NULL COMMENT '金额',
                                             `remark` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
                                             `create_by` bigint NULL DEFAULT NULL COMMENT '创建人',
                                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                             `update_by` bigint NULL DEFAULT NULL COMMENT '更新人',
                                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                             `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '是否删除（0未删除 1已删除）',
                                             `detail_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '子表种类（0橱柜类 1厨具五金类）',
                                             PRIMARY KEY (`detail_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cabinet_quotation_detail
-- ----------------------------
INSERT INTO `cabinet_quotation_detail` VALUES (1, 1, '1', '规格1', 1.1100, 6.0100, 1.0000, 6.6700, '11', 1, NULL, NULL, NULL, '0', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (2, 1, '2', '规格12', 2.2200, 7.0000, 1.0000, 15.5400, '', 1, NULL, NULL, NULL, '0', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (3, 1, '3', '规格13', 3.3300, 8.0000, 1.0000, 26.6400, '11', 1, NULL, NULL, NULL, '0', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (4, 1, '4', '规格14', 4.4400, 9.0000, 1.0000, 39.9600, '', 1, NULL, NULL, NULL, '0', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (5, 1, '5', '规格15', 5.5500, 10.0000, 1.0000, 55.5000, '', 1, NULL, NULL, NULL, '0', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (6, 2, '测试1', '测试11', 1.1000, 100.1000, 1.0000, 110.1100, '测试备注1', 1, '2024-03-28 11:03:48', NULL, NULL, '0', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (7, 2, '测试2', '测试22', 2.0000, 1356.0000, 1.0000, 2712.0000, '测试备注2', 1, '2024-03-28 11:03:48', NULL, NULL, '0', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (8, 2, '测试3', '测试33', 3.0000, 203.0000, 1.0000, 609.0000, '测试备注3', 1, '2024-03-28 11:03:48', NULL, NULL, '0', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (9, 2, '测试4', '测试44', 4.0000, 9999.0000, 1.0000, 39996.0000, '测试备注4', 1, '2024-03-28 11:03:48', NULL, NULL, '0', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (10, 3, '测试1', '测试11', 1.1000, 100.1000, 1.0000, 110.1100, '测试备注1', 1, '2024-03-28 11:05:44', NULL, NULL, '0', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (11, 3, '测试2', '测试22', 2.0000, 1356.0000, 1.0000, 2712.0000, '测试备注2', 1, '2024-03-28 11:05:44', NULL, NULL, '0', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (12, 3, '测试3', '测试33', 3.0000, 203.0000, 1.0000, 609.0000, '测试备注3', 1, '2024-03-28 11:05:44', NULL, NULL, '0', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (13, 3, '测试4', '测试44', 4.0000, 9999.0000, 1.0000, 39996.0000, '测试备注4', 1, '2024-03-28 11:05:44', NULL, NULL, '0', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (14, 45, '', '', 10.1100, 3.6800, 1.1000, 40.9300, '', 1, '2024-03-28 14:34:12', NULL, NULL, '0', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (15, 46, '1', '', 1.0000, 1.0000, 1.0000, 1.0000, '', 1, '2024-03-28 14:43:37', NULL, NULL, '0', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (16, 46, '1', '', 0.0000, 0.0000, 1.0000, 0.0000, '', 1, '2024-03-28 14:43:37', NULL, NULL, '0', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (17, 46, '1', '', 0.0000, 0.0000, 1.0000, 0.0000, '', 1, '2024-03-28 14:43:37', NULL, NULL, '0', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (18, 46, '1', '', 0.0000, 0.0000, 1.0000, 0.0000, '', 1, '2024-03-28 14:43:37', NULL, NULL, '0', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (19, 47, '1', '', 1.0000, 1.0000, 1.0000, 1.0000, '', 1, '2024-03-28 14:54:34', NULL, NULL, '0', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (20, 47, '2', '', 0.0000, 0.0000, 1.0000, 0.0000, '', 1, '2024-03-28 14:54:34', NULL, NULL, '0', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (21, 47, '3', '', 0.0000, 0.0000, 1.0000, 0.0000, '', 1, '2024-03-28 14:54:34', NULL, NULL, '0', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (22, 47, '45', '', 0.0000, 0.0000, 1.0000, 0.0000, '', 1, '2024-03-28 14:54:34', NULL, NULL, '0', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (23, 47, '6', '', 0.0000, 0.0000, 1.0000, 0.0000, '', 1, '2024-03-28 14:54:34', NULL, NULL, '0', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (24, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:26:05', NULL, NULL, '1', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (25, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:26:05', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (26, 49, '2', '2', 2.0000, 2.0000, 2.0000, 8.0000, '2', 1, '2024-03-29 17:26:05', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (27, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:30:04', NULL, NULL, '1', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (28, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:30:04', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (29, 49, '2', '2', 2.0000, 2.0000, 2.0000, 8.0000, '3', 1, '2024-03-29 17:30:04', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (30, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:30:12', NULL, NULL, '1', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (31, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:30:12', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (32, 49, '2', '2', 2.0000, 2.0000, 2.0000, 8.0000, '....', 1, '2024-03-29 17:30:12', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (33, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:31:12', NULL, NULL, '1', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (34, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:31:12', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (35, 49, '2', '2', 2.0000, 2.0000, 2.0000, 8.0000, '....', 1, '2024-03-29 17:31:12', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (36, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:32:58', NULL, NULL, '1', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (37, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:32:58', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (38, 49, '2', '2', 2.0000, 2.0000, 2.0000, 8.0000, '....', 1, '2024-03-29 17:32:58', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (39, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:33:12', NULL, NULL, '1', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (40, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:33:12', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (41, 49, '2', '2', 2.0000, 2.0000, 2.0000, 8.0000, '....', 1, '2024-03-29 17:33:12', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (42, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:33:23', NULL, NULL, '1', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (43, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:33:23', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (44, 49, '2', '2', 2.0000, 2.0000, 2.0000, 8.0000, '....', 1, '2024-03-29 17:33:23', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (45, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:33:27', NULL, NULL, '1', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (46, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:33:27', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (47, 49, '2', '2', 2.0000, 2.0000, 2.0000, 8.0000, '....', 1, '2024-03-29 17:33:27', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (48, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:34:14', NULL, NULL, '1', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (49, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:34:14', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (50, 49, '2', '2', 2.0000, 2.0000, 2.0000, 8.0000, '....', 1, '2024-03-29 17:34:14', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (51, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:35:03', NULL, NULL, '1', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (52, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:35:03', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (53, 49, '2', '2', 2.0000, 2.0000, 2.0000, 8.0000, '....', 1, '2024-03-29 17:35:03', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (54, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:35:39', NULL, NULL, '1', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (55, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:35:39', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (56, 49, '2', '2', 2.0000, 2.0000, 2.0000, 8.0000, '....', 1, '2024-03-29 17:35:39', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (57, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:36:32', NULL, NULL, '1', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (58, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:36:32', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (59, 49, '2', '2', 2.0000, 2.0000, 2.0000, 8.0000, '....', 1, '2024-03-29 17:36:32', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (60, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:36:47', NULL, NULL, '1', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (61, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:36:47', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (62, 49, '2', '2', 2.0000, 2.0000, 2.0000, 8.0000, '....', 1, '2024-03-29 17:36:47', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (63, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:44:09', NULL, NULL, '1', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (64, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:44:09', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (65, 49, '2', '2', 2.0000, 2.0000, 2.0000, 8.0000, '....', 1, '2024-03-29 17:44:09', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (66, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:44:19', NULL, NULL, '1', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (67, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:44:19', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (68, 49, '2', '2', 2.0000, 2.0000, 2.0000, 8.0000, '....', 1, '2024-03-29 17:44:19', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (69, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:44:37', NULL, NULL, '1', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (70, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:44:37', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (71, 49, '2', '2', 2.0000, 2.0000, 2.0000, 8.0000, '....', 1, '2024-03-29 17:44:37', NULL, NULL, '1', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (72, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:44:43', NULL, NULL, '0', '0');
INSERT INTO `cabinet_quotation_detail` VALUES (73, 49, '1', '1', 1.0000, 1.0000, 1.0000, 1.0000, '1', 1, '2024-03-29 17:44:43', NULL, NULL, '0', '1');
INSERT INTO `cabinet_quotation_detail` VALUES (74, 49, '2', '2', 2.0000, 2.0000, 2.0000, 8.0000, '....', 1, '2024-03-29 17:44:43', NULL, NULL, '0', '1');

-- ----------------------------
-- Table structure for notice_management
-- ----------------------------
DROP TABLE IF EXISTS `notice_management`;
CREATE TABLE `notice_management`  (
                                      `notice_id` bigint NOT NULL AUTO_INCREMENT COMMENT '通知主键',
                                      `message` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通知内容',
                                      `create_by` bigint NULL DEFAULT NULL COMMENT '创建人',
                                      `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                      `update_by` bigint NULL DEFAULT NULL COMMENT '更新人',
                                      `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                      `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '是否删除（0未删除 1已删除）',
                                      `need_processed_num` int NULL DEFAULT NULL COMMENT '总需读人数',
                                      `end_processed_num` int NULL DEFAULT NULL COMMENT '已读人数',
                                      `release_date` datetime NULL DEFAULT NULL COMMENT '发布日期',
                                      `release_by` bigint NULL DEFAULT NULL COMMENT '发布人id',
                                      PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice_management
-- ----------------------------
INSERT INTO `notice_management` VALUES (1, '这是一条测试的骚扰通知··', 1, '2024-03-13 11:41:18', 1, '2024-03-13 11:53:41', '0', NULL, NULL, '2024-03-13 11:53:41', 1);
INSERT INTO `notice_management` VALUES (2, '这是创建并发布的骚扰通知 ×2', 1, '2024-03-13 11:58:57', NULL, NULL, '0', NULL, NULL, '2024-03-13 11:58:57', 1);
INSERT INTO `notice_management` VALUES (3, '单独发给管理员的骚扰通知😈', 1, '2024-03-14 13:49:06', 1, '2024-03-16 11:25:14', '1', NULL, NULL, '2024-03-14 13:49:06', 1);
INSERT INTO `notice_management` VALUES (4, 'TEST1', 1, '2024-03-25 14:44:17', NULL, NULL, '0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for pending_notification
-- ----------------------------
DROP TABLE IF EXISTS `pending_notification`;
CREATE TABLE `pending_notification`  (
                                         `pending_id` bigint NOT NULL AUTO_INCREMENT COMMENT '待处理通知主键',
                                         `notice_id` bigint NULL DEFAULT NULL COMMENT '原通知主键',
                                         `Processed` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '是否已读（0没读 1已读）',
                                         `user_id` bigint NULL DEFAULT NULL COMMENT '待处理人',
                                         `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '是否删除（0未删除 1已删除）',
                                         PRIMARY KEY (`pending_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pending_notification
-- ----------------------------
INSERT INTO `pending_notification` VALUES (1, 1, '0', 1, '1');
INSERT INTO `pending_notification` VALUES (2, 1, '0', 3, '1');
INSERT INTO `pending_notification` VALUES (3, 1, '0', 2, '1');
INSERT INTO `pending_notification` VALUES (4, 1, '1', 1, '0');
INSERT INTO `pending_notification` VALUES (5, 1, '0', 3, '0');
INSERT INTO `pending_notification` VALUES (6, 1, '0', 2, '0');
INSERT INTO `pending_notification` VALUES (7, 2, '1', 1, '0');
INSERT INTO `pending_notification` VALUES (8, 2, '1', 7, '0');
INSERT INTO `pending_notification` VALUES (9, 3, '1', 1, '1');
INSERT INTO `pending_notification` VALUES (10, 4, '0', 2, '0');
INSERT INTO `pending_notification` VALUES (11, 4, '0', 3, '0');
INSERT INTO `pending_notification` VALUES (12, 4, '0', 1, '0');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
                            `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
                            `age` int NULL DEFAULT NULL COMMENT '年龄',
                            `birthday` date NULL DEFAULT NULL COMMENT '生日',
                            `tid` int NULL DEFAULT NULL COMMENT '教师id',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (23, 'tom', 24, NULL, 1);
INSERT INTO `student` VALUES (24, 'jack', 23, NULL, 1);
INSERT INTO `student` VALUES (26, 'jack', 23, NULL, 1);
INSERT INTO `student` VALUES (27, 'jack', 23, NULL, 1);
INSERT INTO `student` VALUES (28, 'jack', 23, NULL, NULL);
INSERT INTO `student` VALUES (29, 'jack', 23, NULL, NULL);
INSERT INTO `student` VALUES (30, 'jack', 23, NULL, NULL);
INSERT INTO `student` VALUES (31, 'jack', 23, NULL, NULL);
INSERT INTO `student` VALUES (32, 'jack', 23, NULL, NULL);
INSERT INTO `student` VALUES (33, 'jack', 23, NULL, NULL);
INSERT INTO `student` VALUES (34, '23431112', 99, NULL, NULL);
INSERT INTO `student` VALUES (35, '23432', 99, NULL, NULL);
INSERT INTO `student` VALUES (36, 'jack', 23, NULL, NULL);
INSERT INTO `student` VALUES (37, 'jack', 23, NULL, NULL);
INSERT INTO `student` VALUES (38, 'jack', 23, NULL, NULL);
INSERT INTO `student` VALUES (39, 'many11', 199, NULL, NULL);
INSERT INTO `student` VALUES (40, 'ma1ny11', 1991, NULL, NULL);
INSERT INTO `student` VALUES (41, 'ma1ny21', 1981, NULL, NULL);
INSERT INTO `student` VALUES (42, 'jack', 23, NULL, NULL);
INSERT INTO `student` VALUES (43, 'many1', 99, NULL, NULL);
INSERT INTO `student` VALUES (44, 'many2', 98, NULL, NULL);
INSERT INTO `student` VALUES (45, 'jack', 23, NULL, NULL);
INSERT INTO `student` VALUES (46, 'jack', 23, NULL, NULL);
INSERT INTO `student` VALUES (47, 'tom', 23, NULL, NULL);
INSERT INTO `student` VALUES (48, 'tom', 23, NULL, NULL);
INSERT INTO `student` VALUES (49, 'tom', 23, NULL, NULL);
INSERT INTO `student` VALUES (51, 'tom', 23, NULL, NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
                             `menu_id` bigint NOT NULL AUTO_INCREMENT,
                             `key_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT 'key/name',
                             `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由title',
                             `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单label',
                             `sort` int NULL DEFAULT NULL COMMENT '排序',
                             `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由地址',
                             `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件路径',
                             `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
                             `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
                             `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识',
                             `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '#' COMMENT '菜单图标',
                             `create_by` bigint NULL DEFAULT NULL,
                             `create_time` datetime NULL DEFAULT NULL,
                             `update_by` bigint NULL DEFAULT NULL,
                             `update_time` datetime NULL DEFAULT NULL,
                             `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '是否删除（0未删除 1已删除）',
                             `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
                             `disable_auth` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '登录验证（默认为0需要验证）',
                             `dismiss_tab` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '选项卡（默认为0有）',
                             `router_parent_id` bigint NULL DEFAULT 1 COMMENT '路由父id',
                             `menu_parent_id` bigint NULL DEFAULT 0 COMMENT '菜单父id',
                             `is_Leaf` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                             PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 'base-layout', NULL, '', NULL, '/', '/src/layouts/BaseLayout', '1', '0', NULL, '#', NULL, NULL, 1, '2024-03-14 16:34:39', '0', '（仅路由）', '0', '0', 0, 0, '0');
INSERT INTO `sys_menu` VALUES (2, 'navigation', 'MENU.Navigation', 'MENU.Navigation', 1, '/', '/src/views/Navigation', '0', '0', NULL, '~icons/mdi/compass-outline', NULL, NULL, 3, '2024-03-06 14:18:48', '0', '（菜单）站点导航', '0', '0', 1, 0, '0');
INSERT INTO `sys_menu` VALUES (3, 'system-functions', '', 'MENU.SystemFunctions', 5, NULL, NULL, '0', '0', NULL, '~icons/mdi/function-variant', NULL, NULL, 1, '2024-03-25 10:25:06', '0', '（菜单）系统功能', '0', '0', 1, 0, '1');
INSERT INTO `sys_menu` VALUES (4, 'user-management', 'MENU.UserManagement', 'MENU.UserManagement', 1, '/system-functions/user-management', '/src/views/SystemFunctions/UserManagement', '0', '0', NULL, '~icons/mdi/account-cog-outline', NULL, NULL, 1, '2024-03-14 13:59:38', '0', '（菜单）用户管理', '0', '0', 1, 3, '0');
INSERT INTO `sys_menu` VALUES (5, 'menu-management', 'MENU.MenuManagement', 'MENU.MenuManagement', 2, '/system-functions/menu-management', '/src/views/SystemFunctions/MenuManagement', '0', '0', NULL, '~icons/mdi/account-cog-outline', NULL, NULL, 1, '2024-03-14 14:00:00', '0', '（菜单）菜单管理', '0', '0', 1, 3, '0');
INSERT INTO `sys_menu` VALUES (6, 'test-keyName', NULL, NULL, 0, NULL, NULL, '0', '0', NULL, '#', 3, '2024-03-13 11:33:07', 1, '2024-03-14 15:12:58', '1', '测试', '0', '0', 1, 0, '0');
INSERT INTO `sys_menu` VALUES (7, 'role-management', 'MENU.RoleManagement', 'MENU.RoleManagement', 3, '/system-functions/role-management', '/src/views/SystemFunctions/RoleManagement', '0', '0', NULL, '@vicons/ionicons5/PricetagOutline', 1, '2024-03-14 15:57:55', 1, '2024-03-14 16:14:43', '0', '（菜单）角色管理', '0', '0', 1, 3, '0');
INSERT INTO `sys_menu` VALUES (8, 'notice-management', 'MENU.NoticeManagement', 'MENU.NoticeManagement', 4, '/system-functions/notice-management', '/src/views/SystemFunctions/NoticeManagement', '0', '0', NULL, '@vicons/ionicons5/ChatbubbleEllipsesOutline', 1, '2024-03-14 15:59:31', 1, '2024-03-14 16:14:51', '0', '（菜单）通知公告', '0', '0', 1, 3, '0');
INSERT INTO `sys_menu` VALUES (9, 'system-tools', NULL, 'MENU.SystemTools', 15, NULL, NULL, '0', '0', NULL, '~icons/mdi/tools', 1, '2024-03-14 16:01:34', 1, '2024-03-25 10:24:10', '0', '（菜单）系统工具', '0', '0', 1, 0, '1');
INSERT INTO `sys_menu` VALUES (10, 'qrcode', 'MENU.QRCode', 'MENU.QRCode', 1, '/system-tools/qrcode', '/src/views/SystemTools/QRCode', '0', '0', NULL, '~icons/ic/baseline-qrcode', 1, '2024-03-14 16:03:55', 1, '2024-03-14 16:12:29', '0', '（菜单）二维码', '0', '0', 1, 9, '0');
INSERT INTO `sys_menu` VALUES (11, 'clipboard', 'MENU.Clipboard', 'MENU.Clipboard', 2, '/system-tools/clipboard', '/src/views/SystemTools/Clipboard', '0', '0', NULL, '~icons/ic/baseline-content-copy', 1, '2024-03-14 16:05:36', 1, '2024-03-14 16:08:16', '0', '（菜单）剪切板', '0', '0', 1, 9, '0');
INSERT INTO `sys_menu` VALUES (12, 'print', 'MENU.Print', 'MENU.Print', 3, '/system-tools/print', '/src/views/SystemTools/Print', '0', '0', NULL, '~icons/ic/outline-local-printshop', 1, '2024-03-14 16:06:41', 1, '2024-03-14 16:08:20', '0', '（菜单）打印', '0', '0', 1, 9, '0');
INSERT INTO `sys_menu` VALUES (13, 'websocket', 'MENU.WebSocket', 'MENU.WebSocket', 4, '/system-tools/websocket', '/src/views/SystemTools/WebSocket', '0', '0', NULL, '~icons/tabler/brand-socket-io', 1, '2024-03-14 16:07:53', 1, '2024-03-14 16:08:23', '0', '（菜单）WebSocket', '0', '0', 1, 9, '0');
INSERT INTO `sys_menu` VALUES (14, 'code-templates', NULL, 'MENU.ListTemplates', 20, NULL, NULL, '0', '0', NULL, '~icons/solar/code-bold', 1, '2024-03-14 16:13:21', 1, '2024-03-25 10:25:55', '0', '（菜单）代码模板', '0', '0', 1, 0, '1');
INSERT INTO `sys_menu` VALUES (15, 'list-templates', 'MENU.ListTemplates', 'MENU.ListTemplates', 1, '/code-templates/list-templates', '/src/views/CodeTemplates/ListTemplates', '0', '0', NULL, '~icons/ic/outline-list-alt', 1, '2024-03-14 16:14:30', 1, '2024-03-14 16:14:34', '0', '（菜单）列表模板', '0', '0', 1, 14, '0');
INSERT INTO `sys_menu` VALUES (16, 'universal-components', NULL, 'MENU.UniversalComponents', 25, NULL, NULL, '0', '0', NULL, '~icons/ic/baseline-auto-awesome-mosaic', 1, '2024-03-14 16:16:13', 1, '2024-03-25 10:24:22', '0', '（菜单）通用组件', '0', '0', 1, 0, '1');
INSERT INTO `sys_menu` VALUES (17, 'charts', 'MENU.Charts', 'MENU.Charts', 1, '/universal-components/charts', '/src/views/UniversalComponents/Charts', '0', '0', NULL, '~icons/ic/baseline-bar-chart', 1, '2024-03-14 16:17:28', 1, '2024-03-14 16:17:31', '0', '（菜单）图表', '0', '0', 1, 16, '0');
INSERT INTO `sys_menu` VALUES (18, 'builtin-components', NULL, 'MENU.BuiltinComponents', 30, NULL, NULL, '0', '0', NULL, '~icons/mdi/puzzle', 1, '2024-03-14 16:18:15', 1, '2024-03-25 10:24:27', '0', '（菜单）内置组件', '0', '0', 1, 0, '1');
INSERT INTO `sys_menu` VALUES (19, 'digital-animation', 'MENU.DigitalAnimation', 'MENU.DigitalAnimation', 1, '/builtin-components/digital-animation', '/src/views/BuiltinComponents/DigitalAnimation', '0', '0', NULL, '~icons/ic/baseline-hourglass-empty', 1, '2024-03-14 16:19:18', 1, '2024-03-14 16:20:29', '0', '（菜单）数字动画', '0', '0', 1, 18, '0');
INSERT INTO `sys_menu` VALUES (20, 'timeline', 'MENU.Timeline', 'MENU.Timeline', 2, '/builtin-components/timeline', '/src/views/BuiltinComponents/Timeline', '0', '0', NULL, '~icons/ic/baseline-timeline', 1, '2024-03-14 16:20:19', 1, '2024-03-14 16:20:35', '0', '（菜单）时间线', '0', '0', 1, 18, '0');
INSERT INTO `sys_menu` VALUES (21, 'error-pages', NULL, 'MENU.ERROR.PAGES', 35, NULL, NULL, '0', '0', NULL, '~icons/ic/baseline-error-outline', 1, '2024-03-14 16:21:19', 1, '2024-03-25 10:24:37', '0', '（菜单）错误界面', '0', '0', 1, 0, '1');
INSERT INTO `sys_menu` VALUES (22, '403', '403', '403', 1, '/error-pages/403', '/src/pages/error-pages/403', '0', '0', NULL, '~icons/ic/baseline-do-not-disturb', 1, '2024-03-14 16:22:59', 1, '2024-03-14 16:25:30', '0', '（菜单）403', '0', '0', 1, 21, '0');
INSERT INTO `sys_menu` VALUES (23, '404', '404', '404', 2, '/error-pages/404', '/src/pages/error-pages/404', '0', '0', NULL, '~icons/tabler/error-404', 1, '2024-03-14 16:24:02', 1, '2024-03-14 16:25:35', '0', '（菜单）404', '0', '0', 1, 21, '0');
INSERT INTO `sys_menu` VALUES (24, '418', '418', '418', 3, '/error-pages/418', '/src/pages/error-pages/418', '0', '0', NULL, '~icons/icon-park-outline/tea-drink', 1, '2024-03-14 16:24:42', 1, '2024-03-14 16:25:40', '0', '（菜单）418', '0', '0', 1, 21, '0');
INSERT INTO `sys_menu` VALUES (25, '500', '500', '500', 4, '/error-pages/500', '/src/pages/error-pages/500', '0', '0', NULL, '~icons/lucide/server-off', 1, '2024-03-14 16:25:23', 1, '2024-03-14 16:25:42', '0', '（菜单）500', '0', '0', 1, 21, '0');
INSERT INTO `sys_menu` VALUES (26, 'user-info', 'MENU.UserInfo', NULL, NULL, '/user-info', '/src/views/User/UserInfo', '1', '0', NULL, '~icons/mdi/account-circle', 1, '2024-03-14 16:27:10', 1, '2024-03-14 16:27:26', '0', '（仅路由）', '0', '0', 1, 0, '0');
INSERT INTO `sys_menu` VALUES (27, 'change-password', 'MENU.ChangePassword', NULL, NULL, '/change-password', '/src/views/User/ChangePassword', '1', '0', NULL, '~icons/mdi/key', 1, '2024-03-14 16:29:56', NULL, NULL, '0', '（仅路由）', '0', '0', 1, 0, '0');
INSERT INTO `sys_menu` VALUES (28, 'pathMatch', '404', NULL, NULL, '/:pathMatch(.*)*', '/src/pages/error-pages/404', '1', '0', NULL, '~icons/tabler/error-404', 1, '2024-03-14 16:31:44', NULL, NULL, '0', '（仅路由）通配符路由', '0', '0', 1, 0, '0');
INSERT INTO `sys_menu` VALUES (29, 'auth-layout', NULL, NULL, NULL, '/', '/src/layouts/AuthLayout', '1', '0', NULL, '#', 1, '2024-03-14 16:41:22', NULL, NULL, '0', '（仅路由）认证页面', '1', '1', 0, 0, '1');
INSERT INTO `sys_menu` VALUES (30, 'login', 'MENU.Login', NULL, NULL, '/login', '/src/views/Auth/Login', '1', '0', NULL, '#', 1, '2024-03-14 16:42:24', NULL, NULL, '0', '（仅路由）', '1', '1', 29, 29, '0');
INSERT INTO `sys_menu` VALUES (31, 'signup', 'MENU.Signup', NULL, NULL, '/signup', '/src/views/Auth/Signup', '1', '0', NULL, '#', 1, '2024-03-14 16:43:16', NULL, NULL, '0', '（仅路由）', '1', '1', 29, 29, '0');
INSERT INTO `sys_menu` VALUES (32, 'auth-redirect', 'TEMP.AuthRedirect.Authorizing', NULL, NULL, '/auth-redirect', '/src/views/Auth/AuthRedirect', '1', '0', NULL, '#', 1, '2024-03-14 16:44:04', NULL, NULL, '0', '（仅路由）授权重定向页面', '1', '1', 0, 0, '0');
INSERT INTO `sys_menu` VALUES (33, 'cabinet-related', NULL, 'MENU.CabinetRelated', 10, NULL, NULL, '0', '0', NULL, '~icons/mdi/file-cabinet', 1, '2024-03-25 09:44:31', 1, '2024-03-25 10:26:48', '0', '（菜单）橱柜相关', '0', '0', 1, 0, '1');
INSERT INTO `sys_menu` VALUES (34, 'Quotation', 'MENU.Quotation', 'MENU.Quotation', NULL, '/cabinet-related/quotaion', '/src/views/CabinetRelated/Quotation', '0', '0', NULL, '~icons/mdi/file-document-outline', 1, '2024-03-25 10:37:49', NULL, NULL, '0', '（菜单）报价单', '0', '0', 1, 33, '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                             `role_id` bigint NOT NULL AUTO_INCREMENT,
                             `role_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                             `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色权限字符串',
                             `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
                             `del_flag` int NULL DEFAULT 0 COMMENT 'del_flag',
                             `create_by` bigint NULL DEFAULT NULL,
                             `create_time` datetime NULL DEFAULT NULL,
                             `update_by` bigint NULL DEFAULT NULL,
                             `update_time` datetime NULL DEFAULT NULL,
                             `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
                             PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'ROLE_ADMIN', '0', 0, NULL, '2024-03-07 11:02:14', 3, '2024-03-07 16:04:56', '删掉这个角色吧');
INSERT INTO `sys_role` VALUES (2, '平民', 'ROLE_CIVILIAN', '0', 0, 3, '2024-03-08 09:09:41', 3, '2024-03-08 16:11:08', NULL);
INSERT INTO `sys_role` VALUES (3, '猴子国王', 'ROLE_MonkeyKing', '0', 0, 1, '2024-03-13 11:40:40', NULL, NULL, '猴子国王是猴子王国的国王');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
                                  `role_id` bigint NOT NULL COMMENT '角色ID',
                                  `menu_id` bigint NOT NULL COMMENT '菜单id',
                                  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 7);
INSERT INTO `sys_role_menu` VALUES (1, 8);
INSERT INTO `sys_role_menu` VALUES (1, 9);
INSERT INTO `sys_role_menu` VALUES (1, 10);
INSERT INTO `sys_role_menu` VALUES (1, 11);
INSERT INTO `sys_role_menu` VALUES (1, 12);
INSERT INTO `sys_role_menu` VALUES (1, 13);
INSERT INTO `sys_role_menu` VALUES (1, 14);
INSERT INTO `sys_role_menu` VALUES (1, 15);
INSERT INTO `sys_role_menu` VALUES (1, 16);
INSERT INTO `sys_role_menu` VALUES (1, 17);
INSERT INTO `sys_role_menu` VALUES (1, 18);
INSERT INTO `sys_role_menu` VALUES (1, 19);
INSERT INTO `sys_role_menu` VALUES (1, 20);
INSERT INTO `sys_role_menu` VALUES (1, 21);
INSERT INTO `sys_role_menu` VALUES (1, 22);
INSERT INTO `sys_role_menu` VALUES (1, 23);
INSERT INTO `sys_role_menu` VALUES (1, 24);
INSERT INTO `sys_role_menu` VALUES (1, 25);
INSERT INTO `sys_role_menu` VALUES (1, 33);
INSERT INTO `sys_role_menu` VALUES (1, 34);
INSERT INTO `sys_role_menu` VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                             `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '用户名',
                             `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '呢称',
                             `pass_word` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                             `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '账号状态（0正常1停用)',
                             `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
                             `phone_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
                             `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户性别（0女，1男，2未知)',
                             `avatar_Url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
                             `user_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '1' COMMENT '用户类型（O管理员，1普通用户)',
                             `create_by` bigint NULL DEFAULT NULL COMMENT '创建人的用户id',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_by` bigint NULL DEFAULT NULL COMMENT '更新人',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             `del_flag` int NULL DEFAULT 0 COMMENT '删除标志（O代表未删除，1代表已删除)',
                             `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
                             `birth_Date` datetime NULL DEFAULT NULL COMMENT '生日',
                             `country` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '国家',
                             `province` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省份',
                             `city` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '城市',
                             `address` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
                             `biography` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '简介',
                             PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '管理员张三8', '$2a$10$fsZXpWU9r7WR5ca7s7SNT.vxzarjEtJyqpdSb15gI3.n0qkhBIYUG', '0', '5713392748@qq.com', '13577777778', '1', 'http://127.0.0.1:9000/fishtest-avatar/2024-03-28/icon_1711597293747.svg', '0', NULL, NULL, 1, '2024-03-28 11:41:34', 0, '张三88', '2024-01-15 08:00:00', NULL, NULL, NULL, '长秋秋', '11111122233344');
INSERT INTO `sys_user` VALUES (2, '2', '普通人李四', '$2a$10$fsZXpWU9r7WR5ca7s7SNT.vxzarjEtJyqpdSb15gI3.n0qkhBIYUG', '0', '233@gmail.com', '13577777777', '1', NULL, '1', NULL, '2024-01-08 16:05:42', 1, '2024-03-28 11:39:48', 0, '李四', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (3, '3', '普通人张四', '$2a$10$DC8H1hZ5lnCdMu7glYqJ8u6nW1ECQ.gfyTGtPHuDX0twyxj7xPVuO', '1', NULL, '13577777777', '0', NULL, '1', NULL, '2024-01-08 16:05:42', 3, '2024-02-02 10:54:52', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (4, '4', '普通人张五', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '1', NULL, NULL, '0', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (5, '5', '普通人张六', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', 'qqq@qq.com', '', '1', NULL, '1', NULL, '2024-01-08 16:05:42', 8, '2024-01-27 13:22:03', 0, 'Hollow', '2024-01-27 08:00:00', NULL, NULL, NULL, 'zhong', '111');
INSERT INTO `sys_user` VALUES (6, '6', '普通人张三', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', NULL, '13577777777', '1', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (7, '7', '普通人张三', '$2a$10$fsZXpWU9r7WR5ca7s7SNT.vxzarjEtJyqpdSb15gI3.n0qkhBIYUG', '0', NULL, NULL, '1', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (8, '8', '普通人张三', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', NULL, NULL, '0', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (9, '9', '普通人张三', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', NULL, NULL, '2', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (10, '10', '普通人张三', '$2a$10$s7iZYbu3zSl/w2AXDTwAxucOsodd649FU/buPbixJvnOi0aFCTt0.', '0', NULL, NULL, '1', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (11, '11', '普通人张三', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', NULL, NULL, '0', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (12, '12', '普通人张三', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', NULL, NULL, '2', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (13, 'testcreate', '用户20245558030231', '$2a$10$8097YThwalMC5FwqnyeZz.XgutVX63QaYPvTr5Z/EmmrPtXfTo91i', '0', NULL, NULL, NULL, NULL, '1', 1, '2024-03-13 11:40:12', 1, '2024-03-13 11:40:12', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (14, 'jacktest', '用户20242635764175', '$2a$10$dYWqUy9tYok.RQsliSYaV.kaYo37y65VzLHwvYiXr/Jx1fdOGL.Qm', '0', NULL, NULL, NULL, NULL, '1', 1, '2024-03-14 13:38:12', 1, '2024-03-14 13:38:12', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
                                  `user_id` bigint NOT NULL COMMENT '用户id',
                                  `role_id` bigint NOT NULL COMMENT '角色id',
                                  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (1, 2);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (3, 2);
INSERT INTO `sys_user_role` VALUES (4, 2);
INSERT INTO `sys_user_role` VALUES (5, 2);
INSERT INTO `sys_user_role` VALUES (6, 2);
INSERT INTO `sys_user_role` VALUES (7, 2);
INSERT INTO `sys_user_role` VALUES (8, 2);
INSERT INTO `sys_user_role` VALUES (9, 2);
INSERT INTO `sys_user_role` VALUES (10, 2);
INSERT INTO `sys_user_role` VALUES (11, 2);
INSERT INTO `sys_user_role` VALUES (12, 2);
INSERT INTO `sys_user_role` VALUES (13, 3);
INSERT INTO `sys_user_role` VALUES (14, 2);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
                            `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '这是事务中加入的teacher');
INSERT INTO `teacher` VALUES (2, 'tom');
INSERT INTO `teacher` VALUES (3, '测试清空一级缓存');
INSERT INTO `teacher` VALUES (4, '这是事务中加入的teacher');
INSERT INTO `teacher` VALUES (5, '这是事务中加入的teacher');

SET FOREIGN_KEY_CHECKS = 1;
