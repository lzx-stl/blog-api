package com.lzx.service.impl;

import com.baomidou.mybatisplus.core.batch.MybatisBatch;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzx.domain.entity.Article;
import com.lzx.domain.entity.ArticleTag;
import com.lzx.domain.entity.Tag;
import com.lzx.mapper.ArticleTagMapper;
import com.lzx.mapper.TagMapper;
import com.lzx.service.ArticleTagService;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ArticleTagServiceImpl implements ArticleTagService {
    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private TagMapper tagMapper;


    @Override
    public void addTagToArticle(Long articleId, Long tagId) {
        articleTagMapper.insert(new ArticleTag(articleId, tagId));
    }

    @Override
    public void removeTagFromArticle(Long articleId, Long tagId) {

    }

    @Override
    public List<Tag> selectTagsByArticleId(Long articleId) {
        return articleTagMapper.selectAllTagsByArticleId(articleId);
    }

    @Override
    public List<Long> selectArticleIdsByTagId(Long tagId) {
        return articleTagMapper.selectArticleIdsByTagId(tagId);
    }

    @Override
    public void removeAllTagsFromArticle(Long articleId) {
        articleTagMapper.removeAllTagsFromArticle(articleId);
    }

    @Transactional
    @Override
    public void addTagsToArticle(Long articleId, List<Long> tagIds) {
        ArrayList<ArticleTag> articleTags = new ArrayList<>();
        for (Long tagId : tagIds) {
            articleTags.add(new ArticleTag(articleId, tagId));
        }
        MybatisBatch<ArticleTag> mybatisBatch = new MybatisBatch<>(sqlSessionFactory, articleTags);
        MybatisBatch.Method<ArticleTag> method = new MybatisBatch.Method<>(ArticleTagMapper.class);
        mybatisBatch.execute(method.insert());
    }

    @Override
    public void removeTagsFromArticle(Long articleId, List<Long> tagIds) {
        ArrayList<ArticleTag> articleTags = new ArrayList<>();
        for (Long tagId : tagIds) {
            articleTags.add(new ArticleTag(articleId, tagId));
        }
        MybatisBatch<ArticleTag> mybatisBatch = new MybatisBatch<>(sqlSessionFactory, articleTags);
        MybatisBatch.Method<ArticleTag> method = new MybatisBatch.Method<>(ArticleTagMapper.class);
        mybatisBatch.execute(method.deleteById());
    }

    @Override
    public List<Article> getArticlesByTagId(Long tagId) {
        return null;
    }

    @Override
    public void updateArticleTags(Long articleId, List<Long> tagIds) {

    }
}
