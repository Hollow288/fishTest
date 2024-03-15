package com.pond.build.mapper;

import com.pond.build.model.Response.MenuResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CommonMapper {
    List<Map<String,Object>> getAllRouterAndChildren();

    List<MenuResponse> getAllMenuAndChildren();

    List<String> getAllMenuIdByUserId(String userId);

    List<String> selectMenuParentId(@Param("menuIds") List<String> menuIds);
}
