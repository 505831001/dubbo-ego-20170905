package com.ego.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class TbItemParam implements Serializable {

    private Long id;
    private Long itemCatId;
    private Date created;
    private Date updated;
    private String paramData;

}