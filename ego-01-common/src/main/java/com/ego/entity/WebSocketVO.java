package com.ego.entity;

/**
 * WebSocket 相关配置
 *
 * @author liuweiwei
 * @since 2020-08-14
 */
public class WebSocketVO {
    // 链接地址
    public static final String PATH_PREFIX = "/ws-push";
    public static final String PATH = "/endpointWisely";

    // 消息代理路径
    public static final String PATH_BROADCAST = "/topic";

    // 前端发送给服务端请求地址
    public static final String PATH_FORE_TO_SERVER = "/welcome";

    // 服务端生产地址,客户端订阅此地址以接收服务端生产的消息
    public static final String PATH_PRODUCER = "/topic/getResponse";

    // 点对点消息推送地址前缀
    public static final String PATH_P2P_PUSH_BASE = "/user";

    // 点对点消息推送地址后缀,最后的地址为/user/用户识别码/msg
    public static final String PATH_P2P_PUSH = "/msg";
}
