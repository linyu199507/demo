package com.chinaweal.sdcs.crcs.statistical.common.util;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * @Author: NaJim
 * @Date: 2019/9/25
 */
public class CommonUpload {

    public static String uploadMethod(MultipartFile[] files, HttpServletRequest request) {
        if (files!=null){
            for (MultipartFile file:files){
                String filename = file.getOriginalFilename();
                //修改文件名为UUID以防重复
                String uuid = UUID.randomUUID().toString();
                String substring = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                filename = uuid+substring;

                //服务器真实位置,地址从common.properties里调整
                String urlpath = GetPropertiesValue.getByKey("common.properties", "crcs.upload.path");
                String realPath = request.getServletContext().getRealPath(urlpath);
                File path = new File(realPath);
                if (!path.exists()){
                    path.mkdirs();
                }
                File savefile = new File(path,filename);
                try {
                    file.transferTo(savefile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "success";
    }
}
