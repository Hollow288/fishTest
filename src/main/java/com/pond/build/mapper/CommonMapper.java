package com.pond.build.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CommonMapper {
    List<Map<String,Object>> getAllRouterAndChildren();
}
