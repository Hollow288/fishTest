package com.pond.build.model;

import lombok.Data;

import java.io.Serializable;

/**
 * (PendingNotification)实体类
 *
 * @author makejava
 * @since 2024-03-11 17:48:57
 */
@Data
public class PendingNotification implements Serializable {
    private static final long serialVersionUID = -35224677333548531L;
/**
     * 待处理通知主键
     */
    private String pendingId;
/**
     * 原通知主键
     */
    private String noticeId;
/**
     * 是否已读（0没读 1已读）
     */
    private String processed;
/**
     * 待处理人
     */
    private String userId;
/**
     * 是否删除（0未删除 1已删除）
     */
    private String delFlag;



}

