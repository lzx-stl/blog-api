package com.lzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzx.domain.entity.Article;
import com.lzx.domain.entity.ArticleItem;
import com.lzx.domain.entity.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ArticleMapper extends BaseMapper<Article> {
    IPage<ArticleItem> getArticleItemList(Page<?> page, @Param("tagId") Integer tagId);
}
