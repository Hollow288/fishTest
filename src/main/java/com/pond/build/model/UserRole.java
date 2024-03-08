package com.pond.build.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = 432816066861592506L;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;


}
