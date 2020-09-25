package com.ego.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * 异常：Cannot resolve constructor 'TbItemVO' 解决：一种是生成带参构造，一种是new对象在map()方法中。
 *
 * @author liuweiwei
 * @since 2020-08-14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TbItemVO", description = "物料出参实体")
public class TbItemVO {
    /**
     * 1. Cannot resolve constructor 'Source -> DatabasePO'
     *
     * @param source
     */
    public TbItemVO(TbItem source) {
        BeanUtils.copyProperties(source, this);
    }
    @ApiModelProperty(value = "商品ID")
    private Long id;
    @ApiModelProperty(value = "商品编号")
    private String no;
    @ApiModelProperty(value = "商品标题")
    private String title;
    @ApiModelProperty(value = "商品卖点")
    private String sellPoint;
    @ApiModelProperty(value = "商品价格单位：分")
    private Long price;
    @ApiModelProperty(value = "库存数量")
    private Integer num;
    @ApiModelProperty(value = "商品条形码")
    private String barcode;
    @ApiModelProperty(value = "商品图片")
    private String image;
    @ApiModelProperty(value = "所属类目")
    private Long cid;
    @ApiModelProperty(value = "商品状态 1-正常 2-下架 3-删除 默认1")
    private Byte status;
    @ApiModelProperty(value = "创建时间")
    private Date created;
    @ApiModelProperty(value = "更新时间")
    private Date updated;
}