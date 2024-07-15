package com.lzx.service.impl;

import com.lzx.domain.entity.BlogInfo;
import com.lzx.mapper.BlogInfoMapper;
import com.lzx.service.BlogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogInfoServiceImpl implements BlogInfoService {
    @Autowired
    private BlogInfoMapper blogInfoMapper;
    @Override
    public BlogInfo getDefaultBlogInfo() {
        return blogInfoMapper.selectById(1);
    }
}
