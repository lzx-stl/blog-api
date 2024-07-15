package com.lzx.controller;


import com.lzx.domain.AjaxResult;
import com.lzx.utils.FileUploadUtils;
import com.lzx.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("upload")
public class UploadController {
    private static final String ARTICLE_IMAGE_PATH = "/article/";
    private static final String AVATAR_IMAGE_PATH = "/user/";
    private static final String EMOJI_IMAGE_PATH = "/emoji/";


    @Value(value = "${server.upload.defaultBaseDir}")
    private String RootDirPath;


    //文章图片上传
    @PostMapping("images/article")
    public AjaxResult uploadArticleImage(@RequestParam("image") MultipartFile file, HttpServletRequest request)
    {
        AjaxResult ajaxResult = AjaxResult.success();
        try {
            String url = FileUploadUtils.uploadImage(file, RootDirPath, ARTICLE_IMAGE_PATH, request);
            ajaxResult.put("url", url);
        }catch (Exception e)
        {
            ajaxResult.put("msg","Upload Error");
        }
        return ajaxResult;
    }


    @PostMapping("images/user/avatar")
    public AjaxResult uploadUserAvatar(@RequestParam("image") MultipartFile file, HttpServletRequest request)
    {
        AjaxResult ajaxResult = AjaxResult.success();
        try {
            String url = FileUploadUtils.uploadImage(file,RootDirPath, AVATAR_IMAGE_PATH, request);
            Result result = new Result();
            result.put("url", url);
            ajaxResult.put("data", result);
        }catch (Exception e)
        {
            ajaxResult.put("msg","Upload Error");
        }
        return ajaxResult;
    }


    @PostMapping("images/emoji")
    public AjaxResult uploadEmojiImage(@RequestParam("image") MultipartFile file, HttpServletRequest request)
    {
        AjaxResult ajaxResult = AjaxResult.success();
        try {
            Result data = new Result();
            String fileName = file.getOriginalFilename();
            fileName =  fileName.substring(0, fileName.lastIndexOf("."));
            data.put("name", fileName);
            String url = FileUploadUtils.uploadImage(file, RootDirPath,EMOJI_IMAGE_PATH, request);
            data.put("url", url);
            ajaxResult.put("data", data);
        }catch (Exception e)
        {
            ajaxResult.put("msg","Upload Error");
        }
        return ajaxResult;
    }

}
