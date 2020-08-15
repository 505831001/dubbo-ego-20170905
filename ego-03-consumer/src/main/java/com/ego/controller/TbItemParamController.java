package com.ego.controller;

import com.ego.entity.EasyUIPage;
import com.ego.service.TbItemParamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liuweiwei
 * @since 2020-8-15
 */
@Controller
@RequestMapping(value = "/item/param")
@Api(tags = "TbItemParamController", description = "商品规则参数相关接口")
public class TbItemParamController {

    @Reference
    private TbItemParamService tbItemParamService;

    // http://localhost:8080/item/param/list?page=1&rows=30
    @GetMapping(value = "/list")
    @ResponseBody
    @ApiOperation(value = "查询商品规则参数", notes = "查询")
    public EasyUIPage list(@RequestParam(value = "page") int pageNum, @RequestParam(value = "rows") int pageSize) {
        EasyUIPage itemList = tbItemParamService.list(pageNum, pageSize);
        return itemList;
    }

}