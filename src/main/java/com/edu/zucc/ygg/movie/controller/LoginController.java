package com.edu.zucc.ygg.movie.controller;

import com.edu.zucc.ygg.movie.dao.UserMapper;
import com.edu.zucc.ygg.movie.dto.ResultDto;
import com.edu.zucc.ygg.movie.util.JWTUtil;
import com.edu.zucc.ygg.movie.util.ResultDtoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    UserMapper userMapper;

    @PostMapping("/login")
    public ResultDto login(@RequestParam("username") String username, @RequestParam("password") String password) {
        String realPassword = userMapper.getPassword(username);
        if (realPassword == null) {
            return ResultDtoFactory.toNack("用户名错误");
        } else if (!realPassword.equals(password)) {
            return ResultDtoFactory.toNack("密码错误");
        } else {
            return ResultDtoFactory.toAck(JWTUtil.createToken(username));
        }
    }
}
