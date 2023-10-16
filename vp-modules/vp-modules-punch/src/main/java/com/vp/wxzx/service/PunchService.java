package com.vp.wxzx.service;

import com.vp.wxzx.entity.ResultBody;

/**
 * @author qlh
 * @date 2023/10/16 19:16
 * @description
 */
public interface PunchService {
    ResultBody clockIn(String userId, Integer grade);

    ResultBody getById(String userId);

    ResultBody getGrades();
}
