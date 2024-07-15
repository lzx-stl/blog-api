package com.lzx.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FileUploadUtils {
    /**
     * 时间格式化
     */
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");
    //文件存放根目录

    public static String uploadImage(MultipartFile file, String rootDirPath, String categoryDirPath, HttpServletRequest request)
    {
        String subDirPath = simpleDateFormat.format(new Date());
        /**
         *  2.
         *  如果目录不存在，则创建
         *  在目录下创建文件
         *  将传入的文件保存在改目录下
         */
        String fullDirPath = rootDirPath + categoryDirPath + subDirPath;
        File dir = new File(fullDirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //3.给文件重新设置一个名字
        //后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String newFileName= UUID.randomUUID().toString().replaceAll("-", "")+suffix;
        //4.创建这个新文件
        String filePath = fullDirPath + newFileName;
        File newFile = new File(filePath);
        //5.复制操作
        String url = null;
        try {
            file.transferTo(newFile);
            //协议 :// ip地址 ：端口号 / 文件目录(/images/2020/03/15/xxx.jpg)
            url = "/images" + categoryDirPath + subDirPath + newFileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }


}
