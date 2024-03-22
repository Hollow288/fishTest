package com.pond.build.model.Response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pond.build.model.CabinetQuotationDetail;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * (CabinetQuotation)实体类
 *
 * @author makejava
 * @since 2024-03-22 15:00:33
 */
@TableName(value="Cabinet_Quotation")
@Data
public class CabinetQuotationResponse implements Serializable {
    private static final long serialVersionUID = 868817958688326911L;

    @TableId(type = IdType.AUTO)
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
     * 颜色
     */
    private String color;
/**
     * 柜体
     */
    private String cabinetBody;
/**
     * 报价单日期
     */
    private Date quotationDate;
/**
     * 橱柜类价格
     */
    private BigDecimal cabinetTotalPrice;
/**
     * 电器五金类价格
     */
    private BigDecimal electricalTotalPrice;
/**
     * 总价大写
     */
    private String allTotalPriceWords;
/**
     * 总价
     */
    private BigDecimal allTotalPrice;
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
     * 子表
     */
    @TableField(exist = false)
    private List<CabinetQuotationDetail> cabinetQuotationDetails;

}

