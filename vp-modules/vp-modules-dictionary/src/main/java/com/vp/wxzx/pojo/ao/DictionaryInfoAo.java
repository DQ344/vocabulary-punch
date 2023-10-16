package com.vp.wxzx.pojo.ao;

import lombok.Data;

/**
 * @author qlh
 * @date 2023/10/15 14:09
 * @description
 */
@Data
public class DictionaryInfoAo {
    private Integer id;
    // 英译
    private String english;
    // 中译
    private String chinese;
    // 音标
    private String phonetic;
}

