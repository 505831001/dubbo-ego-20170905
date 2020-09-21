package com.ego.controller;

import com.ego.service.TbUserService;
import com.ego.utils.ResultMap;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuweiwei
 */
@RestController
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

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
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     */
    @PostMapping(value = "/login")
    public ResultMap login(String username, String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        subject.login(token);
        //根据权限，指定返回数据
        String role = tbUserService.getRole(username);
        LOGGER.info("登录用户权限域：" + role);
        System.out.println("登录用户权限域：" + role);
        if ("user".equals(role)) {
            return resultMap.success().message("欢迎登陆");
        }
        if ("admin".equals(role)) {
            return resultMap.success().message("欢迎来到管理员页面");
        }
        return resultMap.fail().message("权限错误！");
    }

    /**
     * @CrossOrigin 注解用来配置跨域请求，第一个参数origins表示那些域名可以跨域访问这个方法。
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping(value = "/login2")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
    public String login2(String username, String password) {
        if ("admin".equals(username) && "123456".equals(password)) {
            return "success or Successful";
        } else {
            return "failure or Failed";
        }
    }
}
