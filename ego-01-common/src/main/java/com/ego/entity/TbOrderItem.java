package com.ego.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author liuweiwei
 * @since  2020-08-14
 */
@Data
@ToString
public class TbOrderItem implements Serializable {

    private String id;

    private String itemId;

    private String orderId;

    private Integer num;

    private String title;

    private Long price;

    private Long totalFee;

    private String picPath;

}