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
public class CookieUtils3 {

    public static final int COOKIE_MAX_AGE = 7 * 24 * 3600;
    public static final int COOKIE_HALF_HOUR = 30 * 60;
    private static HttpServletResponse response;
    private static HttpServletRequest request;

    private HttpServletRequest request2;
    private HttpServletResponse response2;

    @PostConstruct
    public void beforeInit() {
        request = request2;
        response = response2;
    }

    /**
     * 设置Cookie：可以指定过期时间，单位：秒
     */
    public static void setCookie(String name, String value, int maxValue) {
        if (StringUtils.isBlank(name)) {
            return;
        }
        if (null == value) {
            value = "";
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxValue != 0) {
            cookie.setMaxAge(maxValue);
        } else {
            cookie.setMaxAge(COOKIE_HALF_HOUR);
        }
        response.addCookie(cookie);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置Cookie：默认30分钟过期时间
     */
    public static void setCookie(String name, String value) {
        setCookie(name, value, COOKIE_HALF_HOUR);
    }



    /**
     * 获取Cookie对象，不存在该对象则返回Null
     */
    public static Cookie getCookie(String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length < 1) {
            return null;
        }
        Cookie cookie = null;
        for (Cookie c : cookies) {
            if (name.equals(c.getName())) {
                cookie = c;
                break;
            }
        }
        return cookie;
    }

    /**
     * 获取Cookie值
     */
    public static String getCookieValue(String name) {
        Cookie cookie = getCookie(name);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    /**
     * 移除Cookie
     */
    public static void removeCookie(String name) {
        if (null == name) {
            return;
        }
        Cookie cookie = getCookie(name);
        if (null != cookie) {
            cookie.setPath("/");
            cookie.setValue("");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    /**
     * 将cookie封装到Map里面
     */
    public static Map<String, Cookie> getCookieMap() {
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 1) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
