package com.ego.controller;

import com.ego.entity.EasyUIPage;
import com.ego.entity.TbItem;
import com.ego.entity.TbItemVO;
import com.ego.service.TbItemService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品表 控制器类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
@RestController
@Api(tags = "TbItemController", description = "物料管理相关接口")
public class ItemController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Reference
    protected TbItemService tbItemService;

    /**
     * 列表查询物料信息
     *
     * @return TbItemVO
     */
    // http://localhost:8080/item/page?page=1&rows=30
    @GetMapping(value = "/item/page")
    @ApiOperation(value = "列表查询物料信息", notes = "查询")
    @ApiResponse(code = 200, message = "返回码 0-失败 1-成功", response = TbItemVO.class)
    public List<TbItemVO> page() {
        List<TbItem> sourceList = tbItemService.page();
        return sourceList.stream().map(TbItemVO::new).collect(Collectors.toList());
    }

    // http://localhost:8080/item/list?page=1&rows=30
    @GetMapping(value = "/item/list")
    @ApiOperation(value = "分页查询物料列表", notes = "查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "rows", value = "每页条数", required = false, dataType = "int", paramType = "query")
    })
    @ApiResponse(code = 200, message = "返回码:0-失败,1-成功", response = TbItem.class)
    public EasyUIPage list(@RequestParam(value = "page", defaultValue = "0") int pageNum,
                           @RequestParam(value = "rows", defaultValue = "10") int pageSize) {
        EasyUIPage result = tbItemService.list(pageNum, pageSize);
        return result;
    }

    // http://localhost:8080/item/query?page=1&rows=30
    @GetMapping(value = "/item/query")
    @ApiOperation(value = "分页查询物料信息", notes = "查询")
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
    @ApiOperation(value = "上架物料", notes = "上架")
    public Integer reshelf(@RequestParam(value = "ids") String ids) {
        int status = 0;
        try {
            status = tbItemService.reshelf(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // http://localhost:8080/rest/item/instock
    @PutMapping(value = "/rest/item/instock")
    @ApiOperation(value = "下架物料", notes = "下架")
    public Integer instock(@RequestParam(value = "ids") String ids) {
        int status = 0;
        try {
            status = tbItemService.instock(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // http://localhost:8080/rest/item/delete
    @DeleteMapping(value = "/rest/item/delete")
    @ApiOperation(value = "删除物料", notes = "删除")
    public Integer delete(@RequestParam(value = "ids") String ids) {
        int status = 0;
        try {
            status = tbItemService.delete(ids);
        } catch (Exception e) {
            LOGGER.info("TbItemController.delete" + e.getMessage());
            e.printStackTrace();
        }
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
    @ApiOperation(value = "新增物料", notes = "保存")
    public Integer save(@RequestBody TbItem item, String desc) {
        int status = 0;
        try {
            status = tbItemService.save(item, desc);
        } catch (Exception e) {
            LOGGER.info("TbItemController.save" + e.getMessage());
            e.printStackTrace();
        }
        return status;
    }
}
