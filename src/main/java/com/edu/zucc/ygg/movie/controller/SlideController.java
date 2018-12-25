package com.edu.zucc.ygg.movie.controller;

import com.edu.zucc.ygg.movie.constant.ApplicationConstant;
import com.edu.zucc.ygg.movie.domain.Slide;
import com.edu.zucc.ygg.movie.dto.ResultDto;
import com.edu.zucc.ygg.movie.dto.SlideDto;
import com.edu.zucc.ygg.movie.service.SlideService;
import com.edu.zucc.ygg.movie.util.ResultDtoFactory;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/slide")
public class SlideController {
    @Autowired
    SlideService slideService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "幻灯片添加接口")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("admin")
    public ResultDto slideAdd(@RequestBody SlideDto slideDto){
        if (slideDto.getTitle()==null)
            return ResultDtoFactory.toNack("标题不能为空");
        if (slideDto.getContent()==null)
            return ResultDtoFactory.toNack("内容不能为空");
        if (slideDto.getImg()==null)
            return ResultDtoFactory.toNack("图片不能为空");
        Slide slide = slideDto.toSlide();
        slideService.slideAdd(slide);
        return ResultDtoFactory.toAck("幻灯片添加成功",slide);
    }

    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    @ApiOperation(value = "幻灯片更改接口")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("admin")
    public ResultDto slideModify(@RequestBody SlideDto slideDto){
        Slide slide = slideDto.toSlide();
        slide.setUpdateDate(new Date());
        slideService.slideUpdate(slide);
        return ResultDtoFactory.toAck("幻灯片更新成功",slide);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ApiOperation(value = "幻灯片删除接口")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("admin")
    public ResultDto slideDelete(@RequestParam Integer slideId){
        Integer flag = slideService.slideDelete(slideId);
        if (flag>0)
            return ResultDtoFactory.toAck("删除成功",flag);
        return ResultDtoFactory.toAck("删除失败",flag);
    }
}
