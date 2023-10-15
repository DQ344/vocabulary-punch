package com.vp.wxzx.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author qlh
 * @date 2023/10/15 14:10
 * @description 词典实体类
 */
@Data
@TableName("dictionary_info")
public class DictionaryInfoDto {
    @TableField("id")
    private Integer id;
    /**
     * 英译
     */
    @TableField("english")
    private String english;
    /**
     * 中译
     */
    @TableField("chinese")
    private String chinese;
    /**
     * 词性
     */
    @TableField("property")
    private String property;
}
