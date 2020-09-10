package com.ego.service;

import com.ego.entity.WiselyResponse;

import java.util.List;

/**
 * @author liuweiwei
 * @since 2020-08-28
 */
public interface WebSocketService {
    public void sendMessage(WiselyResponse message);

    public void sendUser(List<String> string, WiselyResponse message);
}
