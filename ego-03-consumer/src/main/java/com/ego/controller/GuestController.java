package com.ego.controller;

import com.ego.utils.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 游客控制器类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
@RestController
@RequestMapping(value = "/guest")
public class GuestController {
    @Autowired
    private ResultMap resultMap;

    @GetMapping(value = "/enter")
    public ResultMap login() {
        return resultMap.success().message("欢迎进入，您的身份是游客");
    }

    @GetMapping(value = "/getMessage")
    public ResultMap submitLogin() {
        return resultMap.success().message("您拥有获得该接口的信息的权限！");
    }
}
