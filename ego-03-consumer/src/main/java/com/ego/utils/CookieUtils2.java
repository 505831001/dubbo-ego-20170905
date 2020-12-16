package com.ego.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @Author liuweiwei 505831001@qq.com
 * @Description Cookie 工具类
 * @since 2020-05-20
 */
@Component
public class CookieUtils2 {

    /**
     * 获取Cookie
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 设置Cookie
     *
     * @param response
     * @param cookieName
     * @param value
     * @param cookieMaxAge
     */
    public static void setCookie(HttpServletResponse response, String cookieName, String value, int cookieMaxAge) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setPath("/");
        cookie.setMaxAge(cookieMaxAge);
        response.addCookie(cookie);
    }

    /**
     * 删除Cookie
     *
     * @param response
     * @param cookieName
     */
    public static void deleteCookie(HttpServletResponse response, String cookieName) {
        setCookie(response, cookieName, null, 0);
    }
}
