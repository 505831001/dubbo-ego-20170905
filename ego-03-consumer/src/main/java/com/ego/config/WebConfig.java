package com.ego.config;

import com.ego.intercept.WebInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 1. 定义回调方法来定制基于java的配置，通过{@code @EnableWebMvc}启用Spring MVC。
 * 2. {@code @EnableWebMvc} 带注释的配置类可以实现此接口，以便回调该接口并给予自定义默认配置的机会。
 * 3. 考虑延长{@link WebMvcConfigurerAdapter}，它提供了所有接口方法的存根实现。
 *
 * @author liuweiwei
 * @since 2020-05-20
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 1. Spring MVC XML配置文档版本：dispatchServlet-servlet.xml。Spring XML配置文档：applicationContext.xml
     * 1.1 mvc:interceptors
     * 1.2 mvc:annotation-driven
     * 1.3 mvc:default-servlet-handler
     * 1.4 mvc:resources
     * 1.5 mvc:view-controller
     *
     * 2.
     *
     */

    @Value("${loginIntercept}")
    private boolean loginIntercept;

    @Autowired
    protected WebInterceptor webInterceptor;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 1. 创建拦截器顺路继承自：HandlerInterceptorAdapter 抽象类。HandlerInterceptor 接口。
        InterceptorRegistration interceptor1 = registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                return true;
            }
        });
        // 1.1 所有请求都拦截
        interceptor1.addPathPatterns("/**");
        // 1.2 配置不拦截请求（将不拦截请求添加进去）白名单
        interceptor1.excludePathPatterns("/rest/login.do/info");
        interceptor1.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");

        // 2. 自定义拦截器类继承自：HandlerInterceptorAdapter 抽象类。HandlerInterceptor 接口。
        if (loginIntercept) {
            InterceptorRegistration interceptor2 = registry.addInterceptor(webInterceptor);
            // 2.1 所有请求都拦截
            interceptor2.addPathPatterns("/**");
            // 2.2 配置不拦截请求（将不拦截请求添加进去）白名单
            interceptor2.excludePathPatterns("/rest/login.do/info");
            interceptor2.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath*:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath*:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }
}
