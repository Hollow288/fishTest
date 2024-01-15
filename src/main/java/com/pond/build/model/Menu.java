package com.pond.build.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@TableName(value="sys_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
// Jackson 库中的注解，它用于控制在序列化 Java 对象为 JSON 字符串时，是否包含值为 null 的属性。
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu implements Serializable {
    private static final long serialVersionUID = -54979041104113736L;

    @TableId
    private Long menuId;
    /**
     * 菜单名
     */
    private String menuName;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 组件路径
     */
    private String component;
    /**
     * 菜单状态（0显示 1隐藏）
     */
    private String visible;
    /**
     * 菜单状态（0正常 1停用）
     */
    private String status;
    /**
     * 权限标识
     */
    private String perms;
    /**
     * 菜单图标
     */
    private String icon;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    /**
     * 是否删除（0未删除 1已删除）
     */
    private Integer delFlag;
    /**
     * 备注
     */
    private String remark;
}
