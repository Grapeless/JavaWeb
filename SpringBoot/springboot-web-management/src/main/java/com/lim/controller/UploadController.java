package com.lim.controller;

import com.lim.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @RequestMapping("/upload")
    public Result upload(String username, String password, MultipartFile image) throws Exception {
        log.info("username:{},password:{},image:{}", username, password, image);

        //getOriginalFilename()
        String originFileName = image.getOriginalFilename();
        //UUID.randomUUID()
        String newFileName = UUID.randomUUID() + originFileName.substring(originFileName.lastIndexOf("."));
        //transferTo
        image.transferTo(new File("D:\\Project\\Java\\JavaWeb\\SpringBoot\\springboot-web-management\\" + newFileName));
        log.info("newFileName:{}", newFileName);
        return Result.success();
    }
}
