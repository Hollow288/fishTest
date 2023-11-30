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

 Date: 30/11/2023 18:07:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '这是事务中加入的teacher');
INSERT INTO `teacher` VALUES (2, 'tom');
INSERT INTO `teacher` VALUES (3, '测试清空一级缓存');
INSERT INTO `teacher` VALUES (4, '这是事务中加入的teacher');
INSERT INTO `teacher` VALUES (5, '这是事务中加入的teacher');

-- ----------------------------
-- Table structure for test_copy1
-- ----------------------------
DROP TABLE IF EXISTS `test_copy1`;
CREATE TABLE `test_copy1`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_copy1
-- ----------------------------
INSERT INTO `test_copy1` VALUES (1, 'jack', 12, '2023-11-16');
INSERT INTO `test_copy1` VALUES (3, 'yak', 14, '2023-11-24');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `tid` int NULL DEFAULT NULL COMMENT '教师id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (21, 'jack', 23, NULL, 1);
INSERT INTO `user` VALUES (23, 'tom', 24, NULL, 1);
INSERT INTO `user` VALUES (24, 'jack', 23, NULL, 1);
INSERT INTO `user` VALUES (26, 'jack', 23, NULL, 1);
INSERT INTO `user` VALUES (27, 'jack', 23, NULL, 1);
INSERT INTO `user` VALUES (28, 'jack', 23, NULL, NULL);
INSERT INTO `user` VALUES (29, 'jack', 23, NULL, NULL);
INSERT INTO `user` VALUES (30, 'jack', 23, NULL, NULL);
INSERT INTO `user` VALUES (31, 'jack', 23, NULL, NULL);
INSERT INTO `user` VALUES (32, 'jack', 23, NULL, NULL);
INSERT INTO `user` VALUES (33, 'jack', 23, NULL, NULL);
INSERT INTO `user` VALUES (34, '23431112', 99, NULL, NULL);
INSERT INTO `user` VALUES (35, '23432', 99, NULL, NULL);
INSERT INTO `user` VALUES (36, 'jack', 23, NULL, NULL);
INSERT INTO `user` VALUES (37, 'jack', 23, NULL, NULL);
INSERT INTO `user` VALUES (38, 'jack', 23, NULL, NULL);
INSERT INTO `user` VALUES (39, 'many11', 199, NULL, NULL);
INSERT INTO `user` VALUES (40, 'ma1ny11', 1991, NULL, NULL);
INSERT INTO `user` VALUES (41, 'ma1ny21', 1981, NULL, NULL);
INSERT INTO `user` VALUES (42, 'jack', 23, NULL, NULL);
INSERT INTO `user` VALUES (43, 'many1', 99, NULL, NULL);
INSERT INTO `user` VALUES (44, 'many2', 98, NULL, NULL);
INSERT INTO `user` VALUES (45, 'jack', 23, NULL, NULL);
INSERT INTO `user` VALUES (46, 'jack', 23, NULL, NULL);
INSERT INTO `user` VALUES (47, 'tom', 23, NULL, NULL);
INSERT INTO `user` VALUES (48, 'tom', 23, NULL, NULL);
INSERT INTO `user` VALUES (49, 'tom', 23, NULL, NULL);
INSERT INTO `user` VALUES (51, 'tom', 23, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
