package com.edu.zucc.ygg.movie.controller;

import com.edu.zucc.ygg.movie.constant.ApplicationConstant;
import com.edu.zucc.ygg.movie.domain.Movie;
import com.edu.zucc.ygg.movie.dto.MovieDto;
import com.edu.zucc.ygg.movie.dto.ResultDto;
import com.edu.zucc.ygg.movie.exception.ImgException;
import com.edu.zucc.ygg.movie.service.MovieService;
import com.edu.zucc.ygg.movie.service.UpImgService;
import com.edu.zucc.ygg.movie.util.ResultDtoFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
}
