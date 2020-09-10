package com.ego.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 一个方便的WebSocket消息代理配置实现的抽象基类，为可选方法提供空方法实现。
 *
 * @author liuweiwei
 * @since 2020-08-28
 */
@Configuration
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    /**
     * SLF4J 骚粉日志必备技能
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketConfig.class);

    /**
     * 注册一个STOMP端点
     *
     * @param stompEndpointRegistry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        /**
         * 在给定的映射路径上注册一个STOMP在WebSocket节点上。
         */
        stompEndpointRegistry.addEndpoint("/app").withSockJS();
    }

    /**
     * 配置消息代理
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /**
         * 启用一个简单的消息代理，并配置一个或多个前缀来筛选针对代理的目的地(例如：前缀为"/topic"的目的地)。
         */
        registry.enableSimpleBroker("/topic", "/user");
        /**
         * 配置用于标识用户目的地的前缀。
         * 用户目的地为用户提供订阅其会话唯一的队列名称的能力，也为其他人提供将消息发送到这些惟一的、特定于用户的队列的能力。
         */
        registry.setUserDestinationPrefix("/user");
        /**
         * 配置一个或多个前缀，以筛选目标为应用程序注释方法的目的地。
         * 例如：前缀为"/app"的目的地可能由带注释的方法处理，而其他目的地可能以消息代理为目标("/topic", "/queue")。
         */
        registry.setApplicationDestinationPrefixes("/app");
    }
}
