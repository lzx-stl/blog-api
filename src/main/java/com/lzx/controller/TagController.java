package com.lzx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzx.domain.AjaxResult;
import com.lzx.domain.entity.Tag;
import com.lzx.domain.request.TagTableReq;
import com.lzx.mapper.TagMapper;
import com.lzx.service.TagService;
import com.lzx.domain.page.TableDataInfo;
import com.lzx.service.impl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tag")
public class TagController {

    @Autowired
    private TagMapper tagMapper;


    @Autowired
    private TagServiceImpl tagService;

    @GetMapping("all")
    public AjaxResult all() {
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("data", tagService.selectAllTags());
        return ajaxResult;
    }


    @PostMapping("list")
    public AjaxResult list(@RequestBody TagTableReq tagTableReq)
    {
        AjaxResult ajaxResult;
        Page<Tag> tagPage = tagService.selectTagPage(tagTableReq);
        ajaxResult = AjaxResult.success("返回tag数据成功");
        ajaxResult.put("data",new TableDataInfo(tagPage.getTotal(),tagPage.getRecords()));
        return  ajaxResult;

    }


    @GetMapping("get")
    public AjaxResult get(@RequestParam("tag_id") Integer tag_id)
    {
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("data", tagMapper.selectById(tag_id));
        return ajaxResult;
    }

    @PostMapping("addTag")
    public AjaxResult addTag(@RequestBody Tag tag) {
        AjaxResult ajaxResult ;
        if (tagService.addTag(tag) == 1) {
             ajaxResult = AjaxResult.success("新建标签成功");
            ajaxResult.put("tag", tag);
        } else {
            ajaxResult = AjaxResult.error("新建标签失败");
        }
        return ajaxResult;
    }



    @PostMapping("updateTag")
    public AjaxResult updateTag(@RequestBody Tag tag) {
        AjaxResult ajaxResult ;
        if (tagService.updateTag(tag) == 1) {
            ajaxResult = AjaxResult.success("新建标签成功");
            ajaxResult.put("tag", tag);
        } else {
            ajaxResult = AjaxResult.error("新建标签失败");
        }
        return ajaxResult;
    }



}
