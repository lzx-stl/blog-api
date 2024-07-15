package com.lzx.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTableReq {
    private Integer page;
    private Integer limit;
    private String keyword;
}
