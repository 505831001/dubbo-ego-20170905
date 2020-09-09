package com.ego.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ego.config.AmqpConfig;
import com.ego.service.AmqpService;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuweiwei
 * @since 2020-05-19
 */
@Service
@Component
public class AmqpServiceImpl implements AmqpService {
    /**
     * SLF4J 骚粉日志必备技能
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TbUserServiceImpl.class);

    @Autowired
    protected AmqpTemplate amqpTemplate;

    @Override
    public Integer send(String message) {
        LOGGER.info("业务层发送的消息： -> message: {}" + message);
        amqpTemplate.convertAndSend(AmqpConfig.ALIBABA_NEWS, message);
        amqpTemplate.convertAndSend(AmqpConfig.LIUWEIWEI_NEWS, message);
        amqpTemplate.convertAndSend(AmqpConfig.LIUWEIWEI_PEOPLES, message);
        return 1;
    }

    @Override
    public void sendMessage(String message) {
        String string = JSONObject.toJSONString(message);
        LOGGER.info("业务层发送的消息： -> string: {}", string);
        amqpTemplate.convertAndSend(AmqpConfig.ALIBABA_NEWS, string);
    }
}
