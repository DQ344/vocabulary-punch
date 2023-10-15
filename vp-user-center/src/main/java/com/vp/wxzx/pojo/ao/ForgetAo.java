package com.vp.wxzx.pojo.ao;

import lombok.Data;

/**
 * @author qlh
 * @date 2023/10/14 20:02
 * @description 忘记
 */
@Data
public class ForgetAo {
    // 用户名
    private String username;
    // 旧密码
    private String email;
}
