package com.ego.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuweiwei 505831001@qq.com
 * @Description swagger2 配置
 * @since 2020-05-20
 */
@Configuration
public class SwaggerConfig {

    /**
     * Log4j 罗锅否街日志技能：log4j.properties（非：SLF4J 骚粉日志必备技能：logback.xml）
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ego.controller"))
                // .paths(PathSelectors.any())
                .paths(PathSelectors.regex("/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("介就系标题")
                .description("介就系描述")
                .termsOfServiceUrl("更新服务网址条款《服务条款URL》")
                .contact("更新负责此API的人员的联系信息")
                .version("2.9.2")
                .build();
    }

}
