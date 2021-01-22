package com.colourfur.ossservice.controller;

import com.colourfur.commonutils.Result;
import com.colourfur.ossservice.service.FileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/service-oss/file")
public class FileUploadController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "文件上传云存储服务")
    @PostMapping("upload")
    public Result upload(MultipartFile file){
        String uploadUrl = fileService.upload(file);
        return Result.ok().message("文件上传成功！").data("url", uploadUrl);
    }
}
