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

 Date: 11/03/2024 15:56:48
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
) ENGINE = InnoDB AUTO_INCREMENT = 1765289441486381058 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 'base-layout', NULL, '', 1, '/', '@/layouts/BaseLayout', '1', '0', NULL, '#', NULL, NULL, 3, '2024-03-06 14:18:58', '0', '（仅路由）', '0', '0', 0, 0, '0');
INSERT INTO `sys_menu` VALUES (2, 'navigation', 'MENU.Navigation', 'MENU.Navigation', 1, '/', '@/views/Navigation', '0', '0', NULL, '~icons/mdi/compass-outline', NULL, NULL, 3, '2024-03-06 14:18:48', '0', '（菜单）站点导航', '0', '0', 1, 0, '0');
INSERT INTO `sys_menu` VALUES (3, 'system-functions', '', 'MENU.SystemFunctions', 2, NULL, NULL, '0', '0', NULL, '~icons/mdi/function-variant', NULL, NULL, 3, '2024-03-06 14:19:16', '0', '（菜单）系统功能', '0', '0', 1, 0, '1');
INSERT INTO `sys_menu` VALUES (4, 'user-management', 'MENU.UserManagement', 'MENU.UserManagement', 1, '${SYSTEM_FUNCTIONS_PREFIX}/user-management', '@/views/SystemFunctions/UserManagement', '0', '0', NULL, '~icons/mdi/account-cog-outline', NULL, NULL, 3, '2024-03-11 09:35:32', '0', '（菜单）用户管理', '0', '0', 1, 3, '0');
INSERT INTO `sys_menu` VALUES (5, 'menu-management', 'MENU.MenuManagement', 'MENU.MenuManagement', 2, '${SYSTEM_FUNCTIONS_PREFIX}/menu-management', '@/views/SystemFunctions/MenuManagement', '0', '0', NULL, '~icons/mdi/account-cog-outline', NULL, NULL, 3, '2024-03-11 09:35:49', '0', '（菜单）菜单管理', '0', '0', 1, 3, '0');
INSERT INTO `sys_menu` VALUES (1765204642725765122, '测试', '测试1', '测试2', 3, '测试3', '测试5', '1', '0', NULL, '测试4', 3, '2024-03-06 10:35:57', 3, '2024-03-06 14:17:06', '1', '测试6', '0', '1', 1, 0, '0');
INSERT INTO `sys_menu` VALUES (1765264444961271809, '测试123', '测试123', '测试123', NULL, '测试123', '测试123', '0', '0', NULL, '测试123', 3, '2024-03-06 14:33:35', 3, '2024-03-06 14:34:04', '1', '测试123', '0', '0', 1, 3, '0');
INSERT INTO `sys_menu` VALUES (1765264892720046081, '测试123测试123', '测试123测试123', '测试123测试123', NULL, '测试123测试123', '测试123测试123', '0', '0', NULL, '测试123测试123', 3, '2024-03-06 14:35:22', 3, '2024-03-06 15:32:07', '1', '测试123测试123', '0', '0', 1, 2, '0');
INSERT INTO `sys_menu` VALUES (1765276843302920193, '999', '699', '999', NULL, '99', '99', '0', '0', NULL, '9', 3, '2024-03-06 15:22:51', 3, '2024-03-06 16:06:57', '1', '9', '0', '0', 1, 2, '0');
INSERT INTO `sys_menu` VALUES (1765289441486381057, 'system-tools', NULL, 'MENU.SystemTools', 3, NULL, '@/views/SystemTools/QRCode', '0', '0', NULL, '~icons/ic/baseline-qrcode', 3, '2024-03-06 16:12:55', 3, '2024-03-07 16:51:17', '0', '（菜单）系统工具', '0', '0', 1, 0, '0');

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
) ENGINE = InnoDB AUTO_INCREMENT = 1766013908521037827 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (3, '管理员', 'ROLE_ADMIN', '0', 0, NULL, '2024-03-07 11:02:14', 3, '2024-03-07 16:04:56', '删掉这个角色吧');
INSERT INTO `sys_role` VALUES (4, '平民', 'ROLE_CIVILIAN', '0', 0, 3, '2024-03-08 09:09:41', 3, '2024-03-08 16:11:08', NULL);
INSERT INTO `sys_role` VALUES (1766013908521037826, '猴子国王', 'ROLE_MonkeyKing', '0', 0, 3, '2024-03-08 16:11:41', NULL, NULL, '猴子国王是猴子王国的国王');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
                                  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                                  `menu_id` bigint NOT NULL DEFAULT 0 COMMENT '菜单id',
                                  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (3, 2);
INSERT INTO `sys_role_menu` VALUES (3, 3);
INSERT INTO `sys_role_menu` VALUES (3, 4);
INSERT INTO `sys_role_menu` VALUES (3, 5);
INSERT INTO `sys_role_menu` VALUES (3, 1765289441486381057);
INSERT INTO `sys_role_menu` VALUES (4, 2);

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
                             `avatar_Url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
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
) ENGINE = InnoDB AUTO_INCREMENT = 1766020802417471491 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (3, 'admin', '管理员张三8', '$2a$10$fsZXpWU9r7WR5ca7s7SNT.vxzarjEtJyqpdSb15gI3.n0qkhBIYUG', '0', '5713392748@qq.com', '13577777778', '1', 'http://127.0.0.1:9000/fishtest-avatar/2024-03-06/icon_1709713499655.svg', '0', NULL, NULL, 3, '2024-03-08 16:27:19', 0, '张三88', '2024-01-15 08:00:00', NULL, NULL, NULL, '长秋秋', '11111122233344');
INSERT INTO `sys_user` VALUES (5, '2', '普通人李四', '$2a$10$YdV4TVf8Gm31BMgZOlHCeubVl2oe.peeYIcfFAxOsmtsVMIhFuUme', '0', '233@gmail.com', '13577777777', '1', NULL, '1', NULL, '2024-01-08 16:05:42', 3, '2024-03-11 09:05:37', 0, '李四', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (6, '3', '普通人张四', '$2a$10$DC8H1hZ5lnCdMu7glYqJ8u6nW1ECQ.gfyTGtPHuDX0twyxj7xPVuO', '1', NULL, '13577777777', '0', NULL, '1', NULL, '2024-01-08 16:05:42', 3, '2024-02-02 10:54:52', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (7, '4', '普通人张五', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '1', NULL, NULL, '0', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (8, '5', '普通人张六', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', 'qqq@qq.com', '', '1', NULL, '1', NULL, '2024-01-08 16:05:42', 8, '2024-01-27 13:22:03', 0, 'Hollow', '2024-01-27 08:00:00', NULL, NULL, NULL, 'zhong', '111');
INSERT INTO `sys_user` VALUES (9, '6', '普通人张三', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', NULL, '13577777777', '1', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (10, '7', '普通人张三', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', NULL, NULL, '1', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (11, '8', '普通人张三', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', NULL, NULL, '0', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (12, '9', '普通人张三', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', NULL, NULL, '2', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (13, '10', '普通人张三', '$2a$10$s7iZYbu3zSl/w2AXDTwAxucOsodd649FU/buPbixJvnOi0aFCTt0.', '0', NULL, NULL, '1', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (14, '11', '普通人张三', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', NULL, NULL, '0', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (15, '12', '普通人张三', '$2a$10$SFmzm8FYtoilOCrpYBQfEeWOC0NRH7rkYhJnPe3swsvYZZ2xD0bjC', '0', NULL, NULL, '2', NULL, '1', NULL, '2024-01-08 16:05:42', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (1751108099067248642, 'createTest', '我ID很长', '$2a$10$7R/TVXG6efrDXd3GcGEfh.MaXc1Bld/NRnMxKLILx.TCkBZE/plo2', '0', 'string@qq.com', NULL, '1', NULL, '1', 3, '2024-01-27 13:01:19', 3, '2024-03-08 16:38:10', 0, '鲨包', '2024-01-26 08:00:00', NULL, NULL, NULL, '这是地址', '这是简介');
INSERT INTO `sys_user` VALUES (1751864693792419841, 'ttttt', '20248346481858', '$2a$10$rH1pYtvhCDv6LRGplFQdvOZbjKjpJBLWypJmSS84Ud9EaosBN2q7C', '0', NULL, NULL, NULL, NULL, '1', 3, '2024-01-29 15:07:46', 3, '2024-01-29 15:07:46', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (1766020802417471490, '再建一个试试', '用户20247032771639', '$2a$10$nB0.KPxepCiosheKeMERnejUBlXk.rSqgX3xccWmxajXjBcwlnSlS', '0', '123@qq.com', NULL, NULL, NULL, '1', 3, '2024-03-08 16:39:05', 1766020802417471490, '2024-03-08 16:40:43', 0, '狗剩', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
                                  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
                                  `role_id` bigint NOT NULL DEFAULT 0 COMMENT '角色id',
                                  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1766020802417471491 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (3, 3);
INSERT INTO `sys_user_role` VALUES (3, 4);
INSERT INTO `sys_user_role` VALUES (5, 4);
INSERT INTO `sys_user_role` VALUES (5, 1766013908521037826);
INSERT INTO `sys_user_role` VALUES (6, 4);
INSERT INTO `sys_user_role` VALUES (6, 1766013908521037826);
INSERT INTO `sys_user_role` VALUES (7, 4);
INSERT INTO `sys_user_role` VALUES (8, 4);
INSERT INTO `sys_user_role` VALUES (9, 4);
INSERT INTO `sys_user_role` VALUES (10, 4);
INSERT INTO `sys_user_role` VALUES (11, 4);
INSERT INTO `sys_user_role` VALUES (12, 4);
INSERT INTO `sys_user_role` VALUES (13, 4);
INSERT INTO `sys_user_role` VALUES (14, 4);
INSERT INTO `sys_user_role` VALUES (15, 4);
INSERT INTO `sys_user_role` VALUES (1751108099067248642, 4);
INSERT INTO `sys_user_role` VALUES (1751864693792419841, 4);
INSERT INTO `sys_user_role` VALUES (1766020802417471490, 4);

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
