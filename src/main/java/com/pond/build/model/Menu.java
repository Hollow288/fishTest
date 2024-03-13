package com.pond.build.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@TableName(value="sys_menu")
@Data
// Jackson 库中的注解，它用于控制在序列化 Java 对象为 JSON 字符串时，是否包含值为 null 的属性。
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu implements Serializable {
    private static final long serialVersionUID = 912709152728809735L;

    @TableId(type = IdType.AUTO)
    private String menuId;


    @TableField(exist = false)
    private List<Menu> children;

    /**
     * key/name
     */
    private String keyName;

    /**
     * 路由title
     */
    private String title;

    /**
     * 菜单label
     */
    private String label;

    /**
     * 排序
     */
    private Integer sort;

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

    private String createBy;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private String updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 是否删除（0未删除 1已删除）
     */
    private String delFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 禁用登录验证（默认为0错）
     */
    private String disableAuth;

    /**
     * 关闭选项卡（默认为0错）
     */
    private String dismissTab;

    /**
     * 路由父id
     */
    private String routerParentId;

    /**
     * 菜单父id
     */
    private String menuParentId;


    /**
     * 是否子节点
     */
    private String isLeaf;

}
