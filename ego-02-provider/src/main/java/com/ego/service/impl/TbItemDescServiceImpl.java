package com.ego.service.impl;

import com.ego.dao.TbItemDescMapper;
import com.ego.entity.TbItemDesc;
import com.ego.service.TbItemDescService;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 商品描述表 服务实现类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
@Service
@Component
public class TbItemDescServiceImpl implements TbItemDescService {
    /**
     * SLF4J 骚人日志必备技能
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TbItemDescServiceImpl.class);

    @Autowired(required = false)
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public int insert(TbItemDesc record) throws Exception {
        int index = tbItemDescMapper.insert(record);
        return index;
    }
}
