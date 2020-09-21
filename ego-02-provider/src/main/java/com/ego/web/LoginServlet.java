package com.ego.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. 提供一个抽象类，用于创建适合于Web站点的HTTP servlet的子类。
 * 2. 一个子类的HttpServlet必须覆盖至少一个方法，通常是其中一个：
 *
 * @author liuweiwei
 * @since 2020-09-04
 */
@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        response.setContentType("text/html;charset=UTF-8");
        if ("admin".equals(username) && "123456".equals(password)) {
            response.getWriter().print("success or Successful");
        } else {
            response.getWriter().print("failure or Failed");
        }
        super.service(request, response);
    }
}
