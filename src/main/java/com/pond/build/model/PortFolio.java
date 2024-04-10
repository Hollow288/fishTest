package com.pond.build.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pond.build.model.Response.SelectTypeResponse;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * (PortFolio)实体类
 *
 * @author makejava
 * @since 2024-04-10 17:36:49
 */

@Data
@TableName("Port_Folio")
public class PortFolio implements Serializable {
    private static final long serialVersionUID = -28983468825071590L;
/**
     * 案例ID
     */
    @TableId(type = IdType.AUTO)
    private String folioId;
/**
     * 缩略图Url
     */
    private String thumbnailUrl;
/**
     * 全景图Url
     */
    private String panoramaUrl;
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
    /**
     * 照片种类
     */
    @TableField(exist = false)
    private List<SelectTypeResponse> photoTypes;

}

