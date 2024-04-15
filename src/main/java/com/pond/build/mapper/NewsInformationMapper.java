package com.pond.build.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pond.build.model.NewsInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface NewsInformationMapper extends BaseMapper<NewsInformation> {
    List<Map<String, Object>> getAllNewsInformationPage(@Param("offset") int offset,@Param("limit") int limit);

    Integer getAllNewsInformationCount();

    List<Map<String, Object>> getNewsInformationById(@Param("newsId") Integer newsId);
}
