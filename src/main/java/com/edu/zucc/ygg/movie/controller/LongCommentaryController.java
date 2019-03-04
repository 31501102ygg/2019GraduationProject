package com.edu.zucc.ygg.movie.controller;

import com.edu.zucc.ygg.movie.constant.ApplicationConstant;
import com.edu.zucc.ygg.movie.domain.LongCommentary;
import com.edu.zucc.ygg.movie.domain.Movie;
import com.edu.zucc.ygg.movie.dto.LongCommentaryDto;
import com.edu.zucc.ygg.movie.dto.ResultDto;
import com.edu.zucc.ygg.movie.service.LongCommentaryService;
import com.edu.zucc.ygg.movie.service.UserService;
import com.edu.zucc.ygg.movie.util.JWTUtil;
import com.edu.zucc.ygg.movie.util.ResultDtoFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/long")
public class LongCommentaryController {
    @Autowired
    LongCommentaryService longCommentaryService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "add/normal",method = RequestMethod.POST)
    @ApiOperation(value = "普通用户影评上传接口")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("user")
    public ResultDto addLongCommentary(HttpServletRequest request, @RequestBody LongCommentary longCommentary){
        if (StringUtil.isEmpty(longCommentary.getTitle()))
            return ResultDtoFactory.toNack("影评不能没有标题");
        if (StringUtil.isEmpty(longCommentary.getContent()))
            return ResultDtoFactory.toNack("影评内容不能为空");
        String token = request.getHeader("Authorization");
        String tokenUserName = JWTUtil.getUsername(token);
        longCommentary.setUserId(userService.getUserId(tokenUserName));
        longCommentary = longCommentaryService.add(longCommentary);
        return ResultDtoFactory.toAck("影评添加成功",longCommentary);
    }

    @RequestMapping(value = "add/pro",method = RequestMethod.POST)
    @ApiOperation(value = "专业用户影评上传接口")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("user")
    public ResultDto addLongCommentaryByPro(HttpServletRequest request, @RequestBody LongCommentary longCommentary){
        if (StringUtil.isEmpty(longCommentary.getTitle()))
            return ResultDtoFactory.toNack("影评不能没有标题");
        if (StringUtil.isEmpty(longCommentary.getContent()))
            return ResultDtoFactory.toNack("影评内容不能为空");
        String token = request.getHeader("Authorization");
        longCommentary.setType(1);
        String tokenUserName = JWTUtil.getUsername(token);
        longCommentary.setUserId(userService.getUserId(tokenUserName));
        longCommentary = longCommentaryService.add(longCommentary);
        return ResultDtoFactory.toAck("影评添加成功",longCommentary);
    }

    @RequestMapping(value = "get",method = RequestMethod.GET)
    @ApiOperation(value = "获取影评列表")
    public ResultDto getLongCommentaryList(@RequestParam int page){
        int pageNum = page==0?1:page;
        int pageSize = 10;
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<LongCommentaryDto> pageInfo = new PageInfo<LongCommentaryDto>(longCommentaryService.getLongCommentaryList());
        List<LongCommentaryDto> commentaries = pageInfo.getList();
        return ResultDtoFactory.toAck("影评查询成功",commentaries);
    }

    @RequestMapping(value = "test",method = RequestMethod.POST)
    @ApiOperation(value = "普通用户影评上传接口")
    public ResultDto filterTest(@RequestParam String content){
        return ResultDtoFactory.toAck("影评添加成功");
    }
}
