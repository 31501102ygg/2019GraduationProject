package com.edu.zucc.ygg.movie.controller;

import com.edu.zucc.ygg.movie.constant.ApplicationConstant;
import com.edu.zucc.ygg.movie.domain.Slide;
import com.edu.zucc.ygg.movie.dto.ResultDto;
import com.edu.zucc.ygg.movie.dto.SlideDto;
import com.edu.zucc.ygg.movie.service.SlideService;
import com.edu.zucc.ygg.movie.util.ResultDtoFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        slide.setState(0);
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

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ApiOperation(value = "获取三张幻灯片",notes = "只能返回三张幻灯片")
    public ResultDto slideList(){
        List<SlideDto> slideDtos = new ArrayList<>();
        List<Slide> slideList = slideService.getSlideList();
        if (slideList.size()==3){
            slideList.forEach(slide -> {
                SlideDto slideDto = new SlideDto(slide);
                slideDto.transformDateToString();
                slideDtos.add(slideDto);
            });
            return ResultDtoFactory.toAck("获取幻灯片成功",slideDtos);
        }
        return ResultDtoFactory.toNack("幻灯片数量不正确");
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    @ApiOperation(value = "获取未显示的幻灯片")
    public ResultDto searchSlides(@RequestBody SlideDto slideDto){
        List<SlideDto> slideDtos = new ArrayList<>();
        if (slideDto.getPageNum()==null||slideDto.getPageNum()<=0)slideDto.setPageNum(1);
        if (slideDto.getPageSize()==null||slideDto.getPageSize()<=0)slideDto.setPageSize(7);

        PageHelper.startPage(slideDto.getPageNum(), slideDto.getPageSize());
        PageInfo<Slide> pageInfo = new PageInfo<Slide>(slideService.searchSlideList(slideDto.getTitle()));
        List<Slide> slideList = pageInfo.getList();
        Long total = pageInfo.getTotal();
        if (slideList!=null&&slideList.size()>0){
            slideList.forEach(slide -> {
                SlideDto slideDto1 = new SlideDto(slide);
                slideDto1.transformDateToString();
                slideDtos.add(slideDto1);
            });
        }
        Map map = new HashMap();
        map.put("total",total);
        map.put("list",slideDtos);
        return ResultDtoFactory.toAck("幻灯片查询成功",map);
    }

    @RequestMapping(value = "/exchange",method = RequestMethod.GET)
    @ApiOperation(value = "幻灯片显示替换")
    @RequiresRoles("admin")
    public ResultDto exchangeShowSlide(@RequestParam Integer oldSlideId,@RequestParam Integer newSlideId){
        if (oldSlideId==null||newSlideId==null||oldSlideId<1||newSlideId<1)
            return ResultDtoFactory.toNack("输入的slideId为空或者小于1");
        Slide oldSlide = new Slide();
        oldSlide.setId(oldSlideId);
        oldSlide.setState(0);
        Slide newSlide = new Slide();
        newSlide.setId(newSlideId);
        newSlide.setState(1);
        slideService.exchangeShowSlide(oldSlide,newSlide);
        return ResultDtoFactory.toAck("幻灯片交换成功");
    }
}
