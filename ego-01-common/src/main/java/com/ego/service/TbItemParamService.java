package com.ego.service;

import com.ego.entity.EasyUIPage;

/**
 * <p>
 * 商品规则参数 服务类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
public interface TbItemParamService {
    public EasyUIPage list(int pageNum, int pageSize);
}
