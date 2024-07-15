package com.lzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzx.domain.entity.ArticleTag;
import com.lzx.domain.entity.Tag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    @Select("select tag.* from tag join article_tag on tag.tag_id = article_tag.tag_id where article_tag.article_id = #{articleId}")
    public List<Tag> selectAllTagsByArticleId(Long articleId);
//    public Integer insertAllTags(Tag []tags);
    @Delete("delete from article_tag where article_id = #{articleId}")
    public void removeAllTagsFromArticle(Long articleId);

    @Select("select article_id from article_tag where tag_id = #{tag_id}")
    public List<Long> selectArticleIdsByTagId(Long tag_id);
}
