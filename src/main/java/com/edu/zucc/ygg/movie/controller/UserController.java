package com.edu.zucc.ygg.movie.controller;

import com.edu.zucc.ygg.movie.constant.ApplicationConstant;
import com.edu.zucc.ygg.movie.dto.ResultDto;
import com.edu.zucc.ygg.movie.dto.UserDto;
import com.edu.zucc.ygg.movie.service.UserService;
import com.edu.zucc.ygg.movie.util.ResultDtoFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    @ApiOperation(value = "用户注册")
    public ResultDto registerUser(@RequestBody UserDto userDto){
        return userService.registerUser(userDto);
    }

    @RequestMapping(value = "/user/update",method = RequestMethod.POST)
    @ApiOperation(value = "用户详细信息更新")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles(value={"admin","user"},logical= Logical.OR)
    public ResultDto updateUserInfo(@RequestBody UserDto userDto){
        return userService.updateUserInfo(userDto);
    }

    @RequestMapping(value = "/admin/register",method = RequestMethod.POST)
    @ApiOperation(value = "管理员注册")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("admin")
    @RequiresPermissions("root")
    public ResultDto registerAdmin(@RequestBody UserDto userDto){
        return userService.registerAdmin(userDto);
    }

    @RequestMapping(value = "/admin/delete",method = RequestMethod.GET)
    @RequiresRoles("admin")
    @RequiresPermissions("root")
    public ResultDto deleteAdmin(@RequestParam int adminId){
        return userService.deleteAdmin(adminId);
    }

    @RequestMapping(value = "/user/search",method = RequestMethod.POST)
    @ApiOperation(value = "查询用户")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("admin")
    public ResultDto searchUser(@RequestBody UserDto userDto){
        if (StringUtil.isEmpty(userDto.getUsername())) {
            userDto.setUsername(null);
        }
        PageHelper.startPage(userDto.getPageNum(), userDto.getPageSize());
        PageInfo<UserDto> pageInfo = new PageInfo<UserDto>(userService.getUserList(userDto));
        List<UserDto> users = pageInfo.getList();
        long dateNum = pageInfo.getTotal();
        Map<String,Object> map = new HashMap();
        map.put("list",users);
        map.put("pageNumber",dateNum);
        return ResultDtoFactory.toAck("查询成功",map);
    }

    @RequestMapping(value = "/user/list",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("admin")
    public ResultDto getUserList(@RequestBody UserDto userDto){
        PageHelper.startPage(userDto.getPageNum(), userDto.getPageSize());
        PageInfo<UserDto> pageInfo = new PageInfo<UserDto>(userService.getUserList(userDto));
        List<UserDto> users = pageInfo.getList();
        long dateNum = pageInfo.getTotal();
        Map<String,Object> map = new HashMap();
        map.put("list",users);
        map.put("pageNumber",dateNum);
        return ResultDtoFactory.toAck("查询成功",map);
    }
}
