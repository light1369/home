package com.example.demo.controller;


import com.example.demo.util.ManageFile;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



/**
 * @author Duan
 * @date 2020/5/15 - 10:18
 */
@RestController
@RequestMapping(value = "/file", produces = "application/json;charset=UTF-8")
public class ReceiveFileController {
    @Value("${resources.path}")
    private String FileRoot=null;

    //通过Spring的autowired注解获取spring默认配置的request
    @RequestMapping(value = "/filesUpload", method = {RequestMethod.POST})
    public Result filesUpload(@RequestParam("uploadFile") MultipartFile file) {

            if (file.isEmpty()) {
                return Result.createAddError().put("result", "文件不存在");
            }

            return Result.createSuccess().put("result",ManageFile.refundsFile(file,FileRoot));
    }
}