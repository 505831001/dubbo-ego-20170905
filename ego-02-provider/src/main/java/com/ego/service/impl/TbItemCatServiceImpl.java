package com.ego.service.impl;

import com.ego.entity.TbItemCat;
import com.ego.dao.TbItemCatMapper;
import com.ego.service.TbItemCatService;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 商品类目 服务实现类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
@Service
@Component
public class TbItemCatServiceImpl implements TbItemCatService {
    /**
     * SLF4J 骚粉日志必备技能
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TbItemCatServiceImpl.class);

    @Autowired(required = false)
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TbItemCat> list() {
        List<TbItemCat> list = tbItemCatMapper.selectAll();
        return list;
    }
}
