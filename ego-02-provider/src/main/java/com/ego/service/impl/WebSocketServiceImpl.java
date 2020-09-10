package com.ego.service.impl;

import com.ego.entity.WiselyResponse;
import com.ego.service.WebSocketService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * WebSocket 服务实现类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
@Service
@Component
public class WebSocketServiceImpl implements WebSocketService {

    @Autowired(required = false)
    protected SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void sendMessage(WiselyResponse message) {
        simpMessagingTemplate.convertAndSend("/app", message);
    }

    @Override
    public void sendUser(List<String> list, WiselyResponse message) {
        for (int i = 0; i < list.size(); i++) {
            simpMessagingTemplate.convertAndSendToUser(list.get(i), "/app", message);
        }
    }
}
