package com.vp.wxzx.controller;

import com.vp.wxzx.entity.ResultBody;
import com.vp.wxzx.pojo.ao.ChangeAo;
import com.vp.wxzx.pojo.ao.EnrollAo;
import com.vp.wxzx.pojo.ao.ForgetAo;
import com.vp.wxzx.pojo.ao.LoginAo;
import com.vp.wxzx.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author qlh
 * @date 2023/10/14 14:05
 * @description 登录接口
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    /**
     * 普通登录
     * @param loginAo
     * @return
     */
    @PostMapping("/normal")
    public ResultBody login(@RequestBody @Validated LoginAo loginAo){
        return loginService.login(loginAo);
    }

    /**
     * 微信快速登录
     */
    @PostMapping("wechat")
    public ResultBody wechatLogin(@RequestBody String code){
        return loginService.wechatLogin(code);
    }

    /**
     * 注册用户
     * @return
     */
    @PostMapping("/enroll")
    public ResultBody enroll(@RequestBody EnrollAo enrollAo){
        return loginService.enroll(enrollAo);
    }

    /**
     * 修改密码
     * @return
     */
    @PutMapping("/change")
    public ResultBody change(@RequestBody ChangeAo changeAo){
        return loginService.change(changeAo);
    }

    /**
     * 忘记密码找回
     * @return
     */
    public ResultBody forget(@RequestBody ForgetAo forgetAo){
        return loginService.forget(forgetAo);
    }


}
