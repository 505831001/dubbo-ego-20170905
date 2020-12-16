package com.ego.service;

import com.ego.entity.TbUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 购物车表 服务类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
public interface TbCartService {
    public int save(String token);

    public int delete(long id, HttpServletRequest request);

    public int update(long id, int number, HttpServletRequest request);

    public List<TbUser> list(HttpServletRequest request);
}
