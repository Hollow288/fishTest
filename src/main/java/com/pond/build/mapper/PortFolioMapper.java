package com.pond.build.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pond.build.model.PortFolio;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PortFolioMapper extends BaseMapper<PortFolio> {

    List<PortFolio> getPortFolioByPage(@Param("offset") int offset,@Param("limit") int limit,@Param("searchList") List<String> searchList);

    Integer getPortFolioByPageCount(@Param("searchList") List<String> searchList);
}
