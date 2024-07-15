package com.lzx.domain.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TagTableReq {
    private Integer page;
    private Integer limit;
    private String keyword;
    private Long ctime;
}
