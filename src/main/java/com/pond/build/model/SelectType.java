package com.pond.build.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (SelectType)实体类
 *
 * @author makejava
 * @since 2024-04-09 11:44:06
 */
@Data
@TableName("Select_Type")
public class SelectType implements Serializable {
    private static final long serialVersionUID = 747189217581451489L;
/**
     * 种类ID
     */
    @TableId(type = IdType.AUTO)
    private String typeId;
/**
     * 种类编码
     */
    private String typeCode;
/**
     * 种类名称
     */
    private String typeName;
/**
     * 种类名称
     */
    private String selectCode;
/**
     * 创建人
     */
    private String createBy;
/**
     * 创建时间
     */
    private Date createTime;
/**
     * 更新人
     */
    private String updateBy;
/**
     * 更新时间
     */
    private Date updateTime;
/**
     * 是否删除（0未删除 1已删除）
     */
    private String delFlag;



}

