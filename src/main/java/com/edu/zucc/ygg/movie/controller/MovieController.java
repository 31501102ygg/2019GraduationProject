package com.edu.zucc.ygg.movie.controller;

import com.edu.zucc.ygg.movie.constant.ApplicationConstant;
import com.edu.zucc.ygg.movie.domain.Movie;
import com.edu.zucc.ygg.movie.domain.User;
import com.edu.zucc.ygg.movie.dto.MovieDto;
import com.edu.zucc.ygg.movie.dto.MovieSimpleInfoDto;
import com.edu.zucc.ygg.movie.dto.ResultDto;
import com.edu.zucc.ygg.movie.exception.ImgException;
import com.edu.zucc.ygg.movie.service.MovieService;
import com.edu.zucc.ygg.movie.service.UpImgService;
import com.edu.zucc.ygg.movie.service.UserService;
import com.edu.zucc.ygg.movie.util.JWTUtil;
import com.edu.zucc.ygg.movie.util.ResultDtoFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

@RestController

@RequestMapping("/movie")
@Api(value = "电影信息")
public class MovieController {

    @Autowired
    UpImgService upImgService;
    @Autowired
    MovieService movieService;
    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加电影")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("admin")
    public ResultDto moiveAdd(@RequestBody MovieDto movieDto){
        Movie temp = movieService.addMovie(movieDto);
        if (temp == null){
            return ResultDtoFactory.toNack("添加电影失败");
        }
        return ResultDtoFactory.toAck("添加电影成功");
    }

    @RequestMapping(value = "/img/upload",method = RequestMethod.POST)
    @ApiOperation(value = "上传电影图片")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("admin")
    public ResultDto movieImgUpload(@RequestParam("file")MultipartFile file){
        String url=null;
        if (file!=null&&file.getSize()>0){
            try {
                url = upImgService.updateHead(file);
                return ResultDtoFactory.toAck("电影图片上传成功",url);
            } catch (ImgException e) {
                return ResultDtoFactory.toNack("电影图片上传到图片库失败");
            }
        }
        return ResultDtoFactory.toNack("电影图片上传失败");
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    @ApiOperation(value = "电影搜索")
    public ResultDto movieSearch(@RequestBody MovieDto movieDto){
        int pageNum = movieDto.getPageNum()==null?1:movieDto.getPageNum();
        int pageSize = movieDto.getPageSize()==null?10:movieDto.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Movie> pageInfo = new PageInfo<Movie>(movieService.searchMovie(movieDto));
        List<Movie> movies = pageInfo.getList();
        movies.forEach(movie -> {
            movie.transformDateToString();
        });
        Map<String,Object> map = new HashMap<>();
        map.put("pageNumber",pageInfo.getTotal());
        map.put("list",movies);
        return ResultDtoFactory.toAck("查询成功",map);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation(value = "电影信息更新")
    @RequiresRoles("admin")
    public ResultDto movieUpdate(@RequestBody MovieDto movieDto){
        try{
            return movieService.updateMovieInfo(movieDto);
        }catch(ParseException p){
            return ResultDtoFactory.toNack("日期格式转化出错");
        }catch(Exception e){
            return ResultDtoFactory.toNack("数据库出错");
        }
    }


    @RequestMapping(value = "/newest",method = RequestMethod.POST)
    @ApiOperation(value = "最新电影获取")
    public ResultDto getNewestMovie(@RequestBody MovieDto movieDto){
        int pageNum = movieDto.getPageNum()==null?1:movieDto.getPageNum();
        int pageSize = movieDto.getPageSize()==null?10:movieDto.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Movie> pageInfo = new PageInfo<Movie>(movieService.searchNewestMovie());
        List<Movie> movies = pageInfo.getList();
        movies.forEach(movie -> {
            movie.transformDateToString();
        });
        Map<String,Object> map = new HashMap<>();
        map.put("pageNumber",pageInfo.getTotal());
        map.put("list",movies);
        return ResultDtoFactory.toAck("查询成功",map);
    }

    @RequestMapping(value = "/hot",method = RequestMethod.POST)
    @ApiOperation(value = "最热电影获取")
    public ResultDto getHotMovie(@RequestBody MovieDto movieDto){
        int pageNum = movieDto.getPageNum()==null?1:movieDto.getPageNum();
        int pageSize = movieDto.getPageSize()==null?10:movieDto.getPageSize();
        int start = (pageNum-1)*pageSize;
        int end = pageNum*pageSize-1;
        String dir = "movie:watch";
        Set<Integer> results =  redisTemplate.opsForZSet().reverseRange(dir,start,end);
        List<Movie> movies = new ArrayList<>();
        for (Integer id:results){
            Movie movie = movieService.getMovieInfo(id);
            movie.transformDateToString();
            movies.add(movie);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("pageNumber",movies.size());
        map.put("list",movies);
        return ResultDtoFactory.toAck("查询成功",map);
    }

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ApiOperation(value = "获取某部电影详细信息")
    public ResultDto getMovieInfo(HttpServletRequest request, @RequestParam Integer movieId){
        Movie movie = movieService.getMovieInfo(movieId);
        movie.transformDateToString();
        MovieDto movieDto = new MovieDto(movie);
        String token = request.getHeader("Authorization");
        if (StringUtil.isNotEmpty(token)) {
            String tokenUserName = JWTUtil.getUsername(token);
            User userId = userService.getUser(tokenUserName);
            movieService.checkUserOperation(movieId,userId.getId(),movieDto);
        }
        return ResultDtoFactory.toAck("电影信息查询成功",movieDto);
    }

    @RequestMapping(value = "/info/simple",method = RequestMethod.GET)
    @ApiOperation(value = "获取某部电影的简要信息")
    public ResultDto getMovieSimpleInfo(@RequestParam Integer movieId){
        Movie movie = movieService.getMovieInfo(movieId);
        MovieSimpleInfoDto movieSimpleInfoDto = new MovieSimpleInfoDto(movie);
        String director = movie.getDirector();
        director = director.split("/")[0];
        String actors = movie.getActors();
        actors = actors.split("/")[0];
        String location = movie.getMakeState();
        location = location.split("/")[0];
        String language = movie.getLanguage();
        language = language.split("/")[0];
        String simpleInfo = "导演 "+director+"演员 "+actors+"/"+location+"/"+language;
        movieSimpleInfoDto.setSimpleInfo(simpleInfo);
        return ResultDtoFactory.toAck("电影简要信息查询成功",movieSimpleInfoDto);
    }

}
