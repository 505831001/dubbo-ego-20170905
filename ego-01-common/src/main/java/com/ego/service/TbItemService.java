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
    public Integer reshelf(String ids);
    public Integer instock(String ids);
    public Integer delete(String ids);
    public Integer save(TbItem tbItem, String desc);
}
