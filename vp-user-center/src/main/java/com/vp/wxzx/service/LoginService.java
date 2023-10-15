package com.vp.wxzx.service;

import com.vp.wxzx.entity.ResultBody;
import com.vp.wxzx.pojo.ao.ChangeAo;
import com.vp.wxzx.pojo.ao.EnrollAo;
import com.vp.wxzx.pojo.ao.ForgetAo;
import com.vp.wxzx.pojo.ao.LoginAo;

/**
 * @author qlh
 * @date 2023/10/14 15:53
 * @description
 */
public interface LoginService
{
    /**
     * 登录校验
     * @param loginAo
     * @return
     */
    ResultBody login(LoginAo loginAo);

    /**
     * 用户注册
     * @param enrollAo
     * @return
     */
    ResultBody enroll(EnrollAo enrollAo);

    /**
     * 修改密码
     * @param changeAo
     * @return
     */
    ResultBody change(ChangeAo changeAo);

    /**
     * 忘记密码
     * @param forgetAo
     * @return
     */
    ResultBody forget(ForgetAo forgetAo);

    /**
     * 微信快速登录
     * @param code
     * @return
     */
    ResultBody wechatLogin(String code);
}
