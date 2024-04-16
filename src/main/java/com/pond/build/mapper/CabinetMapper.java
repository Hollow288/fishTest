package com.pond.build.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pond.build.model.CabinetQuotation;
import com.pond.build.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CabinetMapper extends BaseMapper<CabinetQuotation> {

    void deleteQuotationById(@Param("ids") List<String> ids, @Param("userId")String string, @Param("date") Date date);

    void deletePortfolioWebById(@Param("ids")List<String> ids,@Param("userId") String string,@Param("date") Date date);

    void deleteNewsInformationById(@Param("ids")List<String> ids,@Param("userId") String string,@Param("date") Date date);
}
