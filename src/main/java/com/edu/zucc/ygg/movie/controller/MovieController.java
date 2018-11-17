package com.edu.zucc.ygg.movie.controller;

import com.edu.zucc.ygg.movie.constant.ApplicationConstant;
import com.edu.zucc.ygg.movie.domain.Movie;
import com.edu.zucc.ygg.movie.dto.MovieDto;
import com.edu.zucc.ygg.movie.dto.ResultDto;
import com.edu.zucc.ygg.movie.exception.ImgException;
import com.edu.zucc.ygg.movie.service.MovieService;
import com.edu.zucc.ygg.movie.service.UpImgService;
import com.edu.zucc.ygg.movie.util.ResultDtoFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/movie")
@Api(value = "电影信息")
public class MovieController {

    @Autowired
    UpImgService upImgService;
    @Autowired
    MovieService movieService;

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
}
