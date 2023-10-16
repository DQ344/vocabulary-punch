package com.vp.wxzx.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author qlh
 * @date 2023/10/16 19:21
 * @description
 */

@Data
@TableName("warehouse")
public class WarehouseDto {
    @TableId("user_id")
    private String userId;
    @TableField("coin")
    private Integer coin;
    @TableField("clock_in_time")
    private LocalDateTime clockInTime;
    @TableField("clock_in_size")
    private Integer clockInSize;
    @TableField("clock_in_continuity_size")
    private Integer clockInContinuitySize;
    @TableField("grade")
    private Integer grade;
    @TableField("ranking")
    private Integer ranking;
    @TableField("learn_word_id")
    private String learnWordId;
    @TableField("mistake_word_id")
    private String mistakeWordId;
}
