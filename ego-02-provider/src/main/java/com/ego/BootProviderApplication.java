package com.ego;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

/**
 * Action:
 * Consider defining a bean of type 'com.ego.dao.TbUserMapper' in your configuration.
 * 1. @SpringBootApplication(scanBasePackages = {"com.ego.dao"})
 * 2. @ComponentScan(basePackages = {"com.ego.dao"})
 * 3. @MapperScan(basePackages = {"com.ego.dao"})
 *
 * @author liuweiwei
 * @since 2020-8-15
 */
@SpringBootApplication(scanBasePackages = {"com.ego.dao"})
@EnableDubbo
@EnableCaching
//@EnableWebSocketMessageBroker
public class BootProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootProviderApplication.class, args);
    }
}
