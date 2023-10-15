package com.vp.wxzx.pojo.ao;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author qlh
 * @date 2023/10/14 14:51
 * @description 登录参数
 */
@Data
public class LoginAo {
    @NotBlank(message = "username 不能为空")
    private String username;
    @NotBlank(message = "password 不能为空")
    private String password;
}
