package com.example.demo.util;

import com.example.demo.controller.ReceiveFileController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @author Duan
 * @date 2020/5/19 - 12:10
 */
public class ManageFile {
    private static Logger log = LoggerFactory.getLogger(ManageFile.class);


    public static Result refundsFile(MultipartFile file,String FileRoot){

        String ex = null;
        String filePath = null;
        String appid = "test";
        if (appid == null) {
            return Result.createAddError().put("result", "文件上传失败");
        }

        try {
            //上传目录地址
            String uploadDir = FileRoot + appid;

            log.debug("文件路径：{}", uploadDir);
            //如果目录不存在，自动创建文件夹
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            //原文件名
            String fileUpload = file.getOriginalFilename();
            log.debug("文件名：{}", fileUpload);

            //取后缀
            if (fileUpload.lastIndexOf(".") > -1) {
                ex = fileUpload.substring(fileUpload.lastIndexOf("."));
            }
            String fileName = UUID.randomUUID().toString() + ex;
            filePath = appid + "/" + fileName;

            //保存操作
            file.transferTo(new File(uploadDir + "/" + fileName));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.createSuccess().put("filePath", filePath);


    }

}
