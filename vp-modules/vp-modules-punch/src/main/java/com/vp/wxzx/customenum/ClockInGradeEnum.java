package com.vp.wxzx.customenum;

/**
 * @author qlh
 * @date 2023/10/16 21:36
 * @description
 */
public enum ClockInGradeEnum {
    SINGLE_SIMPLE(1, 20, 10, "单人&简单"),
    SINGLE_NORMAL(2,50,20,"单人&中等"),
    SINGLE_HARD(3, 80,30,"单人&困难"),
    SINGLE_PURGATORY(4, 100, 50,"单人&炼狱"),

    DOUBLE_SIMPLE(5, 25, 10,"组队&简单"),
    DOUBLE_NORMAL(6,55,20,"组队&中等"),
    DOUBLE_HARD(7, 85,30,"组队&困难"),
    DOUBLE_PURGATORY(8, 110, 50,"组队&炼狱"),
    ;

    public final Integer grade;
    public final Integer coin;
    public final Integer num;
    public final String msg;

    /**
     * @param grade 难度id
     * @param coin 获得货币
     * @param num 挑战个数
     */
    ClockInGradeEnum(Integer grade, Integer coin, Integer num, String msg){
        this.grade = grade;
        this.coin = coin;
        this.num = num;
        this.msg = msg;
    }
}
