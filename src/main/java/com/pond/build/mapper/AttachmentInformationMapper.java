package com.pond.build.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pond.build.model.AttachmentInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AttachmentInformationMapper  extends BaseMapper<AttachmentInformation> {

    List<Map<String,Object>> attachDataByQuotationId(@Param("quotationId")String quotationId);

    List<Map<String,Object>> attachDataByOrderId(@Param("orderId")String orderId);

    Integer deleteAttachByIds(@Param("attachIds") List<String> ids, @Param("userId") Long userId, @Param("date") Date date);
}
