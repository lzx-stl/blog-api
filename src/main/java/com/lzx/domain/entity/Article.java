package com.lzx.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article {
    @TableId(value = "article_id", type = IdType.AUTO)
    private Long articleId;
    private String title;
    private String coverImage;
    private String brief;
    private String articleContent;
    private Long ctime;
    private Long mtime;
    private Boolean state; // 是否发布 -1 已删除 0草稿  1发布
}
