package com.edu.zucc.ygg.movie.controller;

import com.edu.zucc.ygg.movie.constant.ApplicationConstant;
import com.edu.zucc.ygg.movie.dao.TestDomainMapper;
import com.edu.zucc.ygg.movie.domain.TestDomain;
import com.edu.zucc.ygg.movie.dto.ResultDto;
import com.edu.zucc.ygg.movie.util.ResultDtoFactory;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    TestDomainMapper testDomainMapper;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ApiOperation(value = "搭建测试")
    public String HelloSpring (){
        return "hello spring boot";
    }

    @RequestMapping(value = "/mybatisTest", method = RequestMethod.GET)
    @ApiOperation(value = "mybatis测试")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("admin")
    public ResultDto mybatisTest (){
            TestDomain testDomain = testDomainMapper.selectByPrimaryKey(1);
            return ResultDtoFactory.toAck("mybatis测试",testDomain.getName());
    }

}
