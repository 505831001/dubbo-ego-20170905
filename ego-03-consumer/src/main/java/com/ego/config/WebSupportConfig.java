package com.ego.config;

import com.ego.intercept.WebInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 1.这是提供MVC Java配置背后的配置的主要类。
 * 2.它通常是通过添加{@EnableWebMvc}到应用程序{@Configuration}类来导入的。
 * 3.另一种更高级的选择是直接从这个类扩展，并在必要时覆盖方法，记住添加{@Configuration}到子类，添加{@Bean}到覆盖{@Bean}方法。
 * 4.有关更多细节，请参见{@EnableWebMvc}的Javadoc。
 *
 * @author liuweiwei
 * @since 2020-05-20
 */
//@Configuration
public class WebSupportConfig extends WebMvcConfigurationSupport {

    @Value("${loginIntercept}")
    private boolean loginIntercept;

    @Autowired
    protected WebInterceptor webInterceptor;

    /**
     * 重写此方法以添加用于控制器调用的预处理和后处理的Spring MVC拦截器。
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        if (loginIntercept == true) {
            // 2. 自定义拦截器类继承自：HandlerInterceptorAdapter 抽象类。HandlerInterceptor 接口。
            InterceptorRegistration interceptor2 = registry.addInterceptor(webInterceptor);
            // 2.1 所有请求都拦截
            interceptor2.addPathPatterns("/**");
            // 2.2 配置不拦截请求（将不拦截请求添加进去）白名单
            interceptor2.excludePathPatterns("/rest/login.do/info");
            interceptor2.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
        }
    }

    /**
     * 重写此方法以添加为静态资源提供服务的资源处理程序。
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath*:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath*:/META-INF/resources/webjars/");
    }
}
