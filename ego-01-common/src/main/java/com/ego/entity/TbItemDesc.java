package com.ego.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liuweiwei
 * @since  2020-08-14
 */
@Data
@ToString
public class TbItemDesc implements Serializable {

    private Long itemId;

    private Date created;

    private Date updated;

    private String itemDesc;

}