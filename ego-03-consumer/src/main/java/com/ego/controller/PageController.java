package com.ego.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Api(tags = "PageController", description = "页面跳转相关接口")
public class PageController {

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    /*
    @GetMapping(value = "/{path}")
    public String path(@PathVariable(value = "path") String path) {
        return path;
    }
    */

}
