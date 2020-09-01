package com.ego.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liuweiwei
 * @since 2020-08-14
 */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {
    /**
     * 状态码
     */
    private int code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 数据集
     */
    private T data;

    public Result(T data) {
        super();
        this.data = data;
    }

    public Result(Throwable e) {
        super();
        this.message = e.toString();
        this.code = 0;
    }
}
