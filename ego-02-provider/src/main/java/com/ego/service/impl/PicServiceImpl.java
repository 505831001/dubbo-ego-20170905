package com.ego.service.impl;

import com.ego.service.PicService;
import com.ego.utils.FtpUtil;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author liuweiwei
 * @since 2020-05-19
 */
@Service
@Component
public class PicServiceImpl implements PicService {
    /**
     * SLF4J 骚粉日志必备技能
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PicServiceImpl.class);

    @Value("${ftpclient.host}")
    private String host;
    @Value("${ftpclient.port}")
    private String port;
    @Value("${ftpclient.username}")
    private String username;
    @Value("${ftpclient.password}")
    private String password;
    @Value("${ftpclient.basePath}")
    private String basePath;
    @Value("${ftpclient.filePath}")
    private String filePath;

    /**
     * 文件上传功能
     *
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public Map<String, Object> upload(MultipartFile file) throws IOException {
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        int port = Integer.parseInt(this.port);

        String prefix = UUID.randomUUID().toString().replace("-", "");
        String suffix = file.getOriginalFilename();
        String filename = prefix + suffix.substring(suffix.lastIndexOf("."));
        InputStream stream = file.getInputStream();
        flag = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, filename, stream);
        if (flag == true) {
            map.put("error", 0);
            map.put("message", "File uploaded successfully");
            // nginx 一定是通过 http 协议。
            map.put("url", "ftp://" + host + ":" + port + filePath + filename);
        } else {
            map.put("error", 1);
            map.put("message", "File upload failed");
        }
        return map;
    }

}
