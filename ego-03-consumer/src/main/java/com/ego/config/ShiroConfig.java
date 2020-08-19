package com.ego.config;

import com.ego.utils.CustomRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @Author liuweiwei 505831001@qq.com
 * @Description shiro 过滤器配置
 * @since 2020-05-20
 */
@Configuration
public class ShiroConfig {
    /**
     * 注入SecurityManager
     *
     * @param customRealm
     * @return SecurityManager
     */
    @Bean
    public SecurityManager securityManager(CustomRealm customRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置Realm
        securityManager.setRealm(customRealm);
        System.out.println("<<Spring Boot 启动加载配置>> - Web 安全管理类注入成功：" + securityManager.toString());
        return securityManager;
    }

    /**
     * 设置拦截器
     *
     * @param securityManager
     * @return ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilter(SecurityManager securityManager) {
        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // swagger接口权限开放
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        // test接口权限配置
        filterChainDefinitionMap.put("/guest/**", "anon");
        filterChainDefinitionMap.put("/user/**", "roles[user]");
        filterChainDefinitionMap.put("/admin/**", "roles[admin]");
        filterChainDefinitionMap.put("/login", "anon");
        // 警告All要开放的接口必须在此双星号**之前配置
        filterChainDefinitionMap.put("/**", "authc");
        // 设置拦截器
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(securityManager);
        filter.setLoginUrl("/notLogin");
        filter.setUnauthorizedUrl("/notRole");
        filter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("<<Spring Boot 启动加载配置>> - Shiro 拦截器工厂类注入成功：" + filter.toString());
        return filter;
    }
}