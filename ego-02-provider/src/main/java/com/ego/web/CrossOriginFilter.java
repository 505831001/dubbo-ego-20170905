package com.ego.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. 设置跨域请求相关参数的过滤器。
 * 2. 过滤器是一个对象，它对资源(servlet或静态内容)的请求或资源的响应执行过滤任务，或对两者执行过滤任务。
 *
 * @author liuweiwei
 * @since 2020-09-04
 */
@WebFilter(value = "/*")
public class CrossOriginFilter implements Filter {
    /**
     * 1. 由web容器调用，以指示筛选器将其放置到服务中。
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 2. 每当客户机请求链末端的资源时，容器就会调用过滤器的doFilter方法。
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        request1.setCharacterEncoding("UTF-8");
        // 设置哪些域可以跨域访问，星号*代表所有域。
        response1.setHeader("Access-Control-Allow-Origin", "*");
        // 设置支持那种访问方法。
        response1.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
        chain.doFilter(request, response);
    }

    /**
     * 3. 由web容器调用，以指示筛选器将其从服务中取出。
     */
    @Override
    public void destroy() {

    }
}
