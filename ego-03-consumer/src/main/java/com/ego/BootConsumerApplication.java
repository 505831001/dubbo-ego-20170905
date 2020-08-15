package com.ego;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author liuweiwei
 * @since 2020-8-15
 */
@SpringBootApplication
@EnableSwagger2
@EnableDubbo
public class BootConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootConsumerApplication.class, args);
    }
}
