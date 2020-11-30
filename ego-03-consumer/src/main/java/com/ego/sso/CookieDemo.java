package com.ego.sso;

/**
 * 1. SSO Single Sign-On - 单点登录
 * 1.1 单点登录的解释，一次登录，让其他项目共享登录状态。
 * 1.2 本质：使用特定技术在分布式项目中模拟 HttpSession 功能。
 * 1.3 技术选型：Redis + Cookie模拟 HttpSession 功能。
 * 1.4 传统项目和分布式项目，登录功能对比：
 *   1. 传统项目
 *     1. 浏览器->服务器(Tomcat把用户登录信息放入到HttpSession，放入到服务器内存中)
 *   2. 分布式项目
 *     1. 浏览器->登录模块->Redis存储登录信息->Cookie<Key>返回给浏览器。
 *     2. Cookie<Key>->商品模块->取Redis。
 *     3. Cookie<Key>->留言模块->取Redis。
 *     4. 比如JD首页https://www.jd.com跳转到登录模块https://passport.jd.com。
 *
 * @author liuweiwei
 * @since 2020-11-23
 */
public class CookieDemo {
        
}
