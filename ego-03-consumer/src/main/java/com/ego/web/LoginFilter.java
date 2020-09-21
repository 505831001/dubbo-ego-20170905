package com.ego.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. 设置跨域请求相关参数的过滤器。
 * 2. 过滤器是一个对象，它对资源(servlet或静态内容)的请求或资源的响应执行过滤任务，或对两者执行过滤任务。
 *
 * @author liuweiwei
 * @since 2020-09-04
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = "*.do")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response1 = (HttpServletResponse) response;
        response1.setHeader("Access-Control-Allow-Origin", "*");
        response1.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response1.setHeader("Access-Control-Max-Age", "3600");
        response1.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, client_id, uuid, Authorization");
        response1.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response1.setHeader("Pragma", "no-cache");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
