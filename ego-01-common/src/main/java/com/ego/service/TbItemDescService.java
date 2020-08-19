package com.ego.service;

import com.ego.entity.TbItemDesc;

/**
 * <p>
 * 商品描述表 服务类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
public interface TbItemDescService {
    /**
     * 配置版：<tx:method name="insert" rollback-for="java.lang.Exception"/>
     * 注解版：@Transactional(rollbackFor = java.lang.Exception.class)
     *
     * @param record
     * @return int
     * @throws Exception 此处抛出异常可供Dubbo RPC服务Provider和Consumer进行事务交互
     */
    public int insert(TbItemDesc record) throws Exception;
}
