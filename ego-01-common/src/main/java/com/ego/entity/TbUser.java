package com.ego.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liuweiwei
 * @since  2020-8-14
 */
@Data
@ToString
public class TbUser implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Date created;
    private Date updated;

}