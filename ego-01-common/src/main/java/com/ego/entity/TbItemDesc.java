package com.ego.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class TbItemDesc implements Serializable {

    private Long itemId;

    private Date created;

    private Date updated;

    private String itemDesc;

}