package com.edu.zucc.ygg.movie.controller;

import com.edu.zucc.ygg.movie.domain.Movie;
import com.edu.zucc.ygg.movie.dto.ResultDto;
import com.edu.zucc.ygg.movie.util.ResultDtoFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/movie/read")
@Api(value = "电影信息阅读记录")
public class MovieReadController {
    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("inc")
    @ApiOperation(value = "阅读数加1")
    public ResultDto increase(@RequestParam int id){
        String dir = "movie:watch";
        redisTemplate.opsForZSet().incrementScore(dir,id,1);
        return ResultDtoFactory.toAck("阅读数添加成功");
    }

}
