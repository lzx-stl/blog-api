package com.lzx.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzx.domain.AjaxResult;
import com.lzx.domain.entity.User;
import com.lzx.domain.request.UserReq;
import com.lzx.domain.request.UserTableReq;
import com.lzx.service.impl.UserServiceImpl;
import com.lzx.utils.JwtTokenUtil;
import com.lzx.domain.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("login")
    public AjaxResult userLogin(@RequestBody UserReq userReq) {
        System.out.println(userReq);
        //通过username和加密后的password和数据库比对
        AjaxResult ajaxResult;
        Long userId = userService.findUserId(userReq.getUsername(), userReq.getPassword());
        if (userId != null) {
            String jwtToken = jwtTokenUtil.generateToken(userService.findUserByUserId(userId));
            ajaxResult = AjaxResult.success();
            ajaxResult.put("data", jwtToken);
        } else {
            ajaxResult = AjaxResult.error(0,"用户名或密码错误");
        }
        return ajaxResult;
    }


    @GetMapping("/info")
    public AjaxResult userInfo(@RequestHeader("token") String token) {

        AjaxResult ajaxResult;
        //验证token合法性, token获取到userId,通过userId获取到用户信息
        if (jwtTokenUtil.isTokenExpired(token)) {
            return AjaxResult.error("token已过期,请重新登录!");
        }
        if (!jwtTokenUtil.isTokenValid(token)) {
            return AjaxResult.error("token无效");
        }
        //通过token取出用户id信息，从数据库中读取用户信息返回
        Long userId = jwtTokenUtil.getUserIdFromToken(token);
        ajaxResult = AjaxResult.success("用户信息获取成功");
         ajaxResult.put("data",userService.findUserByUserId(userId));
        return ajaxResult;
    }

    @PostMapping("logout")
    public AjaxResult userLogout(@RequestHeader("toekn") String token) {

        //通过username和加密后的password和数据库比对
        return AjaxResult.success("退出登录成功");
    }

    @PostMapping("add")
    public AjaxResult addUser(@RequestBody User user) {
        AjaxResult ajaxResult;
        if (userService.addUser(user) == 1) {
            return AjaxResult.success("新建用户成功");
        } else {
            return AjaxResult.error("新建用户失败");

        }

    }

    @PostMapping("update")
    public AjaxResult updateUser(@RequestBody User user) {
        AjaxResult ajaxResult;
        if (userService.updateUser(user) == 1) {
            return AjaxResult.success("更新用户成功");
        } else {
            return AjaxResult.error("更新用户失败");
        }

    }


    @PostMapping("list")
    public AjaxResult userPageList(@RequestBody UserTableReq userTableReq){
        AjaxResult ajaxResult = AjaxResult.success();
        Page res =userService.getList(userTableReq);
        List<User> userList = res.getRecords();
        ajaxResult.put("data", new TableDataInfo(res.getTotal(),userList));
        return ajaxResult;
    }

}
