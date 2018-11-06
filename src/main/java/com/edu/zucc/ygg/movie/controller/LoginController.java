package com.edu.zucc.ygg.movie.controller;

import com.edu.zucc.ygg.movie.dao.UserMapper;
import com.edu.zucc.ygg.movie.dto.ResultDto;
import com.edu.zucc.ygg.movie.util.EncryptUtil;
import com.edu.zucc.ygg.movie.util.JWTUtil;
import com.edu.zucc.ygg.movie.util.ResultDtoFactory;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    UserMapper userMapper;

    @PostMapping("/login")
    @ApiOperation(value = "Login登陆接口")
    public ResultDto login(@RequestParam("username") String username,@RequestParam("password") String password) {
        String realPassword = userMapper.getPassword(username);
        String role = userMapper.getRole(username);
        password = EncryptUtil.encryptMd5(password,username);
        int status = userMapper.checkUserBanStatus(username);
        Map<String,String> map = new HashMap<>();
        if (realPassword == null) {
            return ResultDtoFactory.toNack("没有这个用户名");
        } else if (!realPassword.equals(password)) {
            return ResultDtoFactory.toNack("密码错误");
        } else if (status == 1){
            return ResultDtoFactory.toNack("此账号被封号");
        } else {
            map.put("token",JWTUtil.createToken(username));
            map.put("role",role);
            return ResultDtoFactory.toAck("登录成功",map);
        }
    }
}
