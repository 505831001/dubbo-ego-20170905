package com.ego.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.ego.entity.TbUser;
import com.ego.service.TbCartService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
@Service
@Component
public class TbCartServiceImpl implements TbCartService {
    /**
     * Log4j 罗锅否街日志必备技能
     */
    private final org.apache.log4j.Logger logger = Logger.getLogger(this.getClass());

    protected RedisTemplate<String, Object> redisTemplate;

    @Override
    public int save(String token) {
        if (!StringUtils.isEmpty(token)) {
            TbUser user = (TbUser) redisTemplate.opsForValue().get(token);
            logger.info("Cache database: {}" + user.toString());
            user.setUpdated(new Date());
            redisTemplate.opsForValue().set(token, user);
        }
        return 0;
    }

    @Override
    public int delete(long id, HttpServletRequest request) {
        return 0;
    }

    @Override
    public int update(long id, int number, HttpServletRequest request) {
        return 0;
    }

    @Override
    public List<TbUser> list(HttpServletRequest request) {
        List<TbUser> list = new ArrayList<>();
        String key = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        redisTemplate.opsForValue().set(key, JSONUtils.toJSONString(list));
        return list;
    }
}
