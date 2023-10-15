package com.vp.wxzx.pojo.ao;

import lombok.Data;

/**
 * @author qlh
 * @date 2023/10/14 20:02
 * @description 修改密码参数
 */
@Data
public class ChangeAo {
    // 用户名
    private String username;
    // 旧密码
    private String oldPassword;
    // 新密码
    private String newPassword;
    //邮箱
    private String email;
    // 邮箱令牌
    private String token;
}
