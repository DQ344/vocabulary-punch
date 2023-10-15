package com.vp.wxzx.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qlh
 * @date 2023/10/15 20:32
 * @description
 */
@Configuration
public class MybatisPlusConfig {
    @Bean
    public PaginationInnerInterceptor paginationInterceptor() {
        PaginationInnerInterceptor interceptor = new PaginationInnerInterceptor();
        // 设置数据库方言，根据实际情况选择
        interceptor.setDbType(DbType.MYSQL);
        // 设置分页参数合理化
        interceptor.setOverflow(true);
        interceptor.setMaxLimit(1000L);
        return interceptor;
    }
}
