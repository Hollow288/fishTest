package com.pond.build.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * (OrderStatus)实体类
 *
 * @author makejava
 * @since 2024-04-28 10:09:19
 */
@Data
@TableName("order_status")
public class OrderStatus implements Serializable {
    private static final long serialVersionUID = -21849887265422490L;
/**
     * 订单ID
     */
    @TableId(type = IdType.AUTO)
    private Integer orderId;
/**
     * 报价单ID
     */
    private Integer quotationId;
/**
     * 客户姓名
     */
    private String customerName;
/**
     * 地址
     */
    private String address;
/**
     * 电话
     */
    private String telephone;
/**
     * 产品名称
     */
    private String productName;
/**
     * 总价
     */
    private BigDecimal allTotalPrice;
/**
     * 已付款金额
     */
    private BigDecimal paidPrice;
/**
     * 未付款金额
     */
    private BigDecimal unPaidPrice;
/**
     * 备注
     */
    private String remark;
/**
     * 付款备注
     */
    private String paidRemark;
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

