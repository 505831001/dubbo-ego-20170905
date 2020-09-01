package com.ego.service;

/**
 * @author liuweiwei
 * @since  2020-08-29
 */
public interface TbUserServiceApi {
    public String getPassword(String username);

    public String getRole(String username);
}
