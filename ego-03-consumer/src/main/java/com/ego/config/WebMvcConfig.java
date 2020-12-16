package com.ego.config;

import com.ego.intercept.Web01Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * 1. 定义回调方法来定制基于java的配置，通过{@code @EnableWebMvc}启用Spring MVC。
 * 2. {@code @EnableWebMvc} 带注释的配置类可以实现此接口，以便回调该接口并给予自定义默认配置的机会。
 * 3. 考虑延长{@link WebMvcConfigurerAdapter}，它提供了所有接口方法的存根实现。
 *
 * @author liuweiwei
 * @since 2020-05-20
 */
public class WebMvcConfig extends WebMvcAutoConfiguration implements WebMvcConfigurer {

    /**
     * 《过滤器的传说》
     *
     * 1. java web 跨域请求的几种方式
     *      1.1 基于Servlet和过滤器的方式。方式一。
     *      2.1 SpringMVC通过@CrossOrigin注解设置跨域请求。方式一。
     * 3. 通过XML配置文件配置全局的跨域访问。
     *      1.2 使用拦截器，实现javax.servlet.Filter接口。
     *      2.2 使用注解的形式，配置org.springframework.web.filter.CorsFilter。通常SpringBoot项目多数使用这种方式。
     * 4. 常用的方法有传统JDK自带的java.net.URLConnection抽象类。
     * 5. 使用Apache Common包下的子项目的HttpClient工具类。
     * 6. 使用Spring框架的RestTemplate模块类。在SpringBoot项目下使用。
     * 7. 使用Spring Cloud分布式组件Feign。
     */

    /**
     * 《拦截器的传说》
     *
     * 1. Spring MVC 框架 3.0 - 拦截器：Interceptor。
     *
     * 1.1 Spring MVC XML配置文档版本：dispatchServlet-servlet.xml。Spring XML配置文档：applicationContext.xml。
     *      <mvc:default-servlet-handler></mvc:default-servlet-handler>
     *      1. 通过转发到Servlet容器的默认Servlet来配置用于服务静态资源的处理程序。
     *      2. 使用这种处理程序允许使用"/"映射与DispatcherServlet，同时仍然利用Servlet容器提供静态服务资源。
     *      3. org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler。
     *      <mvc:annotation-driven></mvc:annotation-driven>
     *      1. 配置注解驱动的Spring MVC控制器编程模型。注意，在Spring 3.0中，这个标签只能在Servlet MVC中工作。
     *      2. 看到EnableWebMvc Javadoc的关于启用注释驱动的Spring MVC的基于代码的替代方案的信息支持。
     *      3. org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping。
     *      4. org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter。
     *      <mvc:resources></mvc:resources>
     *      <mvc:resources location="/WEB-INF/js/"     mapping="/js/**"></mvc:resources>
     *      <mvc:resources location="/WEB-INF/images/" mapping="/images/**"></mvc:resources>
     *      <mvc:resources location="/WEB-INF/css/"    mapping="/css/**"></mvc:resources>
     *      1. 配置一个处理程序，用于服务静态资源，比如：images，js，css文件与缓存头优化高效在web浏览器中加载。
     *      2. 允许从通过Spring的资源处理可到达的任何路径提供资源。
     *      <mvc:interceptors></mvc:interceptors>
     *      <mvc:interceptors>
     *         <mvc:interceptor>
     *             <mvc:mapping path="/**"/>
     *             <mvc:exclude-mapping path="/swagger-resources/**"/>
     *             <mvc:exclude-mapping path="/v2/**"/>
     *             <mvc:exclude-mapping path="/swagger-ui.html#!/**"/>
     *             <mvc:exclude-mapping path="/swagger-resources/**"/>
     *             <bean class="com.ego.intercept.MvcInterceptor"/>
     *         </mvc:interceptor>
     *      </mvc:interceptors>
     *      1. 拦截器的有序集合，用于拦截由控制器处理的HTTP Servlet请求。
     *      2. 拦截器允许在处理之前/之后对请求进行预处理/后处理。
     *      3. 每个inteceptor必须实现HandlerInterceptor。或者WebRequestInterceptor接口。
     *      4. 此集合中的拦截器在每个注册的处理程序映射上自动配置。
     *      5. 每个拦截器应用的URI路径是可配置的。
     *      <mvc:view-controller></mvc:view-controller>
     *      <mvc:view-controller path="viewController" view-name="login"></mvc:view-controller>
     *      1. 定义一个简单的控制器，它选择一个视图来呈现响应。
     *      2. 视图映射到的URL路径。
     *      3. 要呈现的视图的名称。可选的。
     *      4. 如果没有指定，视图名称将从当前HttpServletRequest中确定通过DispatcherServlet的RequestToViewNameTranslator。
     * 1.2 Spring Boot继承HandlerInterceptorAdapter新版本。或者继承WebMvcConfigurationSupport旧版本。
     *
     */

    @Autowired
    private Web01Interceptor web01Interceptor;

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
        registry.addInterceptor(web01Interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/rest/login.do/info")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
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
