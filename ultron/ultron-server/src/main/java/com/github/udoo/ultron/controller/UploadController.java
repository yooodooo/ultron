package com.github.udoo.ultron.controller;

import com.github.udoo.ultron.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author dong.yang
 * @data 2019/7/18 16:21
 */
@Controller
@Slf4j
public class UploadController {

    @PostMapping("/upload.htm")
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("desc") String desc) {
        if (multipartFile.isEmpty()) {
            return Result.fail("file is empty");
        }
        try {
            System.out.println(IOUtils.toString(multipartFile.getInputStream()));
        } catch (Exception e) {
            log.error("upload error: " + e.getMessage(), e);
        }
        return Result.success();
    }
}
