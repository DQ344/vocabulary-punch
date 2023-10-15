package com.vp.wxzx.service;

import com.vp.wxzx.entity.ResultBody;
import com.vp.wxzx.pojo.ao.UserInfoAo;

/**
 * @author qlh
 * @date 2023/10/15 11:30
 * @description
 */
public interface UserInfoService {
    ResultBody updateUserInfo(UserInfoAo userInfoAo);
}
