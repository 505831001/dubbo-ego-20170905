package com.ego.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * <p>
 * Spring RestTemplate 跨域请求控制器类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
@RestController
@RequestMapping(value = "/rest")
@Api(tags = "RestTemplateController", description = "Spring RestTemplate跨域请求相关接口")
public class RestTemplateController {
    /**
     * SLF4J 骚粉日志必备技能
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateController.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/weather", consumes = MediaType.ALL_VALUE)
    @ApiOperation(value = "查询", notes = "获取心知网站天气信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "language", value = "语言(zh-Hans：简体中文/zh-Hant：繁体中文)", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "location", value = "地址", dataType = "String", paramType = "query")
    })
    public Object weather(@ApiIgnore String language, @ApiIgnore String location) throws URISyntaxException {
        // 1. {@link URI} 实例的构建器。
        URI uri = new URIBuilder()
                .setScheme("https").setHost("api.seniverse.com").setPath("/v3/weather/now.json")
                .setParameter("key", "w99tf57ghc86thhv")
                .setParameter("language", language)
                .setParameter("location", location)
                .build();
        return restTemplate.getForObject(uri, Object.class);
    }
}
