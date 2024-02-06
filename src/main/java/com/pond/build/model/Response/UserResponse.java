package com.pond.build.model.Response;

import com.pond.build.utils.CommonUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class UserResponse {
    //js中最长数字17位数字 改string吧 隔壁的User实体类一般用去接数据,java的long比较长,就不改了
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 账号状态（0正常 1停用）
     */
    private String status;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 用户性别（0女，1男，2未知）
     */
    private String gender;
    /**
     * 用户性别（0女，1男，2未知）
     */
    private String genderLabel;
    /**
     * 头像
     */
    private String avatarUrl;
    /**
     * 用户类型（0管理员，1普通用户）
     */
    private String userType;
    /**
     * 创建人的用户id
     */
    private Long createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private Long updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    private Integer delFlag;
    /**
     * 姓名
     */
    private String name;
    /**
     * 生日
     */
    private Date birthDate;
    /**
     * 国家
     */
    private String country;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 地址
     */
    private String address;
    /**
     * 简介
     */
    private String biography;
    /**
     * 角色
     */
    private List<String> roles;

    public String getAvatarUrl() {
        return CommonUtil.fileUrlEncoderChance(avatarUrl);
    }
}
