package com.pond.build.model.Response;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeResponse {

    /**
     *
     */
    private String pendingId;

    /**
     * 待处理人userId
     */
    private String userId;

    /**
     * 发布人昵称
     */
    private String nickName;
    /**
     *  发布人姓名
     */
    private String name;

    /**
     * 发布人头像
     */
    private String avatarUrl;

    /**
     * 发布日期
     */
    private Date releaseDate;

    /**
     * 通知信息
     */
    private String message;
}
