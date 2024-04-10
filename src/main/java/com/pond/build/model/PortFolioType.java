package com.pond.build.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * (PortFolioType)实体类
 *
 * @author makejava
 * @since 2024-04-10 17:36:41
 */

@Data
@TableName("Port_Folio_type")
public class PortFolioType implements Serializable {
    private static final long serialVersionUID = -50358878433515913L;
/**
     * 种类ID
     */
    private String typeId;
/**
     * 案例ID
     */
    private String folioId;

}

