package com.lzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzx.domain.entity.ArticleItem;
import com.lzx.domain.page.TableDataInfo;
import com.lzx.domain.request.ArticlePageReq;

public interface ArticleItemService {
    public TableDataInfo getList(ArticlePageReq articlePageReq);
    public ArticleItem getArticleItemById(Long articleId);
    public void insertArticleItem(ArticleItem articleItem);
    public void updateArticleItem(ArticleItem articleItem);
}
