package com.ego.controller;

import com.ego.entity.TbItemCat;
import com.ego.service.TbItemCatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liuweiwei
 * @since 2020-8-15
 */
@RestController
@RequestMapping(value = "/item/cat")
@Api(tags = "TbItemCatController", description = "物料品类相关接口")
public class ItemCatController {

    @Reference
    private TbItemCatService tbItemCatService;

    // http://localhost:8080/item/cat/list
    @GetMapping(value = "/list")
    @ApiOperation(value = "查询物料品类列表", notes = "查询")
    public List<TbItemCat> list() {
        List<TbItemCat> list = tbItemCatService.list();
        return list;
    }
}
