package com.ego.service.impl;

import com.ego.service.TbContentService;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
@Service
@Component
public class TbContentServiceImpl implements TbContentService {
    /**
     * SLF4J 骚人日志必备技能
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TbContentServiceImpl.class);
}
