package com.lzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzx.domain.entity.Article;
import com.lzx.domain.request.ArticlePageReq;

public interface ArticleService {
    public Page getList(ArticlePageReq articleItemParam);

    public Article getPrevArticle(Long articleId);
    public Article getNextArticle(Long articleId);

}
