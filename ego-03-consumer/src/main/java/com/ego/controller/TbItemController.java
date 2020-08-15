package com.ego.controller;

import com.ego.entity.TbItem;
import com.ego.service.TbItemService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author liuweiwei
 * @since 2020-8-15
 */
@Controller
@Api(tags = "TbItemController", description = "物料管理相关接口")
public class TbItemController {

    private Logger LOGGER = LoggerFactory.getLogger(TbItemController.class);

    @Reference
    private TbItemService tbItemService;

    // http://localhost:8080/item/list?page=1&rows=30
    @GetMapping(value = "/item/list")
    @ResponseBody
    @ApiOperation(value = "分页查询物料列表", notes = "查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "rows", value = "每页条数", required = false, dataType = "int", paramType = "query")
    })
    @ApiResponse(code = 200, message = "返回码:0-失败,1-成功", response = TbItem.class)
    public List<TbItem> list(@RequestParam(value = "page", defaultValue = "0") int pageNum,
                             @RequestParam(value = "rows", defaultValue = "10") int pageSize) {
        List<TbItem> list = tbItemService.list(pageNum, pageSize);
        LOGGER.info("[Liuweiwei] -> " + list);
        return list;
    }

    // http://localhost:8080/item/query?page=1&rows=30
    @GetMapping(value = "/item/query")
    @ApiOperation(value = "分页查询物料信息", notes = "查询")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, dataType = "int", paramType = "query")
    })
    @ApiResponse(code = 200, message = "返回码:0-失败,1-成功", response = TbItem.class)
    public PageInfo<TbItem> query(@ApiIgnore int pageNum, @ApiIgnore int pageSize) {
        PageInfo<TbItem> pageInfo = tbItemService.query(pageNum, pageSize);
        return pageInfo;
    }

    // http://localhost:8080/rest/item/reshelf
    @PutMapping(value = "/rest/item/reshelf")
    @ResponseBody
    @ApiOperation(value = "上架物料", notes = "上架")
    public Integer reshelf(@RequestParam(value = "ids") String ids) {
        int status = tbItemService.reshelf(ids);
        return status;
    }

    // http://localhost:8080/rest/item/instock
    @PutMapping(value = "/rest/item/instock")
    @ResponseBody
    @ApiOperation(value = "下架物料", notes = "下架")
    public Integer instock(@RequestParam(value = "ids") String ids) {
        int status = tbItemService.instock(ids);
        return status;
    }

    // http://localhost:8080/rest/item/delete
    @DeleteMapping(value = "/rest/item/delete")
    @ResponseBody
    @ApiOperation(value = "删除物料", notes = "删除")
    public Integer delete(@RequestParam(value = "ids") String ids) {
        int status = tbItemService.delete(ids);
        return status;
    }

    // http://localhost:8080/item/save

    /**
     * cid: 1
     * title: 标题01
     * sellPoint: 商品卖点01
     * priceView: 88.00
     * price: 8800
     * num: 100
     * barcode: 210201000098
     * image:
     * desc: 商品描述01
     * itemParams: []
     */
    @PostMapping(value = "/item/save")
    @ResponseBody
    @ApiOperation(value = "新增物料", notes = "保存")
    public Integer save(@RequestBody TbItem item, String desc) {
        TbItem tbItem = new TbItem();
        int status = tbItemService.save(item, desc);
        return status;
    }
}
