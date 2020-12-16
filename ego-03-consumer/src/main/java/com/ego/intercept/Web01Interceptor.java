package com.ego.intercept;

import com.ego.annotation.UnIntercept;
import com.ego.service.TbUserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1. {AsyncHandlerInterceptor} 接口的抽象适配器类，用于简化pre-only/post-only拦截器的实现。
 * 1.1  preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
 * 1.2 postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
 *
 * @author liuweiwei
 * @since 2020-05-20
 */
@Component
public class Web01Interceptor implements HandlerInterceptor {

    @Reference
    protected TbUserService tbUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器01打印信息...");

        // 添加跨域CORS
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,token");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");

        if (!isNeedAuthFilter(handler)) {
            return true;
        }

        String password = tbUserService.getPassword("admin");
        if (StringUtils.isEmpty(password)) {
            throw new RuntimeException("登录失败");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 2. 判断是否需要拦截，自定义不拦截注解类：@UnIntercept。
     *
     * @param handler
     * @return boolean
     */
    private boolean isNeedAuthFilter(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            return handlerMethod.getMethod().getAnnotation(UnIntercept.class) == null;
        }
        return false;
    }
}
