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
        if(strongPasswordChecker(enrollAo.getPassword())) return ResultBody.error().message("密码等级太弱");
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
        UserInfoDto userInfoDto = userInfoMapper.selectById(changeAo.getUsername());
        if(userInfoDto == null) return ResultBody.error().message("用户不存在");

        if(changeAo.getEmail() != null && changeAo.getToken() != null){
            return ResultBody.error().message("邮箱找回未开发");
        }

        if(userInfoDto.getPassword().equals(changeAo.getOldPassword())){
            if(!strongPasswordChecker(changeAo.getNewPassword())) return ResultBody.error().message("密码等级太弱");
            userInfoDto.setPassword(changeAo.getNewPassword());
            userInfoMapper.updateById(userInfoDto);
        }
        return ResultBody.ok().message("修改密码成功");
    }

    /**
     * 忘记密码
     * @param forgetAo
     * @return
     */
    @Override
    public ResultBody forget(ForgetAo forgetAo) {
        return ResultBody.error().message("邮箱找回未开发");
    }

    /**
     * 微信快速登录
     * @param code
     * @return
     */
    @Override
    public ResultBody wechatLogin(String code) {
        return ResultBody.error().message("微信快速登录未开发");
    }

    /**
     * 强密码校验
     * @param password 密码
     * @return true/false
     */
    public boolean strongPasswordChecker(String password) {
        int len = password.length();
        if(len < 8) return false;
        boolean special = false;
        boolean lowercase = false;
        boolean uppercase = false;
        boolean digit = false;
        for(int i = 0; i < len; i++){
            if(Character.isUpperCase(password.charAt(i))) uppercase = true;
            if(Character.isLowerCase(password.charAt(i))) lowercase = true;
            if(Character.isDigit(password.charAt(i))) digit = true;
            if(!Character.isDigit(password.charAt(i)) && !Character.isLowerCase(password.charAt(i)) && !Character.isUpperCase(password.charAt(i))) special = true;
        }
        int strong = (special ? 1 : 0) + (lowercase ? 1 : 0) + (uppercase ? 1 : 0) + (digit ? 1 : 0);
        return strong >= 2;
    }
}
