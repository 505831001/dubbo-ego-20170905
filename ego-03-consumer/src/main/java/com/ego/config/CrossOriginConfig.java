package com.ego.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 解决跨域问题SpringBoot所需配置
 *
 * @author liuweiwei
 */
@Configuration
public class CrossOriginConfig {
    /**
     * Log4j 罗锅否街日志技能（非：SLF4J 骚粉日志必备技能）
     */
    private final Logger logger = Logger.getLogger(this.getClass());

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 1. 允许的域不要写*，否则cookie就无法使用
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        // 2. 是否发送Cookie信息
        config.setAllowCredentials(true);
        // 3. 允许的请求方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        // 4. 允许的头信息
        config.addAllowedHeader("*");
        // 5. 配置有效时长
        config.setMaxAge(3600 * 24L);
        // 2. 添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        // 3. 返回新的CorsFilter.
        return new CorsFilter(configSource);
    }
}
