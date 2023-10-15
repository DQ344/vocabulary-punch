package com.vp.wxzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vp.wxzx.entity.ResultBody;
import com.vp.wxzx.mapper.UserInfoMapper;
import com.vp.wxzx.pojo.ao.ChangeAo;
import com.vp.wxzx.pojo.ao.EnrollAo;
import com.vp.wxzx.pojo.ao.ForgetAo;
import com.vp.wxzx.pojo.ao.LoginAo;
import com.vp.wxzx.pojo.dto.UserInfoDto;
import com.vp.wxzx.service.LoginService;
import com.vp.wxzx.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qlh
 * @date 2023/10/14 15:53
 * @description
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserInfoMapper userInfoMapper;
    /**
     * 登录校验
     * @param loginAo
     * @return
     */
    @Override
    public ResultBody login(LoginAo loginAo) {
        QueryWrapper<UserInfoDto> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", loginAo.getUsername()).eq("password", loginAo.getPassword());

        UserInfoDto userInfoDto = userInfoMapper.selectOne(queryWrapper);
        // 登录成功，生成 JWT 令牌，并下发令牌
        if(userInfoDto != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("user_id", userInfoDto.getUserId());
            claims.put("nickname", userInfoDto.getNickname());
            String jwt = JwtUtils.generateJwt(claims);
            return ResultBody.ok().data("token", jwt);
        }
        return ResultBody.error().message("用户名或密码错误");
    }

    /**
     * 注册用户
     * @param enrollAo
     * @return
     */
    @Override
    public ResultBody enroll(EnrollAo enrollAo) {
        UserInfoDto userInfoDto = new UserInfoDto();
        if(enrollAo.getPassword().length() < 8) return ResultBody.error().message("密码格式有误");
        BeanUtils.copyProperties(enrollAo, userInfoDto);
        try {
            userInfoMapper.insert(userInfoDto);
            return ResultBody.ok().message("注册成功");
        }catch (Exception e){
            return ResultBody.error().message("用户名已存在");
        }
    }

    /**
     * 修改密码
     * @param changeAo
     * @return
     */
    @Override
    public ResultBody change(ChangeAo changeAo) {
        return null;
    }

    /**
     * 忘记密码
     * @param forgetAo
     * @return
     */
    @Override
    public ResultBody forget(ForgetAo forgetAo) {
        return null;
    }

    /**
     * 微信快速登录
     * @param code
     * @return
     */
    @Override
    public ResultBody wechatLogin(String code) {
        return null;
    }
}
