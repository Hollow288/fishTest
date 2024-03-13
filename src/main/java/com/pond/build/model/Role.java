package com.pond.build.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * 角色表(SysRole)实体类
 *
 * @author makejava
 * @since 2024-01-15 17:45:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role")
public class Role implements Serializable {
    private static final long serialVersionUID = -13372344778559328L;

    @TableId(type = IdType.AUTO)
    private String roleId;

    private String roleName;
/**
     * 角色权限字符串
     */
    private String roleKey;
/**
     * 角色状态（0正常 1停用）
     */
    private String status;
/**
     * del_flag
     */
    private Integer delFlag;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
/**
     * 备注
     */
    private String remark;


}

