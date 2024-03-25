package com.pond.build.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (AttachmentInformation)实体类
 *
 * @author makejava
 * @since 2024-03-25 09:25:10
 */
@Data
@TableName(value="attachment_information")
public class AttachmentInformation implements Serializable {
    private static final long serialVersionUID = -36324156375086461L;

    @TableId(type = IdType.AUTO)
    private String attachId;
/**
     * 源表名称
     */
    private String oriTableName;
/**
     * 源表数据id
     */
    private String oriTableId;
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
     * 附件路径
     */
    private String attachUrl;

}

