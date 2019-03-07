package com.edu.zucc.ygg.movie.controller;

import com.edu.zucc.ygg.movie.constant.ApplicationConstant;
import com.edu.zucc.ygg.movie.domain.LongCommentary;
import com.edu.zucc.ygg.movie.domain.Movie;
import com.edu.zucc.ygg.movie.dto.LongCommentaryDto;
import com.edu.zucc.ygg.movie.dto.ResultDto;
import com.edu.zucc.ygg.movie.service.LongCommentaryService;
import com.edu.zucc.ygg.movie.service.UserService;
import com.edu.zucc.ygg.movie.service.impl.LongCommentaryServiceImpl;
import com.edu.zucc.ygg.movie.util.JWTUtil;
import com.edu.zucc.ygg.movie.util.ResultDtoFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    RedisTemplate redisTemplate;

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
    public ResultDto getLongCommentaryList(HttpServletRequest request,@RequestParam int page){
        int pageNum = page==0?1:page;
        int pageSize = 10;
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<LongCommentaryDto> pageInfo = new PageInfo<LongCommentaryDto>(longCommentaryService.getLongCommentaryList());
        List<LongCommentaryDto> commentaries = pageInfo.getList();
        String token = request.getHeader("Authorization");
        if (StringUtil.isEmpty(token)){
            for (LongCommentaryDto commentaryDto:commentaries){
                commentaryDto.setLike(false);
                commentaryDto.setCollection(false);
            }
        }
        String tokenUserName = JWTUtil.getUsername(token);
        int userId = userService.getUserId(tokenUserName);
        for (LongCommentaryDto commentaryDto:commentaries){
            commentaryDto.setLike(longCommentaryService.searchLike(userId,String.valueOf(commentaryDto.getId())));
            commentaryDto.setCollection(longCommentaryService.searchCollections(userId,commentaryDto.getId()));
        }
        return ResultDtoFactory.toAck("影评查询成功",commentaries);
    }

    @RequestMapping(value = "like",method = RequestMethod.GET)
    @ApiOperation(value = "普通用户影评点赞接口")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("user")
    public ResultDto likeCommetary(HttpServletRequest request,@RequestParam String id){
        String dir = "like:";
        String token = request.getHeader("Authorization");
        String tokenUserName = JWTUtil.getUsername(token);
        int userId = userService.getUserId(tokenUserName);
        redisTemplate.opsForSet().add(dir+id,userId);
        long count = redisTemplate.opsForSet().size(dir+id);
        return ResultDtoFactory.toAck("点赞成功",count);
    }

    @RequestMapping(value = "like/cancel",method = RequestMethod.GET)
    @ApiOperation(value = "普通用户影评点赞取消接口")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("user")
    public ResultDto cancelLikeCommetary(HttpServletRequest request,@RequestParam String id){
        String dir = "like:";
        String token = request.getHeader("Authorization");
        String tokenUserName = JWTUtil.getUsername(token);
        int userId = userService.getUserId(tokenUserName);
        redisTemplate.opsForSet().remove(dir+id,userId);
        long count = redisTemplate.opsForSet().size(dir+id);
        return ResultDtoFactory.toAck("点赞取消成功",count);
    }

    @RequestMapping(value = "like/search",method = RequestMethod.GET)
    @ApiOperation(value = "普通用户影评点赞接口")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("user")
    public ResultDto likeSearchCommetary(HttpServletRequest request,@RequestParam String id){
        String dir = "like:";
        String token = request.getHeader("Authorization");
        String tokenUserName = JWTUtil.getUsername(token);
        int userId = userService.getUserId(tokenUserName);
        boolean exist = redisTemplate.opsForSet().isMember(dir+id,userId);
        return ResultDtoFactory.toAck("点赞查询成功",exist);
    }

    @RequestMapping(value = "collection/add",method = RequestMethod.GET)
    @ApiOperation(value = "普通用户影评收藏接口")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("user")
    public ResultDto collectionCommetary(HttpServletRequest request,@RequestParam int id){
        String token = request.getHeader("Authorization");
        String tokenUserName = JWTUtil.getUsername(token);
        int userId = userService.getUserId(tokenUserName);
        if (id<0||userId<0)
            return ResultDtoFactory.toNack("参数有误");
        boolean ops = longCommentaryService.collection(id,userId);
        if (ops)
            return ResultDtoFactory.toAck("收藏成功");
        return ResultDtoFactory.toNack("收藏失败");
    }

    @RequestMapping(value = "collection/cancel",method = RequestMethod.GET)
    @ApiOperation(value = "普通用户影评收藏接口")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("user")
    public ResultDto cancelCollectionCommetary(HttpServletRequest request,@RequestParam int id){
        String token = request.getHeader("Authorization");
        String tokenUserName = JWTUtil.getUsername(token);
        int userId = userService.getUserId(tokenUserName);
        if (id<0||userId<0)
            return ResultDtoFactory.toNack("参数有误");
        boolean ops = longCommentaryService.cancelCollection(id,userId);
        if (ops)
            return ResultDtoFactory.toAck("取消收藏成功");
        return ResultDtoFactory.toNack("取消收藏失败");
    }
}
