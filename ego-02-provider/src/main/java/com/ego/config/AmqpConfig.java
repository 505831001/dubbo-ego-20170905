package com.ego.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuweiwei
 * @since 2020-08-28
 */
@Configuration
public class AmqpConfig {
    /**
     * 1. 交换机名称+队列名称+路由键名称
     */
    // 交换机名称
    public static final String EXCHANGE_DIRECT = "exchange.direct";
    public static final String EXCHANGE_TOPIC = "exchange.topic";
    public static final String EXCHANGE_HEADER = "exchange.header";
    public static final String EXCHANGE_FANOUT = "exchange.fanout";
    // 队列名称
    public static final String ALIBABA_NEWS = "alibaba.news";
    public static final String LIUWEIWEI_NEWS = "liuweiwei.news";
    public static final String LIUWEIWEI_PEOPLES = "liuweiwei.peoples";
    // 路由键名称
    public static final String ROUTING_KEY_01 = "*.news";
    public static final String ROUTING_KEY_02 = "liuweiwei.#";

    /**
     * 2. 四种交换机类型定义。
     *
     * @return
     */
    // 1. Direct 直连交换机。默认。
    @Bean
    public DirectExchange directExchange() {
        DirectExchange directExchange = new DirectExchange(EXCHANGE_DIRECT, true, false);
        return directExchange;
    }
    // 2. Topic 主题交换机。
    @Bean
    public TopicExchange topicExchange() {
        TopicExchange topicExchange = new TopicExchange(EXCHANGE_TOPIC, true, false);
        return topicExchange;
    }
    // 3. Headers 参数类型交换机。
    @Bean
    public HeadersExchange headersExchange() {
        HeadersExchange headersExchange = new HeadersExchange(EXCHANGE_HEADER, true, false);
        return headersExchange;
    }
    // 4. Fanout 交换机模式(广播模式)，不用绑定路由键。
    @Bean
    public FanoutExchange fanoutExchange() {
        FanoutExchange fanoutExchange = new FanoutExchange(EXCHANGE_FANOUT, true, false);
        return fanoutExchange;
    }

    /**
     * 3. 各种队列指定。
     * @return
     */
    @Bean
    public Queue queue01() {
        Queue queue = new Queue(ALIBABA_NEWS, true);
        return queue;
    }

    @Bean
    public Queue queue02() {
        Queue queue = new Queue(LIUWEIWEI_NEWS, true);
        return queue;
    }

    @Bean
    public Queue queue03() {
        Queue queue = new Queue(LIUWEIWEI_PEOPLES, true);
        return queue;
    }

    /**
     * 4.1 后缀方式路由键绑定队列和交换机
     * @return
     */
    // "*.news" = "liuweiwei.news" + "alibaba.news"
    @Bean
    public Binding binding01() {
        Binding binding = BindingBuilder.bind(queue01()).to(topicExchange()).with(ROUTING_KEY_01);
        return binding;
    }
    @Bean
    public Binding binding02() {
        Binding binding = BindingBuilder.bind(queue02()).to(topicExchange()).with(ROUTING_KEY_01);
        return binding;
    }

    /**
     * 4.2 前缀方式路由键绑定队列和交换机
     * @return
     */
    // "liuweiwei.#" = "liuweiwei.news" + "liuweiwei.peoples"
    @Bean
    public Binding binding03() {
        Binding binding = BindingBuilder.bind(queue02()).to(topicExchange()).with(ROUTING_KEY_02);
        return binding;
    }
    @Bean
    public Binding binding04() {
        Binding binding = BindingBuilder.bind(queue03()).to(topicExchange()).with(ROUTING_KEY_02);
        return binding;
    }
}
