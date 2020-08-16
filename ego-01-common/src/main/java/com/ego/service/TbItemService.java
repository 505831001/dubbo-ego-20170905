package com.ego.service;

import com.ego.entity.TbItem;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
public interface TbItemService {
    public List<TbItem> list(int pageNum, int pageSize);

    public PageInfo<TbItem> query(int pageNum, int pageSize);

    public Integer reshelf(String ids) throws Exception;

    public Integer instock(String ids) throws Exception;

    public Integer delete(String ids) throws Exception;

    /**
     * 配置版：<tx:method name="insert" rollback-for="java.lang.Exception"/>
     * 注解版：@Transactional(rollbackFor = java.lang.Exception.class)
     *
     * @param tbItem
     * @param desc
     * @return Integer
     * @throws Exception 此处抛出异常可供Dubbo RPC服务Provider和Consumer进行事务交互
     */
    public Integer save(TbItem tbItem, String desc) throws Exception;
}
