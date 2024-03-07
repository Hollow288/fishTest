package com.pond.build.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pond.build.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    Integer deleteRolesByIds(@Param("roleIds") List<String> roleIds,@Param("userId") Long userId,@Param("date") Date date);
}
