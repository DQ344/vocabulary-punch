package com.vp.wxzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author qlh
 * @date 2023/10/14 15:14
 * @description
 */

@ServletComponentScan // 开启了对 servlet 组件的支持
@SpringBootApplication
public class VpUserCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(VpUserCenterApplication.class, args);
    }
}