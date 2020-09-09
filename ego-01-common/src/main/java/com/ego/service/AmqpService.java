package com.ego.service;

/**
 * @author liuweiwei
 * @since 2020-08-28
 */
public interface AmqpService {
    public Integer send(String message);

    public Integer receive(String message);
}
