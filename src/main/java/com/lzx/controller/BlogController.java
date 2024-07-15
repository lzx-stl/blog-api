package com.lzx.controller;

import com.lzx.domain.AjaxResult;
import com.lzx.domain.entity.BlogInfo;
import com.lzx.service.impl.BlogInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("blog")
public class BlogController {
    @Autowired
    private BlogInfoServiceImpl blogInfoService;

    @GetMapping("/info")
    public AjaxResult getInfo(){
        BlogInfo defaultBlogInfo = blogInfoService.getDefaultBlogInfo();
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("data", defaultBlogInfo);
        return ajaxResult;
    }
}
