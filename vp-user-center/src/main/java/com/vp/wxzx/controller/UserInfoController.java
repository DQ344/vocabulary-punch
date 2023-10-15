package com.vp.wxzx.controller;

import com.vp.wxzx.entity.ResultBody;
import com.vp.wxzx.pojo.ao.UserInfoAo;
import com.vp.wxzx.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qlh
 * @date 2023/10/15 11:28
 * @description
 */
@RestController
@RequestMapping("userinfo")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @PutMapping("update")
    public ResultBody updateUserInfo(@RequestBody UserInfoAo userInfoAo){
        return userInfoService.updateUserInfo(userInfoAo);
    }
}
