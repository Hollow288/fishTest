package com.pond.build.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pond.build.model.SelectType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SelectTypeMapper extends BaseMapper<SelectType> {

    List<Map<String,Object>> getPortFolioType();

    List<Map<String,Object>> getPortFolioWebType();

    void deleteByTypeIds(@Param("ids")List<String> ids,@Param("userId") String userId,@Param("date") Date date);

    void addType(@Param("willAddTypeMaps") List<Map<String, String>> willAddTypeMaps, @Param("userId") String userId,@Param("date") Date date,@Param("portFolioType") String portFolioType);
}
