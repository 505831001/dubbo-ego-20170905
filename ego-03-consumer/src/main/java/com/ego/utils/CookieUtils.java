package com.ego.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @Author liuweiwei 505831001@qq.com
 * @Description Cookie 工具类
 * @since 2020-05-20
 */
@Component
public class CookieUtils {

    /**
     * 设置Cookie
     *
     * @param request
     * @param response
     * @param key
     * @param value
     * @param expiry
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String key, String value, int expiry) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(expiry);
        response.addCookie(cookie);
    }

    /**
     * 获取所有Cookie
     *
     * @param request
     * @return
     */
    public static Map<String, String> getCookies(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (!"JSESSION".equals(cookies[i].getName())) {
                    map.put(cookies[i].getName(), cookies[i].getValue());
                }
            }
        }
        return map;
    }

    /**
     * 清空所有的Cookie
     *
     * @param request
     * @param response
     */
    public static void clear(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = getCookies(request);
        Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> me = iter.next();
            Cookie cookie = new Cookie(me.getKey(), "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    /**
     * 删除Cookie带cookie域名
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        doSetCookie(request, response, cookieName, "", -1, "UTF-8");
    }

    /**
     * 设置Cookie的值，并使其在指定时间内生效
     *
     * @param cookieMaxage cookie生效的最大秒数
     */
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, String encodeString) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else {
                cookieValue = URLEncoder.encode(cookieValue, encodeString);
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0) {
                cookie.setMaxAge(cookieMaxage);
            }
            if (null != request) {// 设置域名的cookie
                String domainName = getDomainName(request);
                System.out.println(domainName);
                if (!"localhost".equals(domainName)) {
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到cookie的域名
     */
    private static final String getDomainName(HttpServletRequest request) {
        String domainName = null;

        String serverName = request.getRequestURL().toString();
        if (serverName == null || serverName.equals("")) {
            domainName = "";
        } else {
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if (len > 3) {
                // www.xxx.com.cn
                domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len <= 3 && len > 1) {
                // xxx.com or xxx.cn
                domainName = "." + domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }
        }

        if (domainName != null && domainName.indexOf(":") > 0) {
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
        return domainName;
    }
}
