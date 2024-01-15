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

 Date: 15/01/2024 18:07:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
  `menu_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '菜单名',
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
  `del_flag` int NULL DEFAULT 0 COMMENT '是否删除（0未删除 1已删除）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (2, 'NULL', NULL, NULL, '0', '0', 'updateUser', '#', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (3, 'NULL', NULL, NULL, '0', '0', 'check', '#', NULL, NULL, NULL, NULL, 0, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (3, '管理者', 'ROLE_ADMIN', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (4, '普通用户', 'ROLE_ORDINARY', '0', 0, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `menu_id` bigint NOT NULL DEFAULT 0 COMMENT '菜单id',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (3, 2);
INSERT INTO `sys_role_menu` VALUES (3, 3);
INSERT INTO `sys_role_menu` VALUES (4, 3);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '呢称',
  `pass_word` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '账号状态（0正常1停用)',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户性别（0女，1男，2未知)',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
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
  `brief_introduction` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (3, '1', '管理员张三', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', '571339274@qq.com', '13577777777', '0', NULL, '0', NULL, NULL, NULL, NULL, 0, '张三', '2024-01-15 16:18:49', '中国', '河北省', '邯郸市', '长秋', NULL);
INSERT INTO `sys_user` VALUES (5, '2', '普通人张三', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '1', NULL, '13577777777', '1', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (6, '3', '普通人张四', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '1', NULL, '13577777777', '0', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (7, '4', '普通人张五', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '1', NULL, NULL, '0', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (8, '5', '普通人张六', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', NULL, '13577777777', '1', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (9, '6', '普通人张三', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', NULL, '13577777777', '1', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (10, '7', '普通人张三', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', NULL, NULL, '1', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (11, '8', '普通人张三', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', NULL, NULL, '0', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (12, '9', '普通人张三', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', NULL, NULL, '2', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (13, '10', '普通人张三', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', NULL, NULL, '1', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (14, '11', '普通人张三', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', NULL, NULL, '0', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (15, '12', '普通人张三', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', NULL, NULL, '2', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `role_id` bigint NOT NULL DEFAULT 0 COMMENT '角色id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (3, 3);
INSERT INTO `sys_user_role` VALUES (3, 4);
INSERT INTO `sys_user_role` VALUES (5, 4);

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

SET FOREIGN_KEY_CHECKS = 1;
