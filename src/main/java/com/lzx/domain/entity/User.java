package com.lzx.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(value = "user_id",  type = IdType.AUTO)
    private Long userId;
    private String username;
    private String nickname;

    private String password;
    private String avatar;
    private String email;
}
