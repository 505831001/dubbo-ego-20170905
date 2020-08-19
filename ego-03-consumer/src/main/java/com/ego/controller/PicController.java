package com.ego.controller;

import com.ego.service.PicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @author liuweiwei
 * @since 2020-8-15
 */
@Controller
@Api(tags = "PicController", description = "文件相关接口")
public class PicController {

    @Reference
    private PicService picService;

    /**
     * 文件上传功能
     *
     * @param uploadFile
     * @return
     * @throws IOException
     */
    // http://localhost:8848/pic/upload?dir=image
    @GetMapping(value = "pic/upload")
    @ResponseBody
    @ApiOperation(value = "文件上传处理列表", notes = "上传")
    @ApiImplicitParam(name = "uploadFile", value = "多部分文件", required = false, dataType = "MultipartFile", paramType = "body")
    @ApiResponse(code = 200, message = "", response = Map.class)
    public Map<String, Object> upload(@RequestBody MultipartFile uploadFile) throws IOException {
        Map<String, Object> upload = picService.upload(uploadFile);
        return upload;
    }
}
