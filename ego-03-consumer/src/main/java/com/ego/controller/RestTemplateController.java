package com.ego.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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

    /**
     * 1. Response Headers - 响应头('响应行', '响应头', '响应体')
     * Connection: keep-alive
     * Content-Encoding: gzip
     * Content-Type: application/json; charset=utf-8
     * Date: Tue, 22 Sep 2020 07:32:52 GMT
     * Server: nginx/1.8.0
     * Strict-Transport-Security: max-age=31536000
     * Tracecode: 19721006560390114570092215
     * Transfer-Encoding: chunked
     * Vary: Accept-Encoding
     * X-Logic-No: null
     * <p>
     * 2. Request Headers - 请求报头('请求行', '请求头', '请求体')
     * Accept: *
     * Accept-Encoding: gzip, deflate, br
     * Accept-Language: zh,en-US;q=0.9,en;q=0.8,zh-CN;q=0.7
     * Connection: keep-alive
     * Cookie: HOSUPPORT_BFESS=1; UBI_BFESS=fi_PncwhpxZ%7ETaKAauiBOgxifpQBCMgzo3Yla1W3lQyP%7EVDSsLP3QkekuLuqNMioarT2rmfPoSMY349ns7L2dK9vLss1SZkmz91N7kZ9LccfsBscWpv6h6l7bDBrW2lXW8rWdPWg3u0xFV6cKz3p1kFOo0HhQ__; USERNAMETYPE_BFESS=2; SAVEUSERID_BFESS=0c60a0bcc8d665dc87910cc7ecd38672afe2840fa7; HISTORY_BFESS=fec841ba17c58f8ce724cd4504f65c5d4e990fa491234e53e8; BDUSS_BFESS=BmekJmTEZVSzFCeXQwc2VMTkQzZ05QSy1oWUlLfllWNGVraHFFaWVGU2pEbmhmSVFBQUFBJCQAAAAAAAAAAAEAAABpf-U-X1FRXzEyMjgxMjQ0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKOBUF-jgVBfcz; STOKEN_BFESS=cc2a7436b141e7736ac4b3b28cfbe7da7158ceacc263778a93d99895ab3f2c41; PTOKEN_BFESS=a21c19a430dafb79585db4f4ab00bd10
     * Host: passport.baidu.com
     * Referer: https://www.hao123.com/
     * Sec-Fetch-Dest: script
     * Sec-Fetch-Mode: no-cors
     * Sec-Fetch-Site: cross-site
     * User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36
     */

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/weather", consumes = MediaType.ALL_VALUE)
    @ApiOperation(value = "获取天气", notes = "获取心知网站天气信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "language", value = "语言(zh-Hans简体中文)", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "location", value = "地址", dataType = "String", paramType = "query")
    })
    public String weather(@ApiIgnore String language, @ApiIgnore String location) throws URISyntaxException {
        // 1. Builder for {@link URI} instances.
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("api.seniverse.com");
        uriBuilder.setPath("/v3/weather/now.json");
        uriBuilder.setParameter("key", "w99tf57ghc86thhv");
        uriBuilder.setParameter("language", language);
        uriBuilder.setParameter("location", location);
        // 2. Represents a Uniform Resource Identifier (URI) reference.
        URI uri = uriBuilder.build();
        // 3. Spring's central class for synchronous client-side HTTP access.
        /**
         * getForObject(URI url, Class<T> responseType)
         * getForEntity(URI url, Class<T> responseType)
         */
        Object get = restTemplate.getForObject(uri, Object.class);
        ResponseEntity<Object> entity = restTemplate.getForEntity(uri, Object.class);
        // 4. Alibaba FastJSON
        String json = JSONObject.toJSONString(get);
        return json;
    }

    @PostMapping(value = "/people")
    @ApiOperation(value = "查询人才", notes = "获取中国人才网信息")
    public String people() throws URISyntaxException {
        String url = "https://s.cjol.com/?SearchType=3&Minsalary=20000&Maxsalary=30000&defaultmust=0";
        HttpPost request = new HttpPost(url);
        /**
         * postForLocation(URI url, Object request)
         * postForObject(URI url, Object request, Class<T> responseType)
         * postForEntity(URI url, Object request, Class<T> responseType)
         */
        URI uri = restTemplate.postForLocation(url, request);
        String post = restTemplate.postForObject(url, request, String.class);
        ResponseEntity<String> entity = restTemplate.postForEntity(url, request, String.class);
        return post;
    }

    @PostMapping(value = "/school")
    @ApiOperation(value = "查询学校", notes = "获取教育局信息")
    public ResponseEntity<String> school() {
        String url = "https://s.cjol.com/?SearchType=3&Minsalary=20000&Maxsalary=30000&defaultmust=0";
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("shopid", "1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        URI uri = restTemplate.postForLocation(url, request);
        String post = restTemplate.postForObject(url, request, String.class);
        ResponseEntity<String> entity = restTemplate.postForEntity(url, request, String.class);
        return entity;
    }
}
