package com.ego.controller;

import com.ego.entity.PageResult;
import com.ego.entity.TbUser;
import com.ego.service.TbUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
@Api(tags = "TbUserController", description = "用户管理相关接口")
public class TbUserController {

    @Reference
    private TbUserService tbUserService;

    /**
     * 查询用户列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/query")
    @ApiOperation(value = "查询用户列表", notes = "查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "Integer", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回码:0-失败,1-成功", response = TbUser.class)
    })
    public PageResult<TbUser> query(@ApiIgnore int pageNum, @ApiIgnore int pageSize) {
        PageResult<TbUser> list = tbUserService.query(pageNum, pageSize);
        return list;
    }

    /**
     * 分页查询用户列表
     *
     * @param pageNum  当前页码
     * @param pageSize 每页条数
     * @return PageInfo 分页对象
     */
    @GetMapping(value = "/pageInfo")
    @ApiOperation(value = "分页查询用户列表", notes = "查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "Integer", paramType = "query")
    })
    public PageInfo<TbUser> pageInfo(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "4") int pageSize) {
        PageInfo<TbUser> pageInfo = tbUserService.getPageTbUser(pageNum, pageSize);
        return pageInfo;
    }
}