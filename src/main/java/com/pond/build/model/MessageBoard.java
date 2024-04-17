package com.pond.build.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * (MessageBoard)实体类
 *
 * @author makejava
 * @since 2024-04-16 14:14:30
 */
@Data
@TableName("Message_Board")
public class MessageBoard implements Serializable {
    private static final long serialVersionUID = -88181591528494086L;
/**
     * 留言ID
     */
    @TableId(type = IdType.AUTO)
    private String messageId;
/**
     * 姓名
     */
    private String name;
/**
     * 邮箱
     */
    private String email;
/**
     * 电话
     */
    private String phone;
/**
     * 留言
     */
    private String message;
    /**
     * 留言日期
     */
    private Date messageDate;

}

