package com.vp.wxzx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author qlh
 * @date 2023/10/15 16:12
 * @description
 */
@ServletComponentScan
@SpringBootApplication
@MapperScan("com.vp.wxzx.mapper")
public class VpPunchApplication {
    public static void main(String[] args) {
        SpringApplication.run(VpPunchApplication.class, args);
    }
}
