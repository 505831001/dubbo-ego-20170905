package com.ego.service.impl;

import com.ego.service.TbUserServiceApi;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
public class TbUserServiceImplApi implements TbUserServiceApi {

    @Override
    public String getPassword(String username) {
        return "admin";
    }

    @Override
    public String getRole(String username) {
        return "admin";
    }
}
