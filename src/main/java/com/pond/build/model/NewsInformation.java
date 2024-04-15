package com.pond.build.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (NewsInformation)实体类
 *
 * @author makejava
 * @since 2024-04-15 11:28:57
 */

@Data
@TableName("news_information")
public class NewsInformation implements Serializable {
    private static final long serialVersionUID = 996097673510635147L;
/**
     * 新闻ID
     */
    @TableId(type = IdType.AUTO)
    private String newsId;
/**
     * 标题
     */
    private String newsTitle;
/**
     * 新闻日期
     */
    private String newsDate;
/**
     * 新闻简介
     */
    private String newsIntroduction;

    /**
     * 新闻正文
     */
    private String newsText;
/**
     * 新闻封面
     */
    private String newsCover;
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

