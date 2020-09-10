package com.ego.controller;

import com.ego.entity.WiselyMessage;
import com.ego.entity.WiselyResponse;
import com.ego.service.WebSocketService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * WebSocket 消息代理(Message Broker)控制器类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
@RestController
@RequestMapping(value = "/websocket")
@Api(tags = "WebSocketController", description = "消息代理(Message Broker)管理相关")
public class WebSocketController {
    /**
     * SLF4J 骚粉日志必备技能
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketController.class);

    @Reference
    protected WebSocketService webSocketService;

    public WiselyResponse send(WiselyMessage message) {
        List<String> list = Lists.newArrayList();
        list.add("d892bf12bf7d11e793b69c5c8e6f60fb");

        WiselyResponse wiselyResponse = new WiselyResponse();
        wiselyResponse.setResponseMessage("Message Broker");

        webSocketService.sendUser(list, wiselyResponse);

        return wiselyResponse;
    }
}
