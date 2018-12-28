package com.edu.zucc.ygg.movie.controller;
/**
 * 电影短评控制类
 */
import com.edu.zucc.ygg.movie.constant.ApplicationConstant;
import com.edu.zucc.ygg.movie.domain.Movie;
import com.edu.zucc.ygg.movie.domain.ShortCommentary;
import com.edu.zucc.ygg.movie.dto.PageHelperDto;
import com.edu.zucc.ygg.movie.dto.ResultDto;
import com.edu.zucc.ygg.movie.dto.ShortCommentaryDto;
import com.edu.zucc.ygg.movie.dto.UserDto;
import com.edu.zucc.ygg.movie.service.MovieService;
import com.edu.zucc.ygg.movie.service.ShortCommentaryService;
import com.edu.zucc.ygg.movie.service.UserService;
import com.edu.zucc.ygg.movie.util.JWTUtil;
import com.edu.zucc.ygg.movie.util.ResultDtoFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/short")
public class ShortCommentaryController {
    @Autowired
    ShortCommentaryService shortCommentaryService;
    @Autowired
    UserService userService;
    @Autowired
    MovieService movieService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ApiOperation(value = "短评添加接口")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("user")
    public ResultDto addShortCommentary(HttpServletRequest request,@RequestBody ShortCommentaryDto shortCommentaryDto){
        if (shortCommentaryDto.getMovieId()==null)
            return ResultDtoFactory.toNack("短评添加失败：缺少电影id");
        if (shortCommentaryDto.getContent()==null||shortCommentaryDto.getContent().length()>350||shortCommentaryDto.getContent().length()<10)
            return ResultDtoFactory.toNack("短评添加失败：缺少短评或者短评长度不再限制范围中");
        if (shortCommentaryDto.getScore()==null)
            return ResultDtoFactory.toNack("短评添加失败：缺少评分");
        String username =  JWTUtil.getUsername(request.getHeader("Authorization"));
        Integer userId = userService.getUserId(username);
        if (userId==null)
            return ResultDtoFactory.toNack("用户不存在");
        if (shortCommentaryService.searchShortCommentary(userId,shortCommentaryDto.getMovieId())!=null)
            return ResultDtoFactory.toNack("短评添加失败：你已经给这部电影发布过短评");
        ShortCommentary shortCommentary = shortCommentaryDto.toShortCommentary();
        shortCommentary.setUserid(userId);
        shortCommentary.setStar(0);
        shortCommentaryService.addShortCommentary(shortCommentary);
        return ResultDtoFactory.toAck("短评添加成功",shortCommentary);
    }

    @RequestMapping(value = "delete",method = RequestMethod.GET)
    @ApiOperation(value = "短评删除接口")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("user")
    public ResultDto deleteShortCommentary(HttpServletRequest request, @RequestParam Integer shortCommentaryId){
        String username =  JWTUtil.getUsername(request.getHeader("Authorization"));
        Integer userId = userService.getUserId(username);
        ShortCommentary shortCommentary = shortCommentaryService.getShortCommentary(shortCommentaryId);
        if (shortCommentary.getUserid()==userId) {
            if(shortCommentaryService.removeShortCommentary(shortCommentaryId))
                return ResultDtoFactory.toAck("短评删除成功");
            return ResultDtoFactory.toAck("短评删除失败,数据出错：id=",shortCommentaryId);
        }
        return ResultDtoFactory.toNack("这不是你发布的短评");
    }

    @RequestMapping(value = "delete/admin",method = RequestMethod.GET)
    @ApiOperation(value = "短评管理员删除接口",notes = "admin管理员删除短评接口")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("admin")
    public ResultDto deleteShortCommentaryByAdmin(@RequestParam Integer shortCommentaryId){
        if (shortCommentaryId==null)
            return ResultDtoFactory.toNack("需要删除的短评id不能为空");
        if(shortCommentaryService.removeShortCommentary(shortCommentaryId)) {
            return ResultDtoFactory.toAck("短评删除成功");
        }else{
            return ResultDtoFactory.toNack("短评删除失败,数据出错：id=",shortCommentaryId);
        }
    }
    @RequestMapping(value = "search/",method = RequestMethod.POST)
    @ApiOperation(value = "电影短评搜索接口",notes = "电影短评查询")
    public ResultDto searchShortCommentary(@RequestBody PageHelperDto<Integer> pageHelperDto){
        List<ShortCommentaryDto> shortCommentaryDtos = new ArrayList<>();
        int pageSize = pageHelperDto.getPageSize()<1?10:pageHelperDto.getPageSize();
        int pageNum = pageHelperDto.getPageNum()<1?1:pageHelperDto.getPageNum();
        PageHelper.startPage(pageSize,pageNum);
        PageInfo<ShortCommentary> pageInfo = new PageInfo<ShortCommentary>(
                shortCommentaryService.searchShortCommentaryListByMovie(pageHelperDto.getData()));
        List<ShortCommentary> shortCommentaries = pageInfo.getList();
        if (shortCommentaries!=null) {
            shortCommentaries.forEach(shortCommentary -> {
                ShortCommentaryDto shortCommentaryDto = new ShortCommentaryDto(shortCommentary);
                UserDto userDto = userService.getUser(shortCommentary.getUserid());
                shortCommentaryDto.addUserInfo(userDto);
                shortCommentaryDtos.add(shortCommentaryDto);
            });
        }
        return ResultDtoFactory.toAck("电影短评查询成功",shortCommentaryDtos);
    }

    @RequestMapping(value = "search/user",method = RequestMethod.POST)
    @ApiOperation(value = "用户短评搜索接口",notes = "用户查询")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("user")
    public ResultDto searchShortCommentaryByUser(@RequestBody PageHelperDto<Integer> pageHelperDto){
        List<ShortCommentaryDto> shortCommentaryDtos = new ArrayList<>();
        int pageSize = pageHelperDto.getPageSize()<1?10:pageHelperDto.getPageSize();
        int pageNum = pageHelperDto.getPageNum()<1?1:pageHelperDto.getPageNum();
        PageHelper.startPage(pageSize,pageNum);
        PageInfo<ShortCommentary> pageInfo = new PageInfo<ShortCommentary>(
                shortCommentaryService.searchShortCommentaryListByUser(pageHelperDto.getData()));
        List<ShortCommentary> shortCommentaries = pageInfo.getList();
        if (shortCommentaries!=null) {
            shortCommentaries.forEach(shortCommentary -> {
                ShortCommentaryDto shortCommentaryDto = new ShortCommentaryDto(shortCommentary);
                Movie movie = movieService.getMovieInfo(shortCommentaryDto.getMovieId());
                shortCommentaryDto.addMovieInfo(movie);
                shortCommentaryDtos.add(shortCommentaryDto);
            });
        }
        return ResultDtoFactory.toAck("电影短评查询成功",shortCommentaryDtos);
    }

    @RequestMapping(value = "search/admin",method = RequestMethod.POST)
    @ApiOperation(value = "管理员短评搜索接口",notes = "管理员查询")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("admin")
    public ResultDto searchShortCommentaryByAdmin(@RequestBody PageHelperDto<Map<String,String>> pageHelperDto){
        Map<String,String> searchData = pageHelperDto.getData();
        String userName = searchData.get("userName");
        String movieName = searchData.get("movieName");
        if (userName==null)
            return ResultDtoFactory.toNack("请输入账号名");
        if (movieName==null)
            return ResultDtoFactory.toNack("请输入电影名");
        int pageSize = pageHelperDto.getPageSize()<1?10:pageHelperDto.getPageSize();
        int pageNum = pageHelperDto.getPageNum()<1?1:pageHelperDto.getPageNum();
        PageHelper.startPage(pageSize,pageNum);
        PageInfo<ShortCommentaryDto> pageInfo = new PageInfo<ShortCommentaryDto>(
                shortCommentaryService.fuzzySearchShortCommentaryListByUser(userName,movieName));
        List<ShortCommentaryDto> shortCommentaries = pageInfo.getList();
        if (shortCommentaries==null)
            return ResultDtoFactory.toAck("没有匹配到符合条件的短评");
        return ResultDtoFactory.toAck("电影短评查询成功",shortCommentaries);
    }
}
