package com.lzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzx.domain.entity.User;
import com.lzx.domain.request.UserReq;
import com.lzx.domain.request.UserTableReq;

import java.util.List;

public interface UserService {
    public Boolean checkUser(UserReq userReq);
    public User findUserByUserId(Long userId);
    public Long findUserId(String username, String password);
    public Integer addUser(User user);
    public Integer updateUser(User user);
    public Integer deleteUser(User user);
    public void deleteUserList(List<User> userList);

    public Page<User> getList(UserTableReq userTableReq);
}
