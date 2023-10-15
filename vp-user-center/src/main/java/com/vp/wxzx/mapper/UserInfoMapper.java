package com.vp.wxzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vp.wxzx.pojo.dto.UserInfoDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author qlh
 * @date 2023/10/14 23:00
 * @description
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoDto> {
}
