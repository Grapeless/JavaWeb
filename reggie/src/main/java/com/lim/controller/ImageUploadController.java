package com.lim.controller;

import com.lim.pojo.Result;
import com.lim.utils.AliOSSProperties;
import com.lim.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/common")
public class ImageUploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;
    @Autowired
    private AliOSSProperties aliOSSProperties;

    @PostMapping("/upload")
    public Result imageUpload(MultipartFile file) throws Exception{
        String url = aliOSSUtils.upload(file);
        log.info("图片上传后得到的文件名:{}",url);

        return Result.success(url);
    }

}
