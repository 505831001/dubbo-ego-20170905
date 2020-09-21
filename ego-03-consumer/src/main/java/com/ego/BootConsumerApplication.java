package com.ego;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
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

    /**
     * 1. 用于同步客户端HTTP访问的Spring的中心类。
     * 2. 它简化了与HTTP服务器的通信，并强化了RESTful原则。
     * 3. 它处理HTTP连接，让应用程序代码提供url(包含可能的模板变量)并提取结果。
     * 4. 默认情况下，RestTemplate依赖于标准JDK工具来建立HTTP连接。
     * 5. 你可以通过{setRequestFactory}属性切换到使用不同的HTTP库，如Apache HttpComponents、Netty和OkHttp。
     *
     * @return restTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate rest = new RestTemplate();
        return rest;
    }
}
