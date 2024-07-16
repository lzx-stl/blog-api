package com.lzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzx.domain.entity.Article;

import com.lzx.domain.request.ArticlePageReq;
import com.lzx.mapper.ArticleMapper;
import com.lzx.service.ArticleService;
import com.lzx.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagServiceImpl tagService;
    @Autowired
    private ArticleTagService articleTagService;


    @Override
    public Page<Article> getList(ArticlePageReq articlePageReq) {
        Integer curr = articlePageReq.getPage();
        Integer limit = articlePageReq.getLimit();
        String keyword = articlePageReq.getKeyword();
        Integer state = articlePageReq.getState();
        Long tagId = articlePageReq.getTagId();
        Page<Article> page = new Page<>(curr, limit);
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        过滤 article_content 字段
        lambdaQueryWrapper.select(Article.class, i -> !i.getProperty().equals("articleContent"));

        if(articlePageReq.getState() != null)
            lambdaQueryWrapper.eq(Article::getState, state);
        if(!keyword.equals("")) lambdaQueryWrapper.like(Article::getTitle, keyword);
        if (tagId != null){

            List<Long> articleIds = articleTagService.selectArticleIdsByTagId(tagId);
           if(articleIds.isEmpty())     {
               page.setRecords(new ArrayList<Article>());
               page.setTotal(0);
               return page;
           }
            lambdaQueryWrapper.in(Article::getArticleId,articleIds);
        }
        lambdaQueryWrapper.orderByDesc(Article::getCtime);
        articleMapper.selectPage(page, lambdaQueryWrapper);
        return page;
    }

    @Override
    public Article getPrevArticle(Long articleId) {


        return null;
    }

    @Override
    public Article getNextArticle(Long articleId) {
        return null;
    }


}
