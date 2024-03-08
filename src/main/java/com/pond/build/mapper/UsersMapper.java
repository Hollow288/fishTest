package com.pond.build.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pond.build.model.Response.UserResponse;
import com.pond.build.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UsersMapper  extends BaseMapper<User> {

    String findRoleById(@Param("userId")long userId);

    List<UserResponse> getUsersByPage(@Param("searchText") String searchText, @Param("startDate") Date startDate, @Param("endDate") Date endDate,
                                      @Param("offset") Integer offset, @Param("limit") Integer limit, @Param("sort") String sort, @Param("order") String order);

    Integer getUsersCountByPage(@Param("searchText") String searchText, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    UserResponse getUserInfoById(long userId);

    List<String> getAllUserByRole(String roleId);

    List<Map<String,Object>> getAllUserRole();


}
