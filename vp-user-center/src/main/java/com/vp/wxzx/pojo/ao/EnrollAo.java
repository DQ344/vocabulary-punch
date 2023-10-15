package com.vp.wxzx.pojo.ao;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author qlh
 * @date 2023/10/14 20:02
 * @description 注册账户
 */
@Data
public class EnrollAo {
    /**
     * 用户的学工号
     * */
    private String userId;

    /**
     * 用户密码
     * */
    private String password;

    /**
     * 用户的昵称
     * */
    private String nickname;

    /**
     * 用户的微信授权码
     * */
    private String openId;

    /**
     * 用户的微信会话码
     * */
    private String sessionId;

    /**
     * 用户的头像图片名
     * */
    private String avatarFile;
    /**
     * 电子邮箱
     */
    private String email;
}
