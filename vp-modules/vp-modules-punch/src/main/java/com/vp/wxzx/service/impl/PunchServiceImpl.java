package com.vp.wxzx.service.impl;

import com.vp.wxzx.customenum.ClockInGradeEnum;
import com.vp.wxzx.entity.ResultBody;
import com.vp.wxzx.mapper.WarehouseMapper;
import com.vp.wxzx.pojo.dto.WarehouseDto;
import com.vp.wxzx.service.PunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qlh
 * @date 2023/10/16 19:17
 * @description 每日打卡
 */
@Service
public class PunchServiceImpl implements PunchService {
    @Autowired
    WarehouseMapper warehouseMapper;
    /**
     * 每日打卡功能
     * @return
     * @param userId
     */
    @Override
    public ResultBody clockIn(String userId, Integer grade) {
//        // 判断难度挑战加分
//        ClockInGradeEnum[] clocks = ClockInGradeEnum.values();
//        int coin = 0;
//        for(ClockInGradeEnum clock : clocks){
//            if(clock.grade.equals(grade)){
//                coin = clock.coin;
//                break;
//            }
//        }
        ClockInGradeEnum clock = Arrays.stream(ClockInGradeEnum.values()).filter(c -> c.grade.equals(grade)).findFirst().orElse(null);
        int coin = (clock != null) ? clock.coin: 0;
        if(coin == 0) return ResultBody.error().message("挑战模式严重错误");

        WarehouseDto warehouseDto = warehouseMapper.selectById(userId);
        if(warehouseDto == null){
            warehouseDto = new WarehouseDto();
            warehouseDto.setUserId(userId);
            warehouseMapper.insert(warehouseDto);
            warehouseDto = warehouseMapper.selectById(userId);
        }
        // 获取上次打卡时间
        LocalDateTime clockInTime = warehouseDto.getClockInTime();
        // 获取当前日期
        LocalDate today = LocalDate.now();
        // 获取待判断的 LocalDateTime 对象所属的日期, 判断是否是今天
        if (clockInTime != null && clockInTime.toLocalDate().equals(today)) {
            // 是今天
            return ResultBody.error().message("今日以打卡");
        } else {
            // 不是今天
            // 判断是否昨天打卡，连续打卡，没有打卡清 0
            if(clockInTime != null && clockInTime.toLocalDate().equals(today.minusDays(1))) warehouseDto.setClockInContinuitySize(0);
            // 刷新当前打卡时间
            warehouseDto.setClockInTime(LocalDateTime.now());
            warehouseDto.setCoin(warehouseDto.getCoin() + coin);
            warehouseDto.setClockInContinuitySize(warehouseDto.getClockInContinuitySize() + 1);
            warehouseDto.setClockInSize(warehouseDto.getClockInSize() + 1);
            warehouseMapper.updateById(warehouseDto);
            return ResultBody.ok().message("打卡成功").data("获得积分", coin);
        }
    }

    /**
     * 查询用户的仓库
     * @param userId
     * @return
     */
    @Override
    public ResultBody getById(String userId) {
        WarehouseDto warehouseDto = warehouseMapper.selectById(userId);
        if(warehouseDto == null){
            warehouseDto = new WarehouseDto();
            warehouseDto.setUserId(userId);
            warehouseMapper.insert(warehouseDto);
            warehouseDto = warehouseMapper.selectById(userId);
        }
        return ResultBody.ok().data("user_warehouse_info", warehouseDto);
    }

    /**
     * 查询所有难度
     * @return
     */
    @Override
    public ResultBody getGrades() {
        ClockInGradeEnum[] clocks = ClockInGradeEnum.values();
        List<String> data = new ArrayList<>();
        for(ClockInGradeEnum clock : clocks){
            data.add("" + clock.msg + ", grade:" + clock.grade);
        }
        return ResultBody.ok().data("难度等级", data);
    }
}
