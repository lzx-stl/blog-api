package com.lzx.domain.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ArticlePageReq {
    private Integer page;
    private Integer limit;
    private String keyword = "";
    private Long ctime;
    private Integer state;
    private Long tagId;

}
