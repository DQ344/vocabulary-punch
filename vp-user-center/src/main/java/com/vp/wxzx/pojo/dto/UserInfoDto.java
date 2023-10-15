package com.vp.wxzx.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author qlh
 * @date 2023/10/14 16:25
 * @description
 */

@Data
@TableName("user_info")
public class UserInfoDto {
    /**
     * 用户的学工号
     * */
    @TableId("user_id")
    private String userId;

    /**
     * 用户密码
     * */
    @TableField("password")
    @JsonIgnore
    private String password;
    /**
     * 用户的昵称
     * */
    @TableField("nickname")
    private String nickname;

    /**
     * 用户的微信授权码
     * */
    @TableField("open_id")
    @JsonIgnore
    private String openId;

    /**
     * 用户的微信会话码
     * */
    @TableField("session_id")
    @JsonIgnore
    private String sessionId;

    /**
     * 用户的头像图片名
     * */
    @TableField("avatar_file")
    private String avatarFile;

    /**
     * 电子邮箱
     */
    @TableField("email")
    private String email;
}
