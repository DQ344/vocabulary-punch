package com.vp.wxzx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author qlh
 * @date 2023/10/15 15:34
 * @description
 */
@ServletComponentScan // 开启了对 servlet 组件的支持
@SpringBootApplication
@MapperScan("com.vp.wxzx.mapper")
@ComponentScan("com.vp.wxzx.*")
public class VpDictionaryApplication {
    public static void main(String[] args) {
        SpringApplication.run(VpDictionaryApplication.class, args);
    }
}
