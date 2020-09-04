package com.ego.service.impl;

import com.ego.service.TbContentCategoryService;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 内容分类 服务实现类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
@Service
@Component
public class TbContentCategoryServiceImpl implements TbContentCategoryService {
    /**
     * SLF4J 骚人日志必备技能
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TbContentCategoryServiceImpl.class);
}
