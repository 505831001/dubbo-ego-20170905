package com.ego.controller;

import com.ego.service.AmqpService;
import io.swagger.annotations.*;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * AMQP 消息队列控制器类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
@RestController
@RequestMapping(value = "/amqp")
@Api(tags = "AmqpController", description = "消息队列接口")
public class AmqpController {
    /**
     * SLF4J 骚粉日志必备技能
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AmqpController.class);

    @Reference
    protected AmqpService amqpService;

    @GetMapping(value = "/send")
    @ApiOperation(value = "发送", notes = "发送到ALIBABA_NEWS队列吧")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "消息内容", dataType = "String", paramType = "form")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "AMQP", response = String.class)
    })
    public String sendMessage(String message) {
        LOGGER.info("");
        amqpService.sendMessage(message);
        return "Okay";
    }
}
