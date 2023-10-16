package com.vp.wxzx.controller;

import com.vp.wxzx.customenum.ClockInGradeEnum;
import com.vp.wxzx.entity.ResultBody;
import com.vp.wxzx.service.PunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author qlh
 * @date 2023/10/16 19:12
 * @description 打卡功能
 */

@RestController
@RequestMapping("punch")
public class PunchController {

    @Autowired
    PunchService punchService;

    /**
     * 每日打卡功能
     * @param userId
     * @return
     */
    @PostMapping("clockIn")
    public ResultBody clockIn(@RequestParam(value = "id") String userId, @RequestParam(value = "grade", defaultValue = "1") Integer grade){
        return punchService.clockIn(userId, grade);
    }

    /**
     * 查询用户的仓库信息
     * @param userId
     * @return
     */
    @GetMapping
    public ResultBody getById(@RequestParam(value = "id") String userId){
        return punchService.getById(userId);
    }

    /**
     * 获取所有难度等级
     * @return
     */
    @GetMapping("/grades")
    public ResultBody getGrades(){
        return punchService.getGrades();
    }


}

