package com.ego.config;

import com.ego.service.TbUserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 */
@Configuration
public class WebSupportConfig extends WebMvcConfigurationSupport {
    @Reference
    protected TbUserService tbUserService;
    /**
     * 重写此方法以添加用于控制器调用的预处理和后处理的Spring MVC拦截器。
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 1. 创建拦截器顺路继承自：HandlerInterceptorAdapter 抽象类。HandlerInterceptor 接口。
        InterceptorRegistration interceptor = registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                String password = tbUserService.getPassword("admin");
                if (StringUtils.isEmpty(password)) {
                    throw new RuntimeException("登录失败");
                }
                return true;
            }
        });
        // 1.1 所有请求都拦截
        interceptor.addPathPatterns("/**");
        // 1.2 配置不拦截请求（将不拦截请求添加进去）白名单
        interceptor.excludePathPatterns("/rest/login.do/info");
        interceptor.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
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
