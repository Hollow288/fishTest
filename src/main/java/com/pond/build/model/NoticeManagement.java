package com.pond.build.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * (NoticeManagement)实体类
 *
 * @author makejava
 * @since 2024-03-11 17:48:45
 */
@Data
@TableName("notice_management")
public class NoticeManagement implements Serializable {
    private static final long serialVersionUID = -59458512135977593L;
/**
     * 通知主键
     */
    @TableId(type = IdType.AUTO)
    private String noticeId;
/**
     * 通知内容
     */
    private String message;
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
     * 发布时间
     */
    private Date releaseDate;
/**
     * 是否删除（0未删除 1已删除）
     */
    private String delFlag;
    /**
     * 总需读人数
     */
    private Integer needProcessedNum;
    /**
     * 已读人数
     */
    private Integer endProcessedNum;
    /**
     *
     */
    private String releaseBy;
    /**
     * 完成度
     */
    @TableField(exist = false)
    private BigDecimal  perfection;



}

