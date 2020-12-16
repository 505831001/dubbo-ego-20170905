package com.ego.config;

import com.ego.intercept.Web02Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 1.这是提供MVC Java配置背后的配置的主要类。
 * 2.它通常是通过添加{@EnableWebMvc}到应用程序{@Configuration}类来导入的。
 * 3.另一种更高级的选择是直接从这个类扩展，并在必要时覆盖方法，记住添加{@Configuration}到子类，添加{@Bean}到覆盖{@Bean}方法。
 * 4.有关更多细节，请参见{@EnableWebMvc}的Javadoc。
 *
 * @author liuweiwei
 * @since 2020-05-20
 */
@Configuration
public class WebMvcSupportConfig extends WebMvcConfigurationSupport {

    @Autowired
    protected Web02Interceptor web02Interceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 2. 自定义拦截器类继承自：HandlerInterceptorAdapter 抽象类。HandlerInterceptor 接口。
        registry.addInterceptor(web02Interceptor)
                // 2.1 所有请求都拦截
                .addPathPatterns("/**")
                // 2.2 配置不拦截请求（将不拦截请求添加进去）白名单
                .excludePathPatterns("/rest/login.do/info")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
