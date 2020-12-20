package com.ego.controller;

import com.ego.entity.TbUser;
import com.ego.service.TbUserService;
import com.ego.utils.ResultMap;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuweiwei
 */
@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ResultMap resultMap;

    @Reference
    protected TbUserService tbUserService;

    @GetMapping(value = "/notLogin")
    public ResultMap notLogin() {
        return resultMap.success().message("刘伟伟，您尚未登陆！");
    }

    @GetMapping(value = "/notRole")
    public ResultMap notRole() {
        return resultMap.success().message("Shiro Filter 按请求路径拦->(/notRole)您没有权限！");
    }

    @GetMapping(value = "/logout")
    public ResultMap logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return resultMap.success().message("成功注销！");
    }

    /**
     * Spring Security 安全框架方式
     *
     * @param username 用户名
     * @param password 密码
     */
    @GetMapping(value = "/login")
    public ResultMap login(
            @RequestParam(name = "username", required = false, defaultValue = "abc") String username,
            @RequestParam(name = "password", required = false, defaultValue = "123") String password) {
        Object details = new Object();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        token.setDetails(details);
        token.setAuthenticated(true);

        String role = tbUserService.getRole(username);
        if (!StringUtils.isNotEmpty(role)) {
            return resultMap.success().message("欢迎来到成功页面！");
        } else {
            return resultMap.fail().message("权限错误！");
        }
    }

    /**
     * Apache shiro 安全框架方式
     *
     * @param username 用户名
     * @param password 密码
     */
    @GetMapping(value = "/login2")
    public ResultMap login2(
            @RequestParam(name = "username", required = false, defaultValue = "abc") String username,
            @RequestParam(name = "password", required = false, defaultValue = "123") String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);

        String role = tbUserService.getRole(username);
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(role)) {
            return resultMap.success().message("欢迎来到成功页面！");
        } else {
            return resultMap.fail().message("权限错误！");
        }
    }

    /**
     * @CrossOrigin 注解用来配置跨域请求，第一个参数origins表示那些域名可以跨域访问这个方法。
     *
     * @param username
     * @param password
     * @return
     */
    @GetMapping(value = "/login3")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
    public String login3(
            @RequestParam(name = "username", required = false, defaultValue = "abc") String username,
            @RequestParam(name = "password", required = false, defaultValue = "123") String password) {
        if ("admin".equals(username) && "123456".equals(password)) {
            return "<<success>> or <<successful>>";
        } else {
            return "<<failure>> or <<failed>>";
        }
    }

    @PostMapping(value = "/login4")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
    public String login4(@RequestBody(required = false)TbUser user) {
        if ("admin".equals(user.getUsername()) && "123456".equals(user.getPassword())) {
            return "<<success>> or <<successful>>";
        } else {
            return "<<failure>> or <<failed>>";
        }
    }
}
