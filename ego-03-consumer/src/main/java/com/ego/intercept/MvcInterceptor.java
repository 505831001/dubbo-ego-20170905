package com.ego.intercept;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1. 允许定制处理程序执行链的工作流接口。
 * 2. 应用程序可以为某些处理程序组注册任意数量的现有或自定义拦截器，
 * 3. 添加通用的预处理行为而不需要修改每个处理程序实现。
 *
 * @author liuweiwei
 * @since 2020-05-20
 */
public class MvcInterceptor implements HandlerInterceptor {
    /**
     * 1. 拦截处理程序的执行。
     * 2. 在HandlerMapping确定适当的处理程序对象之后调用，但在HandlerAdapter调用处理程序之前。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        System.out.println("Spring MVC 拦截器： preHandle() -> " + object);
        return true;
    }

    /**
     * 1. 拦截处理程序的执行。
     * 2. 在HandlerAdapter实际调用处理程序之后，但在DispatcherServlet呈现视图之前调用。
     * 3. 可以通过给定的ModelAndView向视图公开其他模型对象。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView mv) throws Exception {
        System.out.println("Spring MVC 拦截器： postHandle() -> " + object);
    }

    /**
     * 1. 请求处理完成后的回调，即呈现视图后的回调。
     * 2. 将在处理程序执行的任何结果上调用，因此允许正确的资源清理。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception e) throws Exception {
        System.out.println("Spring MVC 拦截器： afterCompletion() -> " + object);
    }
}
