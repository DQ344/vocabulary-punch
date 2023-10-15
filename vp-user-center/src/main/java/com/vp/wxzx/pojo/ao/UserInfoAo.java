package com.vp.wxzx.pojo.ao;

import lombok.Data;

/**
 * @author qlh
 * @date 2023/10/14 19:52
 * @description 用户
 */
@Data
public class UserInfoAo {
    // 用户id
    private String userId;
    // 昵称
    private String nickname;
    // 图片
    private String avatarFile;
    // 电子邮箱
    private String email;
}
