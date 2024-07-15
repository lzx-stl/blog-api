package com.lzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzx.domain.entity.Tag;
import com.lzx.domain.request.TagTableReq;
import com.lzx.mapper.TagMapper;
import com.lzx.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> selectAllTags() {
        return tagMapper.selectList(null);
    }

    @Override
    public Integer addTag(Tag tag) {
        return tagMapper.insert(tag);
    }

    @Override
    public Integer updateTag(Tag tag) {
        return tagMapper.updateById(tag);
    }

    @Override
    public Page<Tag> selectTagPage(TagTableReq tagTableReq) {
        Integer page = tagTableReq.getPage();
        Integer limit = tagTableReq.getLimit();
        String keyword = tagTableReq.getKeyword();
        Page<Tag> tagPage = new Page<>(page, limit);
        LambdaQueryWrapper<Tag> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(!keyword.equals(""))lambdaQueryWrapper.eq(Tag::getTagName, keyword);
        tagMapper.selectPage(tagPage, lambdaQueryWrapper);
        return tagPage;
    }

}

