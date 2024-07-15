package com.lzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzx.domain.entity.Tag;
import com.lzx.domain.request.TagTableReq;

import java.util.List;

public interface TagService {

    public List<Tag> selectAllTags();
    public Integer addTag(Tag tag);

    public Integer updateTag(Tag tag);

    public Page<Tag> selectTagPage(TagTableReq tagTableReq);


}
