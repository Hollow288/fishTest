package com.pond.build.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (WorkArrangement)实体类
 *
 * @author makejava
 * @since 2024-04-22 17:37:44
 */
@Data
@TableName("work_arrangement")
public class WorkArrangement implements Serializable {
    private static final long serialVersionUID = -89901401336108949L;
/**
     * 代办ID
     */
@TableId(type = IdType.AUTO)
    private String workId;
/**
     * 年
     */
    private String year;
/**
     * 月
     */
    private String month;
/**
     * 日
     */
    private String date;
/**
     * 代办事项
     */
    private String agencyMatters;
/**
     * 工作人员
     */
    private String arrangePeople;
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

