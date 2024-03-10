package com.pond.build.model.Response;

import com.pond.build.model.Menu;
import lombok.*;

import java.io.Serializable;
import java.util.List;


/**
 * 用于角色管理中的菜单显示
 */


@Data
// Jackson 库中的注解，它用于控制在序列化 Java 对象为 JSON 字符串时，是否包含值为 null 的属性。
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuRoleResponse implements Serializable {
    private static final long serialVersionUID = 912709152727809735L;

    private String value;


    private List<MenuRoleResponse> children;


    /**
     * 是否删除（0未删除 1已删除）
     */
    private String delFlag;

    /**
     * 备注
     */
    private String label;

    /**
     * 菜单父id
     */
    private String menuParentId;



}
