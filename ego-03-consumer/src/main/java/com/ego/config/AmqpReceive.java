package com.ego.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author liuweiwei
 * @since 2020-08-28
 */
@Component
@RabbitListener(queues = "alibaba.news")
public class AmqpReceive {
    /**
     * SLF4J 骚粉日志必备技能
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AmqpReceive.class);

    @RabbitHandler
    public void process(String message) {
        LOGGER.info("监听器消费的消息： -> message: " + message);
    }
}
