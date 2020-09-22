package com.ego.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import sun.nio.cs.ext.GBK;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

/**
 * <p>
 * HttpClient 跨域请求控制器类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
@RestController
@RequestMapping(value = "/http")
@Api(tags = "HttpClientController", description = "Http跨域请求相关接口")
public class HttpClientController {
    /**
     * SLF4J 骚粉日志必备技能
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientController.class);

    /**
     * HttpClient 跨域请求获取心知网站天气信息
     *
     * @param language
     * @param location
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    @GetMapping(value = "/weather")
    @ApiOperation(value = "查询天气", notes = "获取心知网站天气信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "language", value = "语言(zh-Hans：简体中文/zh-Hant：繁体中文)", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "location", value = "地址", dataType = "String", paramType = "query")
    })
    public ResponseEntity<String> weather(@ApiIgnore String language, @ApiIgnore String location) throws URISyntaxException, IOException {
        // 1. {@link URI} 实例的构建器。
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("api.seniverse.com");
        uriBuilder.setPath("/v3/weather/now.json");
        uriBuilder.setParameter("key", "w99tf57ghc86thhv");
        uriBuilder.setParameter("language", language);
        uriBuilder.setParameter("location", location);
        // 2 表示统一资源标识符(URI)引用。
        URI uri = uriBuilder.build();
        // 3. 第9.3节定义了HTTP GET方法。
        HttpGet request = new HttpGet(uri);
        // 4. {@link CloseableHttpClient} 生成器用于实例。
        CloseableHttpClient client = HttpClientBuilder.create().build();
        // 5. 执行请求调用httpClient.execute()方法，传入httpGet对象，返回CloseableHttpResponse。
        CloseableHttpResponse response = client.execute(request, HttpClientContext.create());
        HttpEntity entity = response.getEntity();
        Header[]  headers = response.getAllHeaders();
        int    statusCode = response.getStatusLine().getStatusCode();
        // 6. 可以通过HTTP消息发送或接收的实体。
        EntityUtils.consume(entity);
        String  toString = EntityUtils.toString(entity);
        HttpHeaders list = new HttpHeaders();
        for (Header header : headers) {
            list.add(header.getName(), header.getValue());
        }
        ResponseEntity<String> result = new ResponseEntity<>(toString, list, HttpStatus.valueOf(statusCode));
        LOGGER.info("添加状态代码{HttpStatus}的{HttpEntity}扩展名：" + request.toString());
        return result;
    }

    /**
     * HttpClient 跨域请求获取心知网站天气信息 - TODO 字符编码未识别
     *
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    @GetMapping(value = "/people")
    @ApiOperation(value = "获取人才", notes = "获取中国人才网信息")
    public ResponseEntity<String> people() throws URISyntaxException, IOException {
        /**
         * 1.1 Builder for {@link URI} instances.
         * 1.2 Represents a Uniform Resource Identifier (URI) reference.
         */
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("www.cjol.com");
        uriBuilder.setCharset(/*Charset.forName("utf-8")*/ /*new GBK()*/Consts.UTF_8);

        /**
         * 2.1 HTTP GET method. The HTTP GET method is defined in section 9.3 of<a>RFC2616</a>:
         * 2.2 模拟浏览器
         */
        HttpGet request = new HttpGet(uriBuilder.build());
        request.setHeader("Host","www.cjol.com");
        request.setHeader("Referer","https://s.cjol.com/?SearchType=3&Minsalary=20000&Maxsalary=30000&defaultmust=0");
        request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36");
        request.setHeader("Accept-Language", "zh,en-US;q=0.9,en;q=0.8,zh-CN;q=0.7");

        /**
         * 3.1 Creates {@link CloseableHttpClient} instance with default configuration.
         * 3.2 Base implementation of {@link HttpClient} that also implements {@link Closeable}.
         * 3.3 Extension of {@link HttpEntity} that adds a {@link HttpStatus} status code.
         * 3.4 Used in {@code RestTemplate} as well {@code @Controller} methods.
         */
        CloseableHttpResponse response = HttpClients.createDefault().execute(request);
        response.setHeader("Content-Type", "text/html; charset=utf-8");
        HttpEntity entity = response.getEntity();
        Header[]  headers = response.getAllHeaders();
        int    statusCode = response.getStatusLine().getStatusCode();

        /**
         * 4. Create a new {@code HttpEntity} with the given body, headers, and status code.
         * @param body the entity body       - EntityUtils.toString(response.getEntity())
         * @param headers the entity headers - (HttpHeaders) response.getAllHeaders()
         * @param status the status code     - HttpStatus.valueOf(response.getStatusLine().getStatusCode())
         */
        String string = EntityUtils.toString(entity, "UTF-8");
        String s = new String(EntityUtils.toString(entity).getBytes("ISO_8859_1"), "UTF-8");
        HttpHeaders httpHeaders = new HttpHeaders();
        for (Header header : headers) {
            httpHeaders.add(header.getName(), header.getValue());
        }
        ResponseEntity<String> result = new ResponseEntity<>(string, httpHeaders, HttpStatus.valueOf(statusCode));
        LOGGER.info("Create a new HttpEntity with the -> body:{}, headers:{}, statusCode:{}", string);
        return result;
    }
}
