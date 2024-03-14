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

 Date: 14/03/2024 18:07:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice_management
-- ----------------------------
INSERT INTO `notice_management` VALUES (1, '这是一条测试的骚扰通知··', 1, '2024-03-13 11:41:18', 1, '2024-03-13 11:53:41', '0', NULL, NULL, '2024-03-13 11:53:41', 1);
INSERT INTO `notice_management` VALUES (2, '这是创建并发布的骚扰通知 ×2', 1, '2024-03-13 11:58:57', NULL, NULL, '0', NULL, NULL, '2024-03-13 11:58:57', 1);
INSERT INTO `notice_management` VALUES (3, '单独发给管理员的骚扰通知😈', 1, '2024-03-14 13:49:06', NULL, NULL, '0', NULL, NULL, '2024-03-14 13:49:06', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `pending_notification` VALUES (9, 3, '1', 1, '0');

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
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 'base-layout', NULL, '', NULL, '/', '@/layouts/BaseLayout', '1', '0', NULL, '#', NULL, NULL, 1, '2024-03-14 16:34:39', '0', '（仅路由）', '0', '0', 0, 0, '0');
INSERT INTO `sys_menu` VALUES (2, 'navigation', 'MENU.Navigation', 'MENU.Navigation', 1, '/', '@/views/Navigation', '0', '0', NULL, '~icons/mdi/compass-outline', NULL, NULL, 3, '2024-03-06 14:18:48', '0', '（菜单）站点导航', '0', '0', 1, 0, '0');
INSERT INTO `sys_menu` VALUES (3, 'system-functions', '', 'MENU.SystemFunctions', 2, NULL, NULL, '0', '0', NULL, '~icons/mdi/function-variant', NULL, NULL, 3, '2024-03-06 14:19:16', '0', '（菜单）系统功能', '0', '0', 1, 0, '1');
INSERT INTO `sys_menu` VALUES (4, 'user-management', 'MENU.UserManagement', 'MENU.UserManagement', 1, '/system-functions/user-management', '@/views/SystemFunctions/UserManagement', '0', '0', NULL, '~icons/mdi/account-cog-outline', NULL, NULL, 1, '2024-03-14 13:59:38', '0', '（菜单）用户管理', '0', '0', 1, 3, '0');
INSERT INTO `sys_menu` VALUES (5, 'menu-management', 'MENU.MenuManagement', 'MENU.MenuManagement', 2, '/system-functions/menu-management', '@/views/SystemFunctions/MenuManagement', '0', '0', NULL, '~icons/mdi/account-cog-outline', NULL, NULL, 1, '2024-03-14 14:00:00', '0', '（菜单）菜单管理', '0', '0', 1, 3, '0');
INSERT INTO `sys_menu` VALUES (6, 'test-keyName', NULL, NULL, 0, NULL, NULL, '0', '0', NULL, '#', 3, '2024-03-13 11:33:07', 1, '2024-03-14 15:12:58', '1', '测试', '0', '0', 1, 0, '0');
INSERT INTO `sys_menu` VALUES (7, 'role-management', 'MENU.RoleManagement', 'MENU.RoleManagement', 3, '/system-functions/role-management', '@/views/SystemFunctions/RoleManagement', '0', '0', NULL, '@vicons/ionicons5/PricetagOutline', 1, '2024-03-14 15:57:55', 1, '2024-03-14 16:14:43', '0', '（菜单）角色管理', '0', '0', 1, 3, '0');
INSERT INTO `sys_menu` VALUES (8, 'notice-management', 'MENU.NoticeManagement', 'MENU.RoleManagement', 4, '/system-functions/notice-management', '@/views/SystemFunctions/NoticeManagement', '0', '0', NULL, '@vicons/ionicons5/ChatbubbleEllipsesOutline', 1, '2024-03-14 15:59:31', 1, '2024-03-14 16:14:51', '0', '（菜单）通知公告', '0', '0', 1, 3, '0');
INSERT INTO `sys_menu` VALUES (9, 'system-tools', NULL, 'MENU.SystemTools', 3, NULL, NULL, '0', '0', NULL, '~icons/mdi/tools', 1, '2024-03-14 16:01:34', 1, '2024-03-14 16:01:50', '0', '（菜单）系统工具', '0', '0', 1, 0, '1');
INSERT INTO `sys_menu` VALUES (10, 'qrcode', 'MENU.QRCode', 'MENU.QRCode', 1, '/system-tools/qrcode', '@/views/SystemTools/QRCode', '0', '0', NULL, '~icons/ic/baseline-qrcode', 1, '2024-03-14 16:03:55', 1, '2024-03-14 16:12:29', '0', '（菜单）二维码', '0', '0', 1, 9, '0');
INSERT INTO `sys_menu` VALUES (11, 'clipboard', 'MENU.Clipboard', 'MENU.Clipboard', 2, '/system-tools/clipboard', '@/views/SystemTools/Clipboard', '0', '0', NULL, '~icons/ic/baseline-content-copy', 1, '2024-03-14 16:05:36', 1, '2024-03-14 16:08:16', '0', '（菜单）剪切板', '0', '0', 1, 9, '0');
INSERT INTO `sys_menu` VALUES (12, 'print', 'MENU.Print', 'MENU.Print', 3, '/system-tools/print', '@/views/SystemTools/Print', '0', '0', NULL, '~icons/ic/outline-local-printshop', 1, '2024-03-14 16:06:41', 1, '2024-03-14 16:08:20', '0', '（菜单）打印', '0', '0', 1, 9, '0');
INSERT INTO `sys_menu` VALUES (13, 'websocket', 'MENU.WebSocket', 'MENU.WebSocket', 4, '/system-tools/websocket', '@/views/SystemTools/WebSocket', '0', '0', NULL, '~icons/tabler/brand-socket-io', 1, '2024-03-14 16:07:53', 1, '2024-03-14 16:08:23', '0', '（菜单）WebSocket', '0', '0', 1, 9, '0');
INSERT INTO `sys_menu` VALUES (14, 'code-templates', NULL, 'MENU.ListTemplates', 4, NULL, NULL, '0', '0', NULL, '~icons/solar/code-bold', 1, '2024-03-14 16:13:21', 1, '2024-03-14 16:13:24', '0', '（菜单）代码模板', '0', '0', 1, 0, '1');
INSERT INTO `sys_menu` VALUES (15, 'list-templates', 'MENU.ListTemplates', 'MENU.ListTemplates', 1, '/code-templates/list-templates', '@/views/CodeTemplates/ListTemplates', '0', '0', NULL, '~icons/ic/outline-list-alt', 1, '2024-03-14 16:14:30', 1, '2024-03-14 16:14:34', '0', '（菜单）列表模板', '0', '0', 1, 14, '0');
INSERT INTO `sys_menu` VALUES (16, 'universal-components', NULL, 'MENU.UniversalComponents', 5, NULL, NULL, '0', '0', NULL, '~icons/ic/baseline-auto-awesome-mosaic', 1, '2024-03-14 16:16:13', 1, '2024-03-14 16:16:17', '0', '（菜单）通用组件', '0', '0', 1, 0, '1');
INSERT INTO `sys_menu` VALUES (17, 'charts', 'MENU.Charts', 'MENU.Charts', 1, '/universal-components/charts', '@/views/UniversalComponents/Charts', '0', '0', NULL, '~icons/ic/baseline-bar-chart', 1, '2024-03-14 16:17:28', 1, '2024-03-14 16:17:31', '0', '（菜单）图表', '0', '0', 1, 16, '0');
INSERT INTO `sys_menu` VALUES (18, 'builtin-components', NULL, 'MENU.BuiltinComponents', 6, NULL, NULL, '0', '0', NULL, '~icons/mdi/puzzle', 1, '2024-03-14 16:18:15', 1, '2024-03-14 16:18:18', '0', '（菜单）内置组件', '0', '0', 1, 0, '1');
INSERT INTO `sys_menu` VALUES (19, 'digital-animation', 'MENU.DigitalAnimation', 'MENU.DigitalAnimation', 1, '/builtin-components/digital-animation', '@/views/BuiltinComponents/DigitalAnimation', '0', '0', NULL, '~icons/ic/baseline-hourglass-empty', 1, '2024-03-14 16:19:18', 1, '2024-03-14 16:20:29', '0', '（菜单）数字动画', '0', '0', 1, 18, '0');
INSERT INTO `sys_menu` VALUES (20, 'timeline', 'MENU.Timeline', 'MENU.Timeline', 2, '/builtin-components/timeline', '@/views/BuiltinComponents/Timeline', '0', '0', NULL, '~icons/ic/baseline-timeline', 1, '2024-03-14 16:20:19', 1, '2024-03-14 16:20:35', '0', '（菜单）时间线', '0', '0', 1, 18, '0');
INSERT INTO `sys_menu` VALUES (21, 'error-pages', NULL, 'MENU.ERROR.PAGES', 7, NULL, NULL, '0', '0', NULL, '~icons/ic/baseline-error-outline', 1, '2024-03-14 16:21:19', 1, '2024-03-14 16:21:26', '0', '（菜单）错误界面', '0', '0', 1, 0, '1');
INSERT INTO `sys_menu` VALUES (22, '403', '403', '403', 1, '/error-pages/403', '@/pages/error-pages/403', '0', '0', NULL, '~icons/ic/baseline-do-not-disturb', 1, '2024-03-14 16:22:59', 1, '2024-03-14 16:25:30', '0', '（菜单）403', '0', '0', 1, 21, '0');
INSERT INTO `sys_menu` VALUES (23, '404', '404', '404', 2, '/error-pages/404', '@/pages/error-pages/404', '0', '0', NULL, '~icons/tabler/error-404', 1, '2024-03-14 16:24:02', 1, '2024-03-14 16:25:35', '0', '（菜单）404', '0', '0', 1, 21, '0');
INSERT INTO `sys_menu` VALUES (24, '418', '418', '418', 3, '/error-pages/418', '@/pages/error-pages/418', '0', '0', NULL, '~icons/icon-park-outline/tea-drink', 1, '2024-03-14 16:24:42', 1, '2024-03-14 16:25:40', '0', '（菜单）418', '0', '0', 1, 21, '0');
INSERT INTO `sys_menu` VALUES (25, '500', '500', '500', 4, '/error-pages/500', '@/pages/error-pages/500', '0', '0', NULL, '~icons/lucide/server-off', 1, '2024-03-14 16:25:23', 1, '2024-03-14 16:25:42', '0', '（菜单）500', '0', '0', 1, 21, '0');
INSERT INTO `sys_menu` VALUES (26, 'user-info', 'MENU.UserInfo', NULL, NULL, '/user-info', '@/views/User/UserInfo', '1', '0', NULL, '~icons/mdi/account-circle', 1, '2024-03-14 16:27:10', 1, '2024-03-14 16:27:26', '0', '（仅路由）', '0', '0', 1, 0, '0');
INSERT INTO `sys_menu` VALUES (27, 'change-password', 'MENU.ChangePassword', NULL, NULL, '/change-password', '@/views/User/ChangePassword', '1', '0', NULL, '~icons/mdi/key', 1, '2024-03-14 16:29:56', NULL, NULL, '0', '（仅路由）', '0', '0', 1, 0, '0');
INSERT INTO `sys_menu` VALUES (28, 'pathMatch', '404', NULL, NULL, '/:pathMatch(.*)*', '@/pages/error-pages/404', '1', '0', NULL, '~icons/tabler/error-404', 1, '2024-03-14 16:31:44', NULL, NULL, '0', '（仅路由）通配符路由', '0', '0', 1, 0, '0');
INSERT INTO `sys_menu` VALUES (29, 'auth-layout', NULL, NULL, NULL, '/', '@/layouts/AuthLayout', '1', '0', NULL, '#', 1, '2024-03-14 16:41:22', NULL, NULL, '0', '（仅路由）认证页面', '1', '1', 0, 0, '1');
INSERT INTO `sys_menu` VALUES (30, 'login', 'MENU.Login', NULL, NULL, '/login', '@/views/Auth/Login', '1', '0', NULL, '#', 1, '2024-03-14 16:42:24', NULL, NULL, '0', '（仅路由）', '1', '1', 29, 29, '0');
INSERT INTO `sys_menu` VALUES (31, 'signup', 'MENU.Signup', NULL, NULL, '/signup', '@/views/Auth/Signup', '1', '0', NULL, '#', 1, '2024-03-14 16:43:16', NULL, NULL, '0', '（仅路由）', '1', '1', 29, 29, '0');
INSERT INTO `sys_menu` VALUES (32, 'auth-redirect', 'TEMP.AuthRedirect.Authorizing', NULL, NULL, '/auth-redirect', '@/views/Auth/AuthRedirect', '1', '0', NULL, '#', 1, '2024-03-14 16:44:04', NULL, NULL, '0', '（仅路由）授权重定向页面', '1', '1', 0, 0, '0');

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
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '管理员张三8', '$2a$10$fsZXpWU9r7WR5ca7s7SNT.vxzarjEtJyqpdSb15gI3.n0qkhBIYUG', '0', '5713392748@qq.com', '13577777778', '1', 'http://127.0.0.1:9000/fishtest-avatar/2024-03-06/icon_1709713499655.svg', '0', NULL, NULL, 3, '2024-03-08 16:27:19', 0, '张三88', '2024-01-15 08:00:00', NULL, NULL, NULL, '长秋秋', '11111122233344');
INSERT INTO `sys_user` VALUES (2, '2', '普通人李四', '$2a$10$YdV4TVf8Gm31BMgZOlHCeubVl2oe.peeYIcfFAxOsmtsVMIhFuUme', '0', '233@gmail.com', '13577777777', '1', NULL, '1', NULL, '2024-01-08 16:05:42', 3, '2024-03-11 09:05:37', 0, '李四', NULL, NULL, NULL, NULL, NULL, NULL);
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
