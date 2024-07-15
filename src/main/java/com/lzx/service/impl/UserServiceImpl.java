package com.lzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzx.domain.entity.Article;
import com.lzx.domain.entity.User;
import com.lzx.domain.request.UserReq;
import com.lzx.domain.request.UserTableReq;
import com.lzx.mapper.UserMapper;
import com.lzx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean checkUser(UserReq userReq) {
        String passwordHash = DigestUtils.md5DigestAsHex(userReq.getPassword().getBytes());
        return findUserId(userReq.getUsername(), passwordHash) != null;
    }

    @Override
    public User findUserByUserId(Long userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public Long findUserId(String username, String password) {

        String passwordHash = DigestUtils.md5DigestAsHex(password.getBytes());
        return userMapper.findUserId(username, passwordHash);
    }

    @Override
    public Integer addUser(User user) {
        String passwordHash = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(passwordHash);
        return userMapper.insert(user);
    }

    @Override
    public Integer updateUser(User user) {
        String passwordHash = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(passwordHash);
        return userMapper.updateById(user);
    }

    @Override
    public Integer deleteUser(User user) {
        return null;
    }

    @Override
    public void deleteUserList(List<User> userList) {

    }

    @Override
    public Page<User> getList(UserTableReq userTableReq) {
        Integer curr = userTableReq.getPage();
        Integer limit = userTableReq.getLimit();
        Page<User> page = new Page<>(curr, limit);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(User::getUserId);
        userMapper.selectPage(page,lambdaQueryWrapper);
        return page;
    }
}
