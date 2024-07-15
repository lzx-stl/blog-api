package com.lzx.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogInfo {
    private String homeImg;
    private String notHomeImg;
    private String siteName;
    private String siteSubtitle;
    private String authorName;
    private String authorAvatar;
    private String authorDescription;
    private String github;
    private Integer id;
}