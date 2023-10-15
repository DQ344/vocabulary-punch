package com.vp.wxzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vp.wxzx.pojo.dto.DictionaryInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author qlh
 * @date 2023/10/15 14:54
 * @description
 */
@Mapper
public interface DictionaryMapper extends BaseMapper<DictionaryInfoDto> {
    @Select("select * from dictionary_info")
    List<DictionaryInfoDto> getAllDictionary();
}
