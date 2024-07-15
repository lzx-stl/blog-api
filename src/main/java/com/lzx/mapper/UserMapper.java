package com.lzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzx.domain.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where username = #{username} and password = #{password}")
    public User findUser(String username, String password);
    @Select("select user_id from user where username = #{username} and password = #{password}")
    public Long findUserId(String username, String password);

}
