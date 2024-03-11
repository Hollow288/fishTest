package com.pond.build.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pond.build.model.Menu;
import com.pond.build.model.ResponseResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MenuMapper extends BaseMapper<Menu> {


    List<String> selectPermsByUserId(Long userid);

    List<String> selectRolesByUserId(Long userid);

    void refreshIsLeaf();

    List<Map<String,Object>> selectMenuAndChildren();

    List<String> allMenuIdByRoleId(@Param("roleId") String roleId);

    Integer deleteMenuIdByRoleId(@Param("roleId")String roleId);

    Integer insertMenuIdByRoleId(@Param("roleId")String roleId,@Param("menuIds") List<String> menuIds);
}
