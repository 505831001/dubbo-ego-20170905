package com.ego.service;

import com.ego.entity.PageResult;
import com.ego.entity.TbUser;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
public interface TbUserService {
    public List<TbUser> getAll();

    public PageResult<TbUser> query(int pageNum, int pageSize);

    public PageInfo<TbUser> getPageTbUser(int pageNum, int pageSize);

    public String getPassword(String username);

    public String getRole(String username);
}
