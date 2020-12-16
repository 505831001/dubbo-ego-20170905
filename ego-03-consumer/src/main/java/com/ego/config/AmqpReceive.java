package com.ego.config;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liuweiwei
 * @since 2020-08-28
 */
@Component
@RabbitListener(queues = "alibaba.news")
public class AmqpReceive {
    /**
     * Log4j 罗锅否街日志技能（非：SLF4J 骚粉日志必备技能）
     */
    private final Logger logger = Logger.getLogger(this.getClass());

    @RabbitHandler
    public void process(String message) {
        logger.info("监听器消费的消息： -> message: " + message);
    }
}
