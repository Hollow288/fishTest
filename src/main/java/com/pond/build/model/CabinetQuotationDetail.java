package com.pond.build.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * (CabinetQuotationDetail)实体类
 *
 * @author makejava
 * @since 2024-03-22 15:00:38
 */

@TableName(value="Cabinet_Quotation_detail")
@Data
public class CabinetQuotationDetail implements Serializable {
    private static final long serialVersionUID = 862399772227934807L;

    @TableId(type = IdType.AUTO)
    private Integer detailId;
/**
     * 主表id
     */
    private Integer quotationId;
/**
     * 项目名称
     */
    private String projectName;
/**
     * 规格型号
     */
    private String specificationModel;
/**
     * 计价数量
     */
    private BigDecimal pricingQuantity;
/**
     * 单价
     */
    private BigDecimal unitPrice;
/**
     * 计价系数
     */
    private BigDecimal pricingCoefficient;
/**
     * 金额
     */
    private BigDecimal priceAmount;
/**
     * 备注
     */
    private String remark;
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

