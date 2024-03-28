package com.pond.build.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pond.build.model.CabinetQuotationDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CabinetQuotationDetailMapper extends BaseMapper<CabinetQuotationDetail> {
}
