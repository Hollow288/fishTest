package com.pond.build.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pond.build.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    User selectUserById(String userId);

    List<String> hasRoleToInterface(String userId);
}
