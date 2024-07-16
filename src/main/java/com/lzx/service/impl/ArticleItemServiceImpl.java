package com.lzx.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzx.domain.entity.Article;
import com.lzx.domain.entity.ArticleItem;
import com.lzx.domain.entity.Tag;
import com.lzx.domain.page.TableDataInfo;
import com.lzx.domain.request.ArticlePageReq;
import com.lzx.mapper.ArticleMapper;
import com.lzx.service.ArticleItemService;
import com.lzx.service.ArticleService;
import com.lzx.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleItemServiceImpl implements ArticleItemService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private ArticleServiceImpl articleService;

    @Override
    public TableDataInfo getList(ArticlePageReq articlePageReq) {
        Page<Article> page = articleService.getList(articlePageReq);
        List<Article> articleList = page.getRecords();
        List<ArticleItem> list = new ArrayList<>();
        for (Article article : articleList) {
            list.add(new ArticleItem(article.getArticleId(),
                    article,
                    articleTagService.selectTagsByArticleId(article.getArticleId())));
        }
        return new TableDataInfo(page.getTotal(), list);
    }

    @Override
    public ArticleItem getArticleItemById(Long articleId) {
        return new ArticleItem(articleId,
                articleMapper.selectById(articleId),
                articleTagService.selectTagsByArticleId(articleId));
    }


    @Transactional
    @Override
    public void insertArticleItem(ArticleItem articleItem) {
        //insert article
        Article article = articleItem.getArticleInfo();
        articleMapper.insert(article);
        Long articleId = article.getArticleId();
        //insert article's tags
        List<Long> tags = articleItem.getTags().stream().map(Tag::getTagId).collect(Collectors.toList());
        articleTagService.addTagsToArticle(articleId, tags);
    }

    @Transactional
    @Override
    public void updateArticleItem(ArticleItem articleItem) {
        Article article = articleItem.getArticleInfo();
        articleMapper.updateById(article);
        Long articleId = article.getArticleId();
        List<Long> tags = articleItem.getTags().stream().map(Tag::getTagId).collect(Collectors.toList());
        articleTagService.removeAllTagsFromArticle(articleId);
        articleTagService.addTagsToArticle(articleId, tags);
    }
}
