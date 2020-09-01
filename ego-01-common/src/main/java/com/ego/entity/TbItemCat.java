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
public class TbItemCat implements Serializable {

    private Long id;

    private Long parentId;

    private String name;

    private Integer status;

    private Integer sortOrder;

    private Boolean isParent;

    private Date created;

    private Date updated;

}