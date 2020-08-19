package com.ego.service.impl;

import com.ego.service.TbUserServiceApi;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
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
