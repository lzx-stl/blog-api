package com.lzx.service;

import com.lzx.domain.entity.Article;
import com.lzx.domain.entity.Tag;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ArticleTagService {

    public void addTagToArticle(Long articleId, Long tagId);

    public void removeTagFromArticle(Long articleId, Long tagId);

   public List<Tag> selectTagsByArticleId(Long articleId);

   public List<Long> selectArticleIdsByTagId(Long tagId);
   public void removeAllTagsFromArticle(Long articleId);

    @Transactional
    public void addTagsToArticle(Long articleId, List<Long> tagIds);
    @Transactional
    public void removeTagsFromArticle(Long articleId, List<Long> tagIds);

    public List<Article> getArticlesByTagId(Long tagId);

    @Transactional
    public void updateArticleTags(Long articleId, List<Long> tagIds);


}

