package com.vp.wxzx.service.impl;

import com.vp.wxzx.entity.ResultBody;
import com.vp.wxzx.mapper.UserInfoMapper;
import com.vp.wxzx.pojo.ao.UserInfoAo;
import com.vp.wxzx.pojo.dto.UserInfoDto;
import com.vp.wxzx.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qlh
 * @date 2023/10/15 11:31
 * @description
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public ResultBody updateUserInfo(UserInfoAo userInfoAo) {
        UserInfoDto userInfoDto = userInfoMapper.selectById(userInfoAo.getUserId());
        if(userInfoDto == null) return ResultBody.error().message("用户信息不存在");

        // 设置需要更新的字段值
        if(userInfoAo.getNickname() != null) userInfoDto.setNickname(userInfoAo.getNickname());
        if(userInfoAo.getAvatarFile() != null) userInfoDto.setAvatarFile(userInfoAo.getAvatarFile());
        if (userInfoAo.getEmail() != null) userInfoDto.setEmail(userInfoAo.getEmail());
        try {
            userInfoMapper.updateById(userInfoDto);
            return ResultBody.ok().message("用户信息已更新");
        }catch (Exception e){
            e.printStackTrace();
            return ResultBody.error().message("用户信息更新失败");
        }
    }
}
