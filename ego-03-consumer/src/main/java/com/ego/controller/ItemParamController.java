package com.ego.controller;

import com.ego.entity.EasyUIPage;
import com.ego.service.TbItemParamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuweiwei
 * @since 2020-8-15
 */
@RestController
@RequestMapping(value = "/item/param")
@Api(tags = "TbItemParamController", description = "商品规则参数相关接口")
public class ItemParamController {
    @Reference
    private TbItemParamService tbItemParamService;

    // http://localhost:8080/item/param/list?page=1&rows=30
    @GetMapping(value = "/list")
    @ApiOperation(value = "查询商品规则参数", notes = "查询")
    public EasyUIPage list(@RequestParam(value = "page") int pageNum, @RequestParam(value = "rows") int pageSize) {
        EasyUIPage itemList = tbItemParamService.list(pageNum, pageSize);
        return itemList;
    }
}