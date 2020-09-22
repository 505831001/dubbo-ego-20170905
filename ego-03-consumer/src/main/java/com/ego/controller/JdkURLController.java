package com.ego.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

/**
 * <p>
 * JDK URLConnection 跨域请求控制器类
 * </p>
 *
 * @author liuweiwei
 * @since 2020-05-19
 */
@RestController
@RequestMapping(value = "/jdk")
@Api(tags = "JdkURLController", description = "JdkURL跨域请求相关接口")
@Slf4j
public class JdkURLController {
    /**
     * SLF4J 骚粉日志必备技能
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JdkURLController.class);

    /**
     * 1. General
     * Request URL:     https://passport.baidu.com/v2/api/qrcode?sign=83244&lp=pc&qrloginfrom=pc
     * Request Method:  GET
     * Status Code:     200 OK
     * Remote Address:  180.97.36.39:443
     * Referrer Policy: unsafe-url
     *
     * 2. Response Headers
     * Access-Control-Expose-Headers: Trace-ID
     * Connection: keep-alive
     * Content-Type: image/png
     * Date: Tue, 22 Sep 2020 02:58:56 GMT
     * P3p: CP=" OTI DSP COR IVA OUR IND COM "
     * Referrer-Policy: no-referrer-when-downgrade, strict-origin-when-cross-origin
     * Server: Apache
     * Set-Cookie: BAIDUID=D1275FE66F176F2A7D5D1E1DC8A96560:FG=1; expires=Wed, 22-Sep-21 02:58:56 GMT; max-age=31536000; path=/; domain=.baidu.com; version=1
     * Strict-Transport-Security: max-age=31536000
     * Trace-Id
     * Tracecode: 35359985030725467914092210
     * Transfer-Encoding: chunked
     *
     * Connection: keep-alive
     * Content-Length: 594
     * Content-Type: application/json; charset=utf-8
     * Date: Tue, 22 Sep 2020 02:58:55 GMT
     * Server: nginx/1.8.0
     * Set-Cookie: pplogid=3978AMxzzXJ9AQ8Xw7N%2FwFU5V7BbCeYUZUjF%2BkNkPgDIyQhSepsJTIlwM3iLqa7IW8gH; expires=Fri, 25-Sep-2020 02:58:55 GMT; path=/; httponly
     * Strict-Transport-Security: max-age=31536000
     * Tracecode: 35359582472755374346092210
     * X-Logic-No: null
     *
     * 3. Request Headers
     * Accept: *
     * Accept-Encoding: gzip, deflate, br
     * Accept-Language: zh,en-US;q=0.9,en;q=0.8,zh-CN;q=0.7
     * Connection: keep-alive
     * Cookie: HOSUPPORT_BFESS=1; UBI_BFESS=fi_PncwhpxZ%7ETaKAauiBOgxifpQBCMgzo3Yla1W3lQyP%7EVDSsLP3QkekuLuqNMioarT2rmfPoSMY349ns7L2dK9vLss1SZkmz91N7kZ9LccfsBscWpv6h6l7bDBrW2lXW8rWdPWg3u0xFV6cKz3p1kFOo0HhQ__; USERNAMETYPE_BFESS=2; SAVEUSERID_BFESS=0c60a0bcc8d665dc87910cc7ecd38672afe2840fa7; HISTORY_BFESS=fec841ba17c58f8ce724cd4504f65c5d4e990fa491234e53e8; BDUSS_BFESS=BmekJmTEZVSzFCeXQwc2VMTkQzZ05QSy1oWUlLfllWNGVraHFFaWVGU2pEbmhmSVFBQUFBJCQAAAAAAAAAAAEAAABpf-U-X1FRXzEyMjgxMjQ0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKOBUF-jgVBfcz; STOKEN_BFESS=cc2a7436b141e7736ac4b3b28cfbe7da7158ceacc263778a93d99895ab3f2c41; PTOKEN_BFESS=a21c19a430dafb79585db4f4ab00bd10
     * Host: passport.baidu.com
     * Referer: https://www.hao123.com/
     * Sec-Fetch-Dest: image
     * Sec-Fetch-Mode: no-cors
     * Sec-Fetch-Site: cross-site
     * User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36
     *
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
     *
     * 4. Query String Parameters
     *
     */

    /**
     * URLConnection 跨域请求获取获取统一资源定位器 - TODO a Uniform Resource Locator
     *
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    @GetMapping(value = "/url")
    @ApiOperation(value = "获取URL", notes = "获取统一资源定位符或者叫统一资源定位器")
    public String url() throws IOException {
        String spec = "https://s.cjol.com/?SearchType=3&Minsalary=20000&Maxsalary=30000&defaultmust=0";
        URL url = new URL(spec);
        URLConnection connect = url.openConnection();
        connect.setRequestProperty("token", "设置请求头。带Token滴那什么");
        LOGGER.info("Response content type: "   + connect.getContentType());
        LOGGER.info("Response content encode: " + connect.getContentEncoding());
        InputStream is = connect.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String message = "";
        StringBuilder stringBuilder = new StringBuilder();
        while ((message = reader.readLine()) != null) {
            stringBuilder.append(message);
        }
        reader.close();
        is.close();
        return stringBuilder.toString();
    }
}
